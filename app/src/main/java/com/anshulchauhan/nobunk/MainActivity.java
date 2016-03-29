package com.anshulchauhan.nobunk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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


import org.mortbay.jetty.Main;

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
    Context context;
    final int BUNK_WRITE_EXTERNAL_PERMISSION = 0;

    //public ArrayAdapter<String> studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);



        String[] studentsList = {
                "011 - Anshul Chauhan",
                "021 - Mayur Bhangale",
                "036 - Gaurav Dabhade",
                "001 - Dinesh Dalvi",
                "002 - Pete Shearer",
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case BUNK_WRITE_EXTERNAL_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    // We are just going to ask them to submit again

                    // Tell the user however you were going to tell them
                    Log.i(TAG, "You granted permission, now submit again");

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    // Tell the user however you were going to tell them
                    Log.i(TAG, "We can't write anything because you said no.");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void submitButtonClicked(View view) {
        Log.i(TAG, "submit button clicked");

        // API >=23 Requires Runtime Permission checks, Manifest isn't enough any more
        // http://developer.android.com/training/permissions/requesting.html
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "We don't have permissions and must ask.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, BUNK_WRITE_EXTERNAL_PERMISSION);
            return;
        }

        if (!isExternalStorageWritable())
        {
            Toast.makeText(MainActivity.this, "There is no active external storage to write to.", Toast.LENGTH_SHORT);
            return;
        }

        SparseBooleanArray checkedItems = theStudentListView.getCheckedItemPositions();
        StringBuilder sb = new StringBuilder();
        if (checkedItems != null) {
            for (int i=0; i<checkedItems.size(); i++) {
                if (checkedItems.valueAt(i)) {
                    String item = theStudentListView.getAdapter().getItem(checkedItems.keyAt(i)).toString() + "\n";
                    sb.append(item);
                }
            }

            String appRoot = Environment.getExternalStorageDirectory().toString();
            Log.i(TAG, "Our app root is " + appRoot);
            File appDirectory = new File(appRoot + "/nobunk");

            if (!appDirectory.exists()) {
                if (!appDirectory.mkdirs()) Log.i(TAG, "Could not make directory");
            }
            else {
                Log.i(TAG, "Folder is already there");
            }

            String fileName = "Attendance.txt";
            File file = new File(appDirectory, fileName);

            if (file.exists()) file.delete();

            try {
                FileOutputStream out = new FileOutputStream(file);
                out.write(sb.toString().getBytes());
                out.flush();
                out.close();
            }
            catch(Exception e){
                Log.i(TAG, "Failed to write out file because: " + e.getMessage());
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
