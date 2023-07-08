# 공부 참고 문서
[setValue vs postValue](https://wooooooak.github.io/android/2019/06/11/Android_liveData_value/)


###### 복습 필요한 부분 정리
- (평소 StateFlow 쓰다가 순간 헷깔렸음) LiveData 는 항상 최신데이터 하나만 간직해서 이전 값은 존재하지 않는다. 그래서 StateFlow와 다르게 항상 최신값을 observe 한다 (라이프 사이클만 맞으면)     
- LiveData 의 value 는 null을 허용한다.
- Component Activity 에서 addObserver 된 lifecycleOwner의 Lifc Cycle 에 따라 observe 콜백이 유지 될지 안될지가 결정 된다.
- postValue 는 워커 스레드에서도 LiveData 에 value 를 set하기 위함이라서 내부적으로 메인 스레드의 루퍼를 가진 핸들러의 post() 를 작동한다. 

