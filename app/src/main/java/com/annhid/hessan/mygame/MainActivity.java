package com.annhid.hessan.mygame;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.annhid.hessan.mygame.R;

/**
 * Created by leejh on 2016-05-15.
 */
public class MainActivity extends Activity{
    private Stage stage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        setContentView(R.layout.main_layout);
        stage = (Stage)findViewById(R.id.my_stage);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stage.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        stage.onResume();
    }
}
