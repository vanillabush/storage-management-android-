package com.example.storage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class External extends AppCompatActivity {
    private EditText input;
    private TextView display;
    private Button save, retrieve;

    String filename = "External.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        isExternalStorageWritable();
        isExternalStorageReadable();

        save = findViewById(R.id.save);
        input = findViewById(R.id.input);
        display = findViewById(R.id.display);
        retrieve = findViewById(R.id.retrieve);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteExternal();
            }
        });
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadExternal();
            }
        });
    }
    public void WriteExternal(){
        String text = input.getText().toString();

        try {
            //open file
            FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory() +"/"+ filename);
            //write data to file
            fos.write(text.getBytes());
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ReadExternal(){
        try{
            FileInputStream fis = new FileInputStream(Environment.getExternalStorageDirectory() + "//"+ filename);
            byte[] read = new byte[fis.available()];
            while(fis.read(read) != -1){
                String data = (new String(read));
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                Log.i("Text in File = ", data);
                fis.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /*checks if external storage is available for read and write */
    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }else {
            Toast.makeText(getApplicationContext(), "No media card found", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    /* checks if external storage is available to at least read */
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }else {
            Toast.makeText(getApplicationContext(), "No media card found", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}