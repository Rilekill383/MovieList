package com.example.android.movies_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private EditText MTitle;
    private EditText MCode;
    private Button Submit;
    public ArrayList<String> MovieTitles;    //Do I really need the Arraylists in here?
    public ArrayList<String> MovieCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MTitle = (EditText) findViewById(R.id.editText1);
        MCode = (EditText) findViewById(R.id.editText2);
        Submit = (Button) findViewById(R.id.button);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent TitlesM = new Intent();
                if (MTitle.getText().toString().equals("") || MTitle.getText().toString().isEmpty())  {
                    Toast.makeText(getApplicationContext(), "Please enter a title into the field", Toast.LENGTH_LONG).show();
                    return;
                }
                if (MCode.getText().toString().equals("") || MCode.getText().toString().isEmpty())  {
                    Toast.makeText(getApplicationContext(), "All Input fields must be filled", Toast.LENGTH_LONG).show();
                    return;
                }
                TitlesM.putExtra("Titles",MTitle.getText().toString());
                TitlesM.putExtra("Codes",MCode.getText().toString());
                setResult(RESULT_OK, TitlesM);
                finish();
            }
        });
    }
}

