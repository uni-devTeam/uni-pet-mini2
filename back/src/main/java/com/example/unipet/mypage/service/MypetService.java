package com.example.unipet.mypage.service;

import com.example.unipet.mypage.domain.MypetDTO;
import com.example.unipet.mypage.entity.Mypet;
import com.example.unipet.mypage.repository.MyPetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MypetService {
    private final MyPetRepository myPetRepository;

    // 펫 조회
    public Map<String, Object> getMyPet(String userId) {
        Optional<Mypet> mypetOptional = myPetRepository.findMyPetByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        if (mypetOptional.isPresent()) {
            Mypet mypet = mypetOptional.get();
            String age = calculatePetAge(mypet.getPetBirth());

            response.put("age", age);
            response.put("mypet", mypetOptional.get());

            return response;
        } else {
            String nopet = "등록된 펫이 없습니다.";
            response.put("nopet", nopet);

            return response;
        }
    }

    private String calculatePetAge(LocalDate petBirth) {
        // 년, 개월수 계산해서 전달
        LocalDate now = LocalDate.now();
        long years = ChronoUnit.YEARS.between(petBirth, now);
        long months = ChronoUnit.MONTHS.between(petBirth.plusYears(years), now);

        String age = "(";
        if (years == 0) {
            age += months + "개월)";
        } else if (months == 0) {
            age += years + "년)";
        } else {
            age += years + "년 " + months + "개월)";
        }
        return age;
    }

    // 펫 등록
    @Transactional
    public boolean addMyPet(MypetDTO mypetDTO, MultipartFile file) {
        try {
            // 이미지 파일 업로드 처리
            if (!file.isEmpty()) {
                try {
                    uploadImg(mypetDTO, file); // session 추가해야함
                } catch (IOException e) {
                    e.printStackTrace();
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body("이미지 등록에 실패하였습니다.");
                    return false;
                }
            }
            Mypet mypet = Mypet.builder()
                    .userId(mypetDTO.getUserId())
                    .petName(mypetDTO.getPetName())
                    .petBirth(mypetDTO.getPetBirth())
                    .petGender(mypetDTO.getPetGender())
                    .petKind(mypetDTO.getPetKind())
                    .petNeuter(mypetDTO.getPetNeuter())
                    .petPic(mypetDTO.getPetPic())
                    .petColor(mypetDTO.getPetColor())
                    .petWeight(mypetDTO.getPetWeight())
                    .petTrait(mypetDTO.getPetTrait())
                    .build();
            myPetRepository.save(mypet);
            return true;  // 성공한 경우
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // 실패한 경우
        }
    }

    // 펫 수정
    @Transactional
    public boolean changePetInfo(MypetDTO mypetDTO, MultipartFile file) {
        Optional<Mypet> optionalMypet = myPetRepository.findMyPetByUserId("user");

        if(optionalMypet.isPresent()) {
            // 이미지 파일 업로드 처리
            // dto로 받아서 mypet 엔티티에 저장하고 이미지 경로만 따로 빼기
            if (file.isEmpty()) {
                // 파일이 선택되지 않은 경우, 기존 이미지 경로를 그대로 유지
                String originalPath = myPetRepository.findPetPicByUserId(mypetDTO.getUserId());
                mypetDTO.setPetPic(originalPath);
            } else {
                try {
                    uploadImg(mypetDTO, file); // session 추가해야함
                } catch (IOException e) {
                    e.printStackTrace();
                    // 실패시 처리 필요
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                            .body("이미지 등록에 실패하였습니다.");
                    return false;
                }
            }
            Mypet mypet = optionalMypet.get();

            // 변경된 값만 업데이트
            mypet.updatePetInfo(mypetDTO);
            return true;
        } else {
            return false;
        }
    }

    // 이미지 업로드 메서드 HttpSession session 추가해야함
    private void uploadImg(MypetDTO mypetDTO, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        String correctedPath = currentDirectory.replace("\\", "/");
        UUID uuid = UUID.randomUUID();
        String path = correctedPath + "/src/assets/images/mypage/upload/" + uuid.toString() + fileName;
        System.out.println(path);
        File destinationFile = new File(path);
        file.transferTo(destinationFile);

        String filePath = "/images/mypage/upload/" + uuid.toString() + fileName;
        mypetDTO.setPetPic(filePath);

        // 세션에 새로운 petpic 값을 저장
//        session.setAttribute("petPic", filePath);
        System.out.println("저장 성공");
    }

    // 펫 삭제
    @Transactional
    public boolean deletePet(String userId) {
        Optional<Mypet> optionalMypet = myPetRepository.findMyPetByUserId(userId);

        if(optionalMypet.isPresent()) {
            Mypet mypet = optionalMypet.get();
            myPetRepository.delete(mypet);
            return true;
        } else {
            return false;
        }
    }
}
