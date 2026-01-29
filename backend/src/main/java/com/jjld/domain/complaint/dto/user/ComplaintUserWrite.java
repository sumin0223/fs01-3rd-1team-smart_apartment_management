package com.jjld.domain.complaint.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintUserWrite {
    private String title;
    private String category;
    private String content;

    private List<Long> referenceId;
}
