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

public class TopicPagerFragment extends Fragment {

    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_pager, container, false);

        Bundle receivedArgs = getArguments();
        long topicId = receivedArgs.getLong(TopicFragment.TOPIC_ID);
        String subjectTitle = receivedArgs.getString(SubjectFragment.SUBJECT_TITLE);

        final TopicFragment topicFragment = new TopicFragment();
        Bundle topicArgs = new Bundle();
        topicArgs.putLong(TopicFragment.TOPIC_ID, topicId);
        topicArgs.putString(SubjectFragment.SUBJECT_TITLE, subjectTitle);
        topicFragment.setArguments(topicArgs);

        final TopicStatsFragment topicStatsFragment = new TopicStatsFragment();

        mViewPager = (ViewPager) view.findViewById(R.id.topic_view_pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return (position == 0) ? topicFragment : topicStatsFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
               return (position == 0) ? "Details" : "Statistics";
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
