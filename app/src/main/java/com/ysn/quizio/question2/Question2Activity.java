package com.ysn.quizio.question2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.ysn.quizio.R;
import com.ysn.quizio.question1.Question1Activity;
import com.ysn.quizio.question3.Question3Activity;
import com.ysn.quizio.score.ScoreActivity;

import douglasspgyn.com.github.circularcountdown.CircularCountdown;
import douglasspgyn.com.github.circularcountdown.listener.CircularListener;

public class Question2Activity extends AppCompatActivity implements View.OnClickListener {

    private CircularCountdown circularCountdown;
    private TextView textViewLabelPertanyaan;
    private TextView textViewPertanyaan;
    private TextView textViewJawaban1;
    private TextView textViewJawaban2;
    private TextView textViewJawaban3;
    private TextView textViewJawaban4;
    private int pilihanJawaban = -1;
    private int jawabanBenar = 3;
    private int pastTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        // Hubungkan ke Layout-nya
        circularCountdown = findViewById(R.id.circular_count_down_activity_question2);
        textViewLabelPertanyaan = findViewById(R.id.text_view_label_pertanyaan_activity_question2);
        textViewPertanyaan = findViewById(R.id.text_view_pertanyaan_activity_question2);
        textViewJawaban1 = findViewById(R.id.text_view_jawaban_1_activity_question2);
        textViewJawaban2 = findViewById(R.id.text_view_jawaban_2_activity_question2);
        textViewJawaban3 = findViewById(R.id.text_view_jawaban_3_activity_question2);
        textViewJawaban4 = findViewById(R.id.text_view_jawaban_4_activity_question2);

        // Set pertanyaan-nya
        textViewLabelPertanyaan.setText("Pertanyaan 2/3");
        textViewPertanyaan.setText(
                "Di Android, untuk membuat tampilannya kita bisa menggunakan XML. Kepanjangan " +
                        "dari XML adalah?");

        // Set jawaban-nya
        textViewJawaban1.setText("Extend Markup Language");
        textViewJawaban2.setText("Extensible Mark Language");
        textViewJawaban3.setText("Extensible Markup Language");
        textViewJawaban4.setText("Extend Mark Language");

