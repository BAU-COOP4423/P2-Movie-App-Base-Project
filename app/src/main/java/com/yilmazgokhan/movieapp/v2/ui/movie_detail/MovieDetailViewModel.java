package com.yilmazgokhan.movieapp.v2.ui.movie_detail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yilmazgokhan.movieapp.v2.data.movie_detail.MovieDetailModel;
import com.yilmazgokhan.movieapp.v2.service.ClientMovie;
import com.yilmazgokhan.movieapp.v2.service.IRequest;
import com.yilmazgokhan.movieapp.v2.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yilmazgokhan.movieapp.v2.util.Constants.CUSTOM_TAG;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieDetailModel> movieDetail = new MutableLiveData<>();

    /**
     * Send HTTP Request for getting the movie detail via movieId
     */
    public void getMovieDetail(int movieId) {
        IRequest request = ClientMovie.getApiClient().create(IRequest.class);
        Call<MovieDetailModel> call = request.getMovieDetail(movieId, Constants.TEST_API_KEY, Constants.API_LANGUAGE);
        call.enqueue(new Callback<MovieDetailModel>() {
            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                if (response.isSuccessful()) {
                    movieDetail.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                Log.d(CUSTOM_TAG, "onFailure: ");
            }
        });
    }

    /**
     * This method is used to observe the Live Data for movie detail
     */
    public MutableLiveData<MovieDetailModel> getMovieDetail() {
        return movieDetail;
    }
}
