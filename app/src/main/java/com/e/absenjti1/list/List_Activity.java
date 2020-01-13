package com.e.absenjti1.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.e.absenjti1.MainActivity;
import com.e.absenjti1.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class List_Activity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference activityRef = db.collection("Employee_Attendance");
    RecyclerView recyclerView;

    private AttendanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);

        recyclerView = findViewById(R.id.attendance_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SetUpRecyclerView();

    }

    private void SetUpRecyclerView() {
        Query query = activityRef.orderBy("Name", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<attendance> recyclerOptions = new FirestoreRecyclerOptions.Builder<attendance>()
                .setQuery(query, attendance.class)
                .build();

        adapter = new AttendanceAdapter(recyclerOptions);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(List_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
