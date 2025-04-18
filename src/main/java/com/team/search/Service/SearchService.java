package com.team.search.Service;

import com.team.search.DTO.CreateDTO;
import com.team.search.DTO.DetailDTO;
import com.team.search.DTO.ListDTO;
import com.team.search.Entity.SearchEntity;
import com.team.search.Repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
// 주석요령 , 설명
// Todo : 앞으로 작업할 지시내용
// FixMe : 수정할 부분에 지시내용


@Service // 문제해결 영역설정
@Transactional
@RequiredArgsConstructor
public class SearchService {
    // 연계 클래스
    private final SearchRepository searchRepository;
    private final ModelMapper modelMapper;

    // Controller 메소드의 ()안에 인수를 보고 작성

    // 목록
    public Page<ListDTO> list(Pageable pageable , String type , String keyword) {
        // 페이지 정보
        int currenPage = pageable.getPageNumber()-1;
        int limit = 10;
        Pageable temp = PageRequest.of(currenPage, limit, Sort.by(Sort.Direction.DESC, "id"));
        // todo : 조건별 조회 추가
        // 2가지 구분(조건대상), 검색어
        // 검색이 여러개면 저장변수를 분리
        Page<SearchEntity> searchEntities;
        // 구분값이 있으면 if문으로(switch) 분리
        // 문자열 비교시 == 부정확 사용X, equals로 대신 사용
        if (type.equals("S") && keyword != null){ // 구분이 제목이고, 검색어가 있으면
            searchEntities = searchRepository.findBySubjectContaining(keyword, temp);
        } else if (type.equals("C") && keyword != null) { // 구분이 내용이고, 검색어가 있으면
            searchEntities = searchRepository.findByContentContaining(keyword, temp);
        } else if (type.equals("A") && keyword != null) { // 구분이 작성자이고 , 검색어가 있으면
            searchEntities = searchRepository.findByAuthorContaining(keyword, temp);
        } else if (type.equals("SC") && keyword != null) { // 구분이 제목+내용이고 , 검색어가 있으면
            searchEntities = searchRepository.findBySubjectContainingOrContentContaining(keyword, keyword, temp);
        } else if (type.equals("SCA") && keyword != null) { // 구분이 제목+내용+작성자이고 , 검색어가 있으면
            searchEntities = searchRepository.findBySubjectContainingOrContentContainingOrAuthorContaining(keyword, keyword, keyword, temp);
        } else { // 해당사항이 없으면
            // 전체조회
            searchEntities = searchRepository.findAll(temp);
        }
        // 변환
        Page<ListDTO> listDTOS = searchEntities.map(data -> modelMapper.map(data, ListDTO.class));
        return listDTOS;
    }

    // 삽입
    public void create(CreateDTO createDTO) {
        // 변환(DTO → Entity) 외부 데이터를 내부용으로 변환(준비)
        SearchEntity searchEntity = modelMapper.map(createDTO, SearchEntity.class);
        // SQL 구동(처리)
        searchRepository.save(searchEntity);
        // 출력
    }

    // 삭제
    public void delete(Integer id) {
        // SQL 처리
        searchRepository.deleteById(id);
    }

    // 상세보기
    public DetailDTO detail(Integer id) {
        // SQL 처리
        Optional<SearchEntity> searchEntity = searchRepository.findById(id);
        // 출력
        DetailDTO detailDTO = modelMapper.map(searchEntity, DetailDTO.class);
        return detailDTO;
    }

    // 수정
    public void modify(DetailDTO detailDTO) {
        // 기존 데이터를 조회해서
        Optional<SearchEntity> temp = searchRepository.findById(detailDTO.getId());
        // 변환
        SearchEntity searchEntity = modelMapper.map(detailDTO, SearchEntity.class);
        // 기존에 등록날짜에 부족한 내용을 추가
        searchEntity.setRegDate(temp.get().getRegDate());
        // SQL 처리
        searchRepository.save(searchEntity);
    }

}
