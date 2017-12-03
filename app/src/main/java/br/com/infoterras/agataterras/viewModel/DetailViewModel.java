package br.com.infoterras.agataterras.viewModel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import br.com.infoterras.agataterras.BR;
import br.com.infoterras.agataterras.R;
import br.com.infoterras.agataterras.adapter.RecyclerBindingAdapter;
import br.com.infoterras.agataterras.model.Recycler;
import br.com.infoterras.agataterras.model.YouTubeComment;
import br.com.infoterras.agataterras.model.YouTubeItem;
import br.com.infoterras.agataterras.model.YouTubeStatistics;
import br.com.infoterras.agataterras.network.ConsumerService;
import br.com.infoterras.agataterras.ui.DetailActivity;
import br.com.infoterras.agataterras.ui.widget.CustomDialog;
import br.com.infoterras.agataterras.utils.Constants;

/**
 * Created by gustavoterras on 26/03/17.
 */

public class DetailViewModel implements ConsumerService.OnTaskCompleted<JsonObject>, RecyclerBindingAdapter.OnItemClickListener<YouTubeComment>{

    private static final String TAG = DetailViewModel.class.getSimpleName();
    private final ConsumerService consumerService;
    private RecyclerBindingAdapter<YouTubeComment> adapter;
    public Recycler recycler;
    public YouTubeStatistics statistics;
    public YouTubeItem youTubeItem;
    private LinearLayout progress;
    private Context context;

    public DetailViewModel(final Context context, View view, YouTubeItem youTubeItem) {
        this.progress = (LinearLayout) view.findViewById(R.id.progress_layout);
        this.recycler = new Recycler();
        this.statistics = new YouTubeStatistics();
        this.youTubeItem = youTubeItem;
        this.context = context;

        consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getComment(youTubeItem.getId().getVideoId(), -1);
        consumerService.getVideoDetail(youTubeItem.getId().getVideoId(), 0);

        initRecycler();
    }

    private void initRecycler() {
        adapter = getAdapter();
        adapter.setOnItemClickListener(this);

        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<YouTubeComment> getAdapter() {
        return new RecyclerBindingAdapter<>(R.layout.item_comment_list, BR.viewModel, new ArrayList<YouTubeComment>());
    }

    @Override
    public void onSuccess(JsonObject response, int code, int requestCode) {
        if (code != 200)  return;

        if(requestCode == -1) {
            ArrayList<YouTubeComment> comments = new ArrayList<>();

            JsonArray jsonArray = response.getAsJsonArray("items");

            for (JsonElement element : jsonArray) {
                YouTubeComment comment = new Gson().fromJson(element.getAsJsonObject().getAsJsonObject("snippet").getAsJsonObject("topLevelComment").getAsJsonObject("snippet").toString(), YouTubeComment.class);
                if (comment != null)
                    comments.add(comment);
            }

            adapter.setList(comments);
            progress.setVisibility(comments.isEmpty() ? View.VISIBLE : View.GONE);
        }else if(requestCode == 0){
            JsonArray jsonArray = response.getAsJsonArray("items");
            if(jsonArray.size() > 0) {
                YouTubeStatistics youTubeStatistics = new Gson().fromJson(jsonArray.get(0).getAsJsonObject().getAsJsonObject("statistics").toString(), YouTubeStatistics.class);
                statistics.setViewCount(youTubeStatistics.getViewCount());
                statistics.setLikeCount(youTubeStatistics.getLikeCount());
                statistics.setDislikeCount(youTubeStatistics.getDislikeCount());
            }
        }else if(requestCode == 1){
            Log.e(TAG, "onSuccess: ");
        }
}

    @Override
    public void onFailure(Throwable error, int requestCode) {
        Log.e(TAG, "onFailure: ");
    }

    @Override
    public void onItemClick(int position, View view, YouTubeComment item) {

    }

    public void doShare(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, Constants.URL_VIDEO + youTubeItem.getId().getVideoId());
        context.startActivity(Intent.createChooser(shareIntent, "Compartilhe meu vídeo"));    }

    public void doComment(){
        CustomDialog dialog = new CustomDialog(((DetailActivity) context));
        dialog.show();

        Button yes = (Button) dialog.findViewById(R.id.btn_yes);

        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                consumerService.addComment(context.getString(R.string.channel_id), youTubeItem.getId().getVideoId(), "legal", 1);
            }
        });
    }
}
