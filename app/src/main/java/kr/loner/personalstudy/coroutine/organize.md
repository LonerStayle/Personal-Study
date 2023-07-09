# 공부 참고 문서

[찰스의 코루틴 시작하기](https://www.charlezz.com/?p=45962)   
[찰스의 생명주기에 맞게 코루틴 시작하기](https://www.charlezz.com/?p=46044)   
[코루틴의 CPS 설명](https://june0122.github.io/2021/06/09/coroutines-under-the-hood/)   
[suspendCancellableCoroutine](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/suspend-cancellable-coroutine.html)   
[Retrofit2KotlinExts](https://github.com/square/retrofit/blob/master/retrofit/src/main/java/retrofit2/KotlinExtensions.kt)
[태환님의 레트로핏2과 코루틴](https://thdev.tech/kotlin/2021/01/12/Retrofit-Coroutines/)
[명표님의 Coroutine 정리](https://myungpyo.medium.com/reading-coroutine-official-guide-thoroughly-part-0-20176d431e9d)
[홍범님의 Android Dev 2021 Summit Flow 정리](https://medium.com/hongbeomi-dev/%EC%A0%95%EB%A6%AC-%EC%BD%94%ED%8B%80%EB%A6%B0-flow-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-android-dev-summit-2021-3606429f3c5f)
[코루틴의 취소_안드로이드 공식 블로그](https://medium.com/androiddevelopers/cancellation-in-coroutines-aa6b90163629)

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
- withContext 는 값을 반환한다. (내부적으로 Continuation 을 통해 Result<T> 로 T를 반환 함)

- 코루틴은 비선점형 동시성 프로그래밍 이라서 한 스레드안에 여러 코루틴이 서로 협력해서 움직인다.    
- 코루틴은 컨텍스트 스위칭이 전혀 없는것은 아니다. IO, Default Dispatcher 를 사용하면 백그라운드 스레드 풀에서 다른 스레드를 사용하거나 스레드를 새로 생성할 수도 있다.    

  
- async 와 launch 의 예외 처리가 다르다. async 는 block 에 예외가 발생하면 await 호출부에도 예외가 전파 되고, launch 는 block 내에서만 예외가 발생한다.   
- 위와 관련된 문제로 await() 호출부 없이 async 만 사용해서 예외가 발생하면 android 로깅 시스템 때문에 logcat 에 예외가 찍히지 않는다.    
- scope 도 cancel() 함수가 있다. 모든 자식의 코루틴을 취소한다.   
- 각각 IO 디스패쳐를 사용하는 async 를 만들어서 결과값을 내면 비동기로 작업속도 최적화도 가능하다. (a = async(IO) b = async(IO) -> println("$a.await() 와 $b.await()"))   
- Deferred 는 Job을 상속받아 만들어졌다.   
- Coroutine Context 는 Job, Coroutine Dispatcher , Coroutine Name , Coroutine Exception Handler 등등의 동작을 정의 한다.

- 기본 Flow 는 1명의 관찰자가 있어야 방출이 되고, 없으면 해제 된다. (Cold) State Flow 는 관찰자가 있다가 없어져도 바로 해제되지 않는다. (Hot)  
- 기본 Flow 는 내부적으로 value 를 가지지 않는다. 그리고 collect 를 만들때마다 생산자로부터 새롭게 방출을 시도한다.   
- 기본 Flow 는 Shared Flow 와 달리 1:1 구독 형식을 갖는다.   
- Flow 에서 Produce block (flow 생성부, emit...) 이 있고 UpSteam Block(map,filter,catch...) 이 있고 나머지는 DownStream Block (collect,first...) 이있다.   
- State Flow 에 WhileSubscribed(5000) 는 수집이 중단되었을 때 5초동안 수집을 하는지 확인하고 수집 하지 않으면 flow 의 수집을 취소 한다. 수집하면 flow 수집을 유지 한다.    
- android view 에서 repeatOnLifecycle(state), flowWithLifecycle(lifecycle,state) 을 사용해야 백그라운드에서 flow 수집하는 실수를 방지할 수 있다.   
- lifecycleScope.launch 만 사용해서 collect 를 하게 된다면 백그라운드에서 flow 수집이 될수 있다.   

- Channel 은 Blocking Queue 와 흡사하다. put 이 send, take 는 receive 가 되었다. 하지만 Channel 은 스스로 큐를 닫을 수도 있다.
- Channel 는 produce 와 consume 패턴으로 되어 있다. 
- produce 확장함수를 통해서 ReceiveChannel<T> 로 채널을 만들 수 있다. 
- produce 를 produce 에게 넘겨서 consume 가공으로 계속 데이터를 필터링 해서 넘기는 것을 파이프 라인이라고 한다. 


###### 안드로이드 코루틴 권장 방식    
[안드로이드 코루틴 권장 코드 공식 문서](https://developer.android.com/kotlin/coroutines/coroutines-best-practices#inject-dispatchers)   
- Dispatcher 를 주입해서 사용할 것 -> 테스트 할 때 Dispatcher 를 주입해서 테스트를 해볼 수 있도록   
- Suspend 함수는 메인 스레드에서 안전하게 호출이 가능할 것 -> 어떤 스레드에서 작업하는 건지 걱정없도록 (백그라운드 작업이 필요할 경우 withContext(IO) 로 구문을 감싸기)   
- ViewModel 은 비즈니스 로직 처리하기 위해서 가능하면 suspend 함수 노출보다 코루틴을 생성해서 사용을 권장함 -> ui 모델로 바꿔서 단일 값만 쓸 수 있도록 그외에 라이프사이클 문제 및 비즈니스 로직 제어 처리 관심사 분리 등등   
- Data Layer 및 Domain Layer 는 Flow 및 suspend 함수를 노출해야함 -> 데이터 변경사항을 알리는 Flow, 일회성 호출용 suspend 함수   
- Data Layer 및 Domain Layer 에서 코루틴을 만들어야 할 경우 아래 예시코드와 같이 한다.    
```
// 뷰모델에서 viewModelScope 수명 주기에 맞출 경우  
class GetAllBooksAndAuthorsUseCase(
    private val booksRepository: BooksRepository,
    private val authorsRepository: AuthorsRepository,
) {
    suspend fun getBookAndAuthors(): String {
        return coroutineScope {
            val books = async { booksRepository.getAllBooks() }
            val authors = async { authorsRepository.getAllAuthors() }
            BookAndAuthors(books.await(), authors.await())
        }
    }
}
//View의 수명주기 상관 없이 쭉 진행되어야 하는 경우 외부에서 스코프를 받는다. 
class ArticlesRepository(
    private val articlesDataSource: ArticlesDataSource,
    private val externalScope: CoroutineScope,
) {
    suspend fun bookmarkArticle(article: Article) {
        externalScope.launch { articlesDataSource.bookmarkArticle(article) }
            .join() // Wait for the coroutine to complete
    }
}

```   
- GlobalScope 피하기 -> CoroutineContext 가 없어서 커스텀 불가, 제어되지 않는 범위에서 코드 실행, Dispatcher 하드 코딩 문제와 겹침   
- 코루틴은 항상 취소 가능하게 만들기 -> ex) 반복문의 경우 ensureActive() 사용   
- 예외에 주의하기 -> try Catch 로 앱 비정상 종료를 피하도록 조심하기   
   

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



