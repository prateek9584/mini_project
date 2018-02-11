package com.example.prateek.Miniproject;
        import android.annotation.SuppressLint;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.design.widget.TextInputEditText;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.FirebaseException;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
        import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.auth.PhoneAuthCredential;
        import com.google.firebase.auth.PhoneAuthProvider;

        import java.util.concurrent.TimeUnit;

public class Otp extends AppCompatActivity {

    EditText phoneNumber, inputOtp;
    Button sendOtp, resendOtp, verifyOtp;
    FirebaseAuth PhAuth;
    String PhoneVeificationId;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    ProgressDialog verificationProcess;
    Toolbar mToolbar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        PhAuth = FirebaseAuth.getInstance();
        phoneNumber = findViewById(R.id.number);
        inputOtp = findViewById(R.id.otp);
        sendOtp = findViewById(R.id.send);
        resendOtp = findViewById(R.id.resend);
        verifyOtp = findViewById(R.id.verify);
        verifyOtp.setEnabled(false);
        sendOtp.setEnabled(false);
        resendOtp.setEnabled(false);
        verificationProcess = new ProgressDialog(this);
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!phoneNumber.getText().toString().trim().equals(""))
                {
                    sendOtp.setEnabled(true);
                }
                else
                    sendOtp.setEnabled(false);
            }
        });
        inputOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!inputOtp.getText().toString().trim().equals(""))
                {
                    verifyOtp.setEnabled(true);
                }
                else
                    verifyOtp.setEnabled(false);
            }
        });

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpVerificationContact();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber.getText().toString(), 2, TimeUnit.MINUTES, Otp.this, verificationCallbacks);
            }
        });
        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationProcess.setMessage("Please Wait");
                verificationProcess.setTitle("Verify OTP");
                verificationProcess.show();
                verificationProcess.setCanceledOnTouchOutside(false);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(PhoneVeificationId,inputOtp.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNumber.getText().toString();
                setUpVerificationContact();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(number,2,TimeUnit.MINUTES,Otp.this,verificationCallbacks,resendingToken);

            }
        });
    }

    public void setUpVerificationContact() {
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                inputOtp.setText("");
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if(e instanceof FirebaseAuthInvalidCredentialsException)
                {
                    Toast.makeText(getApplicationContext(),"Invalid credential", Toast.LENGTH_LONG).show();
                }
                else if(e instanceof FirebaseAuthRecentLoginRequiredException)
                {
                    Toast.makeText(getApplicationContext(),"TRY AFTER 1 HOUR", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCodeSent(String verificationId,PhoneAuthProvider.ForceResendingToken token){
                PhoneVeificationId = verificationId;
                resendingToken = token;
                resendOtp.setEnabled(true);
            }
        };
    }



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        PhAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent=new Intent(Otp.this,Confirm.class);
                            startActivity(intent);
                            FirebaseUser user = task.getResult().getUser();
                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}

