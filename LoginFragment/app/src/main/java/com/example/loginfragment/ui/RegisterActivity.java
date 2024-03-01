package com.example.loginfragment.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.loginfragment.model.User;
import com.example.loginfragment.R;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {
    private  EditText edUserNameC;
    private  EditText edPasswordC;
    private  EditText edConfirmPasswordC;
    private  EditText edEmailC;
    private  EditText edPhoneNumberC;
    private RadioGroup rbSex;
    private Button btnRegister;
    private ImageButton imbBack;

    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // lay du lieu
        anhxadulieu();
        taosukien();
    }

    void anhxadulieu(){
        edUserNameC = findViewById(R.id.edUserNameRe);
        edPasswordC = findViewById(R.id.edPasswordRe);
        edConfirmPasswordC = findViewById(R.id.edt_confirm_password);
        edEmailC = findViewById(R.id.edEmail);
        edPhoneNumberC = findViewById(R.id.edPhone);
        rbSex = findViewById(R.id.rgSex);
        btnRegister = findViewById(R.id.btRegister);
        imbBack = findViewById(R.id.imbBack);
    }

    void taosukien(){
        btnRegister.setOnClickListener(v -> sukienRegister());
        imbBack.setOnClickListener(v -> finish());
    }

    void sukienRegister(){
        String userName = edUserNameC.getText().toString().trim();
        String password = edPasswordC.getText().toString().trim();
        String confirmPassword = edConfirmPasswordC.getText().toString().trim();
        String email = edEmailC.getText().toString().trim();
        String phone = edPhoneNumberC.getText().toString().trim();

        // if sex = 1 is female , sex = 0 is male

        int sex = 1;
        boolean isValid = checkUserName(userName) && checkPassword(password , confirmPassword);
        if(isValid){
            // neu doi tuong hop le , tao doi tuong de luu vo share preferences
            User userNew = new User();
            userNew.setUserName(userName);
            userNew.setPassword(password);
            userNew.setEmail(email);
            userNew.setPhoneNumber(phone);
            // lay radio bt id dang duoc checked
            int sexSelected = rbSex.getCheckedRadioButtonId();
            if(sexSelected == R.id.rbFemale){
                sex = 0;
            }
            userNew.setSex(sex);
            // vi user la object nen convert qua string voi format la json de luu vao share pre
            String userStr = gson.toJson(userNew);
            editor.putString(Utils.KEY_USER, userStr);
            editor.commit();
            // dung Toast de show thong bao dang ky thanh cong
            Toast.makeText(RegisterActivity.this, "Dang ky tai khoan thanh cong", Toast.LENGTH_LONG).show();
            // finish activity
            finish();
        }
    }
    private boolean checkUserName(String userName){
        if(userName.isEmpty()){
            edUserNameC.setError("Vui long nhap ten dang nhap");
            return false;
        }
        if(userName.length() <= 5){
            edUserNameC.setError("Ten dang nhap phai it nhat 6 ky tu");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password , String confirmPassword){
        if(password.isEmpty()){
            edUserNameC.setError("Vui long nhap mat khau");
            return false;
        }
        if(password.length() <= 5){
            edUserNameC.setError("Mat khau phai lon hon 5 ky tu");
            return false;
        }
        if(!password.equals(confirmPassword)){
            edConfirmPasswordC.setError("Xac nhan mat khau khong khop");
            return false;
        }
        return true;
    }
}