package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Subject;

public class AddSubjectFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    EditText mTitleEditText;
    EditText mDetailEditText;
    Button mAddButton;
    Button mFinishButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_teachable, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        mTitleEditText = (EditText) view.findViewById(R.id.add_title_edit);
        mTitleEditText.setHint(R.string.subject_title_hint);

        mDetailEditText = (EditText) view.findViewById(R.id.add_detail_edit);
        mDetailEditText.setHint(R.string.subject_detail_hint);

        mAddButton = (Button) view.findViewById(R.id.add_button);
        mAddButton.setText(R.string.subject_add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitleEditText.getText().toString();
                String detail = mDetailEditText.getText().toString();
                Subject subject = new Subject(title, detail);
                mDbHelper.saveSubject(subject);
                getFragmentManager().popBackStack();
                Toast.makeText(getActivity(), R.string.subject_toast_add_success, Toast.LENGTH_SHORT).show();
            }
        });

        mFinishButton = (Button) view.findViewById(R.id.add_finish_button);
        mFinishButton.setVisibility(View.GONE);

        return view;
    }
}
