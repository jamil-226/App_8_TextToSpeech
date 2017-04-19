package com.example.jameel.app_8_texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
TextToSpeech toSpeech;
    EditText editText;
    int result;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.editText);
        toSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS)
                {
                    result=toSpeech.setLanguage(Locale.US);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Feature is not supported in your device",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void TTS(View view)
    {
        switch(view.getId()){
            case R.id.btnSpeak:
                if(result==TextToSpeech.LANG_MISSING_DATA|| result==TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(getApplicationContext(),"Feature is not supported in your device",Toast.LENGTH_LONG).show();
                }
                else
                {
                    text = editText.getText().toString();
                    toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
            case R.id.btnStop:
                if(toSpeech!=null)
                {
                    toSpeech.stop();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(toSpeech!=null){
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }
}
