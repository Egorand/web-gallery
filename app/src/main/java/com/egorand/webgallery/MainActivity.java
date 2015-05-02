package com.egorand.webgallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_IMAGES_URL = "https://www.google.com/imghp";

    @InjectView(R.id.webview) CustomWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initWebView();
    }

    private void initWebView() {
        webView.setOnImageSelectedListener(url -> returnSelectedUrl(url));
        webView.loadUrl(GOOGLE_IMAGES_URL);
    }

    private void returnSelectedUrl(String url) {
        Uri resultUri = Uri.parse(url);
        Intent resultIntent = new Intent();
        resultIntent.setData(resultUri);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
