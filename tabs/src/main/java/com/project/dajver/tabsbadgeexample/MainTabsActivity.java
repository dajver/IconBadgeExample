package com.project.dajver.tabsbadgeexample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.project.dajver.tabsbadgeexample.adapter.PagerAdapter;
import com.project.dajver.tabsbadgeexample.section.Fragment1;
import com.project.dajver.tabsbadgeexample.section.Fragment2;
import com.project.dajver.tabsbadgeexample.section.Fragment3;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainTabsActivity extends AppCompatActivity {

    private ArrayList<String> tabTitle = new ArrayList<>();
    private int[] unreadCount = {0, 0, 0};

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_main);
        ButterKnife.bind(this);

        tabTitle.add(getString(R.string.title_my_friends_screen));
        tabTitle.add(getString(R.string.title_my_groups_screen));
        tabTitle.add(getString(R.string.title_my_invites_screen));

        unreadCount[1] = 23;
        unreadCount[2] = 3;
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        Fragment1 friendsTabFragment = new Fragment1();
        Fragment2 myGroupsTabFragment = new Fragment2();
        Fragment3 invitesTabFragment = new Fragment3();

        adapter.addFragment(friendsTabFragment, getString(R.string.title_my_friends_screen));
        adapter.addFragment(myGroupsTabFragment, getString(R.string.title_my_groups_screen));
        adapter.addFragment(invitesTabFragment, getString(R.string.title_my_invites_screen));
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        for(int i = 0; i < tabTitle.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.view_tabs_with_badge,null);
            TextView textView = view.findViewById(R.id.title);
            TextView countView = view.findViewById(R.id.count);
            textView.setText(tabTitle.get(i));
            if(unreadCount[i] > 0) {
                countView.setVisibility(View.VISIBLE);
                countView.setText(String.valueOf(unreadCount[i]));
            } else
                countView.setVisibility(View.GONE);
            tabLayout.getTabAt(i).setCustomView(view);
        }
    }
}
