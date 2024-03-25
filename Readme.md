# QueryDSL 학습

### Q클래스 인스턴스를 사용하는 2가지 방법

```java
QMember qMember = new QMember("m"); //별칭 직접 지정
QMember qMember = QMember.member;   //기본 인스턴스 사용
```

> 참고 :같은 테이블을 조회하는 경우가 아니면 기본 인스턴스 사용 권장

### JPQL 로그 확인

```properties
spring.jpa.properties.hibernate.use_sql_comments: true
```

### JPQL 이 제공하는 모든 검색조건 제공

```java
member.username.eq("member1") // username = 'member1'
member.username.ne("member1") //username != 'member1'
member.username.eq("member1").not() // username != 'member1'
member.username.isNotNull() //이름이 is not null
member.age.in(10, 20) // age in (10,20)
member.age.notIn(10, 20) // age not in (10, 20)
member.age.between(10,30) //between 10, 30
member.age.goe(30) // age >= 30
member.age.gt(30) // age > 30
member.age.loe(30) // age <= 30
member.age.lt(30) // age < 30
member.username.like("member%") //like 검색
member.username.contains("member") // like ‘%member%’ 검색
member.username.startsWith("member") //like ‘member%’ 검색
```

### 결과 조회

- `fetch()` : 리스트 조회, 데이터 없으면 빈 리스트 반환
- `fetchOne()` : 단 건 조회
  - 결과가 없으면 : null
  - 결과가 둘 이상이면 : com.querydsl.core.NonUniqueResultException
- `fetchFirst()` : limit(1).fetchOne()
- `fetchResults()` : 페이징 정보 포함, total count 쿼리 추가 실행
- `fetchCount()` : count 쿼리로 변경해서 count 수 조회


### 프로젝션 결과반환

- 프로젝션 대상이 하나면 타입을 명확하게 지정할 수 있음
- 프로젝션 대상이 둘 이상이면 튜플이나 **DTO**로 조회

> 튜플은 Repository 안에서 사용을 권장하며, DTO 를통해 외부로 전달


### fetchResults() -> deprecated
>fetchResult 는 Querydsl 내부에서 count 용 쿼리를 만들어서 실행해야 하는데, 이때 작성한 쿼리 기반으로
> count 쿼리를 만들어냄, 단순한 쿼리에서는 작동하지만 복잡한 쿼리에서는 잘 동작하지 않음. --> total 별도 쿼리 작성 권장