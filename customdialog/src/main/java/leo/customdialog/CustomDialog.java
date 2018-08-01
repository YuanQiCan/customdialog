package leo.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * Created by leo on 2018/7/30.
 */
public class CustomDialog extends Dialog implements View.OnClickListener{
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;



    private Context mContext;
    private String content;


    private LinearLayout linearLayout = null;

    private OnCloseListener listener;
    private String posttiveName;
    private String negativeName;
    private String title;

    public CustomDialog(Context context) {
        super(context);
        this.mContext = context;

    }

    public CustomDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public CustomDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

//    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//        this.mContext = context;
//    }


    public CustomDialog setPosttiveName(String posttiveName) {
        this.posttiveName = posttiveName;
        return this;
    }

    public CustomDialog setNegativeName(String negativeName) {
        this.negativeName = negativeName;
        return this;
    }

    public CustomDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        linearLayout = (LinearLayout) findViewById(R.id.custom_dialog_ll);
        setCanceledOnTouchOutside(true);
        initView();



    }


    private void initView()
    {
        contentTxt = (TextView)findViewById(R.id.dialog_content);
        titleTxt = (TextView)findViewById(R.id.dialog_title);

        submitTxt = (TextView)findViewById(R.id.dialog_submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView)findViewById(R.id.dialog_cancel);
        cancelTxt.setOnClickListener(this);

        contentTxt.setText(content);
        if (!TextUtils.isEmpty(posttiveName))
        {
            submitTxt.setText(posttiveName);
        }
        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }

        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }

        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);

        window.setWindowAnimations(R.style.DialogAnimation);
        WindowManager.LayoutParams lp = window.getAttributes();
        linearLayout.measure(0,0);
        lp.width = (int)mContext.getResources().getDisplayMetrics().widthPixels - 20;
        linearLayout.measure(0,0);
        lp.height = linearLayout.getMeasuredHeight();

        lp.alpha = 9f;
        window.setAttributes(lp);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.dialog_cancel) {
            if (listener != null) {
                listener.onClick(this, false);
            }
            this.dismiss();

        } else if (i == R.id.dialog_submit) {
            if (listener != null) {
                listener.onClick(this, true);
            }

        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }

    public interface OnCancelListener{
        void onClick(Dialog dialog, boolean confirm);
    }
}
