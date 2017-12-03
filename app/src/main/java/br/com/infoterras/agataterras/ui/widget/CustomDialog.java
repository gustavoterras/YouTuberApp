package br.com.infoterras.agataterras.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import br.com.infoterras.agataterras.R;

/**
 * Created by gustavo.filho on 03/12/17.
 */

public class CustomDialog extends Dialog {

    public Activity activity;
    public Button no;

    public CustomDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_custom_dialog);

        no = (Button) findViewById(R.id.btn_no);

        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}