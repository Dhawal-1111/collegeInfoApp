package com.example.collegeinfoapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SyllabusFragment extends Fragment {

    private WebView webView;

    public SyllabusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);

        setView(view);
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setView(View view){
        webView = view.findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        try {
            String url ="";
            String pdf ="https://firebasestorage.googleapis.com/v0/b/collegeinfoapp-37d86.appspot.com/o/Android%20IoT%20Dashboard%20Project.pdf?alt=media&token=a2a8b1c8-b06a-4c5f-9d1e-47f5793f4c04";
            url= URLEncoder.encode(pdf,"UTF-8");
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}