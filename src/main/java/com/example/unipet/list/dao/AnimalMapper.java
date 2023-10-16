package com.example.unipet.list.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.example.unipet.list.domain.AnimalListDTO;

import java.util.List;

@Mapper
public interface AnimalMapper {
    @Select("SELECT * FROM picture ORDER BY uploaded_at desc")
    List<AnimalListDTO> selectAllAnimals();

    @Insert("INSERT INTO picture (image_id, file_path, caption, uploaded_at, notice_no, happen_place, care_nm, kind_cd, notice_sdt, notice_edt, process_state) " +
            "VALUES (#{image_id}, #{popfile}, #{specialMark}, #{happenDt}, #{noticeNo}, #{happenPlace},#{careNm},#{kindCd},#{noticeSdt}, #{noticeEdt}, #{processState})")
    void insertAnimal(AnimalListDTO animal);


    @Select("SELECT * FROM picture WHERE notice_no = #{noticeNo}")
    AnimalListDTO selectByNoticeNo(String noticeNo);



    @Update("UPDATE picture SET file_path = #{popfile}, caption = #{specialMark}, " +
            "uploaded_at = #{happenDt}, happen_place = #{happenPlace}, " +
            "care_nm = #{careNm}, kind_cd = #{kindCd}, " +
            "notice_sdt = #{noticeSdt}, notice_edt = #{noticeEdt}, " +
            "process_state = #{processState} WHERE notice_no = #{noticeNo}")
    void updateAnimal(AnimalListDTO animal);
}
