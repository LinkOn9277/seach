package com.team.search.DTO;

import lombok.*;

import java.time.LocalDateTime;

// 목록에 사용할 DTO
@Getter@Setter@ToString@Builder
@AllArgsConstructor @NoArgsConstructor
public class ListDTO {

    private Integer id;             // 일련번호
    private String subject;         // 제목
    private String author;          // 작성자
    private LocalDateTime modDate;  // 수정일자

}
