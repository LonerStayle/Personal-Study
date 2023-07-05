# 공부 참고 문서 on 2023-07-05
[찰스의 코루틴 시작하기](https://www.charlezz.com/?p=45962) 

### 개인 궁금증 & 답변
###### Q.
suspend와 일반 함수가 동작이 다르다는 점을 알겠어, 그럼 확실하게 코드로 확인 할만한 예제 코드가 없을까?   
   
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



   


