# volume : Volume Server Api
https://volume-server-api.herokuapp.com

# REST API
<details close>
    <summary> Korean API Doucment</summary>

    
[1. 회원 가입](#회원-가입)

[1-1. 중복 확인](#중복-확인)

[2. 로그인](#로그인)

[3. 프로필 사진 업로드](#프로필-사진-업로드)

[4. 프로필 사진 다운로드](#프로필-사진-다운로드)

[5. 배경 사진 업로드](#배경-사진-업로드)

[6. 배경 사진 다운로드](#배경-사진-다운로드)

[7. 회원 정보 수정](#회원-정보-수정)

[8. 음악 업로드](#음악-업로드)

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
    "exist": false,     //true (이미 해당 아이디가 사용중), false (해당 아이디가 사용중이지 않음 => 사용할 수 있음)
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

***

</br>

# 음악 업로드

* **URL** : "/api/uploadMusic

* **Method** : POST

### Request
```javascript
{   
    "userId" : "testerId",
    "title" : "testMusic",
    "musicFilePath" : 파일 
}
```

### Response
```javascript
{   
    "id" : "testMusicId",
    "title" : "testMusicTitle" 
}
```
</br>

***

</details>

<details close>
    <summary> English API Document </summary>
    
   
[1. Sign up](#sign-up)

[1-1. Check Duplication](#check-duplication)

[2. Login](#login)

[3. Upload Profile Picture](#upload-profile-picture)

[4. Get Profile Picture](#get-profile-picture)

[5. Upload Background Picture](#upload-background-picture)

[6. Get Background Picture](#get-background-picture)

[7. Modify User Information](#modify-user-information)

[8. Upload Music](#upload-music)

***
***
</br>

# Sign Up

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
- Login with Kakao : The value of kakao is true and password is not required
- General Login : The value of kakao is false or not used, and the password is required.

### Response
```javascript
{   "id" : "testerid"}
```
</br>

***
</br>

# Check Duplication

* **URL** : "/api/signup/checkDuplication"

* **Method** : POST

<br>

<h2>Check Email Duplication</h2>

### Request
```javascript
{
    "email" : "test"
}
```
- If you check the email duplicate, just send the email to the body.

### Response
```javascript
{
    "exist": false,     //true (This email is already occupied), false (This email is not in use => Able to use this email)
    "type": "EMAIL",    //Request for duplicate email verification
    "id": null,         //When requesting duplicate email verification, the ID has not been crossed, so null
    "email": "test"     //Duplicate confirmed e-mail
}
```

</br>
<h2>Check Id Duplication</h2>

### Request
```javascript
{
    "id" : "tester12"
}
```
- When confirming the duplication of the id, only the id is handed over to the body.

### Response
```javascript
{
    "exist": false,     //true (This Id is alredy occupied), false (This id is not in use => Able to use this id)
    "type": "ID",       //Request for duplicate ID verification
    "id": "tester12",   //Duplicate identification ID.
    "email": null       //When requesting duplicate ID verification, the email has not been crossed, so null
}
```

</br>

***
</br>

# Login

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

# Upload Profile Picture

* **URL** : "/api/uploadProfilePic"

* **Method** : POST

### Request
```javascript
{   "id" : "testerid",
    "profilePic" : file }
```

### Response
```javascript
{   "id" : "testerid"}
```
</br>

***
</br>

# Get Profile Picture

* **URL** : "/api/getProfilePic"

* **Method** : GET

### Request
```javascript
{   "id" : "testerid" }
```

### Response
file

</br>

***
</br>

# Upload Background Picture

* **URL** : "/api/uploadBackgroundPics"

* **Method** : POST

### Request
```javascript
{   "id" : "testerid",
    "profilePic" : file }
```

### Response
```javascript
{   "id" : "testerid"}
```

</br>

***
</br>

# Get Background Picture

* **URL** : "/api/getBackgroundPics"

* **Method** : GET

### Request
```javascript
{   "id" : "testerid" }
```

### Response
file

</br>

***

</br>

# Modify User Information

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

***

</br>

# Upload Music

* **URL** : "/api/uploadMusic

* **Method** : POST

### Request
```javascript
{   
    "userId" : "testerId",
    "title" : "testMusic",
    "musicFilePath" : file
}
```

### Response
```javascript
{   
    "id" : "testMusicId",
    "title" : "testMusicTitle" 
}
```
</br>

***
</details>
