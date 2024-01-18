package com.example.vpa

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout

class CustomWebChromeClient(private val activity: Activity) : WebChromeClient() {

    private var customView: View? = null
    private var customViewCallback: WebChromeClient.CustomViewCallback? = null
    private var originalOrientation: Int = 0
    private var fullScreenContainer: FrameLayout = activity.findViewById(R.id.fullscreencontainer)

    override fun onHideCustomView() {
        if (customView == null) return

        fullScreenContainer.removeView(customView)
        fullScreenContainer.visibility = View.GONE
        customView = null
        customViewCallback?.onCustomViewHidden()

        activity.requestedOrientation = originalOrientation
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE

    }

    override fun onShowCustomView(view: View, callback: WebChromeClient.CustomViewCallback) {
        if (customView != null) {
            onHideCustomView()
            return
        }

        customView = view
        originalOrientation = activity.requestedOrientation
        customViewCallback = callback

        fullScreenContainer.addView(customView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        fullScreenContainer.visibility = View.VISIBLE
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


    }

}