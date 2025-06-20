package com.tenco.blog_test.repository;

import com.tenco.blog_test.model.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardNativeRepository {

    private EntityManager em;


    public BoardNativeRepository(EntityManager em) {
        this.em = em;
    }

    // 게시글 작성
    // 트랜잭션 처리
    public void save(String title, String content, String username) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, username)" +
                "values(?, ?, ?, now())");

        query.setParameter(1,title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }

    // 게시글 목록 조회
    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc ", Board.class);

        return query.getResultList();
    }
}
