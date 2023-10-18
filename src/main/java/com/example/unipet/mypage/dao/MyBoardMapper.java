package com.example.unipet.mypage.dao;

import com.example.unipet.mypage.domain.BoardVO;
import com.example.unipet.mypage.domain.MyWritingVO;
import com.example.unipet.mypage.domain.UserResultDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyBoardMapper {

    // 나의 나눔 가져오기
    @Select("SELECT board_no, board_id, title, content, posting_date, img_path " +
            "FROM boards " +
            "WHERE user_id = #{userId} " +
            "ORDER BY board_no DESC")
    public List<BoardVO> getMyShare(String userId);

    // 나눔 좋아요 가져오기
    @Select("SELECT b.board_no, b.board_id, b.title, b.content, b.posting_date, b.img_path " +
            "FROM boards b " +
            "LEFT JOIN uni_pet.like l " +
            "ON b.board_no = l.board_no " +
            "WHERE l.user_id = #{userId} " +
            "ORDER BY l.board_no DESC")
    public List<BoardVO> getShareLikes(String userId);

    @Select("SELECT board_no, board_id, title, posting_date " +
            "FROM boards " +
            "WHERE board_id = 2 AND user_id = #{userId} " +
            "ORDER BY board_no DESC")
    public List<MyWritingVO> getMyWritings(String userId);
}
