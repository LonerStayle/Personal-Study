# 공부 참고 문서

[서비스 개요 공식문서](https://developer.android.com/guide/components/services?hl=ko)
[바인드 서비스](https://developer.android.com/guide/components/bound-services?hl=ko)
[백그래운드 제한](https://developer.android.com/guide/components/services?hl=ko)



###### 복습 필요한 부분 정리

- 서비스는 기본적으로 UI 스레드로 시작 된다.
- 서비스는 스스로 중단 하거나 외부 컴포넌트에서 중단해야 하는 라이프 사이클을 갖는다.    
- 백그라운드 서비스는 안드로이드 8.0 부터 시스템이 유휴 상태로 정리하는 경우가 있다.   
- 서비스는 포그라운드, 백그라운드, 바인딩된 서비스로 나뉜다.   
- 안드로이드 8.0 대체 방법으로 Job Scheduler 를 소개하는 편이다.   
- 기본적으로 서비스 보다 Intent Service 로 onHandleIntent() 를 사용해서 서비스를 이용하는 것이 불필요한 라이프 사이클 작업 처리가 필요 없긴 하다.  
- 안드로이드 12 부터 백그라운드 서비스에서 포그라운드 서비스로 승격하는 것에 제한이 생겼다.  
- stopSelf(startId:Int) 를 사용해서 onStartCommend() 에서 어떤 시작인가에 따라 중지할 수 있다.
- START_STICKY 는 OS에 의해 서비스가 강제로 종료되었을 때 자동으로 다시 시작하는 방식,onStartCommand()가 호출되지만 Intent는 Null   
- START_REDELIVER_INTENT 는 OS에 의해 서비스가 강제로 종료되었을 때 자동으로 다시 시작하는 방식이면서 Intent 는 not null   
- START_NOT_STICKY 는 OS에 의해 서비스가 강제로 종료되었을 때 자동으로 다시 시작하지 않는 방식 (전달할 Pending Intent가 있는 경우는 예외)  
- START_STICKY 와 START_REDELIVER_INTENT 는 백그라운드 서비스 일경우 안드로이드 8.0 부터 다시 살아나는 제한이 좀 있음   
- 암시적 서비스는 안드로이드 SDK 21 부터 제한이 있음   
- 포그라운드 서비스를 실행하면 시스템 리소스를 많이 소모하여, 낮은 우선순위 알림을 사용해 서비스를 숨기려 하면 성능 저해가 일어날 수 있음   
- 기본 바인딩 서비스는 모든 클라이언트 연결이 끊기면 자동으로 소멸 된다. (예외는 onStartCommend()를 같이 쓰면 된다.)   
- 안드로이드 권장 문서는 바인드 서비스 사용법 3가지를 설명 한다.   
1. 바인드 클래스를 상속한 바인더 객체로 소통   
2. android.os.Messenger 사용   
- Messenger 는 모든 서비스 호출을 큐에 올려서 처리한다. 그래서 하나씩 처리가 가능하다.    
- Messenger 는 내부적으로 핸들러를 통해서 메시지를 받아 처리 한다.   
- 각각 클라이언트는 서비스의 Messenger 를 참조해서 값을 받거나 Message 를 보낼 수 있다.   


### 개인 궁금증 & 답변

###### Q.   

"서비스가 바인딩도 제공하지 않는 경우, startService()와 함께 전달된 인텐트가 애플리케이션 구성 요소와 서비스 사이의 유일한 통신 수단입니다.     
그러나 서비스가 결과를 돌려보내기를 원하는 경우, 서비스를 시작한 클라이언트가 브로드캐스트를 위해 PendingIntent를 만들고(getBroadcast() 사용)    
이를 서비스를 시작한 Intent의 서비스에 전달할 수 있습니다. 그러면 서비스가 이 브로드캐스트를 사용하여 결과를 전달할 수 있게 됩니다."
공식 문서에 언급된 위 내용을 코드로 하면? (ChatGpt 코드)   
   
A.    
```
// 클라이언트에서 PendingIntent를 생성하여 서비스에 전달하는 코드
val resultIntent = Intent(this, MyBroadcastReceiver::class.java)
val pendingIntent = PendingIntent.getBroadcast(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

val serviceIntent = Intent(this, MyService::class.java)
serviceIntent.putExtra("pendingIntent", pendingIntent)
startService(serviceIntent)

// 서비스에서 PendingIntent를 사용하여 결과를 전달하는 코드
override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val pendingIntent = intent?.getParcelableExtra<PendingIntent>("pendingIntent")
    val resultIntent = Intent().apply {
        putExtra("result", "작업 완료")
    }

    if (pendingIntent != null) {
        try {
            pendingIntent.send(this, RESULT_OK, resultIntent)
        } catch (e: PendingIntent.CanceledException) {
            e.printStackTrace()
        }
    }

    return START_NOT_STICKY
}

// 결과를 수신하는 BroadcastReceiver 코드
class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val result = intent?.getStringExtra("result")
        // 결과 처리
    }
}
```

###### Q.   
   
포그라운드랑 포그라운드 서비스는 다른데 정확한 정리가 필요 할 것 같다.   

A.    
1. 포그라운드(Foreground):   
포그라운드는 안드로이드 운영 체제에서 실행 중인 앱 또는 앱의 구성 요소(예: 액티비티)가 사용자에게 직접적으로 노출되고 활성화된 상태를 의미합니다.   
   포그라운드에서 실행되는 앱은 사용자에게 현재 작업 중인 앱으로 인식되며, 화면에 표시되거나 알림을 통해 사용자와 상호작용할 수 있습니다.   
   
2. 포그라운드 서비스(Foreground Service):   
포그라운드 서비스는 백그라운드에서 실행되는 서비스(Service)로, 사용자에게 직접적인 알림을 통해 알려주는 서비스입니다.    
   포그라운드 서비스는 중요한 작업을 수행하는 동안에도 시스템에서 제한하는 백그라운드 제한 정책을 우회하여 계속 실행되도록 하는 역할을 합니다.
      포그라운드 서비스는 사용자가 알 수 있는 상태로 유지되어야 하며, 사용자가 중지할 수 있는 알림을 제공해야 합니다.   
   
즉, 포그라운드와 포그라운드 서비스는 모두 사용자에게 직접적으로 인식되는 상태를 가리키지만, 포그라운드는 앱 전체에 대한 개념이며,   
포그라운드 서비스는 특정 서비스를 가리키는 개념입니다. 포그라운드 서비스는 앱이 백그라운드에서도 계속 실행되어야 할 중요한 작업을 수행할 때 사용됩니다.   
예를 들어, 음악 재생, 위치 추적 등의 작업은 포그라운드 서비스로 실행될 수 있습니다.   
   
참고로, 안드로이드 8.0(Oreo)부터는 백그라운드에서 실행되는 서비스의 제한이 강화되었으며, 포그라운드 서비스를 사용하여 제한을 우회할 수 있게 되었습니다.