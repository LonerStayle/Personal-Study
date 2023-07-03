# 공부 참고 문서 on 2023-07-03
[Passive View](https://martinfowler.com/eaaDev/PassiveScreen.html)
[Supervising_Presenter](https://martinfowler.com/eaaDev/SupervisingPresenter.html)
[Passive View Mvp](https://medium.com/android-testing-daily/passive-view-e750bb10b20d)
[mvp 역사](https://blog.naver.com/jukrang/221597910488)

### 개인 궁금증 & 답변
###### Q.  
안드로이드 MVVM는 대부분 AAC ViewModel을 활용한다. AAC VM은 이미 라이프 사이클을 알고 있고, onCleard로 activity의 Destory단계를 넘어서면 알아서 데이터가 정리되는데,   
왜 AAC VM에서 Context를 가지는 것이 좋지 않은지 구체적으로 다시 생각해보자.
   
A.    
기본적으로 MVVM 에서 쓰는 View에 대한 의존성 분리 원칙을 지키기 위해서 Context를 가지지 않고 ui상태를 관리하는 역활에만 신경써야 되기 때문이다.  
또한 유닛 테스트에 좋지 않다. aac vm 얘기만 해보면 구성 변경 관련해서 좋지 않기 때문이다.   
구성 변경할 때 새 Context를 생성하는데 이전 Context의 참조를 유지중이기 때문에 메모리 누수로 이어질 수 있다.  
   


