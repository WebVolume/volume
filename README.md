# volume : Volume Server Api
https://volume-server-api.herokuapp.com

# REST API
[1. 회원 가입](#회원-가입)

[1-1. 중복 확인](#중복-확인)

[2. 로그인](#로그인)

[3. 프로필 사진 업로드](#프로필-사진-업로드)

[4. 프로필 사진 다운로드](#프로필-사진-다운로드)

[5. 배경 사진 업로드](#배경-사진-업로드)

[6. 배경 사진 다운로드](#배경-사진-다운로드)

[7. 회원 정보 수정](#회원-정보-수정)

***
***
</br>

# 회원 가입

* **URL** : "/api/signup"

* **Method** : POST

### Request
```javascript
{   "id" : "testerid",
    "password" : "testpassword",
    "userName" : "tester",
    "email" : "test@volume.co.kr",
    "kakao" : false}
```
- kakao 연동으로 회원가입 시 : kakao 값을 true로 한 뒤, password는 입력하지 않아도 됨
- 일반 회원가입 시 : kakao 값을 false로 하거나 사용하지 않고, password는 필수임

### Response
```javascript
{   "id" : "testerid"}
```
</br>

***
</br>

# 중복 확인

* **URL** : "/api/signup/checkDuplication"

* **Method** : POST

<br>

<h2>Email 중복 확인</h2>

### Request
```javascript
{
    "email" : "test"
}
```
- Email 중복 확인 시, email만 body로 넘긴다.

### Response
```javascript
{
    "exist": false,     //true (이미 해당 이메일이 사용중), false (해당 이메일이 사용중이지 않음 => 사용할 수 있음)
    "type": "EMAIL",    //이메일 중복 확인 요청했음
    "id": null,         //이메일 중복 확인 요청 시, 아이디는 넘어온게 없으므로 null
    "email": "test"     //중복 확인 된 이메일
}
```

</br>
<h2>Id 중복 확인</h2>

### Request
```javascript
{
    "id" : "tester12"
}
```
- id 중복 확인 시, id만 body로 넘긴다.

### Response
```javascript
{
    "exist": false,     //true (이미 해당 이메일이 사용중), false (해당 이메일이 사용중이지 않음 => 사용할 수 있음)
    "type": "ID",       //아이디 중복 확인 요청했음
    "id": "tester12",   //중복 확인 된 아이디
    "email": null       //아이디 중복 확인 요청 시, 이메일는 넘어온게 없으므로 null
}
```

</br>

***
</br>

# 로그인

* **URL** : "/api/login"

* **Method** : POST

### Request
```javascript
{   "id" : "testerid",
    "password" : "testpassword"}
```

### Response
```javascript
{   "id" : "testerid"}
```

</br>

***
</br>

# 프로필 사진 업로드

* **URL** : "/api/uploadProfilePic"

* **Method** : POST

### Request
```javascript
{   "id" : "testerid",
    "profilePic" : 파일 }
```

### Response
```javascript
{   "id" : "testerid"}
```
</br>

***
</br>

# 프로필 사진 다운로드

* **URL** : "/api/getProfilePic"

* **Method** : GET

### Request
```javascript
{   "id" : "testerid" }
```

### Response
파일

</br>

***
</br>

# 배경 사진 업로드

* **URL** : "/api/uploadBackgroundPics"

* **Method** : POST

### Request
```javascript
{   "id" : "testerid",
    "profilePic" : 파일 }
```

### Response
```javascript
{   "id" : "testerid"}
```

</br>

***
</br>

# 배경 사진 다운로드

* **URL** : "/api/getBackgroundPics"

* **Method** : GET

### Request
```javascript
{   "id" : "testerid" }
```

### Response
파일

</br>

***

</br>

# 회원 정보 수정

**아직 서버 업로드 안됨**

* **URL** : "/api/updateUser"

* **Method** : PATCH

### Request
```javascript
{
    "id" : "tester1",
    "password" : "password1",
    "newPassword" : "1234",
    "userName" : "TestUpdate",
    "email" : "update@email.com"
}
```

### Response
```javascript
{   "id" : "testerid"}
```

</br>
