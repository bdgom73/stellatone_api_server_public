#  StellaTone API

---

## 🟣 URL

<p style="background: #222; color : white; padding: 1rem; border-radius: 1rem">
    <a href="https://stellatone.xyz">https://stellatone.xyz</a>
</p>


- 스텔라톤은 유튜브에 업로드된 스텔라이브의 음악을 편히 들을 수 있도록 해주는 플랫폼 입니다.
- 공개된 소스코드
  - 회원 관련 도메인이 포함된 내용을 제외한 모든 소스코드
    - 아티스트 (스텔라이브 멤버)
    - 음악 
    - 팀 (n기생)

## 🟣 사용 기술 및 개발 환경

<p style="background: #222; color : white; padding: 1rem; border-radius: 1rem">
    java 17, Spring boot 3.2, Spring data JPA, QueryDSL, IntelliJ, Gradle, Docker, MySQL, GCE, NCP 
</p>

## 🟣 Use Case

- ### 아티스트 Use Case
  - 전체 아티스트 조회 및 상세 조회를 진행합니다.
  
- ### 음악 Use Case
  - 조건별(키워드 검색, 아티스트 검색, 공식 여부) 음악 조회
  - 랜덤 음악 목록 조회

- ### 팀(기수) Use Case
  - 팀 (1기생, 2기생, 3기생 ...) 다중 조회
  - 팀 단건 조회

## 🟣 appliaction yml 설정

{ 변수명 } 부분을 본인에게 맞게 설정하여 사용해 주세요. 필요에 따라 수정이 가능 합니다.

```yaml

spring:
  datasource:
    url: ## { DB_URL }
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ## { DB_USERNAME }
    password: ## { DB_PASSWORD }

  h2:
    console:
      path: /h2/console
      enabled: true

  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
    properties:
      hibernate:
        default_batch_fetch_size: 500
        batch_fetch_style : padded
        format_sql: true

```

## 🟣 실행
프로젝트의 gradlew 이 있는 위치에서 api 모듈을 실행합니다.

```bash
./gradlew :music-api:bootRun
```

## 🟣 문서
해당 프로젝트의 Swagger 문서 주소입니다.

현재 `8080 포트` 기준입니다.

```
http://localhost:8080/swagger-ui/index.html
```
