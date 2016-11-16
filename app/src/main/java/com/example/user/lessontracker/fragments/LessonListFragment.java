package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.adapters.LessonAdapter;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonListFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    RelativeLayout mHeaderLayout;
    TextView mHeaderTitle;
    Button mNewLessonButton;
    ListView mPendingLessonList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());

        mHeaderLayout = (RelativeLayout) view.findViewById(R.id.list_fragment_header_layout);
        mHeaderTitle = (TextView) view.findViewById(R.id.list_fragment_title);
        mHeaderTitle.setText("Pending Lessons");


        mNewLessonButton = (Button) view.findViewById(R.id.list_new_button);
        mNewLessonButton.setText(R.string.list_new_lesson_button);

        mNewLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LessonTracker", "new lesson button clicked");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                AddLessonFragment addLessonFragment = new AddLessonFragment();
                transaction.replace(R.id.fragment_container, addLessonFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        mPendingLessonList = (ListView) view.findViewById(R.id.teachable_list);
        List<Lesson> lessons = new ArrayList<>(mDbHelper.findPendingLessons());
        LessonAdapter lessonAdapter =
                new LessonAdapter(getActivity(), lessons);
        mPendingLessonList.setAdapter(lessonAdapter);

        mPendingLessonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LessonTracker", "Pending lesson item selected");
                Lesson selectedLesson = (Lesson) mPendingLessonList.getItemAtPosition(position);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                TakeLessonFragment takeLessonFragment = new TakeLessonFragment();

                Bundle args = new Bundle();
                args.putLong("lessonId", selectedLesson.getId());
                takeLessonFragment.setArguments(args);
                transaction.replace(R.id.fragment_container, takeLessonFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return view;
    }
}
