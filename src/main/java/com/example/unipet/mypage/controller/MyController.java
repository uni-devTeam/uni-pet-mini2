package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.domain.EmailDTO;
import com.example.unipet.mypage.domain.UserResultDTO;
import com.example.unipet.mypage.service.MyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/mypage")
@RequiredArgsConstructor
@SessionAttributes({"userId", "myname"})
public class MyController {

    private final MyService myService;

    // session 테스트 때문에 RequestParam 으로 받는거 ModelAttribute로 받아야함

    // 회원 정보
    @GetMapping(value = "/myprofile")
    public ResponseEntity<Map<String, Object>> myprofile(@RequestParam("userId") String userId) {
        Optional<UserResultDTO> optionalUser = myService.getUserResultDTO(userId);
        Map<String, Object> response = new HashMap<>();

        if(optionalUser.isPresent()) {
            response.put("user", optionalUser.get());
            return ResponseEntity.ok()
                    .body(response);
        } else {
            response.put("error", "사용자 정보를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    }


    // 로그아웃
    @PostMapping(value = "/logout")
    public void logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
    }

    // 이메일 변경
    @PostMapping(value = "/changeemail")
    public ResponseEntity<String> changeemail(@RequestParam("userId") String userId,
                                              @RequestParam("email") String email,
                                              @RequestParam("domain") String domain) {
        EmailDTO emailDTO = EmailDTO.builder()
                .userId(userId)
                .email(email)
                .domain(domain)
                .build();
        boolean emailChanged = myService.saveEmail(emailDTO);

        if (emailChanged) {
            return ResponseEntity.ok()
                    .body("이메일이 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("사용자 정보를 찾을 수 없습니다.");
        }
    }

    // 비밀번호 확인
    @RequestMapping(value = "/passcheck")
    public ResponseEntity<String> passcheck(@RequestParam("userId") String userId,
                            @RequestParam("inputPass") String inputPass) {
        boolean checked = myService.getPass(userId, inputPass);
        if(checked) {
            return ResponseEntity.ok()
                    .body("비밀번호 확인 완료");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("비밀번호가 일치하지 않습니다.");
        }
    }

    // 비밀번호 변경
    @RequestMapping(value = "/passchange")
    public ResponseEntity<String> passchange(@RequestParam("userId") String userId,
                                             @RequestParam("changedPass") String changedPass,
                                             @RequestParam("changedPassCheck") String changedPassCheck) {
        if(changedPass.equals(changedPassCheck)) {
            boolean pwChanged = myService.changeMyPass(userId, changedPass);
            if (pwChanged) {
                return ResponseEntity.ok()
                        .body("비밀번호 변경이 성공적으로 변경되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("오류가 발생하였습니다.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("비밀번호가 일치하지 않습니다.");
        }
    }

    @PostMapping(value = "/delaccount")
    public ResponseEntity<String> deleteAcc(/* SessionStatus sessionStatus, */ @RequestParam("userId") String userId) {
        boolean success = myService.saveRolesOut(userId);
        if (success) {
            // sessionStatus.setComplete();
            return ResponseEntity.ok("회원 탈퇴 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("회원 탈퇴 실패"); // 실패 시 400 Bad Request 반환
        }
    }
}
