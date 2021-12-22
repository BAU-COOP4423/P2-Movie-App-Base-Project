package com.yilmazgokhan.movieapp.v2.ui.movie_search;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yilmazgokhan.movieapp.v2.data.movie_search.Result;
import com.yilmazgokhan.movieapp.v2.data.movie_search.SearchModel;
import com.yilmazgokhan.movieapp.v2.service.ClientMovie;
import com.yilmazgokhan.movieapp.v2.service.IRequest;
import com.yilmazgokhan.movieapp.v2.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yilmazgokhan.movieapp.v2.util.Constants.CUSTOM_TAG;

public class MovieSearchViewModel extends ViewModel {

    private MutableLiveData<List<Result>> searchList = new MutableLiveData<>();
    private MutableLiveData<Boolean> searchControl = new MutableLiveData<>();

    /**
     * Null check
     *
     * @param query as String
     */
    public void search(String query) {
        if (!query.isEmpty()) {
            searchControl.postValue(false);
            searchMovies(query);
        } else
            searchControl.postValue(true);
    }

    /**
     * Send HTTP Request for searching movies
     */
    private void searchMovies(String query) {
        IRequest request = ClientMovie.getApiClient().create(IRequest.class);
        Call<SearchModel> call = request.searchMovie(Constants.TEST_API_KEY, query);
        call.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    searchList.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Log.d(CUSTOM_TAG, "onFailure: ");
            }
        });
    }

    /**
     * This method is used to observe the Live Data for search
     */
    public MutableLiveData<List<Result>> getSearchList() {
        return searchList;
    }

    /**
     * This method is used to observe the Live Data for null check
     */
    public MutableLiveData<Boolean> getSearchControl() {
        return searchControl;
    }
}