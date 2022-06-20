package com.example.storage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private TextView display;
    private Button save, retrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save);
        input = findViewById(R.id.input);
        display = findViewById(R.id.display);
        retrieve = findViewById(R.id.retrieve);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile();
            }
        });
        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readfromFile();
            }
        });

    }
    public void writeToFile(){
        String text = input.getText().toString();
        try{
            FileOutputStream fos = openFileOutput("Example.txt", MODE_APPEND);
            fos.write(text.getBytes());
            fos.close();

            Toast.makeText(getApplicationContext(), "Text Save", Toast.LENGTH_SHORT).show();

        }
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }

    public void readfromFile(){
        try{
            FileInputStream fis = openFileInput("Example.text");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            StringBuffer stringBuffer = new StringBuffer();

            String lines;

            while ((lines = br.readLine())!= null){
                stringBuffer.append(lines + "\n");

            }
            display.setText(stringBuffer.toString());
        }
        catch (FileNotFoundException fe){
            fe.printStackTrace();
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }


}