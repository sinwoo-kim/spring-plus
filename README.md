<div align="center">
<!-- logo -->
<img src="https://user-images.githubusercontent.com/80824750/208554611-f8277015-12e8-48d2-b2cc-d09d67f03c02.png" width="400"/>

### 일정관리앱 개선 프로젝트 ✅

<br/> <img src="https://img.shields.io/badge/프로젝트 기간-2022.12.10~2022.12.19-green?style=flat&logo=&logoColor=white" />
</div>

## 📝 소개

1. 본 프로젝트는 Spring boot에 대한 이해와 활용을 넓히기 위해 설계된 과제입니다.
2. 개인 과제로 다음 항목과 관련된 문제 상황을 제시하며, 대응책을 요구합니다.

### Lv 1

1. 읽기 지연으로 인한 에러 상황
2. 기획자가 JWT에서 유저의 닉네임을 꺼내 보여주길 원하는 상황
3. 로그가 메서드 실행 후에 동작하는 상황
4. 테스트 코드 에러
5. 기획자가 검색 조건 추가를 요구하는 상황

### Lv 2

6. cascade를 활용한 자동 등록
7. N+1 문제 해결하기
8. JPQL -> QueryDSL 리팩토링
9. Argument Resolver -> Spring Security 리팩토링

### Lv 3

10. QueryDSL 검색 기능 만들기(Projections 활용)
11. @Transactional 독립 처리 구현하기
12. AWS 활용

<br />

## 🗂️ APIs

추가 된 Lv 3-10 작성한 API는 아래에서 확인할 수 있습니다. (기존 API 명세는 제외)

👉🏻 [API 바로보기](https://github.com/sinwoo-kim/spring-plus/blob/main/APIs.md)

<br />

## ⚙ 기술 스택

### Back-end

<div>
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Java.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/SpringBoot.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/SpringSecurity.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/SpringDataJPA.png?raw=true" width="80">
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/Mysql.png?raw=true" width="80">
</div>

### Infra

<div>
<img src="https://github.com/yewon-Noh/readme-template/blob/main/skills/AWSEC2.png?raw=true" width="80">
</div>

<br />

## 🤔 트러블 슈팅

### 1. LocalDate 타입 사용하여 날짜 검색 기능 구현하기
👉 [왜 Null로 처리되는가?](https://www.notion.so/OR-null-NPE-17deed21a254801d958dc07787d01594?pvs=4)
<br />
### 2. n+1 
👉 [소프트 딜리트 된 엔티티 조회 시 NPE 발생](https://www.notion.so/n-1-EntityNotFoundException-17eeed21a254806a98fed81ecc639397?pvs=4)</br>
👉 [소프트 딜리트 시 연관 관계 객체 n+1](https://www.notion.so/n-1-17feed21a254805fab88fa1b489ab1fe?pvs=4)

### 3. 리팩토링/구조
👉 [storeId받는다 vs 안받는다](https://www.notion.so/Store-Id-181eed21a2548068ad80d54811fe6d3d?pvs=4)

### 4. @Transaction 독립 처리 구현하기
👉 [Propagation.REQUIRES_NEW가 먹히질 않는다](https://www.notion.so/3-11-182eed21a2548091852dfc861eab5f25?pvs=4)

### 5. aws 배포
👉 https://www.notion.so/AWS-Elastic-Beanstalk-188eed21a254809d9652e5bd9f20771a?pvs=4