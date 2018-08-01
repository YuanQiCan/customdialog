package test.leo.testpopupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by leo on 2018/8/1.
 */
public class CustomPopupWindow extends PopupWindow {

    Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;

    public CustomPopupWindow(Context context, Context mContext) {
        super(context);
        this.mContext = mContext;
    }
}
