package com.kyriakosalexandrou.ampersandtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kyriakosalexandrou.ampersandtest.adapters.NewsFeedAdapter;
import com.kyriakosalexandrou.ampersandtest.decorations.VerticalSpaceItemDecoration;
import com.kyriakosalexandrou.ampersandtest.models.NewsFeed;
import com.kyriakosalexandrou.ampersandtest.models.SocialAggregates;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsFeedAdapter.NewsFeedAdapterCallback {
    private static final int VERTICAL_ITEM_SPACE = 40;
    private RecyclerView mNewsFeedRecycler;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNewsFeedRecycler = (RecyclerView) findViewById(R.id.news_feed);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(getNewsFeedsData(), this, this);
        setUpNewsFeedRecycler(newsFeedAdapter);
        setUpSwipeRefreshLayout(newsFeedAdapter);
    }

    private void setUpNewsFeedRecycler(RecyclerView.Adapter newsFeedAdapter) {
        mNewsFeedRecycler.setLayoutManager(new LinearLayoutManager(this));
        mNewsFeedRecycler.setHasFixedSize(true);
        mNewsFeedRecycler.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mNewsFeedRecycler.setAdapter(newsFeedAdapter);
    }

    private void setUpSwipeRefreshLayout(final NewsFeedAdapter newsFeedAdapter) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsFeedAdapter.clear();
                newsFeedAdapter.addAll(getRefreshedNewsFeedsData());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                R.color.bondi_blue,
                R.color.mine_shaft
        );
    }

    @NonNull
    private ArrayList<NewsFeed> getNewsFeedsData() {
        ArrayList<NewsFeed> newsFeed = new ArrayList<>();

        newsFeed.add(new NewsFeed(R.drawable.zpark, "Zpark", false, new SocialAggregates(16, 0)));
        newsFeed.add(new NewsFeed(R.drawable.aigia, "Aigia Fuxia", true, new SocialAggregates(699, 0)));
        newsFeed.add(new NewsFeed(R.drawable.cloud, "Cloud Strife", true, new SocialAggregates(1, 0)));
        newsFeed.add(new NewsFeed(R.drawable.kapios, "Kapios Kapiou", false, new SocialAggregates(0, 0)));
        newsFeed.add(new NewsFeed(R.drawable.laimargos, "Sotirakis O Laimargos", false, new SocialAggregates(0, 0)));
        newsFeed.add(new NewsFeed(R.drawable.sailor_moon, "Sailor Moon", false, new SocialAggregates(23, 2)));
        newsFeed.add(new NewsFeed(R.drawable.the_water_world, "The Water World", false, new SocialAggregates(0, 0)));
        newsFeed.add(new NewsFeed(R.drawable.the_forest, "The Forest", true, new SocialAggregates(33, 0)));
        newsFeed.add(new NewsFeed(R.drawable.sonic, "Sonic", false, new SocialAggregates(99, 0)));
        newsFeed.add(new NewsFeed(R.drawable.sotirakis, "Sotirakis", false, new SocialAggregates(69, 0)));
        return newsFeed;
    }

    @NonNull
    private ArrayList<NewsFeed> getRefreshedNewsFeedsData() {
        ArrayList<NewsFeed> newsFeed = new ArrayList<>();

        newsFeed.add(new NewsFeed(R.drawable.laimargos, "Sotirakis O Laimargos", true, new SocialAggregates(1, 1)));
        newsFeed.add(new NewsFeed(R.drawable.sailor_moon, "Sailor Moon", false, new SocialAggregates(654, 231)));
        newsFeed.add(new NewsFeed(R.drawable.the_water_world, "The Water World", false, new SocialAggregates(0, 0)));
        newsFeed.add(new NewsFeed(R.drawable.the_forest, "The Forest", true, new SocialAggregates(300, 0)));
        newsFeed.add(new NewsFeed(R.drawable.sonic, "Sonic", false, new SocialAggregates(999, 0)));
        newsFeed.add(new NewsFeed(R.drawable.sotirakis, "Sotirakis", false, new SocialAggregates(111, 0)));
        return newsFeed;
    }

    @Override
    public void onNewsFeedItemClicked(int position) {
        mNewsFeedRecycler.smoothScrollToPosition(position);
    }
}
