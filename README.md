# Housing-Finance

## 개발 프레임워크
- Common 
  - Spring Boot
  - JPA
  - Gradle
- Test
  - H2
- Prod
  - MySQL

## 빌드
- git clone
<pre><code>https://github.com/korea8378/Housing-Finance.git</code></pre>
- 빌드(해당 프로젝트 루트 위치에서)
<pre><code>cd Housing-Finance

./gradlew clean build
</code></pre>

## 실행
- 실행(개발) - H2
<pre><code>nohup java -jar -Dspring.profiles.active=local -DJWT_Secret_Key={secret key} ./build/libs/finance-0.0.1-SNAPSHOT.jar &</code></pre>
- 실행(운영) - MySQL
<pre><code>nohup java -jar -Dspring.profiles.active=prod -DJWT_Secret_Key={secret key} -DMYSQL_URL={msyql url} -DUSER_NAME={user name} -DUSER_PASSWORD={user password} ./build/libs/finance-0.0.1-SNAPSHOT.jar &</code></pre>
- spring.profiles.active
  - 실행 환경 - 개발(prod) or 운영(local)
- JWT_Secret_Key
  - JWT 인코딩에 사용될 비밀 키/{secret key}에 입력
- MYSQL_URL
  - MySQL Url/{msyql url}에 입력
- USER_NAME
  - MySQL 사용자 이름/{user name}에 입력
- USER_PASSWORD
  - MySQL 사용자 비밀번호/{user password}에 입력

## 문제 해결 전략

