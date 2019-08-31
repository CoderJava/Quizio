package com.ysn.quizio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonMulaiKuis;
    private EditText editTextNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNama = findViewById(R.id.edit_text_nama_activity_main);
        buttonMulaiKuis = findViewById(R.id.button_mulai_kuis_activity_main);
        buttonMulaiKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2019-08-31 do something in here
            }
        });
    }
}
