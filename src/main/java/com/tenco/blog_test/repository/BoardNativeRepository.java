package com.tenco.blog_test.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

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
}
