package com.example.unipet.mypageTest;

import com.example.unipet.mypage.domain.MypetDTO;
import com.example.unipet.mypage.entity.Mypet;
import com.example.unipet.mypage.repository.MyPetRepository;
import com.example.unipet.mypage.service.MypetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MyPetTest {
    @Autowired private MyPetRepository myPetRepository;

    @BeforeEach
    void pr() { System.out.println("=".repeat(80)); }

    // 나의 펫
    @Test
    void getMyPet(){
        Optional<Mypet> mypetOptional = myPetRepository.findMyPetByUserId("user");
        if(mypetOptional.isPresent()) {
            System.out.println("보유 펫: " + mypetOptional.get());
        } else {
            System.out.println("펫이 존재하지 않습니다.");
        }
    }

    // 펫 등록
    @Test
    void petSave() {
        Mypet mypet = Mypet.builder()
                .userId("user1")
                .petName("Fluffy")
                .petBirth(LocalDate.of(2020, 1, 1))
                .petGender("f")
                .petKind("치와와")
                .petNeuter("n")
                .petPic("examplePicPath")
                .petColor("갈색")
                .petWeight(3.5)
                .petTrait("Playful")
                .build();
        System.out.println("저장된 펫 정보: " + myPetRepository.save(mypet));
    }

    // 나의 펫 사진 url
    @Test
    void getPetPic() {
        System.out.println("펫 url: " + myPetRepository.findPetPicByUserId("user"));
    }

    // 펫 정보 수정
    @Test
    void changePetInfo() {
        Optional<Mypet> optionalMypet = myPetRepository.findMyPetByUserId("user");

        if(optionalMypet.isPresent()) {
            System.out.println("저장된 펫: " + optionalMypet.get());
            Mypet mypet = optionalMypet.get();
            // 빌더를 사용하여 변경된 값만 설정
            MypetDTO updatedPet = MypetDTO.builder()
                    .petName("멍멍이")
                    .petKind("리트리버")
                    .petBirth(LocalDate.parse("2018-05-24"))
                    .petGender("n")
                    .petNeuter("n")
                    .petWeight(2.5)
                    .build();

            // 변경된 값만 업데이트
            mypet.updatePetInfo(updatedPet);

            System.out.println("수정된 펫: " + myPetRepository.findMyPetByUserId("user").get());
        }
    }

    // 펫 삭제
    @Test
    void deletePet() {
        Optional<Mypet> optionalMypet = myPetRepository.findMyPetByUserId("user");

        if(optionalMypet.isPresent()) {
            System.out.println("저장된 펫: " + optionalMypet.get());
            Mypet mypet = optionalMypet.get();
            myPetRepository.delete(mypet);
            deletePet();
        } else {
            System.out.println("펫이 존재하지 않습니다.");
        }
    }
}
