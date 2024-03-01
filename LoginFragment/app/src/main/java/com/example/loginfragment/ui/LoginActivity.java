package com.example.loginfragment.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginfragment.MainActivity;
import com.example.loginfragment.R;
import com.example.loginfragment.model.User;
import com.google.gson.Gson;
import java.util.stream.Stream;

public class LoginActivity extends AppCompatActivity {
    Button btLogin , btRegister;
    EditText edUserNameC , edPasswordC;

    SharedPreferences.Editor editor;
    private final Gson gson = new Gson();
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");



        //
        anhxa();
        //
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
       //
        taosukien();


    }

    private void anhxa(){
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        edUserNameC = findViewById(R.id.edUserName);
        edPasswordC = findViewById(R.id.edPassword);
    }

    private void taosukien(){
        btLogin.setOnClickListener(v -> checkUserLogin());
        btRegister.setOnClickListener(nhanvaoRegister());
    }

    private View.OnClickListener nhanvaoLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String username = edUserNameC.getText().toString().trim();
            String password = edPasswordC.getText().toString().trim();

            if(checkUserName(username) && checkPassword(password)){
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
            }
        };
    }
    @NonNull
    private View.OnClickListener nhanvaoRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }

    boolean checkUserName(String username){
        if(username.isEmpty()){
            edUserNameC.setError("Vui long nhap");
            return false;
        }
        return true;
    }

    boolean checkPassword(String password){
        if(password.isEmpty()){
            edUserNameC.setError("Vui long nhap");
            return false;
        }
        return true;
    }

    private void checkUserLogin(){
        String userPref = sharedPreferences.getString(Utils.KEY_USER, null);
        User user = gson.fromJson(userPref , User.class);
        // user = null co nghia la chua co user dang ki
        if(user == null){
            return;
        }
        // kiem tra user name va pass co trung voi doi tuong user trong share pre khong
        boolean isValid = edUserNameC.getText().toString().trim().equals(user.getUserName()) && edPasswordC.getText().toString().trim().equals(user.getPassword());
        if(isValid){
            Intent intent = new Intent(this , MainActivity.class);
            // khoi tao bundle de truyen data qua MainAc
            Bundle bundle = new Bundle();
            // vi user la obj nen dung putSerializable
            bundle.putSerializable(Utils.KEY_USER_PROFILE, user);
            // or dung putString if using user name
            // bundle.putString(Utils.KEY_USER_NAME , user.getUserName());

            // put bunlde cho intent
            intent.putExtras(bundle);
            startActivity(intent);
            // sau khi start main activity thi finish login ac
            finish();
        }
    }
}