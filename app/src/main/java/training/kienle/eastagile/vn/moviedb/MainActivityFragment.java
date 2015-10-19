package training.kienle.eastagile.vn.moviedb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import retrofit.*;
import training.kienle.eastagile.vn.moviedb.API.MovieDbApi;
import training.kienle.eastagile.vn.moviedb.model.MovieDataModel;
import training.kienle.eastagile.vn.moviedb.model.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String API_KEY = Constants.MOVIEDB_API_KEY;

    TextView textView;
    ImageView imageView;
    RecyclerView mRecyclerView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        textView = (TextView)rootView.findViewById(R.id.showText);
        imageView = (ImageView)rootView.findViewById(R.id.imageView);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.gridview_poster);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        MovieDbApi movieDbApi = retrofit.create(MovieDbApi.class);

        Date today = new Date();

        Call<MovieDataModel> call = movieDbApi.getMoviesData("2015-09-25", API_KEY);

        call.enqueue(new Callback<MovieDataModel>(){

            @Override
            public void onResponse(Response<MovieDataModel> response, Retrofit retrofit) {
                int statusCode = response.code();
                MovieDataModel movieDataModel = response.body();
                List<Result> resultList = movieDataModel.getResults();

                /**
                 * Views for test, fetch data from themoviedb.org API
                 */
                String resText = "";
                resText = movieDataModel.getResults().get(0).getOriginalTitle();
                textView.setText(resText);
                /**
                 * Imageview for test, show poster image by Glide
                 */
                String posterUrl = "http://image.tmdb.org/t/p/w780" + movieDataModel.getResults().get(0).getPosterPath();
                Glide.with(getContext())
                        .load(posterUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
                /**
                 * Populate poster images to RecyclerView
                 */
                mRecyclerView.setAdapter(new MoviePosterAdapter(new ArrayList<>(resultList), getActivity()));

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}
