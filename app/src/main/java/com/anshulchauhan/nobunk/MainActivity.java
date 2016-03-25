package com.anshulchauhan.nobunk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ArrayAdapter<String> studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] studentsList = {
                "011 - Anshul Chauhan",
                "021 - Mayur Bhangale",
                "036 - Gaurav Dabhade",
                "001 - Dinesh Dalvi",
                "002 - abc",
                "003 - qwwe",
                "004 - asdsad",
                "005 - asd",
                "006 - asd",
                "007 - asdsd",
                "008 - sad",
                "009 - sfsd",
                "010 - sdsf",
                "012 - Aniket",
                "013 - sfsdff",
                "014 - hhffhhf",
                "015 - ssdd",
                "016 - huty"
        };

        ArrayAdapter<String> theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
                studentsList);

        ListView theStudentListView = (ListView) findViewById(R.id.listViewOfStudents);

        theStudentListView.setAdapter(theAdapter);
        theStudentListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        theStudentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*
             * Parameters
             * adapterView - where the click happened
             * view - the view withing the adapterView that was clicked
             * position - position of the view in adapter
             * l - row
            */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String studentPicked = "You selected " + String.valueOf(adapterView.getItemAtPosition(position));

                //Toast.makeText(MainActivity.this, studentPicked, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
