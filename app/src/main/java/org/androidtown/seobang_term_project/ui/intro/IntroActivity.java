package org.androidtown.seobang_term_project.ui.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.androidtown.seobang_term_project.R;
import org.androidtown.seobang_term_project.ui.main.MainActivity;
import org.androidtown.seobang_term_project.ui.tutorial.TutorialActivity;

/**
 * Developed by hayeon0824 on 2018-05-19.
 * Copyright (c) 2018 hayeon0824 rights reserved.
 */

/**
 * @When:
 * This activity is shown when the user enters 개발자 정보 button from the MainActivity
 *
 * @Function:
 * This activity just simply show the information about the users with application description
 *
 * @Technique:
 * This activity is composed with open source libraries for maintaining the material designing
 */


public class IntroActivity extends AppCompatActivity {

    private Handler handler;
    SharedPreferences preference;
    SharedPreferences.Editor toEdit;
    Boolean State = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        applySharedPreference();
        Drawable alpha = ((ImageView) findViewById(R.id.logo)).getDrawable();
        alpha.setAlpha(80);
        handler = new Handler();
        handler.postDelayed(pass, 2000);
    }

    private Runnable pass = new Runnable() {
        @Override
        public void run() {
            if (State == false) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else {
                sharedPreferences();
                applySharedPreference();
                Intent intent = new Intent(IntroActivity.this, TutorialActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            finish();
        }
    };

    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(pass);
    }

    public void sharedPreferences() {
        preference = getSharedPreferences("isFirst", MODE_PRIVATE);
        toEdit = preference.edit();
        toEdit.putBoolean("State", false);
        toEdit.commit();
    }

    public void applySharedPreference() {
        preference = getSharedPreferences("isFirst", MODE_PRIVATE);
        if (preference != null && preference.contains("State")) {
            State = preference.getBoolean("State", true);
        }
    }
}
