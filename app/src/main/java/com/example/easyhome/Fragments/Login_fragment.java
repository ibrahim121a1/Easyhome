package com.example.easyhome.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easyhome.R;
import com.example.easyhome.Register;
import com.example.easyhome.Splash_Screen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login_fragment extends Fragment {

    private EditText username;
    private EditText password;
    private Button signup;
    private FirebaseAuth objectfirebaseauth;



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_login_fragment,container,false);

        objectfirebaseauth=FirebaseAuth.getInstance();
        username=view.findViewById(R.id.editText);
        password=view.findViewById(R.id.editText2);
        signup=view.findViewById(R.id.btn2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!username.getText().toString().isEmpty()&&!password.getText().toString().isEmpty())
                {
                    if (objectfirebaseauth!=null)
                    {
                        if (objectfirebaseauth.getCurrentUser()!=null)
                        {
                            objectfirebaseauth.signOut();
                            Toast.makeText(getContext(), "Signout!", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            objectfirebaseauth.signInWithEmailAndPassword(username.getText().toString(),
                                    password.getText().toString()).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "Failed to sign in", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    Toast.makeText(getContext(), "Signin Sucessfully", Toast.LENGTH_SHORT).show();
                                    Home_Fragment home_fragment=new Home_Fragment();
                                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.containerRL,home_fragment);

                                }
                            });
                        }

                    }
                }

                else if (username.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "You have not entered your Email", Toast.LENGTH_SHORT).show();
                }

                else if (password.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "You have entered your password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;




    }




}
