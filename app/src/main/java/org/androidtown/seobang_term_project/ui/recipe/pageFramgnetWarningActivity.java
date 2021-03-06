package org.androidtown.seobang_term_project.ui.recipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import org.androidtown.seobang_term_project.R;

/**
 * @When:
 * It is executed when the user wants to terminate while the timer is on.
 *
 * @Function:
 * If the user desires, it will terminate the timer.
 *
 * @Technique:
 *  Receive user choice through setonclicklistener
 */

public class pageFramgnetWarningActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_page_framgnet_warning);

        findViewById(R.id.OKButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("결과", "종료");
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.noButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