        textViewJawaban1.setOnClickListener(this);
        textViewJawaban2.setOnClickListener(this);
        textViewJawaban3.setOnClickListener(this);
        textViewJawaban4.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        initCircularTimer();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (circularCountdown.isRunning()) {
            circularCountdown.stop();
        }
        super.onPause();
    }

    private void startScoreActivity() {
        startActivity(new Intent(this,
                ScoreActivity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    private void initCircularTimer() {
        circularCountdown.disableLoop();
        circularCountdown.create(pastTime, 10, CircularCountdown.TYPE_SECOND)
                .listener(new CircularListener() {
                    @Override
                    public void onTick(int i) {
                        pastTime = i;
                    }

                    @Override
                    public void onFinish(boolean b, int i) {
                        SharedPreferences sharedPreferences = getSharedPreferences("PREF_QUIZ",
                                Context.MODE_PRIVATE);
                        sharedPreferences.edit().putInt("nomor_soal_sekarang", 3).apply();
                        int totalBenar = sharedPreferences.getInt("total_benar", 0);
                        int totalSalah = sharedPreferences.getInt("total_salah", 0);
                        if (pilihanJawaban == -1) {
                            totalSalah += 1;
                            new FancyAlertDialog.Builder(Question2Activity.this)
                                    .setTitle("Waktu Habis")
                                    .setBackgroundColor(Color.parseColor("#E5494A"))
                                    .setMessage("Kamu belum pilih jawabannya.")
                                    .setPositiveBtnText("Selanjutnya")
                                    .setPositiveBtnBackground(Color.parseColor("#E5494A"))
                                    .setNegativeBtnText("Menyerah")
                                    .setAnimation(Animation.SLIDE)
                                    .isCancellable(false)
                                    .setIcon(R.drawable.ic_timer_white_24dp, Icon.Visible)
                                    .OnPositiveClicked(new FancyAlertDialogListener() {
                                        @Override
                                        public void OnClick() {
                                            startQuestion3Activity();
                                        }
                                    })
                                    .OnNegativeClicked(new FancyAlertDialogListener() {
                                        @Override
                                        public void OnClick() {
                                            startScoreActivity();
                                        }
                                    })
                                    .build();
                        } else if (pilihanJawaban == jawabanBenar) {
                            totalBenar += 1;
                            new FancyAlertDialog.Builder(Question2Activity.this)
                                    .setTitle("Yeayy")
                                    .setBackgroundColor(Color.parseColor("#8BC34A"))
                                    .setMessage("Jawaban kamu benar.")
                                    .setPositiveBtnText("Selanjutnya")
                                    .setPositiveBtnBackground(Color.parseColor("#8BC34A"))
                                    .setNegativeBtnText("Menyerah")
                                    .setAnimation(Animation.SLIDE)
                                    .isCancellable(false)
                                    .setIcon(R.drawable.ic_sentiment_satisfied_white_24dp,
                                            Icon.Visible)
                                    .OnPositiveClicked(new FancyAlertDialogListener() {
                                        @Override
                                        public void OnClick() {
                                            startQuestion3Activity();
                                        }
                                    })
                                    .OnNegativeClicked(new FancyAlertDialogListener() {
                                        @Override
                                        public void OnClick() {
                                            startScoreActivity();
                                        }
                                    })
                                    .build();
                        } else {
                            totalSalah += 1;
                            new FancyAlertDialog.Builder(Question2Activity.this)
                                    .setTitle("Kasihan ya")
                                    .setBackgroundColor(Color.parseColor("#E5494A"))
                                    .setMessage("Jawaban kamu salah.")
                                    .setPositiveBtnText("Selanjutnya")
                                    .setPositiveBtnBackground(Color.parseColor("#E5494A"))
                                    .setNegativeBtnText("Menyerah")
                                    .setAnimation(Animation.SLIDE)
                                    .isCancellable(false)
                                    .setIcon(R.drawable.ic_sentiment_dissatisfied_white_24dp,
                                            Icon.Visible)
                                    .OnPositiveClicked(new FancyAlertDialogListener() {
                                        @Override
                                        public void OnClick() {
                                            startQuestion3Activity();
                                        }
                                    })
                                    .OnNegativeClicked(new FancyAlertDialogListener() {
                                        @Override
                                        public void OnClick() {
                                            startScoreActivity();
                                        }
                                    })
                                    .build();
                        }
                        sharedPreferences.edit().putInt("total_benar", totalBenar).apply();
                        sharedPreferences.edit().putInt("total_salah", totalSalah).apply();
                    }
                })
                .start();
    }

    private void startQuestion3Activity() {
        startActivity(new Intent(this, Question3Activity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view_jawaban_1_activity_question2:
                pilihanJawaban = 1;
                textViewJawaban1.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer_selected));
                textViewJawaban2.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban3.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban4.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                break;
            case R.id.text_view_jawaban_2_activity_question2:
                pilihanJawaban = 2;
                textViewJawaban1.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban2.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer_selected));
                textViewJawaban3.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban4.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                break;
            case R.id.text_view_jawaban_3_activity_question2:
                pilihanJawaban = 3;
                textViewJawaban1.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban2.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban3.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer_selected));
                textViewJawaban4.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                break;
            case R.id.text_view_jawaban_4_activity_question2:
                pilihanJawaban = 4;
                textViewJawaban1.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban2.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban3.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer));
                textViewJawaban4.setBackground(
                        ContextCompat.getDrawable(this, R.drawable.bg_text_view_answer_selected));
                break;
            default:
                Toast.makeText(this, "Pilihan tidak tersedia", Toast.LENGTH_LONG)
                        .show();
        }
    }
}
