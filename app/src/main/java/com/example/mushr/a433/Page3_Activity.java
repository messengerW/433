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

public class Page3_Activity extends Fragment {
    public static Page3_Activity newInstance(String param3) {
        Page3_Activity fragment = new Page3_Activity();
        Bundle args = new Bundle();
        args.putString("agrs3", param3);
        fragment.setArguments(args);
        return fragment;
    }

    public Page3_Activity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page3, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs3");
        TextView tv = (TextView)view.findViewById(R.id.container3);
        tv.setText(agrs1);
        return view;
    }
}