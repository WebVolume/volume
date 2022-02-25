# volume : Volume Server Api
https://volume-server-api.herokuapp.com

# REST API
[1. 회원 가입](#회원-가입)

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
    "email" : "test@volume.co.kr" }
```

### Response
```javascript
{   "id" : "testerid"}
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
