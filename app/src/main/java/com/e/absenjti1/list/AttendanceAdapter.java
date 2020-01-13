package com.e.absenjti1.list;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.absenjti1.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class AttendanceAdapter extends FirestoreRecyclerAdapter<attendance, AttendanceAdapter.AttendanceHolder> {

    public AttendanceAdapter(@NonNull FirestoreRecyclerOptions<attendance> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AttendanceHolder attendanceHolder, int i, @NonNull attendance attendance) {
        attendanceHolder.textViewid.setText(attendance.getId());
        attendanceHolder.textViewname.setText(attendance.getName());
        attendanceHolder.textViewTimestamp.setText(attendance.getDate().toDate().toString());
        attendanceHolder.textViewLocation.setText(attendance.getLocation());
    }

    @NonNull
    @Override
    public AttendanceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_design,
                parent, false);
        return new AttendanceHolder(view);
    }

    class AttendanceHolder extends RecyclerView.ViewHolder {

        TextView textViewid;
        TextView textViewname;
        TextView textViewTimestamp;
        TextView textViewLocation;

        public AttendanceHolder(@NonNull View itemView) {
            super(itemView);

            textViewid = itemView.findViewById(R.id.txt_attendance_id);
            textViewname = itemView.findViewById(R.id.txt_attendance_name);
            textViewTimestamp = itemView.findViewById(R.id.txt_timestamp);
            textViewLocation = itemView.findViewById(R.id.txt_location);

        }
    }
}
