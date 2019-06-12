package com.example.filepersistencetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SharedPreferencesTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_test);
        Button saveData = findViewById(R.id.save_data);
        Button readData = findViewById(R.id.read_data);
        Button nextActivity = findViewById(R.id.broadBestPractice);
        final EditText editTextName = findViewById( R.id.editTextName);
        final EditText editTextAge = findViewById(R.id.editTextAge);
        final RadioGroup radioGroupMarried = findViewById(R.id.radioGroupMarried);
        saveData.setOnClickListener(new View.OnClickListener() {
            String name;//姓名
            int age;//年龄
            boolean married;//是否已婚
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                name = editTextName.getText().toString();
                age = Integer.parseInt(editTextAge.getText().toString());
                int id = radioGroupMarried.getCheckedRadioButtonId();
                if (id== R.id.radioButtonYes){
                    married = true;
                }else{
                    married = false;
                }
                editor.putString("Name",name);
                editor.putInt("Age",age);
                editor.putBoolean("Married",married);
                editor.apply();
            }
        });
        readData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;//姓名
                int age;//年龄
                boolean married;//是否已婚
                SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
                name = pref.getString("Name","某某某");
                age = pref.getInt("Age",0);
                married = pref.getBoolean("Married",false);
                Log.d("MainActivity", "name is: " + name);
                Log.d("MainActivity", "age is: " + age);
                Log.d("MainActivity", "married is: " + married);
                editTextName.setText("任富强");
                editTextAge.setText(String.valueOf(age));
                int id;
                if (married){
                    id=R.id.radioButtonYes;
                }else{
                    id = R.id.radioButtonNo;
                }
                radioGroupMarried.check(id);

            }
        });
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SharedPreferencesTest.this,BroadcastBestPractice.class);
                startActivity(intent);
            }
        });
    }
}
