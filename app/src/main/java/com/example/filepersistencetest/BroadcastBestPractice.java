package com.example.filepersistencetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class BroadcastBestPractice extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;//登录按键
    private Button nextActivity;//下一活动按键
    private CheckBox rememberPass;//记住密码复选框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_best_practice);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.name);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);
        login = findViewById(R.id.login);
        nextActivity = findViewById(R.id.nextActivity);
        boolean isRemember = preferences.getBoolean("remember_password",false);
        if (isRemember){//判断是否保存
            String name = preferences.getString("account","");
            String password = preferences.getString("password","");
            accountEdit.setText(name);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (name.equals("admin") && password.equals("123456")){
                    editor = preferences.edit();
                    if (rememberPass.isChecked()){//复选框是否选中
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",name);
                        editor.putString("password",password);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(BroadcastBestPractice.this,CloseAllActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(BroadcastBestPractice.this,"账户或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
