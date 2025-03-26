package com.team.search.Service;

import com.team.search.DTO.CreateDTO;
import com.team.search.DTO.DetailDTO;
import com.team.search.DTO.ListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// 주석요령 , 설명
// Todo : 앞으로 작업할 지시내용
// FixMe : 수정할 부분에 지시내용


@Service // 문제해결 영역설정
@Transactional
public class SearchService {
    // Controller 메소드의 ()안에 인수를 보고 작성

    // 목록
    public Page<ListDTO> list(Pageable pageable , String type , String keyword) {
        return null;
    }

    // 삽입
    public void create(CreateDTO createDTO) {

    }

    // 삭제
    public void delete(Integer id) {

    }

    // 상세보기
    public DetailDTO detail(Integer id) {
        return null;
    }

    // 수정
    public void modify(DetailDTO detailDTO) {

    }

}
