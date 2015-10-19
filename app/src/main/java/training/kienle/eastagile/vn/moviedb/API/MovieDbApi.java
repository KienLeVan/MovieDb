package training.kienle.eastagile.vn.moviedb.API;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import training.kienle.eastagile.vn.moviedb.model.MovieDataModel;

/**
 * Created by kienle on 10/16/15.
 */
public interface MovieDbApi {

    @GET("/3/discover/movie")
    Call<MovieDataModel> getMoviesData(@Query("primary_release_date.gte") String date, @Query("api_key") String apiKey);
//    Call<MovieDataModel> getMoviesData(@Query("primary_release_date.gte") String date, Callback<MovieDataModel> response);
}