### signup 계정 생성 API
- [signup 계정 생성 API Spec](https://github.com/korea8378/Housing-Finance/issues/32)
- 유저 정보(아이디, 패스워드)를 데이터베이스에 저장시 패스워를 암호화 하여 저장해야 했습니다.
- 인코딩(암호화)방식으로 단순한 단방향 해시함수보다는 단방향 해시함수에 솔팅과 키 스트레칭을 적용한 방식을 적용된 bcrypt를 사용하게 되었습니다.

### signin 로그인 API
- [signin 로그인 API Spec](https://github.com/korea8378/Housing-Finance/issues/33)
- 유저의 패스워드를 암호화/복호화하는 로직의 누구의 책임(도메인모델, 서비스)인지 고민하는 시간을 가져야 했습니다.
- 암호화/복호화는 유저도메인모델의 책임으로 판단하여 유저 엔티티 안에 위치시켜 유저엔티티 자체가 암호화/복호화 할 수 있도록 하였습니다.
- 유저 엔티티자체가 암호화 되어있는 비밀번호를 가지고 있습니다.
- 그렇기 때문에 사용자가 로그인시 입력하는 비밀번호가 유저 엔티티의 비밀번호와 동일한지 비교하는 주체가 유저 엔티티의 책임이라고 생각했습니다.

### refresh 토큰 API
- [refresh 토큰 API Spec](https://github.com/korea8378/Housing-Finance/issues/34)
- 토큰 재발급시 Bearer Token을 입력받아 JWT를 재발급 해야 했습니다.
- 하지만 Bearer Token라는 문자열로만으로는 어떤 유저의 JWT를 재발급해야하는지 힘들기 때문에 Bearer Token과 함께 기존에 발급받은 JWT받았습니다.
- JWT가 복호화가 되면 안에 유저의 정보가 들어 있기때문에 정보를 재사용하여 JWT를 만들어 재발급하였습니다.

### 서비스 이용시 인증(JWT)
- 서비스 이용시 인증 과정을 거쳐야 하기 때문에 중복되는 절차가 반복 되었습니다.
- AOP를 이용하여 중복되는 인증 로직을 한곳에서 관리 할 수 있도록 하였습니다.

### 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 
- [데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API Spec](https://github.com/korea8378/Housing-Finance/issues/36)
- 1.데이터베이스에 데이터 저장(테이블 설계)
  - CSV파일의 헤더 컬럼 그대로 사용하여 데이터베이스에 저장하기 보다는 년도, 월, 은행이름, 금액이라는 컬럼으로 저장하기로 하였습니다.
  - 은행별로 데이터를 관리하는게 은행별 지원금액 합계, 은행의 평균 지원금, 은행의 예상 지원금액등을 효율적으로 처리 할 수 있다고 판단 하였기 때문입니다.
- 2.CSV 파일 파싱
  - CSV파일을 맵핑하는 과정에서 헤더 컬럼 중 "" <- 빈공간을 처리해야 했습니다.
  - enum에 CSV파일의 헤더(은행이름과금액단위)와 데이터베이스에 저장 될 은행이름을 같이 저장하여 처리하였습니다. 

### 주택금융 공급 금융기관(은행) 목록을 출력하는 API
- [주택금융 공급 금융기관(은행) 목록을 출력하는 API Spec](https://github.com/korea8378/Housing-Finance/issues/35)
- 금융지원(금융기관별 지원금액 서비스 처리)과 은행(은행이름과 은행코드 서비스 처리)를 서로 다른 패키지에 속해 있었습니다.
- 개발을 진행하면서 금융지원과 은행은 상품과 카테고리의 관계로 판단하여 두개의 패키지를 하나의 패키지로 병합하였습니다.

### 년도별 각 금융기관의 지원금액 합계를 출력하는 API
- [년도별 각 금융기관의 지원금액 합계를 출력하는 API Spec](https://github.com/korea8378/Housing-Finance/issues/37)
- 년도별 총 지원금액, 년도별 은행들의 각 총지원금액을 출력해야 했습니다.
- 데이터베이스에는 년도, 월, 은행이름, 금액으로 저장되어 있기 때문에 개별적으로 select문을 요청 해야 하기 때문에 효율적인 select문이 필요했습니다.
- JPQL과 group by, case, sum 키워드들을 이용하여 select문을 작성하였습니다.

### 각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API
- [각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API Spec](https://github.com/korea8378/Housing-Finance/issues/38)
- 은행의 지원금액이 월단위로 쌓여 있기 때문에 지원금액을 년도별로 합쳐서 처리해야 했습니다.
- group by, sum, order by Desc를 이용하여 내림차순으로 정렬 한 뒤 상위 하나의 데이터만 들고 올 수 있도록 JPQL을 작성하였습니다.

### 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API
- [전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API Spec](https://github.com/korea8378/Housing-Finance/issues/39)
- 외환은행의 지원금액이 월단위로 쌓여 있기 때문에 지원금액을 년도별로 합쳐서 처리해야 햇습니다.
- group by, avg를 내림차순과 오름차순 두개의 로직으로 분리하여 최대 평균값, 최소 평균값을 구할 수 있도록 하였습니다.
- 내림차순으로 하나의 메소드를 이용하여 데이터베이스에 다 들고 온 뒤 첫번째와 마지막번째 데이터를 들고 올 수도 있습니다.
- 하지만 데이터의 수가 많아지면 애플리케이션에 부담을 줄 수 있기 때문에 내림차순과 오름차순 정렬된 상태에서 상위 하나의 데이터만 들고 오는쪽이 부담을 적게 줄 수 있다고 판단하였습니다.

### 특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측하는 API
- [특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측하는 API Spec](https://github.com/korea8378/Housing-Finance/issues/51)
- 데이터베이스에 저장된 지원금액 데이터(2017년까지만 저장)로 2018년의 지원금액을 예측해야 했습니다.
- 선형 회귀를 이용하면 지원금액을 예측 할 수 있다고 판단하였습니다.
- 기존의 있는 데이터를 이용하여 x(날짜), y(지원금액) 그래프에 점을 찍고 점들에 가장 가까이 있는 직선을 찾습니다.
- 해당 직선의 기준으로 저장되어 있지 않는 데이터의 x(날짜)를 입력하면 직선에서 x(날짜)위치의 y(지원금액)를 반환해주게 됩니다.
