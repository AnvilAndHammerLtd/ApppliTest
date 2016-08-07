package com.kyriakosalexandrou.ampersandtest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyriakosalexandrou.ampersandtest.R;
import com.kyriakosalexandrou.ampersandtest.models.Datum;
import com.kyriakosalexandrou.ampersandtest.models.LikesCount;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder> {
    private Context mContext;
    private ArrayList<Datum> mDatums = new ArrayList<>();
    private NewsFeedAdapterCallback mNewsFeedAdapterCallback;

    public NewsFeedAdapter(Context context, NewsFeedAdapterCallback newsFeedAdapterCallback) {
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
        Datum datum = mDatums.get(position);
        updateViewsContent(holder, datum);
        setListeners(holder, datum, position);
    }

    private void updateViewsContent(final NewsFeedViewHolder holder, final Datum datum) {
        Picasso.with(mContext).load(datum.getImageUrl()).into(holder.mImage);
        holder.mTitle.setText(datum.getUser().getUsername());

        setUserLikedIcon(holder, datum);

        holder.mLikesCounter.setText(mContext.getResources().getString(R.string.single_number, datum.getLikesCount().size()));
        holder.mCommentsCounter.setText(mContext.getResources().getString(R.string.single_number, datum.getCommentsCount().size()));
    }

    private void setUserLikedIcon(NewsFeedViewHolder holder, Datum datum) {
        int userLikedIconRes = datum.getUserLiked() ? R.drawable.like_button_sel : R.drawable.like_button_unsel;
        holder.mUserLiked.setImageResource(userLikedIconRes);
    }

    private void setListeners(final NewsFeedViewHolder holder, final Datum datum, final int position) {

        holder.mMainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsFeedAdapterCallback.onNewsFeedItemClicked(position);
            }
        });

        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentVisibleTitle = holder.mTitle.getText().toString();
                String username = datum.getUser().getUsername();
                String fullName = getFullName();

                String newTitle = currentVisibleTitle.equals(username) ? fullName : username;
                holder.mTitle.setText(newTitle);
            }

            private String getFullName() {
                String firstName = datum.getUser().getFirstName().toString();
                String lastName = datum.getUser().getLastName().toString();
                return mContext.getResources().getString(R.string.string_space_string, firstName, lastName);
            }
        });

        holder.mUserLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                not sure why the server returns a list of them as there is always only one LikesCount object.
                Therefore, we assume that there is always only one LikesCount object
                 */
                LikesCount likesCount = datum.getLikesCount().get(0);
                int currentLikes = likesCount.getLikes();

                if (datum.getUserLiked()) {
                    datum.setUserLiked(false);
                    likesCount.setLikes(currentLikes - 1);
                    holder.mUserLiked.setImageResource(R.drawable.like_button_unsel);
                } else {
                    datum.setUserLiked(true);
                    likesCount.setLikes(currentLikes + 1);
                    holder.mUserLiked.setImageResource(R.drawable.like_button_sel);
                }

                holder.mLikesCounter.setText(mContext.getResources().getString(R.string.single_number, likesCount.getLikes()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatums.size();
    }

    public void clear() {
        mDatums.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Datum> datums) {
        mDatums.addAll(datums);
        notifyDataSetChanged();
    }

    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        public View mMainContainer;
        public ImageView mImage;
        public ImageButton mUserLiked;
        public TextView mTitle;
        public TextView mLikesCounter;
        public TextView mCommentsCounter;

        public NewsFeedViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.main_image);
            mUserLiked = (ImageButton) itemView.findViewById(R.id.user_liked);
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
