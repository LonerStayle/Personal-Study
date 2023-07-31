# 공부 참고 문서 
[루퍼_핸들러_기초 개념](https://velog.io/@haero_kim/Android-Looper-Handler-%EA%B8%B0%EC%B4%88-%EA%B0%9C%EB%85%90)    


###### 복습 필요한 부분 정리   
- Message에 Runnble 이 없을 경우 what, arg1,arg2,obj 로 인자들을 보내서 핸들러의 Handle Message에서 처리하도록 한다.
- 핸들러의 handel message는 필수 override 이다.

### 개인 궁금증 & 답변
###### Q.  
루퍼 내부에 메시지 큐가 있다. 그러면 루퍼가 없으면 메시지 큐도 존재 할수 없는건가?   
   
A.    
맞다. 당연히 루퍼 내부에 있기 때문에 루퍼가 없으면 메시지 큐도 없다.
   


