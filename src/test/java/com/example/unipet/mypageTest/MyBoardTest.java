package com.example.unipet.mypageTest;

import com.example.unipet.mypage.domain.BoardDTO;
import com.example.unipet.mypage.domain.MyPagingDTO;
import com.example.unipet.mypage.service.BoardsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MyBoardTest {
    @Autowired
    private BoardsService boardsService;

    @BeforeEach
    void pr() { System.out.println("=".repeat(80)); }

    @AfterEach
    void prAfter() { System.out.println("=".repeat(80)); }

    // 나의 나눔 가져오기
    @Test
    void getMyShare() {
        MyPagingDTO myPagingDTO = MyPagingDTO.builder()
                .userId("user")
                .page(0)
                .boardId(1)
                .build();
        List<BoardDTO> list = boardsService.getMyWritings(myPagingDTO);

        list.forEach(
                System.out::println
        );
    }


    // 나눔 좋아요 가져오기
//    @Select("SELECT b.board_no, b.board_id, b.title, b.content, b.posting_date, b.img_path " +
//            "FROM boards b " +
//            "LEFT JOIN uni_pet.like l " +
//            "ON b.board_no = l.board_no " +
//            "WHERE l.user_id = #{userId} " +
//            "ORDER BY l.board_no DESC")
//    public List<BoardVO> getShareLikes();

//    @Select("SELECT count(*) " +
//            "FROM boards " +
//            "WHERE user_id = #{userId}")
//    public int countWritings(@Param("userId") String userId);

//    @Select("SELECT board_no, board_id, title, posting_date, " +
//            "ROW_NUMBER() OVER (ORDER BY board_no) as num " +
//            "FROM boards " +
//            "WHERE board_id = 2 AND user_id = #{userId} " +
//            "ORDER BY board_no DESC " +
//            "LIMIT #{limit} " +
//            "OFFSET #{offset}")
//    public List<MyWritingVO> getPagingItems(@Param("limit") int limit, @Param("offset") int offset,
//                                            @Param("userId") String userId);
}
