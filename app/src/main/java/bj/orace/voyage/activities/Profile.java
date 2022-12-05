package bj.orace.voyage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import bj.orace.voyage.UserModel;
import bj.orace.voyage.databinding.ActivityProfileBinding;
import bj.orace.voyage.utility.LoadingDialog;

public class Profile extends AppCompatActivity {
    private ActivityProfileBinding binding;
    protected final int REQUEST_CODE_PROFILE = 1236;
    private ImageView back;
    private FirebaseAuth firebaseAuth;
    LoadingDialog loadingDialog;
    private ImageView imageView;
    private TextInputEditText txtName;
    private TextInputEditText txtEmail;
    private TextView textView,txtChanel;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        back = binding.imageBack;

        btn_update = binding.btnUpdate;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        loadingDialog = new LoadingDialog(this);
        txtName = binding.edittextName;
        txtEmail = binding.edittextEmail;
        textView = binding.textviewFullname;

        imageView = binding.imageProfile;

        getUserData();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EditionProfile.class));
            }
        });
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
                    txtEmail.setText(userModel.getEmail());
                    txtName.setText(userModel.getUsername());
                    textView.setText(userModel.getUsername());
                    imageView.setImageURI(firebaseAuth.getCurrentUser().getPhotoUrl());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}