package com.ysn.quizio.score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysn.quizio.MainActivity;
import com.ysn.quizio.R;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewScore;
    private ImageView imageViewIlustrasi;
    private TextView textViewNama;
    private TextView textViewTotalBenar;
    private TextView textViewTotalSalah;
    private Button buttonCobaLagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        SharedPreferences sharedPreferences = getSharedPreferences("PREF_QUIZ",
                Context.MODE_PRIVATE);
        int totalPertanyaan = sharedPreferences.getInt("total_soal", 0);
        int totalBenar = sharedPreferences.getInt("total_benar", 0);
        int totalSalah = totalPertanyaan-totalBenar;
        String nama = sharedPreferences.getString("nama", "User");

        textViewScore = findViewById(R.id.text_view_score_activity_score);
        imageViewIlustrasi = findViewById(R.id.image_view_ilustrasi_activity_score);
        textViewNama = findViewById(R.id.text_view_nama_activity_score);
        textViewTotalBenar = findViewById(R.id.text_view_total_benar_activity_score);
        textViewTotalSalah = findViewById(R.id.text_view_total_salah_activity_score);
        buttonCobaLagi = findViewById(R.id.button_coba_lagi_activity_score);

        double scoreSementara = (double) totalBenar / totalPertanyaan;
        int score = (int) (scoreSementara * 100);
        textViewScore.setText("Score\n" + score);
        if (score > 50) {
            imageViewIlustrasi.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_undraw_smiley_face));
        } else {
            imageViewIlustrasi.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_undraw_feeling_blue));
        }
        textViewNama.setText(nama);
        textViewTotalBenar.setText("" + totalBenar);
        textViewTotalSalah.setText("" + totalSalah);
        sharedPreferences.edit().clear().apply();

        buttonCobaLagi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_coba_lagi_activity_score) {
            Intent intentMainActivity = new Intent(this, MainActivity.class);
            intentMainActivity.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentMainActivity);
        }
    }
}
