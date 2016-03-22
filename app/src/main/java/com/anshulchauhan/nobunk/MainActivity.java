package com.anshulchauhan.nobunk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String studentName;
    boolean studentAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void absent(String name, boolean attendance) {
        studentName = name;
        attendance = false;
        // sumbit to file
    }

    public void present(String name, boolean attendance) {
        studentName = name;
        attendance = true;
        // submit to file
    }

    public void nextStudent() {

    }

    public void absentClicked(View view) {
        absent(studentName,studentAttendance);
        nextStudent();
    }

    public void presentClicked(View view) {
        present(studentName,studentAttendance);
        nextStudent();
    }
}
