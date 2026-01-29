package com.jjld.domain.notice.dao;

import com.jjld.domain.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeDAO {

    // 페이지&개수만큼의 리스트 호출
    Page<Notice> findAll(Pageable pageable);

    // 고정 게시글 리스트
    List<Notice> findAllByFixStatus();

    // 제목으로 리스트 조회
    List<Notice> findByNoticeTitle(String noticeTitle);

    // 작성자로 리스트 조회
    List<Notice> findByAdminName(String adminName);

    // 공지사항 등록
    void writeNotice(Notice notice);

    // 아이디로 상세내역 조회
    Notice findByNoticeId(Long noticeId);

    // 공지사항 수정
    void updateNotice(Notice notice);

    // 아이디로 공지사항 삭제
    void deleteByNoticeId(Long noticeId);
}
