package com.anshulchauhan.nobunk;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.io.File;
import android.widget.Toast;


import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class MainActivity extends AppCompatActivity {

    String TAG = "pokemon";
    ListView theStudentListView;
    Context context = getApplicationContext();

    String filename = "attendance.txt";
    File path = Environment.getExternalStoragePublicDirectory("nobunk");
    File attendanceFile;

    OutputStreamWriter outputStreamWriter;
    //public ArrayAdapter<String> studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attendanceFile = new File(path,filename);

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

        theStudentListView = (ListView) findViewById(R.id.listViewOfStudents);

        theStudentListView.setAdapter(theAdapter);

        // set the selection choice to multiple
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

                Toast.makeText(MainActivity.this, studentPicked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void submitButtonClicked(View view) {
        Log.i(TAG, "submit button clicked");
        SparseBooleanArray checkedItems = theStudentListView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i=0; i<checkedItems.size(); i++) {
                if (checkedItems.valueAt(i)) {
                    String item = theStudentListView.getAdapter().getItem(
                                          checkedItems.keyAt(i)).toString() + "\n";
                    //Log.i(TAG,item + " was selected");

                    // writing in file
                    try {
                        path.mkdirs();
                        FileWriter out = new FileWriter(new File(context.getFilesDir(),filename));
                        out.write(item);
                        out.close();
                        Log.i(TAG, "FileWriter executed");

                    } catch (Exception e) {
                        Log.e("Exception","File Write failed " + e.toString());
                    }
                }
            }
        }
    }


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

}
