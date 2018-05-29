package org.androidtown.seobang_term_project.ui.history;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import org.androidtown.seobang_term_project.R;

public class HistoryActivity extends FragmentActivity {

    ViewPager mPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tabLayout = findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("요리별 기록 보기"));
        tabLayout.addTab(tabLayout.newTab().setText("날짜별 기록 보기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        mPager = findViewById(R.id.history_pager);
        mPager.setAdapter(pagerAdapter);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}

