# 공부 참고 문서    
[찰스의 mvi 설명](https://www.charlezz.com/?p=46365)   
[찰스의 mvi 라이브러리 소개 :Orbit](https://www.charlezz.com/?p=46377)   

###### 복습 필요한 부분 정리   
- Model 은 상태, Intent 는 의도(이벤트), View, User , Side Effect 관점으로 봐야한다.
- View(Model(Intent())) 순으로 이어진다. 의도에 의해 상태가 바뀌고 상태에 의해 뷰가 바뀐다.
- MVI 의 Model 은 불변 데이터 다. Intent 로 부터 새로 객체를 생성 해서 상태의 변경을 알린다. 가변 데이터의 변경 히스토리를 알 필요 없어서 디버깅이 쉬워진다.
- User -> Intent -> Model -> View -> User ... 단방향 플로우 로 볼 수 있다. Intent -> Model 사이 -> 에 Side Effect 가 있다.
- Reducer 는 Intent, State 이 합쳐 지고 새로운 State 이 반환 되는 것이다. Reducer(state,intent)
- Side Effect 는 어떠한 상태를 바뀌지 않고 이벤트 를 처리 하는 개념 이다.