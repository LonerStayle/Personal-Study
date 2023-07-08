import android.content.Context
import android.net.http.SslError
import android.webkit.*


class TestWebView(context:Context):WebView(context){
    init {
        /**튜닝 옵션 시리즈*/
        this.settings.cacheMode = WebSettings.LOAD_NO_CACHE

    }
}

private class WebViewClientClass : WebViewClient() {
    // SSL 인증서 무시
    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        handler.proceed()
    }
}

private class WebChromeClientClass : WebChromeClient() {
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        val log = consoleMessage?.message()

        return true
    }
}