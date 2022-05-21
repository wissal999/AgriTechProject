package com.example.agritechproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class LoginActivity extends AppCompatActivity {
    EditText edtEmailLogin,edtMdpLogin ;
    Button btnLogin;
    TextView txtRedirectInscri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmailLogin=findViewById(R.id.edt_email_login);
        edtMdpLogin=findViewById(R.id.edt_mdp_login);
        btnLogin =findViewById(R.id.btn_login);
        txtRedirectInscri =findViewById(R.id.txt_inscription);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });

        txtRedirectInscri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
                finish();

            }
        });

        SharedPreferences prefs= getSharedPreferences("login",Context.MODE_PRIVATE);

        String emailsp=prefs.getString("email","");
        String passwordsp=prefs.getString("password","");
        if(emailsp.equals("")&& passwordsp.equals(""))
        {
            Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_LONG).show();
        }
        else{
            Intent toHomeActivity =new Intent(this,MainActivity.class);
            startActivity(toHomeActivity);
            finish();

        }
    }
    public  void login(){
        String email=edtEmailLogin.getText().toString().trim();
        Log.i(" email",email);
        String password=edtMdpLogin.getText().toString().trim();
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        Call<User> LoginUser=ApiClient.getApiClient().create(UserService.class).loginUser(user);
        LoginUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    if (response.body() != null) {

                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), response.body().getUsername(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                            SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putString("idUser", response.body().getId().toString());
                            editor.putString("username",response.body().getUsername());
                            editor.putString("userNumber",response.body().getPhoneNumber().toString());
                            editor.commit();
                            Toast.makeText(getApplicationContext(), response.body().getId().toString(), Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(),"failed"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}