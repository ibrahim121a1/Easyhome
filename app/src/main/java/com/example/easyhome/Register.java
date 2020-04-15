package com.example.easyhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easyhome.Fragments.Home_Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private EditText address;
    private EditText contactno;
    private Button Confirm;
    private EditText name;
    FirebaseAuth objectfirebaseauth;
    FirebaseFirestore objectfirebasefirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        convertxmltojava();
        objectfirebaseauth=FirebaseAuth.getInstance();
        objectfirebasefirestore=FirebaseFirestore.getInstance();


    }

    private void convertxmltojava()
    {
        name=findViewById(R.id.name);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        address=findViewById(R.id.address);
        contactno=findViewById(R.id.contactno);
        Confirm=findViewById(R.id.btn_confirm);
    }

    public void signup(View v)
    {
        try {
            if (!Email.getText().toString().isEmpty()&&!Password.getText().toString().isEmpty()&&!address.getText().toString().isEmpty()&&
            !contactno.getText().toString().isEmpty())
            {
                if (objectfirebaseauth != null) {
                    Confirm.setEnabled(false);
                    objectfirebaseauth.createUserWithEmailAndPassword(Email.getText().toString(),Password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    if (authResult.getUser()!=null)
                                    {
                                        Toast.makeText(Register.this,
                                                "Account Created Sucessfully", Toast.LENGTH_SHORT).show();
                                        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

                                        Home_Fragment home_fragment=new Home_Fragment();

                                        fragmentTransaction.replace(R.id.containerR,home_fragment);

                                        objectfirebasefirestore.collection("USER DETAIL").document
                                                (name.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                                if (task.getResult().exists())
                                                {
                                                    Toast.makeText(Register.this, "UserName Already Exsist", Toast.LENGTH_SHORT).show();

                                                }

                                                else {

                                                    Map<String,Object> objectMap=new HashMap<>();

                                                    objectMap.put("Name",name.getText().toString());
                                                    objectMap.put("Address",address.getText().toString());
                                                    objectMap.put("ContactNo",contactno.getText().toString());

                                                    objectfirebasefirestore.collection("USER DETAIL").document
                                                            (name.getText().toString()).set(objectMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(Register.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(Register.this, "Not Uploaded", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }
                                        });







                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Not Sign in", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }

        catch (Exception e)
        {
            Toast.makeText(this, "Signin"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
