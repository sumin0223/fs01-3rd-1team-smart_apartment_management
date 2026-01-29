package com.jjld.domain.notice.controller;

import com.jjld.domain.notice.dto.NoticeDetailRequest;
import com.jjld.domain.notice.dto.NoticeDetailResponse;
import com.jjld.domain.notice.dto.NoticeListResponse;
import com.jjld.domain.notice.service.NoticeService;
import com.jjld.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices/api")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    // 페이지&개수만큼의 리스트 호출
    @GetMapping("/list")
    public ResponseEntity<?> noticeList(
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "1") int page
    ){
        Page<NoticeListResponse> noticeList = noticeService.getNoticeList(size, page-1);
        return ResponseEntity.ok(noticeList);
    }

    // 고정 게시글 리스트
    @GetMapping("fixed")
    public ResponseEntity<?> fixedNoticeList(){
        List<NoticeListResponse> fixedList = noticeService.getFixedNoticeList();
        return ResponseEntity.ok(ApiResponse.success(fixedList));
    }

    // 제목 또는 작성자로 공지사항 리스트 조회
    @GetMapping("/search")
    public ResponseEntity<?> searchNoticeList(
            @RequestParam(name = "search_type") String searchType,
            @RequestParam(name = "keyword") String keyword
    ){
        List<NoticeListResponse> findByTypeList = noticeService.findByTypeList(searchType, keyword);
        return ResponseEntity.ok(ApiResponse.success(findByTypeList));
    }

    // 공지사항 등록
    @PostMapping("/write")
    public ResponseEntity<?> noticeWrite(@RequestBody NoticeDetailRequest writeRequest){
        noticeService.noticeWrite(writeRequest);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 아이디로 상세내역 조회
    @GetMapping("/detail")
    public ResponseEntity<?> noticeDetail(@RequestParam(name = "notice_id") Long notice_id){
        NoticeDetailResponse findByNoticeId = noticeService.findByNoticeId(notice_id);
        return ResponseEntity.ok(ApiResponse.success(findByNoticeId));
    }

    // 공지사항 수정
    @PutMapping("/update")
    public ResponseEntity<?> noticeUpdate(@RequestBody NoticeDetailRequest updateRequest){
        noticeService.updateNotice(updateRequest);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 아이디로 공지사항 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<?> noticeDelete(@RequestParam(name = "notice_id") Long notice_id){
        noticeService.deleteNotice(notice_id);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 게시글 고정으로 바꾸기
    @PutMapping("/{notice_id}/fixStatus/change")
    public ResponseEntity<?> noticeChangeFixStatus(@PathVariable Long notice_id ){
        noticeService.fixStatusChange(notice_id);

        return ResponseEntity.ok(ApiResponse.success());
    }
}
