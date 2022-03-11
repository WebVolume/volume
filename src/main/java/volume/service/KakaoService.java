package volume.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import volume.DTO.KakaoUserDTO;
import volume.entity.User;
import volume.repository.UserRepository;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KakaoService{
    //private final String redirectURL = "http://localhost:8080/oauth/kakao";
    private String redirectURL = "https://volume-server-api.herokuapp.com/oauth/kakao";

    private final UserRepository userRepository;
    private final UserService userService;

    public String getKakaoAccessToken(String code){
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 setDoOutput을 기본값 false -> true로 변경
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //요청시 필요한 파라미터 설정 후 요청 전송
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=f31b9f81fdf64d528eaafa1878d102c3"); // REST_API_KEY 입력
            stringBuilder.append("&redirect_uri="+redirectURL); // redirect_uri 입력
            stringBuilder.append("&code=" + code);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.flush();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200 ){
                System.out.println(conn.getResponseMessage());
                throw new Exception("ResponseCode가 200이 아닙니다. 오류 발생");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonElement element = JsonParser.parseString(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            bufferedReader.close();
            bufferedWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return access_Token;
    }

    public KakaoUserDTO createKakaoUser(String token) throws Exception{
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        KakaoUserDTO kakaoUserDTO = new KakaoUserDTO();

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonElement element = JsonParser.parseString(result);

            String id = Integer.toString(element.getAsJsonObject().get("id").getAsInt());

            User findUser = userRepository.findOne(id);
            if (findUser != null){
                String userId = userService.login(findUser);
                kakaoUserDTO.setId(userId);
                kakaoUserDTO.setLogin(true);
            }
            else {
                kakaoUserDTO.setId(id);

                boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
                if (hasEmail) {
                    String email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
                    kakaoUserDTO.setEmail(email);
                }

                boolean hasAge = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_age_range").getAsBoolean();
                if(hasAge){
                    String age_range = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("age_range").getAsString();
                    int age = Integer.parseInt(age_range.replaceAll("\"","").split("~")[0]);
                    kakaoUserDTO.setAge(age);
                }

                String nickname = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
                if (nickname != null){
                    kakaoUserDTO.setUserName(nickname);
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return kakaoUserDTO;
    }
}
