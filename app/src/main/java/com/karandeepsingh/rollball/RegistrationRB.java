package com.karandeepsingh.rollball;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class RegistrationRB extends Fragment {
    WebView browser2;
    ProgressBar bar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_registration_rb,container,false);
        bar = view.findViewById(R.id.progressBar);
        browser2 = view.findViewById(R.id.tab4);
        browser2.setWebViewClient(new MyWebViewClient());


        WebSettings webSettings = browser2.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        browser2.loadUrl("https://rollballregistration.org/");
        return view;
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
            bar.setVisibility(View.VISIBLE);
            // ^^^ use it as it is

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            bar.setVisibility(View.GONE);
            // ^^^ use it as it is
            super.onPageFinished(view, url);
        }
    }
}
