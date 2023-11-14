package com.example.unipet.mypage.service;

import com.example.unipet.mypage.domain.EmailDTO;
import com.example.unipet.mypage.domain.UserResultDTO;
import com.example.unipet.mypage.entity.User;
import com.example.unipet.mypage.repository.MyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyService {
    private final MyRepository myRepository;

    public Optional<UserResultDTO> getUserResultDTO(String userId) {
        Optional<User> optionalUser = Optional.ofNullable(myRepository.findUserByUserId(userId));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResultDTO userResultDTO = UserResultDTO.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
            return Optional.of(userResultDTO);
        } else {
            return Optional.empty();
        }
    }


    // 이메일 변경
    @Transactional
    public boolean saveEmail(EmailDTO emailDTO) {
        Optional<User> optionalUser = Optional.ofNullable(myRepository.findUserByUserId(emailDTO.getUserId()));

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            String combinedEmail = emailDTO.getEmail() + "@" + emailDTO.getDomain();
            user.setEmail(combinedEmail);
            return true;
        } else {
            return false;
        }
    }

    // 비밀번호 확인
    public boolean getPass(String userId, String inputPass) {
        String pw = myRepository.findPasswordByUserId(userId);
        return inputPass.equals(pw);
    }

    // 비밀번호 변경
    @Transactional
    public boolean changeMyPass(String userId, String newPw) {
        Optional<User> optionalUser = Optional.ofNullable(myRepository.findUserByUserId(userId));

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPw);
            return true;
        } else {
            return false;
        }
    }

    // 탈퇴
    @Transactional
    public boolean saveRolesOut(String userId) {
        Optional<User> optionalUser = Optional.ofNullable(myRepository.findUserByUserId(userId));

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            String role = "out";
            user.setRoles(role);
            return true;
        } else {
            return false;
        }
    }

}
