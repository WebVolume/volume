# volume : Volume Server Api

# REST API
[1. 회원 가입](#회원-가입)

[2. 로그인](#로그인)

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
