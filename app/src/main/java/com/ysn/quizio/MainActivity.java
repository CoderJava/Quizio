package com.ysn.quizio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ysn.quizio.question1.Question1Activity;
import com.ysn.quizio.question2.Question2Activity;
import com.ysn.quizio.question3.Question3Activity;

public class MainActivity extends AppCompatActivity {

    private Button buttonMulaiKuis;
    private EditText editTextNama;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences = getSharedPreferences("PREF_QUIZ",
                Context.MODE_PRIVATE);
        int nomorSoalSekarang = sharedPreferences.getInt("nomor_soal_sekarang", -1);
        if (nomorSoalSekarang == 1) {
            Intent intentQuestion1Activity = new Intent(this, Question1Activity.class);
            intentQuestion1Activity.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentQuestion1Activity);
            return;
        } else if (nomorSoalSekarang == 2) {
            Intent intentQuestion2Activity = new Intent(this, Question2Activity.class);
            intentQuestion2Activity.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentQuestion2Activity);
            return;
        } else if (nomorSoalSekarang == 3) {
            Intent intentQuestion3Activity = new Intent(this, Question3Activity.class);
            intentQuestion3Activity.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentQuestion3Activity);
            return;
        }

        editTextNama = findViewById(R.id.edit_text_nama_activity_main);
        buttonMulaiKuis = findViewById(R.id.button_mulai_kuis_activity_main);
        buttonMulaiKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aksi yang dilakukan ketika si pengguna tap button "Mulai Kuis"
                String nama = editTextNama.getText().toString().trim();
                if (nama.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Kamu belum isi nama ya...",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    // Simpan nama si pengguna ke dalam SharedPreferences
                    sharedPreferences.edit()
                            .putString("nama", nama)
                            .apply();
                    sharedPreferences.edit()
                            .putInt("nomor_soal_sekarang", 1)
                            .apply();
                    sharedPreferences.edit()
                            .putInt("total_soal", 3)
                            .apply();

                    // Arahkan si pengguna ke screen Question 1
                    Intent intentQuestion1Activity = new Intent(MainActivity.this,
                            Question1Activity.class);
                    intentQuestion1Activity.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentQuestion1Activity);
                }
            }
        });
    }
}
