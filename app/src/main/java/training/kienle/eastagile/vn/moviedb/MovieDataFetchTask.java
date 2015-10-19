package training.kienle.eastagile.vn.moviedb;

import android.os.AsyncTask;

/**
 * Created by kienle on 10/16/15.
 */
public class MovieDataFetchTask extends AsyncTask <String, Void, String[]>{

    private final String LOG_TAG = MovieDataFetchTask.class.getSimpleName();

    @Override
    protected String[] doInBackground(String... strings) {
        return new String[0];
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
    }
}
