# 공부 참고 문서

[백그래운드 제한](https://developer.android.com/guide/components/services?hl=ko)
[브로드캐스트 개요](https://developer.android.com/guide/components/broadcasts?hl=ko)


###### 복습 필요한 부분 정리

- 암시적 브로드캐스트 리시버는 안드로이드 8.0 부터 매니페스트에 미리 등록해서 사용할 수 없음 
- 서명 권한 필요한 브로드 캐스트 리시버는 이 제한으로부터 제외가 됨   
- 런타임에서 context.registerReceiver() 는 암시적 브로드캐스트 리시버 사용이 가능함 
- 브로드 캐스트 리시버에서 onReceive() 를 받게되면 해당 함수가 끝나기전 까지 잠시 포그라운드 상태로 취급 받는다. 

### 개인 궁금증 & 답변

###### Q.   


   
A.    
