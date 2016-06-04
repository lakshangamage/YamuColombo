package com.phrixus.yamucolombo.yamucolomboversion10.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.phrixus.yamucolombo.yamucolomboversion10.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final LinearLayout splashLayout = (LinearLayout) findViewById(R.id.splashLayout);

        final Handler updateBackground = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what){
                    case 1:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_1);
                        break;
                    case 2:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_2);
                        break;
                    case 3:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_3);
                        break;
                    case 4:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_4);
                        break;
                    case 5:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_5);
                        break;
                    case 6:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_6);
                        break;
                    case 7:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_7);
                        break;
                    case 8:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_8);
                        break;
                    case 9:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_9);
                        break;
                    case 10:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_10);
                        break;
                    case 11:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_11);
                        break;
                    case 12:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_12);
                        break;
                    case 13:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_13);
                        break;
                    case 14:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_14);
                        break;
                    case 15:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_15);
                        break;
                    case 16:
                        splashLayout.setBackgroundResource(R.drawable.yamu_colombo_splash_16);
                        login();
                        break;
                }
            }
        };

        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 1;
                while(count < 17){
                    updateBackground.obtainMessage(count).sendToTarget();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        });
        animationThread.start();

    }
    public void login(){
        Intent loginIntent = new Intent(this,LoginActivity.class);
        startActivity(loginIntent);
    }

}
