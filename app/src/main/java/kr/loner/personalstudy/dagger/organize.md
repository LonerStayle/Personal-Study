# 공부 참고 문서
[대거_안드 공식 문서](https://developer.android.com/training/dependency-injection/dagger-basics?hl=ko)
[대거2_개인 블로그](https://brunch.co.kr/@oemilk/73)
[pluu님의 드로이드 카기2019 정리 _대거사용](http://pluu.github.io/blog/android/droidkaigi/2020/01/27/droidkaigi-multimodule-with-dagger/)
[배민 대거 적용기](https://techblog.woowahan.com/2639/):
[대거_미디엄](https://medium.com/@jason_kim/tasting-dagger-2-on-android-%EB%B2%88%EC%97%AD-632e727a7998)
[대거 내부 동작01](https://proandroiddev.com/deep-dive-into-dagger-generated-code-part-1-58f3cb9563de)
[대거 내부 동작02](https://proandroiddev.com/deep-dive-into-dagger-generated-code-part-2-13de4781b49d)
[대거 내부 동작03](https://proandroiddev.com/deep-dive-into-dagger-generated-code-part-3-2d1593ad154)
[조세영 님의 대거 정리](https://kotlinworld.com/111)

   
###### 복습 필요한 부분 정리   
- Component Builder 는 첫 init 시에 외부 인자로 패러미터를 전달 받는다.   
- Component Builder 의 메서드는 패러미터를 각각 하나씩 밖에 가질 수가 없다.   
- Component Factory 는 하나의 메서드의 여러인자를 넣을 수 있다. 하지만 create() 메서드 외에는 아무 메서드를 가질 수 없다.   
- MultiBinding 은 set,map, key 지정, 컬렉션 단위 insert 를 지원 한다.   
- @Binds 는 함수(TImpl):T 로 되어 있다. TImpl 생성자에 파라미터를 넘겨야할 경우 @Binds 를 사용할 수 없다.