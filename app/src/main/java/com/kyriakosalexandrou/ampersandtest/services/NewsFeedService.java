package com.kyriakosalexandrou.ampersandtest.services;

import android.util.Log;

import com.kyriakosalexandrou.ampersandtest.events.NewsFeedEvent;
import com.kyriakosalexandrou.ampersandtest.models.NewsFeed;

import org.greenrobot.eventbus.EventBus;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by Kyriakos on 25/07/2016.
 */
public class NewsFeedService {
    private static final String TAG = NewsFeedService.class.getName();
    private INewsFeedService mService;


    public NewsFeedService(RestAdapter restAdapter) {
        mService = restAdapter.create(INewsFeedService.class);
    }

    public void getNewsFeedRequest(final NewsFeedEvent event) {

        mService.getNewsFeed(
                new Callback<NewsFeed>() {
                    @Override
                    public void success(NewsFeed newsFeed, Response response) {
                        Log.v(TAG, "getNewsFeedRequest success");
                        event.setNewsFeed(newsFeed);
                        EventBus.getDefault().postSticky(event);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v(TAG, "getNewsFeedRequest failure");
                        EventBus.getDefault().postSticky(event.getErrorEvent());
                    }
                }
        );
    }

    public interface INewsFeedService {
        @GET("/default-environment-7p45veqn6g.elasticbeanstalk.com/api/feed?event_id=3&user_id=3&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6XC9cL2RlZmF1bHQtZW52aXJvbm1lbnQtN3A0NXZlcW42Zy5lbGFzdGljYmVhbnN0YWxrLmNvbVwvYXBpXC9hdXRoZW50aWNhdGUiLCJpYXQiOjE0NjkxMTk5MTAsImV4cCI6MTQ3MDMyOTUxMCwibmJmIjoxNDY5MTE5OTEwLCJqdGkiOiJlMThmNmRjNzQyMWJlZjgwYjIzN2FhMjE1N2QyYzQwYSJ9.PjQvbjoAoJRETMpJ-3_iA8od8LN03UI2eNBJn7zbVQg")
        void getNewsFeed(Callback<NewsFeed> response);
    }
}
