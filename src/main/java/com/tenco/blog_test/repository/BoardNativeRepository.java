package com.tenco.blog_test.repository;

import com.tenco.blog_test.model.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BoardNativeRepository {

    private EntityManager em;


    public BoardNativeRepository(EntityManager em) {
        this.em = em;
    }

    // 게시글 수정하기
    @Transactional
    public void updateById(Long id, String title, String content, String username) {
        String sql = "update board_tb set title = ?, " +
                    "content = ?, username = ? where id =  ? ";
        Query query = em.createNativeQuery(sql);

        query.setParameter(1,title);
        query.setParameter(2,content);
        query.setParameter(3, username);
        query.setParameter(4,id);

         int updateRows = query.executeUpdate();
        System.out.println("수정된 행의 개수 : " + updateRows);
    }


    // 게시글 삭제하기
    @Transactional
    public void deleteById(Long id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ? ");
        query.setParameter(1,id);
        query.executeUpdate();
    }

    // 특정 게시글 조회
    public Board findById(Long id) {
        String sql = "select * from board_tb where id = ? ";

        Query query = em.createNativeQuery(sql, Board.class);
        query.setParameter(1,id);
        return (Board) query.getSingleResult();
    }


    // 게시글 목록 조회
    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc ", Board.class);

        // 여러 행의 결과를 List 객체로 반환
        return query.getResultList();
    }


    // 게시글 작성
    // 트랜잭션 처리
    @Transactional
    public void save(String title, String content, String username) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, username, created_at)" +
                "values(?, ?, ?, now())");

        query.setParameter(1,title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }

}
