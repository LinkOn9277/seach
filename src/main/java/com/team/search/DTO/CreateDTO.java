package com.team.search.DTO;

import lombok.*;

import java.time.LocalDateTime;
// 등록DTO
@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
public class CreateDTO {

    private Integer id;             // 일련번호
    private String subject;         // 제목
    private String content;         // 내용
    private String author;          // 작성자
    private LocalDateTime modDate;  // 수정일자

}
