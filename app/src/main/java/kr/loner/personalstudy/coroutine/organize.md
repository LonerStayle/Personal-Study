# 공부 참고 문서

[찰스의 코루틴 시작하기](https://www.charlezz.com/?p=45962)   
[찰스의 생명주기에 맞게 코루틴 시작하기](https://www.charlezz.com/?p=46044)   
[코루틴의 CPS 설명](https://june0122.github.io/2021/06/09/coroutines-under-the-hood/)   
[suspendCancellableCoroutine](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/suspend-cancellable-coroutine.html)   
[Retrofit2KotlinExts](https://github.com/square/retrofit/blob/master/retrofit/src/main/java/retrofit2/KotlinExtensions.kt)
[태환님의 레트로핏2과 코루틴](https://thdev.tech/kotlin/2021/01/12/Retrofit-Coroutines/)
[명표님의 Coroutine 정리](https://myungpyo.medium.com/reading-coroutine-official-guide-thoroughly-part-0-20176d431e9d)

###### 복습 필요한 부분 정리

- Coroutine Context 에서 KEY 는 Element 를 제네릭 으로 가짐
- Element 는 Coroutine Context 를 상속
- Element 는 CoroutineId, CoroutineName, CoroutineDispatcher, ContinuationInterceptor,
  CoroutineExceptionHandler 등등
- Coroutine Context 의 plus 함수는 내부 에서 fold 와 Combined Context 를 사용 한다.   
- Coroutine Scope 에서 스코프 빌더(coroutineScope, withContext) , 코루틴 빌더(launch, async) 는 CoroutineScope의   
  확장 함수로 정의됨   
- Coroutine Scope 는 Coroutine Context 를 하나만 가지고 있어서 구현을 해줘야 함 (기본적으로 디스패처 를 구현해서 쓰게 됨 )   
- Global Scope 의 context 는 싱글턴 (Kotlin Object) 로 구현 되어 있는 EmptyCoroutineContext 를 사용 하는데 이 context 는   
  어떤 생명주기에 바인딩 된 Job이 없기 때문에 전역 속성을 갖는다.   
- JVM 에서 제공하는 Coroutine Dispatcher 는 4가지가 있다. Main,IO,Default, Unconfined    
- Unconfined 는 Continuation 에서 바로 suspend -> Resume 되는 스레드에서 바로 실행   
- Main 은 메인 스레드에서 Event Loop 를 이용해 코루틴의 실행을 스케줄링 함    
- IO 는 LimitingDispatcher 로 래핑해서 병렬 실행 수 에 따라 CoroutineScheduler 에서 실행 할지 대기 할지 결정   
- Default 는 Coroutine Scheduler 에 요청하고자 하는 Task로 바로 래핑되어서 사용함 (TaskContext 의 afterTask() 가 비어있음)   
- Coroutine Scheduler 는 Java 의 Executor 를 구현해서 만든 것인데, Dispatcher 로 생성된 Task 는 Scheduler 의 Worker 들에 의해 관리 된다.    
- 위 Worker 는 Java 의 기본 Thread 임   
- suspend 는 언제든지 취소가 가능하며 취소가 발생하면 CancellationException 이 발생한다.   
- withContext 는 3가지 경우를 분기처리 한다.
  1. 인자로 받은 coroutine Context 가 동일할 경우 바로 Scope Coroutine 을 만들어 실행
  2. coroutine Context 가 다르지만 Dispatcher 가 동일할 경우 UndispatchedCoroutine 를 만들어서 새로운 컨텍스트 안에서 실행
  3. 그외의 경우 DispatchedCoroutine 을 생성하여 실행합니다.
     
-

### 개인 궁금증 & 답변

###### Q.

suspend 와 일반 함수가 동작이 다르 다는 것을 알겠어, 그럼 확실 하게 코드로 확인 할만한 예제 코드가 없을까?

A.    
일반 함수를 사용할 경우

```
fun regularFunction() {
    println("Starting regular function")
    for (i in 1..3) {
        println("Regular function: $i")
        Thread.sleep(1000) // 1초간 대기
    }
    println("Finishing regular function")
}

fun main() {
    println("Before function call")
    regularFunction()
    println("After function call")
}

```   

결과:   
Before function call   
Starting regular function   
Regular function: 1   
Regular function: 2   
Regular function: 3   
Finishing regular function   
After function call

   
---------------------------------------   

Suspend를 사용할 경우

```
suspend fun coroutineFunction() {
    println("Starting coroutine function")
    for (i in 1..3) {
        println("Coroutine function: $i")
        delay(1000) // 1초간 대기
    }
    println("Finishing coroutine function")
}

fun main() = runBlocking {
    println("Before function call")
    coroutineFunction()
    println("After function call")
}
```   

결과:   
Before function call   
Starting coroutine function   
Coroutine function: 1   
After function call   
Coroutine function: 2   
Coroutine function: 3   
Finishing coroutine function

### 개인 궁금증 & 답변

###### Q.

CoroutineContext에 관한 내용 중에   
plus() : 현재 컨텍스트와 파라미터로 주어진 다른 컨텍스트가 갖는 요소들을 모두 포함하는 컨텍스트를 반환합니다. 현재 컨텍스트 요소 중 파라미터로 주어진 요소에 이미 존재하는
요소(중복)는 버려집니다.   
라고 정의 되어있는데 replace 된다는 말일까?

A.   
파라미터로 넘어온 걸로 덮어 씌워진다. plus() 코드를 보면 확인이 가능함. tmi 이지만 plus를 보면 내부에서 fold를 사용하고 있음    



