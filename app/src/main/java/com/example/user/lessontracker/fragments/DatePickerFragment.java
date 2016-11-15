package com.example.user.lessontracker.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.user.lessontracker.R;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.Inflater;

public class DatePickerFragment extends DialogFragment {

    DatePicker mDatePicker;
    Button mDateButton;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.dialog_date, container, false);
//
//        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_date_picker);
//        mDateButton = (Button) view.findViewById(R.id.date_picker_button);
//        mDateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int year = mDatePicker.getYear();
//                int month = mDatePicker.getMonth();
//                int day = mDatePicker.getDayOfMonth();
//                Date date = new GregorianCalendar(year, month, day).getTime();
//
//                Intent intent = new Intent();
//                intent.putExtra("date", date);
//                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
//                getFragmentManager().beginTransaction().remove(DatePickerFragment.this).commit();
//            }
//        });
//
//        return view;
//    }
}
