package com.kyriakosalexandrou.ampersandtest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyriakosalexandrou.ampersandtest.R;
import com.kyriakosalexandrou.ampersandtest.Util;
import com.kyriakosalexandrou.ampersandtest.events.ErrorEvent;
import com.kyriakosalexandrou.ampersandtest.events.NewsFeedEvent;
import com.kyriakosalexandrou.ampersandtest.models.NewsFeed;
import com.kyriakosalexandrou.ampersandtest.services.NewsFeedService;
import com.kyriakosalexandrou.ampersandtest.ui.activities.BaseActivity;
import com.kyriakosalexandrou.ampersandtest.ui.adapters.NewsFeedAdapter;
import com.kyriakosalexandrou.ampersandtest.ui.decorations.VerticalSpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Kyriakos on 25/06/2016.
 */
public class NewsFeedFragment extends Fragment implements NewsFeedAdapter.NewsFeedAdapterCallback {
    public static final String TAG = NewsFeedFragment.class.getName();
    private static final int VERTICAL_ITEM_SPACE = 40;
    private final NewsFeedService mNewsFeedService = new NewsFeedService(BaseActivity.REST_ADAPTER);

    private RecyclerView mNewsFeedRecycler;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private NewsFeed mNewsFeed;
    private NewsFeedAdapter mNewsFeedAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        bindViews(view);

        mNewsFeedAdapter = new NewsFeedAdapter(getContext(), this);
        setUpNewsFeedRecycler();
        setUpSwipeRefreshLayout();
        requestNewsFeedData();
        return view;
    }

    private void bindViews(View view) {
        mNewsFeedRecycler = (RecyclerView) view.findViewById(R.id.news_feed);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
    }

    private void requestNewsFeedData() {
        ErrorEvent errorEvent = new ErrorEvent(getString(R.string.request_failure_news_feed));
        mNewsFeedService.getNewsFeedRequest(new NewsFeedEvent(errorEvent));
    }

    private void setUpNewsFeedRecycler() {
        mNewsFeedRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewsFeedRecycler.setHasFixedSize(true);
        mNewsFeedRecycler.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mNewsFeedRecycler.setAdapter(mNewsFeedAdapter);
    }

    private void setUpSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                requestNewsFeedData();
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                R.color.bondi_blue,
                R.color.mine_shaft
        );
    }

    @Subscribe
    public void onEventMainThread(NewsFeedEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        mSwipeRefreshLayout.setRefreshing(false);
        updateNewsFeed(event.getNewsFeed());
    }

    private void updateNewsFeed(NewsFeed newsFeed) {
        mNewsFeed = newsFeed;
        mNewsFeedAdapter.clear();
        mNewsFeedAdapter.addAll(mNewsFeed.getData());
    }

    @Subscribe
    public void onEventMainThread(ErrorEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        mSwipeRefreshLayout.setRefreshing(false);
        Util.showToastMessageCentered(getContext(), event.getErrorMessage());
    }

    @Override
    public void onNewsFeedItemClicked(int position) {
        mNewsFeedRecycler.smoothScrollToPosition(position);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}