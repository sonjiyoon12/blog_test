package com.tenco.blog_test.repository;

import com.tenco.blog_test.model.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardNativeRepository.class) // 테스트에 사용할 수 있는 해당 클래스 로드
@DataJpaTest // JPA 관련된 테스트만 로드
public class BoardNativeRepositoryTest {

    @Autowired // DI 의존성 주입
    private BoardNativeRepository br;

//    public BoardNativeRepositoryTest (BoardNativeRepository br) {
//        this.br = br;
//    }


    @Test
    public void deleteById_test() {
        Long id = 4L;

        br.deleteById(id);

        List<Board> boardList = br.findAll();
        Assertions.assertThat(boardList.size()).isEqualTo(3);
    }


    @Test
    public void findById_test () {

        Long id = 1L;

        Board board = br.findById(id);

        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.getUsername()).isEqualTo("ssar");
        Assertions.assertThat(board).isNotNull();

    }


    @Test
    public void findAll_test() {
        // given
        //  data.sql 데이터 이미 준비완료

        // when
        List<Board> boardList = br.findAll();

        //then: 결과 검증 예상동작
        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(3).getUsername()).isEqualTo("ssar");
    }
}
