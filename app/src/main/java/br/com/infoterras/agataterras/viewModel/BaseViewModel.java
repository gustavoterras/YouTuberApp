package br.com.infoterras.agataterras.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by gustavo.filho on 02/12/17.
 */

public class BaseViewModel {

    private Context context;
    private Activity activity;

    public BaseViewModel(Context context) {
        this.activity = (Activity) context;
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public Context getContext() {
        return context;
    }

    public void startActivityForResult(Intent intent, int requestCode){
        getActivity().startActivityForResult(intent, requestCode);
    }
}
