package com.example.konvertervoice;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tv_input, tv_result;
    Button voiceActivate, btn_speak;
    private static final int recognizer_result = 1;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        setUpClicklistener();

    }



    private void setupUI(){
        tv_result = findViewById(R.id.tv_result);
        voiceActivate = findViewById(R.id.btn_activateVpice);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.GERMANY);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(MainActivity.this, "Language not supported",Toast.LENGTH_LONG);
                    }else{
                        voiceActivate.setEnabled(true);
                    }
                }
            }
        });
    }

    private void setUpClicklistener(){
        voiceActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Bitte Umwandlung sagen");
                startActivityForResult(speechIntent,recognizer_result);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == recognizer_result && resultCode == RESULT_OK){
            assert data != null;
            ArrayList<String> match = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            assert match != null;
            Log.i("tag",match.get(0));
            String mystring = match.get(0);
            String[] arr = mystring.split(" ", 2);
            String firstWord = arr[0];
            firstWord = firstWord.replace(",",".");
            if(match.get(0).contains("in Euro") || match.get(0).contains("zu Euro")) {
                tv_result.setText((formeln.usdeur(Double.parseDouble(firstWord))) + " €");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Dollar") || match.get(0).contains("zu Dollar")) {
                tv_result.setText((formeln.eurusd(Double.parseDouble(firstWord))) + " $");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Celsius") || match.get(0).contains("zu Celsius")) {
                tv_result.setText((formeln.ftoc(Double.parseDouble(firstWord))) + " Grad Celsius");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Fahrenheit") || match.get(0).contains("zu Fahrenheit")) {
                tv_result.setText((formeln.ctof(Double.parseDouble(firstWord))) + " Grad Fahrenheit");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Meilen per Gallone") || match.get(0).contains("zu Meilen per Gallone")) {
                tv_result.setText((formeln.lkmtompg(Double.parseDouble(firstWord))) + " Meilen per Gallone");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Liter pro Kilometer") || match.get(0).contains("zu Liter pro Kilometer")) {
                tv_result.setText((formeln.mpgtolkm(Double.parseDouble(firstWord))) + " Liter pro 100 Kilometer");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Meter") || match.get(0).contains("zu Meter")) {
                tv_result.setText((formeln.fttome(Double.parseDouble(firstWord))) + " m");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Fuß") || match.get(0).contains("zu Fuß")) {
                tv_result.setText((formeln.mtoft(Double.parseDouble(firstWord))) + " ft");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Kilogram") || match.get(0).contains("zu Kilogram")) {
                tv_result.setText((formeln.lbstokg(Double.parseDouble(firstWord))) + " kg");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Pfund") || match.get(0).contains("zu Pfund")) {
                tv_result.setText((formeln.kgtolbs(Double.parseDouble(firstWord))) + " Pfund");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Kilometer") || match.get(0).contains("zu Kilometer")) {
                tv_result.setText((formeln.mitokm(Double.parseDouble(firstWord))) + " km");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }else if(match.get(0).contains("in Meilen") || match.get(0).contains("zu Meilen")) {
                tv_result.setText((formeln.kmtomi(Double.parseDouble(firstWord))) + " Meilen");
                tts.speak(tv_result.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}}
