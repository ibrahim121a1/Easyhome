package com.example.easyhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.easyhome.Fragments.Home_Fragment;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread thread= new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (Exception e)
                {


                }finally {
                    Intent intent=new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
