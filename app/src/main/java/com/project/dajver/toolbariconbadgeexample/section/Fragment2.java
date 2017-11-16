package com.project.dajver.toolbariconbadgeexample.section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.dajver.toolbariconbadgeexample.R;

/**
 * Created by gleb on 11/16/17.
 */

public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        return rootView;
    }
}
