package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class convertorActivity extends AppCompatActivity {
    private EditText decimalInput, megaByteInput, celsiusInput;
    private Spinner decimalSpinner, megaByteSpinner;
    private RadioGroup celciusRadioGroup;
    private RadioButton kelvinRadioButton, fahrenheitRadioButton;
    private Button convertDecimalButton, convertMegaByteButton, convertCelsiusButton;
    private TextView decimalResultTextView, megaByteResultTextView, celsiusResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        //Decimal bölümü
        decimalInput=findViewById(R.id.decimalInput);
        decimalSpinner = findViewById(R.id.decimal_spinner);
        convertDecimalButton =findViewById(R.id.convertDecimalButton);
        decimalResultTextView = findViewById(R.id.decimalResultTextView);

        //Mega byte bölümü
        megaByteInput = findViewById(R.id.megaByteInput);
        megaByteSpinner = findViewById(R.id.megaByteSpinner);
        convertMegaByteButton = findViewById(R.id.convertMegaByteButton);
        megaByteResultTextView = findViewById(R.id.megaByteResultTextView);

        //celsius bölümü
        celsiusInput = findViewById(R.id.celsiusInput);
        celciusRadioGroup = findViewById(R.id.celsiusRadioGroup);
        kelvinRadioButton = findViewById(R.id.kelvinRadioButton);
        fahrenheitRadioButton = findViewById(R.id.fahrenheitRadioButton);
        convertCelsiusButton = findViewById(R.id.convertCelciusButton);
        celsiusResultTextView = findViewById(R.id.celciusResultTextView);


        //decimal için spinner
        ArrayAdapter<CharSequence> decimalAdapter = ArrayAdapter.createFromResource(this,R.array.taban,
                android.R.layout.simple_spinner_item);
        decimalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decimalSpinner.setAdapter(decimalAdapter);

        //mega byte için spinner
        ArrayAdapter<CharSequence> megaByteAdapter = ArrayAdapter.createFromResource(this,
                R.array.donusum, android.R.layout.simple_spinner_item);
        megaByteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        megaByteSpinner.setAdapter(megaByteAdapter);

        //convert decimal button için listener
        convertDecimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                convertDecimal();
            }
        });

        //convert mega byte için listener
        convertMegaByteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertMegaByte();
            }
        });

        //convert celsius için listener
        convertCelsiusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                convertCelsius();
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private void convertDecimal(){
        try{
            String decimalInputStr = decimalInput.getText().toString().trim();
            if(!decimalInputStr.isEmpty()){
                int decimalValue=Integer.parseInt(decimalInputStr);
                String selectedDecimalConversion = decimalSpinner.getSelectedItem().toString();
                String decimalResult = "";

                switch (selectedDecimalConversion){
                    case "ikilik":
                        decimalResult = Integer.toBinaryString(decimalValue);
                        break;
                    case "sekizlik":
                        decimalResult = Integer.toOctalString(decimalValue);
                        break;
                    case "onaltılık":
                        decimalResult = Integer.toHexString(decimalValue);
                        break;
                }
                decimalResultTextView.setText(decimalResult);
            }else{
                showToast("Lütfen bir decimal değer giriniz");
            }
        }catch (NumberFormatException e){
            showToast("geçersiz decimal girişi .Lütfen tam sayı giriniz.");
        }

    }

    private void convertMegaByte(){
        try{
            String megaByteInputStr = megaByteInput.getText().toString().trim();
            if(!megaByteInputStr.isEmpty()){
                double megaByteValue = Double.parseDouble(megaByteInputStr);
                String selectedMegaByteConversion = megaByteSpinner.getSelectedItem().toString();
                double megabyteresult = 0;

                switch (selectedMegaByteConversion){
                    case "bit":
                        megabyteresult =megaByteValue * 1024 * 1024 * 8;
                        break;
                    case "byte":
                        megabyteresult = megaByteValue * 1024 * 1024;
                        break;
                    case "kibibyte":
                        megabyteresult = megaByteValue * 1024 * 1024 / 1024;
                        break;
                    case "kilobyte":
                        megabyteresult = megaByteValue * 1024;
                        break;
                }

                megaByteResultTextView.setText(String.valueOf(megabyteresult));
            }else{
                showToast("Lütfen geçerli bir mega byte değeri giriniz.");
            }
        }catch(NumberFormatException e){
            showToast("Geçersiz mega byte girişi.");
        }
    }

    private void convertCelsius(){
        try{
            RadioButton kelvinRadioButton = findViewById(R.id.kelvinRadioButton);
            RadioButton fahrenheitRadioButton = findViewById(R.id.fahrenheitRadioButton);
            String celsiusInputStr = celsiusInput.getText().toString().trim();
            if(!celsiusInputStr.isEmpty()){
                double celsiusValue = Double.parseDouble(celsiusInputStr);
                double celsiusresult = 0;

                if(kelvinRadioButton.isChecked()){
                    celsiusresult = celsiusValue + 273;
                } else if(fahrenheitRadioButton.isChecked()){
                    celsiusresult = (celsiusValue * 9/5) + 32;
                }

                celsiusResultTextView.setText(String.valueOf(celsiusresult));

                kelvinRadioButton.setVisibility(View.VISIBLE);
                fahrenheitRadioButton.setVisibility(View.VISIBLE);
            } else {
                showToast("Lütfen bir Celsius değeri girin.");
            }
        } catch (NumberFormatException e) {
            showToast("Geçersiz Celsius girişi.");
        }
    }
}
