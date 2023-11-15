package com.example.unipet.mypage.controller;

import com.example.unipet.mypage.domain.EmailDTO;
import com.example.unipet.mypage.domain.UserResultDTO;
import com.example.unipet.mypage.service.MyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@SessionAttributes("userId")
@CrossOrigin(originPatterns = {"http://localhost:5173"})
public class MyController {

    private final MyService myService;

    // session 테스트 때문에 RequestParam 으로 받는거 ModelAttribute로 받아야함

    // 회원 정보
    @GetMapping(value = "/myprofile")
    public ResponseEntity<Map<String, Object>> myprofile(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 세션에서 가져오기 때문에 나중에 삭제 필요
        session.setAttribute("userId", "user");
        String userId = (String) session.getAttribute("userId");

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
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
    }

    // 이메일 변경
    @PostMapping(value = "/changeemail")
    public ResponseEntity<String> changeemail(HttpServletRequest request,
                                              @RequestParam("email") String email,
                                              @RequestParam("domain") String domain) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

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
    public ResponseEntity<String> passcheck(HttpServletRequest request, @RequestParam("inputPass") String inputPass) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

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
    public ResponseEntity<String> passchange(HttpServletRequest request,
                                             @RequestParam("changedPass") String changedPass,
                                             @RequestParam("changedPassCheck") String changedPassCheck) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

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
    public ResponseEntity<String> deleteAcc(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        boolean success = myService.saveRolesOut(userId);
        if (success) {
            request.getSession(false);
            session.invalidate();
            return ResponseEntity.ok("회원 탈퇴 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("회원 탈퇴 실패"); // 실패 시 400 Bad Request 반환
        }
    }
}
