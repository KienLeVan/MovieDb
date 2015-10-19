package training.kienle.eastagile.vn.moviedb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import training.kienle.eastagile.vn.moviedb.model.Result;

import java.util.ArrayList;

/**
 * Created by eastagile50 on 10/19/15.
 */
public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.ViewHolder> {

    private static final String IMAGE_URL_PREFIX = "http://image.tmdb.org/t/p/w92";

    private ArrayList<Result> mDataSet;
    Context mContext;

    @Override
    public MoviePosterAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.poster_gridview_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MoviePosterAdapter.ViewHolder viewHolder, int i) {
        String imageUrl = IMAGE_URL_PREFIX + mDataSet.get(i).getPosterPath();
        Glide.with(mContext)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.posterImage);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;
        public ViewHolder(View view){
            super(view);
            posterImage = (ImageView)view.findViewById(R.id.gridview_poster_image);
        }
    }

    public MoviePosterAdapter(ArrayList<Result> dataSet, Context context) {

        mDataSet = dataSet;
        mContext = context;
    }

}
