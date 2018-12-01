package com.example.mushr.a433;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * added on 2017/8/16.
 */

public class Page2_Activity extends Fragment {
    public static Page2_Activity newInstance(String param2) {
        Page2_Activity fragment = new Page2_Activity();
        Bundle args = new Bundle();
        args.putString("agrs2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Page2_Activity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page2, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs2");
        TextView tv = (TextView)view.findViewById(R.id.container2);
        tv.setText(agrs1);
        return view;
    }
}