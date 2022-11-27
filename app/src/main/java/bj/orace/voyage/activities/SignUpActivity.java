package bj.orace.voyage.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import bj.orace.voyage.UCropperActivity;
import bj.orace.voyage.UserModel;
import bj.orace.voyage.constant.AllConstant;
import bj.orace.voyage.databinding.ActivitySignUpBinding;
import bj.orace.voyage.permissions.AppPermission;
import bj.orace.voyage.utility.LoadingDialog;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private Uri imageUri;
    private AppPermission appPermission;
    private LoadingDialog loadingDialog;
    private String email,username,password;
    private StorageReference storageReference;
    ActivityResultLauncher<String> cropImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cropImage = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            Intent intent = new Intent(SignUpActivity.this.getApplicationContext(), UCropperActivity.class);
            intent.putExtra("SendImageData",result.toString());
            startActivityForResult(intent,100);
        });

        appPermission = new AppPermission();
        loadingDialog = new LoadingDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference();

        binding.btnback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.txtLogin.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.btnSignUp.setOnClickListener(view -> {
            if (areFieldReady()){
                if (imageUri != null){
                    signUp();
                }else {
                    Toast.makeText(this, "L'image est requise", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(SignUpActivity.this,Home.class));
                    signUp();
                }
            }
        });

        binding.imgPick.setOnClickListener(view -> {
            if (appPermission.isStorageOk(this)){
                pickImage();
            }else {
                appPermission.requestStoragePermission(this);
            }
        });
    }

    private void pickImage() {
      Dexter.withContext(SignUpActivity.this)
              .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
              .withListener(new PermissionListener() {
                  @Override
                  public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                    cropImage.launch("image/*");
                  }

                  @Override
                  public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                      Toast.makeText(SignUpActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                  }


                  @Override
                  public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                    permissionToken.continuePermissionRequest();
                  }
              }).check();
    }



    private boolean areFieldReady(){
        username = binding.edtUsername.getText().toString().trim();
        email = binding.edtEmail.getText().toString().trim();
        password = binding.edtPassword.getText().toString().trim();

        boolean flag = false;
        View requestView = null;

        if (username.isEmpty()){
            binding.edtUsername.setError("Ce champs est requis");
            flag = true;
            requestView = binding.edtUsername;
        }else if (email.isEmpty()){
            binding.edtEmail.setError("Ce champs est requis");
            flag = true;
            requestView = binding.edtEmail;
        }else if(password.isEmpty()){
            binding.edtPassword.setError("Ce champs est requis");
            flag = true;
            requestView =  binding.edtPassword;
        }else if (password.length() < 8){
            binding.edtPassword.setError("Minimum 8 caracteres");
            flag = true;
            requestView = binding.edtPassword;
        }

        if (flag){
           requestView.requestFocus();
            return false;
        }else {
            return true;
        }
    }

    private void signUp(){
        loadingDialog.startLoading();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> signUp) {

                if (signUp.isSuccessful()){
                    storageReference.child(firebaseAuth.getUid()+ AllConstant.IMAGE_PATH).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> image = taskSnapshot.getStorage().getDownloadUrl();
                            image.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> imageTask) {

                                    if (imageTask.isSuccessful()){
                                        String url = imageTask.getResult().toString();
                                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(username)
                                                .setPhotoUri(Uri.parse(url))
                                                .build();

                                        firebaseAuth.getCurrentUser().updateProfile(profileChangeRequest).
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            UserModel userModel = new UserModel(email,
                                                                    username,url,true);
                                                            databaseReference.child(firebaseAuth.getUid())
                                                                    .setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {
                                                                            firebaseAuth.getCurrentUser().sendEmailVerification()
                                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                        @Override
                                                                                        public void onSuccess(Void unused) {
                                                                                            loadingDialog.stopLoading();
                                                                                            Toast.makeText(SignUpActivity.this, "Verifier email", Toast.LENGTH_SHORT).show();
                                                                                            onBackPressed();
                                                                                        }
                                                                                    });
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }else {
                                        // TODO: 05/11/2022 Ici on enregistre la photo de l'utilisateur 
                                        loadingDialog.stopLoading();
                                        Log.d("TAG","onComplete: Image Path"+imageTask.getException());
                                        Toast.makeText(SignUpActivity.this, "Image Path"+imageTask.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }else {
                    // TODO: 05/11/2022 Ici on cree un utilisateur  
                    loadingDialog.stopLoading();
                    Log.d("TAG","onComplete: Creer utilisateur"+signUp.getException());
                    Toast.makeText(SignUpActivity.this, ""+signUp.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100  && resultCode ==101){
            String result = data.getStringExtra("CROP");
             imageUri =data.getData();
            if(result!=null){
                imageUri = Uri.parse(result);
            }
            binding.imgPick.setImageURI(imageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AllConstant.STORAGE_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImage();
            }else {
                Toast.makeText(this, "Storage Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}