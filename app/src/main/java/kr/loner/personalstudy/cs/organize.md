# 공부 참고 문서
[Array 와 LinkedList](https://kimmeh1.tistory.com/473)
[Array 와 ArrayList](https://velog.io/@humblechoi/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-Array-vs-ArrayList)   
   
###### 복습 필요한 부분 정리   
- Array 는 필요한 요소를 O(1) 로 찾을 수 있다. 왜냐하면 찾으려는 포지션 값에 따라 메모리 주소를 몇번 점프 해서 바로 찾을 수 있는지 알 수 있기 때문이다.
- Array 의 요소 추가는 O(N) 이다. 왜냐하면 넣기 위해서 전체 한칸씩 뒤로 밀리기 때문이다.
- Linked List 의 필요한 요소를 O(N) 으로 찾을 수 있다. 왜냐하면 각각 노드 에서 다음 메모리 주소를 알 수 있기 때문에 원하는 요소를 찾아서 노드 조회를 해야 알 수 있기 때문 이다.   
- Linked List 의 요소 추가는 O(1) 이다. 필요한 위치에 양 옆 노드가 가리키는 주소만 바꾸고 그 자리에 위치 시킬 수 있기 때문 이다.   
- Array 는 Stack 영역에 할당이 되고, Linked List 는 Heep 영역에 할당이 된다.   
- Array 의 사이즈는 고정이고, ArrayList 사이즈는 정적이다. 전체 사이즈 * 1.5배 씩 늘어난다