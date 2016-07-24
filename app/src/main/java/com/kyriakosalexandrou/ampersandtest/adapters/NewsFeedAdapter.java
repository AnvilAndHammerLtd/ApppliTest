package com.kyriakosalexandrou.ampersandtest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyriakosalexandrou.ampersandtest.R;
import com.kyriakosalexandrou.ampersandtest.models.NewsFeed;

import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder> {
    private List<NewsFeed> mNewsFeeds;
    private Context mContext;
    private NewsFeedAdapterCallback mNewsFeedAdapterCallback;

    public NewsFeedAdapter(List<NewsFeed> newsFeeds, Context context, NewsFeedAdapterCallback newsFeedAdapterCallback) {
        mNewsFeeds = newsFeeds;
        mContext = context;
        mNewsFeedAdapterCallback = newsFeedAdapterCallback;
    }

    @Override
    public NewsFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_feed, null);
        return new NewsFeedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NewsFeedViewHolder holder, final int position) {
        NewsFeed newsFeed = mNewsFeeds.get(position);
        updateViewsContent(holder, newsFeed);
        setListeners(holder, newsFeed, position);
    }

    private void updateViewsContent(NewsFeedViewHolder holder, NewsFeed newsFeed) {
        holder.mImage.setImageResource(newsFeed.getImageRes());
        holder.mTitle.setText(newsFeed.getTitle());

        int favouriteIconRes = newsFeed.isFavourite() ? R.drawable.like_button_sel : R.drawable.like_button_unsel;
        holder.mFavourite.setImageResource(favouriteIconRes);

        holder.mLikesCounter.setText(mContext.getResources().getString(R.string.single, newsFeed.getAggregates().getLikesCounter()));
        holder.mCommentsCounter.setText(mContext.getResources().getString(R.string.single, newsFeed.getAggregates().getCommentsCounter()));
    }

    private void setListeners(final NewsFeedViewHolder holder, final NewsFeed newsFeed, final int position) {

        holder.mMainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsFeedAdapterCallback.onNewsFeedItemClicked(position);
            }
        });

        holder.mFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newsFeed.isFavourite()) {
                    newsFeed.getAggregates().DecreaseLike();
                    newsFeed.setFavourite(false);

                    holder.mFavourite.setImageResource(R.drawable.like_button_unsel);
                } else {
                    newsFeed.getAggregates().incrementLike();
                    newsFeed.setFavourite(true);

                    holder.mFavourite.setImageResource(R.drawable.like_button_sel);
                }

                holder.mLikesCounter.setText(mContext.getResources().getString(R.string.single, newsFeed.getAggregates().getLikesCounter()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsFeeds.size();
    }

    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        public View mMainContainer;
        public ImageView mImage;
        public ImageButton mFavourite;
        public TextView mTitle;
        public TextView mLikesCounter;
        public TextView mCommentsCounter;

        public NewsFeedViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.main_image);
            mFavourite = (ImageButton) itemView.findViewById(R.id.favourite);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mLikesCounter = (TextView) itemView.findViewById(R.id.likes_counter);
            mCommentsCounter = (TextView) itemView.findViewById(R.id.comment_counter);
            mMainContainer = itemView.findViewById(R.id.main_container);
        }
    }

    public interface NewsFeedAdapterCallback {
        void onNewsFeedItemClicked(int position);
    }
}
