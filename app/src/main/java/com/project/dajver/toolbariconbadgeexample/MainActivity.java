package com.project.dajver.toolbariconbadgeexample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.project.dajver.toolbariconbadgeexample.adapter.PagerAdapter;
import com.project.dajver.toolbariconbadgeexample.section.Fragment1;
import com.project.dajver.toolbariconbadgeexample.section.Fragment2;
import com.project.dajver.toolbariconbadgeexample.section.Fragment3;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tabTitle = new ArrayList<>();
    private int[] unreadCount = {0, 0, 0};
    private boolean isYouWantToShowBadge = false;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.view_tabs_with_badge,null);
        TextView textView = view.findViewById(R.id.title);
        TextView countView = view.findViewById(R.id.count);
        textView.setText(tabTitle.get(pos));
        if(unreadCount[pos] > 0) {
            countView.setVisibility(View.VISIBLE);
            countView.setText(String.valueOf(unreadCount[pos]));
        } else
            countView.setVisibility(View.GONE);
        return view;
    }

    private void setupTabIcons() {
        for(int i = 0; i < tabTitle.size(); i++) {
            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        if(isYouWantToShowBadge)
            menu.findItem(R.id.groups).setIcon(buildCounterDrawable(R.mipmap.icon_invited_users));
        menu.findItem(R.id.panics).setIcon(buildCounterDrawable(R.mipmap.icon_panic_counts));

        return true;
    }

    private Drawable buildCounterDrawable(int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.view_menu_icon_badge, null);
        view.setBackgroundResource(backgroundImageId);

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

}
