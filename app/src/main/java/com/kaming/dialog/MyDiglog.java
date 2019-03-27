package com.kaming.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class MyDiglog extends Dialog {

    public MyDiglog(Context context) {
        super(context);
        setContentView(R.layout.dialog_diy);

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
