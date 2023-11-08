package com.example.unipet.list.repository;

import com.example.unipet.list.domain.AnimalListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    public interface  AnimalListRepository extends JpaRepository<AnimalListDTO, Integer> {
    AnimalListDTO findByDesertionNo(long desertionNo);
    List<AnimalListDTO> findByHappenDtGreaterThanEqualOrderByHappenDtDesc(String startDate);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM AnimalListDTO a WHERE a.noticeEdt < :endDate")
    void deleteAllByNoticeEdtBefore(@Param("endDate") String endDate);

    AnimalListDTO findByImageId(int imageId);

    //    void deleteAllByNoticeEdtBefore(String noticeEdt);


    }