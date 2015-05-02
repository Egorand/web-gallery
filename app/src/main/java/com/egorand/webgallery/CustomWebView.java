package com.egorand.webgallery;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebView extends WebView {

    private static final int ID_COPY_LINK = 0x01;

    private OnImageSelectedListener onImageSelectedListener;

    public CustomWebView(Context context) {
        super(context);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
        final HitTestResult result = getHitTestResult();
        if (result.getType() == HitTestResult.IMAGE_TYPE || result.getType() == HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
            menu.add(0, ID_COPY_LINK, 0, R.string.select).setOnMenuItemClickListener(item -> {
                if (onImageSelectedListener != null) {
                    onImageSelectedListener.onImageSelected(result.getExtra());
                }
                return true;
            });
        }
    }

    public void setOnImageSelectedListener(OnImageSelectedListener onImageSelectedListener) {
        this.onImageSelectedListener = onImageSelectedListener;
    }

    public interface OnImageSelectedListener {
        void onImageSelected(String url);
    }
}
