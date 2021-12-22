package com.example.woodygroupapplication;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AccountSettingFragment extends Fragment {
    public static final int RESULT_OK = -1;
    public ContentResolver getContentResolver() {
        throw new RuntimeException("Stub!");
    }



    ImageView imvEditName, imvEditPass , imvAvatar;
    TextView txtEmail;
    Button btnLogOut,btnSave;
    EditText edtName,edtPass;

    LinearLayout sheetOpenCam,sheetOpenGallery;

    ActivityResultLauncher<Intent> activityResultLauncher;

    boolean isCamera;

    private View mview;

    private Uri muri ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_account_setting, container, false);


        linkView();
        setUserInformation();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    if(isCamera) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        imvAvatar.setImageBitmap(bitmap);
                    } else {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                imvAvatar.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        imvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClickRequestPermission();
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.bottom_sheet_myprofile);
                sheetOpenCam = dialog.findViewById(R.id.sheetOpenCamera);
                sheetOpenCam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Open camera
                        isCamera = true;
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        activityResultLauncher.launch(intent);
                        dialog.dismiss();
                    }
                });
                sheetOpenGallery = dialog.findViewById(R.id.sheetOpenGallery);
                sheetOpenGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Open gallery
                        isCamera = false;
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        activityResultLauncher.launch(intent);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDateProfile();
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });


        return mview;


    }

    private void linkView() {
        txtEmail = mview.findViewById(R.id.txtEmailSetting);
        imvEditPass = mview.findViewById(R.id.imvEditPass);
        imvEditName = mview.findViewById(R.id.imvEditName);
        btnLogOut = mview.findViewById(R.id.btnLogOut);
        btnSave = mview.findViewById(R.id.btnSave);
        edtName = mview.findViewById(R.id.edtNameSetting);
        edtPass = mview.findViewById(R.id.edtPassSetting);
        imvAvatar = mview.findViewById(R.id.imvAvatar);
    }



    private void upDateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            return;
        }

        String name = edtName.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
//                .setPhotoUri(Uri.parse(muri))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Update profile success", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }

        edtName.setText(user.getDisplayName());
        txtEmail.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.account).into(imvAvatar);
    }




}
