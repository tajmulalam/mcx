package com.mcx.mannyvinny.mcx.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mcx.mannyvinny.mcx.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity implements AdvancedWebView.Listener {
    private AdvancedWebView mWebView;

    private static final String ROOT_URL = "http://www.mcxliverates.in/";
//    private static final String ROOT_URL = "https://flightmantra.com/b2c/";
private RelativeLayout rlLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl(ROOT_URL);
        rlLoading=findViewById(R.id.rlLoading);

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {

        showProgress();
    }

    @Override
    public void onPageFinished(String url) {
        mWebView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('sidebarmenu')[0].style.display='none'; " +
                "document.getElementsByClassName('sidebarbtn center-block text-center')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-12')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-12')[1].style.display='none'; " +
                "document.getElementsByClassName('col-md-3')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-5 col-sm-5 col-xs-12 mt20')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-5 col-sm-5 col-xs-12')[1].style.display='none'; " +
                "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12 text-left')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-12 top-marg')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12 mt20 mt-30')[0].style.display='none'; " +
                "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12 mt20')[1].style.display='none'; " +
                "document.getElementsByClassName('col-md-12')[2].style.display='none'; " +
                "document.getElementsByClassName('sharethis-inline-share-buttons st-left st-has-labels  st-inline-share-buttons st-animated')[0].style.display='none'; " +
                "} )()");
        hideProgress();
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        hideProgress();
        Toast.makeText(this, "Called", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    private KProgressHUD hud;

    void showProgress() {
        hud = KProgressHUD.create(MainActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
//                .setDetailsLabel("Downloading data")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        rlLoading.setVisibility(View.VISIBLE);
    }

    void hideProgress() {
        if (hud != null && hud.isShowing()) {
            hud.dismiss();
            rlLoading.setVisibility(View.GONE);
            mWebView.loadUrl("javascript:(function() { " +
                    "document.getElementsByClassName('sidebarmenu')[0].style.display='none'; " +
                    "document.getElementsByClassName('sidebarbtn center-block text-center')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12')[1].style.display='none'; " +
                    "document.getElementsByClassName('col-md-3')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-5 col-sm-5 col-xs-12 mt20')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-5 col-sm-5 col-xs-12')[1].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12 text-left')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12 top-marg')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12 mt20 mt-30')[0].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12 col-sm-5 col-xs-12 mt20')[1].style.display='none'; " +
                    "document.getElementsByClassName('col-md-12')[2].style.display='none'; " +
                    "document.getElementsByClassName('sharethis-inline-share-buttons st-left st-has-labels  st-inline-share-buttons st-animated')[0].style.display='none'; " +
                    "} )()");
        }
    }
}
