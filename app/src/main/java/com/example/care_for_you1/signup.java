package com.example.care_for_you1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;


        import android.os.Bundle;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

public class signup extends Activity {
    EditText e1, e2;
    String email;
    Button b1;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        email = e1.getText().toString();
        password = e2.getText().toString();

        b1 = findViewById(R.id.b1);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = e1.getText().toString();
                password = e2.getText().toString();
                Toast.makeText(signup.this,"entered",Toast.LENGTH_SHORT).show();

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(signup.this, "Email Verification Link Sent", Toast.LENGTH_SHORT).show();
                                        if(firebaseAuth.getCurrentUser().isEmailVerified())
                                        {
                                            Toast.makeText(signup.this,"verified email",Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {

                                            firebaseAuth.getCurrentUser().delete();
                                        }
                                        Intent intent = new Intent(signup.this, signin.class);
                                        startActivity(intent);

                                    }
                                    else {
                                        Toast.makeText(signup.this, "Failed Email Verification", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                           // Toast.makeText(signup.this, "Registered", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(signup.this, "failed to Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }



        });

    }

}
