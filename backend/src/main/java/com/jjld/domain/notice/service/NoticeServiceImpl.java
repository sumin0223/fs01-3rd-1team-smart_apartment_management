package com.jjld.domain.notice.service;

import com.jjld.domain.admin.dao.AdminDAO;
import com.jjld.domain.admin.entity.Admin;
import com.jjld.domain.notice.dao.NoticeDAO;
import com.jjld.domain.notice.dto.NoticeDetailRequest;
import com.jjld.domain.notice.dto.NoticeDetailResponse;
import com.jjld.domain.notice.dto.NoticeListResponse;
import com.jjld.domain.notice.entity.Notice;
import com.jjld.global.exception.admin.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDAO noticeDAO;
    private final AdminDAO adminDAO;

    private final ModelMapper modelMapper;

    // 페이지&개수만큼의 리스트 호출
    @Override
    public Page<NoticeListResponse> getNoticeList(int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createAt").descending());

        return noticeDAO.findAll(pageable)
                .map(notice -> modelMapper.map(notice, NoticeListResponse.class)
                );
    }

    // 고정 게시글 리스트
    @Override
    public List<NoticeListResponse> getFixedNoticeList() {
        return noticeDAO.findAllByFixStatus().stream()
                .map(notice -> modelMapper.map(notice, NoticeListResponse.class))
                .collect(Collectors.toList());
    }

    // 제목 또는 작성자로 공지사항 리스트 조회
    @Override
    public List<NoticeListResponse> findByTypeList(String searchType, String keyword) {

        List<Notice> noticeList;

        switch (searchType){
            case "admin_name" -> noticeList = noticeDAO.findByAdminName(keyword);
            case "notice_title" -> noticeList = noticeDAO.findByNoticeTitle(keyword);
            default -> throw new IllegalArgumentException("지원하지 않는 검색 타입입니다.");
        }

        return noticeList.stream()
                .map(notice -> modelMapper.map(notice, NoticeListResponse.class))
                .toList();
    }

    // 공지사항 등록
    @Override
    public void noticeWrite(NoticeDetailRequest writeRequest) {
        Admin adminEntity = adminDAO.getAdmin(writeRequest.getAdminId())
                .orElseThrow(() -> new AdminNotFoundException());

        Notice entity = Notice.builder()
                .admin(adminEntity)
                .noticeTitle(writeRequest.getNoticeTitle())
                .noticeContent(writeRequest.getNoticeContent())
                .fixStatus(false)
                .build();

        noticeDAO.writeNotice(entity);
    }

    // 아이디로 상세내역 조회
    @Override
    public NoticeDetailResponse findByNoticeId(Long noticeId) {
        Notice noticeEntity = noticeDAO.findByNoticeId(noticeId);

        return modelMapper.map(noticeEntity, NoticeDetailResponse.class);
    }

    // 공지사항 수정
    @Override
    public void updateNotice(NoticeDetailRequest updateRequest) {
        Notice noticeEntity = noticeDAO.findByNoticeId(updateRequest.getNoticeId());
        Admin adminEntity = adminDAO.getAdmin(updateRequest.getAdminId())
                .orElseThrow(() -> new AdminNotFoundException());

        noticeEntity.setAdmin(adminEntity);
        noticeEntity.setNoticeTitle(updateRequest.getNoticeTitle());
        noticeEntity.setNoticeContent(updateRequest.getNoticeContent());

        noticeDAO.updateNotice(noticeEntity);
    }

    // 아이디로 공지사항 삭제
    @Override
    public void deleteNotice(Long noticeId) {

        noticeDAO.deleteByNoticeId(noticeId);
    }

    // 게시글 고정으로 바꾸기
    @Override
    public void fixStatusChange(Long noticeId) {
        Notice noticeEntity = noticeDAO.findByNoticeId(noticeId);

        noticeEntity.setFixStatus(!noticeEntity.getFixStatus());

        noticeDAO.updateNotice(noticeEntity);
    }
}
