package com.example.scandal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Profile extends AppCompatActivity {
    private String name;
    private String phoneNumber;
    private String homePage;
    private Uri imageUri;
    ImageView imageView;
    FloatingActionButton editButton;
    FloatingActionButton deleteButton;
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private TextView textView;
    private FirebaseFirestore db;


    // Getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        //storageReference.child("profile").child(userId).setValue(name);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.profilePicture);
        editButton = findViewById(R.id.editImageButton);
        deleteButton = findViewById(R.id.deleteImageButton);
        editTextName = findViewById(R.id.editTextName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        Button buttonSave = findViewById(R.id.buttonSave);
        db = FirebaseFirestore.getInstance();

        // Image Edit Button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Profile.this)
                        .crop() // Crop image(Optional)
                        .compress(1024) // Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080) //Final image resolution will be less than 1080 x 10800
                        .start();
            }

        });
        // Image Delete Button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.baseline_person_24); // Show the default profile picture after deletion
            }
        });
        // Save information button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfileData();
            }
        });
    }

    // Method to update the image view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.REQUEST_CODE) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
                uploadPicture();
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    // Upload picture to firebase
    private void uploadPicture() {
        final String randomKey = UUID.randomUUID().toString();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = storageReference.child("profile/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Tell the user image is uploaded successfully
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Tell the user image is uploaded unsuccessfully
                        Toast.makeText(getApplicationContext(), "Failed To Upload", Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Method to save profile data to Firestore
    private void saveProfileData() {
        String name = editTextName.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        // Check if name and phone number are not empty
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phoneNumber)) {
            // Create a new profile document with a unique ID
            Map<String, Object> profileData = new HashMap<>();
            profileData.put("name", name);
            profileData.put("phoneNumber", phoneNumber);

            db.collection("profiles")
                    .add(profileData)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Profile saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to save profile", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Please enter name and phone number", Toast.LENGTH_SHORT).show();
        }
    }
}
