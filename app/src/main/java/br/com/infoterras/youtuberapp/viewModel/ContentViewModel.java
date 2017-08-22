package br.com.infoterras.youtuberapp.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.AbstractList;
import java.util.ArrayList;

import br.com.infoterras.youtuberapp.BR;
import br.com.infoterras.youtuberapp.R;
import br.com.infoterras.youtuberapp.adapter.RecyclerBindingAdapter;
import br.com.infoterras.youtuberapp.model.Recycler;
import br.com.infoterras.youtuberapp.model.YouTubeItem;
import br.com.infoterras.youtuberapp.model.YouTubeResponse;
import br.com.infoterras.youtuberapp.network.ConsumerService;
import br.com.infoterras.youtuberapp.ui.DetailActivity;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class ContentViewModel implements ConsumerService.OnTaskCompleted<YouTubeResponse>, RecyclerBindingAdapter.OnItemClickListener<YouTubeItem>{

    private static final String TAG = ContentViewModel.class.getSimpleName();
    private RecyclerBindingAdapter<YouTubeItem> adapter;
    public Recycler recycler;
    private Context context;

    public ContentViewModel(final Context context) {
        this.context = context;
        this.recycler = new Recycler();

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getContent(context.getString(R.string.channel_id), -1);

        initRecycler();
    }

    private void initRecycler() {
        adapter = getAdapter();
        adapter.setOnItemClickListener(this);

        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<YouTubeItem> getAdapter() {
        return new RecyclerBindingAdapter<>(R.layout.item_content_list, BR.viewModel, new ArrayList<YouTubeItem>());
    }

    @Override
    public void onSuccess(YouTubeResponse response, int code, int requestCode) {

        if (code != 200) return;

        adapter.setList((AbstractList<YouTubeItem>) response.getItems());
    }

    @Override
    public void onFailure(Throwable error, int requestCode) {
        Log.e(TAG, "onFailure: ", error);
    }

    @Override
    public void onItemClick(int position, View view, YouTubeItem item) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation((AppCompatActivity) context, view.findViewById(R.id.img_video), "video").toBundle();
        context.startActivity(new Intent(context, DetailActivity.class).putExtra("extra", item), bundle);
    }
}
