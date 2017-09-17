package com.example.hp.internal_storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    // Declaring variables
    EditText editText;
    Button save_file , check_file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creating and initializing objects by ID.
        editText=(EditText)findViewById(R.id.editText);
        save_file=(Button)findViewById(R.id.save_file);
        check_file=(Button)findViewById(R.id.check_file);

        save_file.setOnClickListener(new View.OnClickListener() { // onClickListener method
            @Override
            public void onClick(View v) {
                try { //try statement
                    /*
                    * FileOutputStream creates a file output stream to write to the file with the
                    specified name.
                    * openFileOutput will open a private file associated with this Context's
                    application package for writing.
                     */
                    FileOutputStream fileOutputStream=openFileOutput
                            ("internalStorage.txt",MODE_PRIVATE);

                    /*
                    An OutputStreamWriter is a bridge from character streams to byte streams
                     */
                    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
                    // It will write the string input by user in edit text into the file .
                    outputStreamWriter.write(editText.getText().toString());
                    outputStreamWriter.flush();  // Flushes the stream.
                    outputStreamWriter.close(); //Closes the stream, flushing it first.

                    Toast.makeText(getBaseContext(), "File saved",//Toast message when file is saved
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) { // Catch staement
                    e.printStackTrace();
                }
            }
        });



        check_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                  Returns the absolute path to the directory on the filesystem where files created
                    with openFileOutput are stored.
                 */
                String path =getFilesDir().getAbsolutePath()+"/internalStorage.txt";
                File file=new File(path); //Creating object

                if (file.exists()){ // if else statement to check whether the file exist or not
                    Toast.makeText(getBaseContext(), "File Exist",// Toast message
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "File doesn't exist !!", // Toast message
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
