package volume.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import volume.DTO.KakaoUserDTO;
import volume.service.KakaoService;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    //https://kauth.kakao.com/oauth/authorize?client_id=f31b9f81fdf64d528eaafa1878d102c3&redirect_uri=http://localhost:8080/oauth/kakao&response_type=code
    //https://kauth.kakao.com/oauth/authorize?client_id=f31b9f81fdf64d528eaafa1878d102c3&redirect_uri=https://volume-server-api.herokuapp.com/oauth/kakao&response_type=code
//    @GetMapping("/oauth/kakao")
//    public KakaoUserDTO kakaoLogin(@RequestParam String code) throws Exception{
//        String access_Token = kakaoService.getKakaoAccessToken(code);
//        return kakaoService.createKakaoUser(access_Token);
//    }

}
