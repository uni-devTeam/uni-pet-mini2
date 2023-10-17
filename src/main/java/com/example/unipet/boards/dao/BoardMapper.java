package com.example.unipet.boards.dao;

import com.example.unipet.boards.domain.BoardDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select board_no, user_id, board_id, title, content, date_format(posting_date, '%Y-%m-%d') posting_date, views from Boards")
    public List<BoardDTO> list();

    @Insert("insert into boards (user_id, board_id, title, posting_date, content) values (#{user_id}, #{board_id}, #{title}, now(), #{content})")
    public boolean insert(BoardDTO save);

    @Select("select user_id, title, board_no, date_format(posting_date, '%Y.%m.%d. %m:%s') posting_date, content, views from boards where board_no = #{board_no}")
    public BoardDTO showContent(int board_no); //one

    @Update("update boards set views = views + 1 where board_no = #{board_no}")
    void increaseViews(int board_no);

    @Select("select board_no, user_id, board_id, title, content, date_format(posting_date, '%Y-%m-%d') posting_date, views from boards where title like concat('%', #{title},'%')")
    public List<BoardDTO> search(String title);

    @Delete("delete from boards where board_no = #{board_no}")//나중에 user_id = #{user_id}
    public boolean delete(int board_no);

    @Update("update boards set user_id = #{user_id}, content = #{content} where board_no = #{board_no}")
    public boolean update(BoardDTO board);

    @Select("select user_id, title, board_no, date_format(posting_date, '%Y.%m.%d. %m:%s') posting_date, content, views from boards where board_no = #{board_no}")
    public BoardDTO one(int board_no);

}
