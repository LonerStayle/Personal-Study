# 공부 참고 문서
[Coroutine 에서의 멀티스레딩](https://programmmingphil.tistory.com/25#:~:text=%EB%AE%A4%ED%85%8D%EC%8A%A4%EB%8A%94%20%EC%95%9E%EC%84%9C%20%EB%B3%B4%EC%95%98%EB%8D%98%20synchronized,%EC%84%B1%EB%8A%A5%EC%9D%80%20%EB%8D%94%20%EC%A2%8B%EB%8B%A4%EA%B3%A0%20%ED%95%9C%EB%8B%A4)
[자바 멀티스레딩_가시성_원자성](https://velog.io/@syleemk/Java-Concurrent-Programming-%EA%B0%80%EC%8B%9C%EC%84%B1%EA%B3%BC-%EC%9B%90%EC%9E%90%EC%84%B1)   

###### 복습 필요한 부분 정리   
- 가시성은 CPU 캐싱의 값을 본래 메모리 값에 옮기기 전에 다른 스레드가 원래 자원을 읽어서 계산 했을 때가 고려된다.
- 원자성은 공유되는 변수를 변경할 때, 기존의 값을 기반으로 하여 새로운 값이 결정되는 과정에서 여러 Thread가 이를 동시에 수행할 때 생기는 이슈를 부르는 명칭이다.
- 가시성 이슈 - Volatile 을 쓰면 CPU 연산은 캐시로 가는것이 아니라 바로 메모리에 저장된다.
- 
