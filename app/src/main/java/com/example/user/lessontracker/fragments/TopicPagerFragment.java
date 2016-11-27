package com.example.user.lessontracker.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lessontracker.R;
import com.example.user.lessontracker.database.LessonTrackerDbHelper;
import com.example.user.lessontracker.models.Topic;

public class TopicPagerFragment extends Fragment {

    LessonTrackerDbHelper mDbHelper;
    Topic mTopic;

    ViewPager mViewPager;
    TabLayout mTabLayout;
    TextView mSubjectTitleTextView;
    TextView mTitleTextView;
    TextView mDetailTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_pager, container, false);

        mDbHelper = new LessonTrackerDbHelper(getActivity());
        Bundle receivedArgs = getArguments();
        long topicId = receivedArgs.getLong(TopicFragment.TOPIC_ID);
        String subjectTitle = receivedArgs.getString(SubjectFragment.SUBJECT_TITLE);
        mTopic = mDbHelper.findTopic(topicId);

        final TopicFragment topicFragment = new TopicFragment();
        Bundle topicArgs = new Bundle();
        topicArgs.putLong(TopicFragment.TOPIC_ID, topicId);
        topicFragment.setArguments(topicArgs);

        final TopicStatsFragment topicStatsFragment = new TopicStatsFragment();
        topicStatsFragment.setArguments(topicArgs);

        mSubjectTitleTextView = (TextView) view.findViewById(R.id.topic_view_pager_subject_title);
        mSubjectTitleTextView.setText(subjectTitle);

        mTitleTextView = (TextView) view.findViewById(R.id.topic_view_pager_topic_title);
        mTitleTextView.setText(mTopic.getTitle());

        mDetailTextView = (TextView) view.findViewById(R.id.topic_view_pager_topic_detail);
        mDetailTextView.setText(mTopic.getDetail());

        mViewPager = (ViewPager) view.findViewById(R.id.topic_view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return (position == 0) ? topicFragment : topicStatsFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
               return (position == 0) ? "Learning Objectives" : "Statistics";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mTabLayout = (TabLayout) view.findViewById(R.id.topic_view_pager_tab);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }
}
