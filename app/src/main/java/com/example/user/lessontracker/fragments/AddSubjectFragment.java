package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;

public class AddSubjectFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    EditText mTitleEditText;
    EditText mDetailEditText;
    Button mAddButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_subject, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        mTitleEditText = (EditText) view.findViewById(R.id.subject_title_edit);
        mDetailEditText = (EditText) view.findViewById(R.id.subject_detail_edit);
        mAddButton = (Button) view.findViewById(R.id.subject_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitleEditText.getText().toString();
                String detail = mDetailEditText.getText().toString();
                Subject subject = new Subject(title, detail);
                mDbHelper.saveSubject(subject);
                long id = subject.getId();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SubjectFragment fragment = new SubjectFragment();
                Bundle args = new Bundle();
                args.putLong("id", id);
                fragment.setArguments(args);

                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });







        return view;
    }
}
