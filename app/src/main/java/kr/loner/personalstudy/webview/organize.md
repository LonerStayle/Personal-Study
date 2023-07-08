# 공부 참고 문서 
[웹컨텐츠 설명](https://developer.android.com/develop/ui/views/layout/webapps)   
[웹뷰 설명 공식문서](https://developer.android.com/develop/ui/views/layout/webapps/webview#custom-url?hl=ko)   
[웹뷰 객체 관리_공식문서](https://developer.android.com/guide/webapps/managing-webview?hl=ko#safe-browsing)   
[웹뷰 알고쓰기 블로그](https://velog.io/@pachuho/Android-WebView-%EC%95%8C%EA%B3%A0-%EC%93%B0%EA%B8%B0)   

###### 복습 필요한 부분 정리
- WebViewClient 는 웹 페이지 로딩 시 생기는 콜백 처리, WebChromeClient 는 웹 페이지 내부에서 일하는 콜백 처리   
- Settings 는 웹뷰 전반적인 Setting 및 자바스크립트 관련 셋팅(자바스크립트 허용,window.open() 허용 등등..) 

### 개인 궁금증 & 답변
###### Q.  
webViewClient 의 onReceivedSslError() 는 ssl 에러를 받았을 시에 사용 된다 여기서 ssl 이란 무엇인가?   
   
A.    
SSL(Secure Sockets Layer) 인증서는 웹사이트의 신원을 확인하는 디지털 인증서이며 SSL 기술을 이용하여 서버에 전송되는 정보를 암호화합니다.   
   
SSL 인증서에는 다음 정보가 포함됩니다.
- 인증서 보유자의 이름    
- 인증서의 일련 번호 및 만료일   
- 인증서 보유자의 공개 키 사본   
- 인증서 발행 기관의 디지털 서명


###### Q.  
Console 메시지를 로그캣에서 확인 하는 법?   
   
A.   
WebChromeClient 의 onConsoleMessage 를 사용해서 확인 할 수 있다.   
   
```
myWebView.webChromeClient = object : WebChromeClient() {

    override fun onConsoleMessage(message: ConsoleMessage): Boolean {
        Log.d("MyApplication", "${message.message()} -- From line " +
              "${message.lineNumber()} of ${message.sourceId()}")
        return true
    }
}
```