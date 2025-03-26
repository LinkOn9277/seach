package com.team.search.Repository;

import com.team.search.Entity.SearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {
    // 제목으로 조회
    Page<SearchEntity> findBySubjectContaining(String subject, Pageable pageable);
    // 내용으로 조회
    Page<SearchEntity> findByContentContaining(String content, Pageable pageable);
    // 작성자로 조회
    Page<SearchEntity> findByAuthorContaining(String author, Pageable pageable);
    // 제목+내용 조회
    Page<SearchEntity> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);
    // 제목+내용+작성 조회
    Page<SearchEntity> findBySubjectContainingOrContentContainingOrAuthorContaining(String subject, String content, String author, Pageable page);


    // 쿼리사용시 변수명도 임의사용 가능 , 메소드명도 임의지정 가능
    // 다중문장처리시 각 행마다 "" 범위 지정 , + 으로 행을 결합 , ""사이에 앞,뒤 빈공백 1 칸이상
    @Query("select s from SearchEntity s " +
            " where s.subject like %:keyword% " +
            " or s.content like %:keyword% " +
            " or s.author like %:keyword%")
    Page<SearchEntity> searchAll(String keyword, Pageable pageable);

}
