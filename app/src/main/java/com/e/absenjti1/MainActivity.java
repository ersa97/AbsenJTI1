package com.e.absenjti1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.absenjti1.list.List_Activity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackPressed;

    private Handler handler = new Handler();

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            doubleBackPressed = false;
        }
    };

    TextInputEditText editTextId;
    TextView TextViewName;
    Button buttonVerify;
    Button buttonSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.text_input_serial_number);
        buttonVerify = findViewById(R.id.btn_serial_number);
        TextViewName = findViewById(R.id.employee_name);
        buttonSignIn = findViewById(R.id.btn_sign_in);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.employee_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_goto_detail:
                Intent intent = new Intent(MainActivity.this, List_Activity.class);
                startActivity(intent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onBackPressed() {
        if (doubleBackPressed){
            super.onBackPressed();
            return;
        }

        this.doubleBackPressed = true;
        Toast.makeText(this, "please click again to exit app", Toast.LENGTH_SHORT).show();
        handler.postDelayed(runnable, 2000);
    }
}
