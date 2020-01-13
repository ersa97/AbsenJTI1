package com.e.absenjti1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.absenjti1.list.List_Activity;
import com.e.absenjti1.list.attendance;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackPressed;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ListRef = db.collection("Employee_List");
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = findViewById(R.id.text_input_serial_number);
        buttonVerify = findViewById(R.id.btn_serial_number);
        TextViewName = findViewById(R.id.employee_name);
        buttonSignIn = findViewById(R.id.btn_sign_in);


        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListRef.whereEqualTo("Id", editTextId).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot snapshot : task.getResult()) {
                                        if (snapshot.getString("id").equals(editTextId)) {
                                            TextViewName.setText(snapshot.get("Name").toString());
                                        }
                                    }
                                }
                            }
                        });

            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = editTextId.getText().toString();
                String Name = TextViewName.getText().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssz");
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                String Location = "Office";

                if (Id.trim().isEmpty() || Name.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in the required information", Toast.LENGTH_SHORT).show();
                    return;
                }
                upload(Id, Name, dateFormat, Location);
            }
        });
    }

    public void upload(final String Id, final String Name, final DateFormat dateFormat, final String Location) {
        CollectionReference reference = FirebaseFirestore.getInstance().collection("Employee_Attendance");
        reference.add(new attendance(Id, Name, dateFormat, Location));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.employee_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
        if (doubleBackPressed) {
            super.onBackPressed();
            return;
        }

        this.doubleBackPressed = true;
        Toast.makeText(this, "please click again to exit app", Toast.LENGTH_SHORT).show();
        handler.postDelayed(runnable, 2000);
    }
}
