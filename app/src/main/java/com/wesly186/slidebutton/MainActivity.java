package com.wesly186.slidebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SlideButton slideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideButton = (SlideButton) findViewById(R.id.btn_slide);

        slideButton.setSlideBackgroundResource(R.drawable.switch_background);
        slideButton.setSlideResource(R.drawable.slide_image);
        slideButton.setToggleState(true);

        slideButton.setOnToggleStateChangeListener(new SlideButton.onToggleStateChangeListener() {
            @Override
            public void onToggleStateChange(boolean toogleOn) {
                Toast.makeText(MainActivity.this, toogleOn ? "开启" : "关闭", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
