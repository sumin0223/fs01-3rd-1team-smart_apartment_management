package com.jjld.domain.notice.service;

import com.jjld.domain.notice.dto.NoticeDetailRequest;
import com.jjld.domain.notice.dto.NoticeDetailResponse;
import com.jjld.domain.notice.dto.NoticeListResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeService {

    // 페이지&개수만큼의 리스트 호출
    Page<NoticeListResponse> getNoticeList(int size, int page);

    // 고정 게시글 리스트
    List<NoticeListResponse> getFixedNoticeList();

    // 제목 또는 작성자로 공지사항 리스트 조회
    List<NoticeListResponse> findByTypeList(String searchType, String keyword);

    // 공지사항 등록
    void noticeWrite(NoticeDetailRequest writeRequest);

    // 아이디로 상세내역 조회
    NoticeDetailResponse findByNoticeId(Long noticeId);

    // 공지사항 수정
    void updateNotice(NoticeDetailRequest updateRequest);

    // 아이디로 공지사항 삭제
    void deleteNotice(Long noticeId);

    // 게시글 고정으로 바꾸기
    void fixStatusChange(Long noticeId);
}
