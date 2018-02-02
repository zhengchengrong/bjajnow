package com.threehmis.bjaj.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.threehmis.bjaj.R;
import com.threehmis.bjaj.utils.ScreenUtil;


/**
 * @author CH
 */
public class AddOrEditDialog extends Dialog implements View.OnClickListener {

    Activity context;

    Lisenter lisenter;

    boolean isstop;

    public AddOrEditDialog(Activity context, Lisenter lisenter, boolean isstop) {
        this(context, R.style.enterDialog);
        this.context = context;
        this.lisenter = lisenter;
        this.isstop = isstop;
    }

    public AddOrEditDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addoredit_dialog);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.alpha = 1.0f;
        wl.gravity = Gravity.CENTER;
        wl.width = ScreenUtil.getScreenWidth(context);
        window.setAttributes(wl);
        setCancelable(false);
        setCanceledOnTouchOutside(false);


        TextView add= (TextView) findViewById(R.id.add);
        if (isstop)
        add.setText(" 取消 ");

        findViewById(R.id.edit).setOnClickListener(this);
        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.edit:
                lisenter.Getstats(0);
                dismiss();
                break;
            case R.id.add:
                lisenter.Getstats(1);
                dismiss();
                break;
        }
    }

    public interface Lisenter {

        void Getstats(int data);


    }


}
