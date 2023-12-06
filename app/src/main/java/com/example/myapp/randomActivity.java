package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class randomActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private EditText adetEditText, minEditText, maxEditText;
    private Button generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        scrollView = findViewById(R.id.scrollView);
        linearLayout = findViewById(R.id.linearLayout);
        adetEditText = findViewById(R.id.adetEditText);
        minEditText = findViewById(R.id.minEditText);
        maxEditText = findViewById(R.id.maxEditText);
        generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                generateProgressBars();
            }
        });
    }

    private void generateProgressBars() {
        linearLayout.removeAllViews(); // Önceki ProgressBar, TextView ve SeekBar'ları temizle

        // Kullanıcının girdiği sayıyı al
        int adet = Integer.parseInt(adetEditText.getText().toString());

        for (int i = 0; i < adet; i++) {
            int userMin = Integer.parseInt(minEditText.getText().toString());
            int userMax = Integer.parseInt(maxEditText.getText().toString());

            // Kullanıcının girdiği min ve max arasında random min ve max oluştur
            int generatedMin = generateRandomValueInRange(userMin, userMax);
            int generatedMax = generateRandomValueInRange(generatedMin, userMax);

            int randomValue = generateRandomValueInRange(generatedMin, generatedMax);
            int percentage = calculatePercentage(generatedMin, generatedMax, randomValue);

            LinearLayout progressBarLayout = new LinearLayout(this);
            progressBarLayout.setOrientation(LinearLayout.VERTICAL);
            progressBarLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            TextView minMaxTextView = new TextView(this);
            minMaxTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            minMaxTextView.setText("Min: " + generatedMin + ", Max: " + generatedMax);

            ProgressBar progressBar = createProgressBar(generatedMin, generatedMax, randomValue);
            TextView textView = createTextView(generatedMin, generatedMax, randomValue, percentage);

            progressBarLayout.addView(minMaxTextView);
            progressBarLayout.addView(progressBar);
            progressBarLayout.addView(textView);

            linearLayout.addView(progressBarLayout);
        }
    }

    private int generateRandomValueInRange(int minValue, int maxValue) {
        return new Random().nextInt((maxValue - minValue) + 1) + minValue;
    }

    private int calculatePercentage(int minValue, int maxValue, int value) {
        return (int) ((float) (value - minValue) / (maxValue - minValue) * 100);
    }

    private ProgressBar createProgressBar(int minValue, int maxValue, int value) {
        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        progressBar.setMax(maxValue - minValue);
        progressBar.setProgress(value - minValue);
        return progressBar;
    }

    private TextView createTextView(int minValue, int maxValue, int Value, int percentage) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        textView.setText("Value: " + Value + ", Percentage: " + percentage + "%");
        return textView;
    }


}