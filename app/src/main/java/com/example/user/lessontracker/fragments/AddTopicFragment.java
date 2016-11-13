package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.lessontracker.R;

public class AddTopicFragment extends Fragment {

    TextView mSubjectText;
    EditText mTitleEditText;
    EditText mDetailEditText;
    Button mAddButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_topic, container, false);

        mSubjectText = (TextView) view.findViewById(R.id.topic_subject_text);
        mTitleEditText = (EditText) view.findViewById(R.id.topic_title_edit);
        mDetailEditText = (EditText) view.findViewById(R.id.topic_detil_edit);
        mAddButton = (Button) view.findViewById(R.id.topic_add_button);



        return view;
    }
}
