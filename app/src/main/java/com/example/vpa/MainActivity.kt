package com.example.vpa

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Displaying videos from local storage
        val videoView: VideoView = findViewById(R.id.videoview1)
        videoView.setVideoPath(
                "android.resource://"
                +packageName+"/"
                +R.raw.tori);

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()

        // Displaying videos from the internet
        val webView : WebView = findViewById(R.id.webview)
        val video: String ="https://youtu.be/p6uXFreFPyA"
        webView.loadData(video.toString(), "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccess = true
        webView.webChromeClient = CustomWebChromeClient(this)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(video)


        // Media Controller
//        val mediaController2 = MediaController(this)
//        mediaController2.setAnchorView(videoView2)
//        mediaController2.setMediaPlayer(videoView2)
//        videoView2.setMediaController(mediaController2)
//        videoView2.requestFocus()
//        videoView2.start()

    }
}