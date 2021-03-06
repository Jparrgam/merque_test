package com.co.test.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.co.test.R;
import com.co.test.model.Result;
import com.co.test.view.ViewMovies;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("JavadocReference")
public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.cardViewMovie> {

    private List<Result> items;
    private ViewMovies viewMovies;
    private Context context;

    public AdapterMovies(List<Result> items, ViewMovies viewMovies, Context context) {
        this.items = items;
        this.viewMovies = viewMovies;
        this.context = context;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public cardViewMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new cardViewMovie(v);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(cardViewMovie holder, int position) {
        Result result = items.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+result.getPosterPath()).into(holder.imageMovie);

        holder.containerMovie.setOnClickListener(v -> viewMovies.onItemSelected(result));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * @author Jaime Gamboa
     * @see android.support.v7.widget.RecyclerView.ViewHolder
     */
    class cardViewMovie extends RecyclerView.ViewHolder {

        @BindView(R.id.container_movie) CardView containerMovie;
        @BindView(R.id.image_moview) ImageView imageMovie;

        cardViewMovie(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
