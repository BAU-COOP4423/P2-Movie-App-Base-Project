package com.yilmazgokhan.movieapp.v2.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yilmazgokhan.movieapp.v2.R;
import com.yilmazgokhan.movieapp.v2.data.movie_search.Result;
import com.yilmazgokhan.movieapp.v2.databinding.ItemMovieBinding;
import com.yilmazgokhan.movieapp.v2.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<com.yilmazgokhan.movieapp.v2.adapter.MovieAdapter.MovieViewHolders> {

    private List<Result> movies;
    private Context context;
    private ItemClickListener itemClickListener;

    /**
     * Constructor for MovieAdapter class
     *
     * @param context as Context for Glide
     */
    public MovieAdapter(Context context) {
        this.context = context;
        this.movies = new ArrayList<>();
    }

    /**
     * Setting the list data & notify the adapter for render list
     *
     * @param movies as List<Result>
     */
    public void setAdapterList(List<Result> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        this.notifyDataSetChanged();
    }

    /**
     * Default onCreate method.
     * Connect Adapter & layout
     */
    @NotNull
    @Override
    public MovieViewHolders onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolders(binding);
    }

    /**
     * Prepare row item with data
     */
    @Override
    public void onBindViewHolder(MovieViewHolders holder, int position) {
        Result movie = movies.get(position);
        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            String posterPath = Constants.POSTER_BASE_PATH + movie.getPosterPath();
            Glide.with(context)
                    .load(posterPath)
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(holder.binding.ivPoster);
        }
        if (!TextUtils.isEmpty(movie.getOriginalTitle()))
            holder.binding.tvTitle.setText(movie.getOriginalTitle());
        if (!TextUtils.isEmpty(movie.getPopularity().toString())) {
            String popularity = "Popularity: " + movie.getPopularity().toString();
            holder.binding.tvPopularity.setText(popularity);
        }
    }

    private Result getItem(int pos) {
        return movies.get(pos);
    }

    /**
     * @return the data list count
     */
    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    //region Click Listener

    /**
     * This method is used for te interface in the adapter to communicate with the View
     *
     * @param itemClickListener as ItemClickListener
     */
    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * This interface is used to catch the item click event
     */
    public interface ItemClickListener {
        void onClick(int pos, Result movie);
    }
    //endregion

    public class MovieViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemMovieBinding binding;

        public MovieViewHolders(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(getAdapterPosition(), getItem(getAdapterPosition()));
        }
    }
}