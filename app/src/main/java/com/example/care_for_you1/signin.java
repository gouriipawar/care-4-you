package com.example.care_for_you1;

import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
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

public class signin extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    String email,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        b1 = findViewById(R.id.b1);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= e1.getText().toString();
                pass = e2.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty())
                {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                if(firebaseAuth.getCurrentUser().isEmailVerified())
                                {
                                    Toast.makeText(signin.this,"verified email",Toast.LENGTH_LONG).show();
                                    Toast.makeText(signin.this,"logged in with successfully",Toast.LENGTH_LONG).show();

                                }
                                else
                                {
                                    Toast.makeText(signin.this,"Email Verification Required",Toast.LENGTH_LONG).show();
                                    //firebaseAuth.getCurrentUser().delete();
                                }

                            }
                            else
                            {
                                Toast.makeText(signin.this,"login failed ",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}