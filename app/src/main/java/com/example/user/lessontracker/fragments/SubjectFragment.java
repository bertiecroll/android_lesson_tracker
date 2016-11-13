package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;

import org.w3c.dom.Text;

public class SubjectFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    TextView mTitleTextView;
    TextView mDetailTextView;
    Button mNewButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        mTitleTextView = (TextView) view.findViewById(R.id.subject_title);
        mDetailTextView = (TextView) view.findViewById(R.id.subject_detail);
        mNewButton = (Button) view.findViewById(R.id.subject_new_button);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddSubjectFragment AddFragment = new AddSubjectFragment();
                transaction.replace(R.id.fragment_container, AddFragment);
                transaction.commit();
            }
        });

        Subject subject = new Subject(mTitleTextView.getText().toString(), mDetailTextView.getText().toString());

        Subject querySubject = mDbHelper.findSubject(subject.getId());
//        mQueryTextView.setText("Title: " + querySubject.getTitle() + "%nDetail: " + querySubject.getDetail() +
//                "%nID: " + Long.toString(querySubject.getId()));

        return view;
    }

}
