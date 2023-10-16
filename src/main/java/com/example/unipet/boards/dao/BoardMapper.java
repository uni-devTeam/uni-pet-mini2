package com.example.unipet.boards.dao;

import com.example.unipet.boards.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select board_no, user_id, board_id, title, content, date_format(posting_date, '%Y-%m-%d') posting_date, views from Boards")
    public List<BoardDTO> list();
}
