package com.bbarrett.flikrpickr.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbarrett.flikrpickr.R;
import com.bbarrett.flikrpickr.data.remote.model.PhotoData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * @author bbarrett on 2020-02-25
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {

    public interface ImageViewClickListener {
        void onImageClicked(PhotoData imageData);
    }

    private Context mContext;
    private List<PhotoData> mData = new ArrayList<>();
    private ImageViewClickListener mListener;

    @LayoutRes
    private int mLayoutRes;

    public ImageRecyclerAdapter(Context context, @LayoutRes int layoutResource, ImageViewClickListener listener) {
        this.mContext = context;
        this.mLayoutRes = layoutResource;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(mLayoutRes, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<PhotoData> mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private PhotoData mPhoto;

        private ImageView mFlickrImageView;
        private TextView mFlickrAuthorName;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            mFlickrImageView = itemView.findViewById(R.id.flickrListImageView);
            mFlickrAuthorName = itemView.findViewById(R.id.flickrListAuthor);

            itemView.setOnClickListener(this);
        }

        public void bind(PhotoData photo) {
            mPhoto = photo;

            mFlickrAuthorName.setText(photo.getTitle());

            // TODO - Loading Image
            Glide.with(mContext)
                    .load(photo.getImageUrl())
                    .into(mFlickrImageView);
        }

        @Override
        public void onClick(View view) {
            mListener.onImageClicked(mPhoto);
        }
    }

}
