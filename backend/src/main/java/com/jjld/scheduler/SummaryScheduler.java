package com.jjld.scheduler;


import com.jjld.domain.complaint.ai.AiSummaryService;
import com.jjld.domain.complaint.entity.Complaint;
import com.jjld.domain.complaint.entity.ComplaintAnalysis;
import com.jjld.domain.complaint.entity.Enum.AnalysisPeriodType;
import com.jjld.domain.complaint.entity.Enum.SummaryStatus;
import com.jjld.domain.complaint.repository.ComplaintAnalysisRepository;
import com.jjld.domain.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class SummaryScheduler {
    private final ComplaintRepository complaintRepository;
    private final ComplaintAnalysisRepository analysisRepository;
    private final AiSummaryService aiSummaryService;


    // 1분마다
    @Scheduled(fixedDelay = 60000)
    public void run(){
        List<Complaint> list =
                complaintRepository.findBySummaryStatus(SummaryStatus.WAITING);

        log.info("요약 대상 개수 = {}", list.size());

        for(Complaint c : list){
            String content = c.getContent();

            try{
                // AI 호출
                String summary =
                        String.valueOf(aiSummaryService.summarize(content));

                ComplaintAnalysis analysis =
                        ComplaintAnalysis.builder()
                                .complaint(c)
                                .periodType(AnalysisPeriodType.TEST)
                                .startDate(LocalDate.now())
                                .endDate(LocalDate.now())
                                .summary(summary)
                                .build();

                analysisRepository.save(analysis);

                c.summaryCompleted();

            }catch (Exception e){
                log.info("요약 실패 id= "+c.getComplaintId());
            }
        }
    }
}
