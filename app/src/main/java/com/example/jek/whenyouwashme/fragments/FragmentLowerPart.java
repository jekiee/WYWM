package com.example.jek.whenyouwashme.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jek.whenyouwashme.R;
import com.example.jek.whenyouwashme.activity.MapsActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by jek on 10.08.2017.
 */

public class FragmentLowerPart extends Fragment {
    private static final String LOG = FragmentLowerPart.class.getSimpleName();

    Button bNearestCarWashes;
    Button bWashed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_portrait_lower_fragment, container, false);
        bNearestCarWashes = (Button) rootView.findViewById(R.id.bNearestCarWashes);
        bWashed = (Button) rootView.findViewById(R.id.bWashed);
        final Intent intent = new Intent(getActivity(), MapsActivity.class);
        bNearestCarWashes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + getClass().toString());

    }

    public static Fragment newInstance() {
        return new FragmentLowerPart();
    }
}
