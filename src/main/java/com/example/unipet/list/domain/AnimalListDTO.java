package com.example.unipet.list.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AnimalListDTO {
    private Long image_id; // image_id 필드를 추가
    private String happenDt; // 게시일 (완)
    private String noticeNo; // 강원-원주-2023-00604 와 같은 공고 (완)
    private String popfile; // 이미지파일url (완)
    private String processState; // 보호중 or 종료와 같은 상태표기(완)
    private String specialMark; // 특징(완)
    private String happenPlace; // 유기견 발견 장소 (완)
    private String careNm; // 보호소명 (완)
    private String kindCd; // 품종 (완)
    private String noticeSdt;  // 공고 시작 일자 (완)
    private String noticeEdt; // 공고 마감 일자 (완)


    // Getter 및 Setter 메서드를 추가
    public Long getImageId() {
        return image_id;
    }

    public void setImageId(Long image_id) {
        this.image_id = image_id;
    }

}
