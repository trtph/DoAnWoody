package com.example.woodygroupapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentChangePassword extends Fragment {

    private View mView;

    private EditText edtChangePassword;
    private Button btnChangePassword;

    private ProgressDialog progressDialog ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_change_pass_word, container, false);

        linkView();
        onClickChangePassword();

        return mView;
    }

    private void linkView() {
        progressDialog = new ProgressDialog(getActivity());
        edtChangePassword = mView.findViewById(R.id.edtPassCreate);
        btnChangePassword = mView.findViewById(R.id.btnChangePassword);
    }

    private void onClickChangePassword() {
        String newPassword = edtChangePassword.getText().toString().trim();
        progressDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User Password updated", Toast.LENGTH_SHORT).show();
                            progressDialog.show();
                        }
                    }
                });

    }


}