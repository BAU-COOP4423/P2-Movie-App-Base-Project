package com.yilmazgokhan.movieapp.v2.service;

import com.yilmazgokhan.movieapp.v2.data.movie_detail.MovieDetailModel;
import com.yilmazgokhan.movieapp.v2.data.movie_search.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IRequest {

    @GET("search/movie?language=en-US&page=1")
    Call<SearchModel> searchMovie(@Query("api_key") String api_key,
                                  @Query("query") String query);


    @GET("movie/{movieId}")
    Call<MovieDetailModel> getMovieDetail(@Path("movieId") int movieId,
                                          @Query("api_key") String api_key,
                                          @Query("language") String language);
}