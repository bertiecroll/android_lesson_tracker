package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lessontracker.R;

public class TaggingDialogFragment extends DialogFragment {

    TextView mTaggingTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_tagging, container, false);

        mTaggingTitle = (TextView) view.findViewById(R.id.tagging_title);

        return view;
    }


}
