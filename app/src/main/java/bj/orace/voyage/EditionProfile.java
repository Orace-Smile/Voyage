package bj.orace.voyage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;
import java.util.Map;

import bj.orace.voyage.constant.AllConstant;
import bj.orace.voyage.databinding.ActivityEditionProfileBinding;
import bj.orace.voyage.permissions.AppPermission;
import bj.orace.voyage.utility.LoadingDialog;

public class EditionProfile extends AppCompatActivity {
    private ActivityEditionProfileBinding binding;
    private AppPermission appPermission;
    private FirebaseAuth firebaseAuth;
    ActivityResultLauncher<String> cropImage;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditionProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        appPermission = new AppPermission();

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getUserData();

        binding.circleImageView.setOnClickListener(camera ->{
            if (appPermission.isStorageOk(this)){
                pickImage();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},AllConstant.STORAGE_REQUEST_CODE);
                }
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.nom.getText().toString().trim();
                if (!userName.isEmpty()){
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(userName).build();
                    firebaseAuth.getCurrentUser().updateProfile(profileChangeRequest)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                        Map<String, Object> map =  new HashMap<>();
                                        map.put("username",userName);
                                        databaseReference.child(firebaseAuth.getUid()).updateChildren(map);

                                        binding.nom.setText(userName);
                                        Toast.makeText(EditionProfile.this, "Username is updated", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Log.d("TAG","onComplete"+task.getException());
                                        Toast.makeText(EditionProfile.this, "Echec: "+task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(EditionProfile.this, "Username est requis", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void pickImage() {
        Dexter.withContext(EditionProfile.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        cropImage.launch("image/*");
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(EditionProfile.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void getUserData(){
        DatabaseReference databaseReference = FirebaseDatabase
                .getInstance().getReference("Users")
                .child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    UserModel userModel = snapshot.getValue(UserModel.class);
                    binding.email.setText(userModel.getEmail());
                    binding.nom.setText(userModel.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == AllConstant.STORAGE_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImage();
            }else{
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100  && resultCode ==101){
            String result = data.getStringExtra("CROP");
            Uri uri =data.getData();
            if(result!=null){
                uri = Uri.parse(result);
                uploadImage(uri);
            }
            binding.circleImageView.setImageURI(uri);
        }
    }

    private void uploadImage(Uri imageUri) {
         loadingDialog.startLoading();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(firebaseAuth.getUid()+AllConstant.IMAGE_PATH).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> image = taskSnapshot.getStorage().getDownloadUrl();
                image.addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            String url = task.getResult().toString();

                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(Uri.parse(url)).build();

                            firebaseAuth.getCurrentUser().updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> profile) {
                                    if (profile.isSuccessful()){
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                        Map<String, Object> map =  new HashMap<>();
                                        map.put("image",url);
                                        databaseReference.child(firebaseAuth.getUid()).updateChildren(map);
                                        binding.circleImageView.setImageURI(Uri.parse(url));
                                        loadingDialog.stopLoading();
                                    }else {
                                        loadingDialog.stopLoading();
                                        Log.d("TAG","Profile: "+profile.getException());
                                        Toast.makeText(EditionProfile.this, "Profile: "+profile.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else {
                            loadingDialog.stopLoading();
                            Toast.makeText(EditionProfile.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("TAG","onComplete: image uri "+task.getException());
                        }
                    }
                });
            }
        });
    }
}