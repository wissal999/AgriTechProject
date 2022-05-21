package com.example.agritechproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agritechproject.Models.User;
import com.example.agritechproject.Services.UserService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUsernameSignUp,edtEmailSignUp,edtMdpSignUp,edtPhoneSignUp;
    Button btnSignUp;
    TextView  txtToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtUsernameSignUp=findViewById(R.id.edt_username_inscri);
        edtEmailSignUp=findViewById(R.id.edt_email_inscri);
        edtPhoneSignUp=findViewById(R.id.edt_phone);
        edtMdpSignUp=findViewById(R.id.edt_mdp);
        btnSignUp =findViewById(R.id.btn_inscri);
        txtToLogin =findViewById(R.id.txt_inscription);
        btnSignUp .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
        txtToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    public  void SignUp(){
        String email=edtEmailSignUp.getText().toString().trim();
        Log.i(" email",email);
        String password=edtMdpSignUp.getText().toString().trim();
        String username=edtUsernameSignUp.getText().toString().trim();
        String phone =edtPhoneSignUp.getText().toString().trim();
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setPhoneNumber(phone);
        Call<User> addUser=ApiClient.getApiClient().create(UserService.class).addUser(user);
        addUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()){

                    Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_LONG).show();

                    Intent toLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(toLoginActivity);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}