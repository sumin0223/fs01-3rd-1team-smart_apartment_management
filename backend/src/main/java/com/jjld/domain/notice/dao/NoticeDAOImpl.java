package com.jjld.domain.notice.dao;

import com.jjld.domain.notice.entity.Notice;
import com.jjld.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAOImpl implements NoticeDAO {
    private final NoticeRepository noticeRepository;

    // 페이지&개수만큼의 리스트 호출
    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    // 고정 게시글 리스트
    @Override
    public List<Notice> findAllByFixStatus() {
        Boolean fixStatus = true;
        return noticeRepository.findByFixStatus(fixStatus);
    }

    @Override
    public List<Notice> findByNoticeTitle(String noticeTitle) {
        return noticeRepository.findByNoticeTitleContaining(noticeTitle);
    }

    @Override
    public List<Notice> findByAdminName(String adminName) {
        return noticeRepository.findByAdmin_AdminNameContaining(adminName);
    }

    // 공지사항 등록
    @Override
    public void writeNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    // 아이디로 상세내역 조회
    @Override
    public Notice findByNoticeId(Long noticeId) {
        return noticeRepository.findByNoticeId(noticeId);
    }

    // 공지사항 수정
    @Override
    public void updateNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    // 아이디로 공지사항 삭제
    @Override
    public void deleteByNoticeId(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}
