package com.example.jek.whenyouwashme.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jek.whenyouwashme.R;
import com.example.jek.whenyouwashme.activity.MapsActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jek on 10.08.2017.
 */

public class FragmentLowerPart extends Fragment {
    private static final String LOG = FragmentLowerPart.class.getSimpleName();

    private SharedPreferences preference;
    private final String NAME = "DATE";
    private String pattern = "yyyy-MM-dd";
    private DateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    TextView textLastWashed;
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
        textLastWashed = rootView.findViewById(R.id.textSetTimeAfterLastCarWash);
        bWashed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference = getActivity().getPreferences(MODE_PRIVATE);

                String date = simpleDateFormat.format(new Date());

                SharedPreferences.Editor ed = preference.edit();
                ed.putString(NAME, date);
                ed.commit();
                textLastWashed.setText("0 day");
            }
        });

        preference = this.getActivity().getPreferences(MODE_PRIVATE);

        try {
            //Date date = simpleDateFormat.parse(preference.getString(NAME, ""));
            Date date = simpleDateFormat.parse("2018-09-19");
            textLastWashed.setText(String.valueOf(printDifference(date, new Date())) + "days");
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    public long printDifference(Date startDate, Date endDate) {

        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;

        return elapsedDays;
    }



}

