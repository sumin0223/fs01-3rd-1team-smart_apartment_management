package com.jjld.domain.complaint.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiSummaryService {

    private final RestTemplate restTemplate;
    private static final String AI_URL = "http://localhost:8000/summarize";

    // 민원을 AI 서버로 보내고 요약 결과 반환
    public SummaryResponse summarize(String text){

        AiSummaryRequest body = new AiSummaryRequest(text);

        return
                restTemplate.postForObject(
                        AI_URL,
                        body,
                        SummaryResponse.class);

    }
}
