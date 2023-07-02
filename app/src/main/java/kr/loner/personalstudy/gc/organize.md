# 공부 참고 문서 on 2023-07-02
[가비지콜렉터 01](https://mangkyu.tistory.com/118) 

### 개인 궁금증 & 답변
###### Q.  
두 개의 Survivor 영역(S0, S1)이 나뉘어져 있는 이유?   
   
A.    
Eden 영역에서 가비지 콜렉터 발동하고 살아남은 객체를 Survivor 로 옮기는데 이때 S0 으로 옮긴다.    
이후 가비지 콜렉터가 반복될 때 마다 S0와 Eden 영역사이에서 객체들이 이동하면서 객체의 수명이 확인된다. 이때 살아남으면 S1로 이동된다.   
S0와 S1은 객체의 이동을 위한 작업 공간으로 사용되며, 두 영역이 교차되면서 가비지 컬렉션이 수행됩니다.   
S0이 가득차면 S1으로 이동되기도 한다.   
   
   
###### Q.
GC 발동 조건을 다 정리하면??

A.
1. Young Generation 의 Eden 영역이 꽉 찼을 때 -> Miner GC 발동 (살아남은 객체들을 Survivor 영역으로 이동)
2. Young Generation 의 Survivor 영역이 꽉 찼을 때 -> Miner GC 발동(참조되지 않은 객체들을 회수, 살아남은 객체들을 다른 Survivor 영역으로 이동)
3. Old Generation 이 꽉찼을 때 -> Major GC 발동
4. 시스템 메모리 부족 -> Major GC 발동
5. 일정 시간 주기 -> Major GC 발동


   


