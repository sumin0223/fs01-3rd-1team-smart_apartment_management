package com.jjld.domain.notice.repository;

import com.jjld.domain.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // 페이지&개수만큼의 데이터 호출
    Page<Notice> findAll(Pageable pageable);

    // 리스트 전체조회

    // 고정 게시글 따로 조회
    List<Notice> findByFixStatus(Boolean fixStatus);

    // 작성자별 게시글 조회
    List<Notice> findByAdmin_AdminNameContaining(String adminName);

    // 제목별 게시글 조회
    List<Notice> findByNoticeTitleContaining(String noticeTitle);

    // 아이디로 상세내역 조회
    Notice findByNoticeId(Long noticeId);

}
