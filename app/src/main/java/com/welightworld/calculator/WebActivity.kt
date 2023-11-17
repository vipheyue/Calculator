package com.welightworld.calculator

import android.net.http.SslError
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.webkit.*
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initView()
    }

    private fun initView() {


        var stringTitle = intent.getStringExtra("title")
        var stringUrl = intent.getStringExtra("url")
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = stringTitle
//        webView.webViewClient = MyWebViewClient()
//        webView!!.webChromeClient = MyWebChromeClient()
//        webView.loadUrl(stringUrl)
    }

    private inner class MyWebViewClient : WebViewClient() {

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            handler?.cancel();
        }


        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
        }


        override fun onPageFinished(view: WebView?, url: String?) {

            super.onPageFinished(view, url)

        }
    }


    private inner class MyWebChromeClient : WebChromeClient() {

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
        }
    }

    //改写物理按键——返回的逻辑
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
//                    if (webView!!.canGoBack()) {
//                        webView!!.goBack()
//                    } else {
//                        finish()
//                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
