package com.team.search.Controller;

import com.team.search.DTO.CreateDTO;
import com.team.search.DTO.DetailDTO;
import com.team.search.DTO.ListDTO;
import com.team.search.Service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 제어권이 있는 클래스
@RequiredArgsConstructor
// @RestController
public class SearchController {
    // 클래스 연동
    private final SearchService searchService;

    // 목록
    // ()안에 입력 인수 등록, 출력값이 있으면 model
    @GetMapping(value = {"/", "/list"})  // 메소드명 연관
    public String listView(@PageableDefault(page = 1) Pageable page ,
                           @RequestParam(value = "type",defaultValue = "")String type ,
                           @RequestParam(value = "keyword",defaultValue = "") String keyword ,
                           Model model) {
        // Service 연동
        Page<ListDTO> listDTOS = searchService.list(page , type , keyword);
        // 값전달(Model)
        model.addAttribute("list", listDTOS);
        return "search/list"; // String 연관
    }

    // 삽입(h2-console)
    @GetMapping("/create")
    public String createView() {
        return "search/create";
    }
    @PostMapping("/create")
    public String createProc(CreateDTO createDTO) {
        // Service 통해 내부처리
        searchService.create(createDTO);
        System.out.println(createDTO);
        return "redirect:/";
    }

    // 삭제
    @GetMapping("/delete")
    public String deleteProc(Integer id) {
        return "redirect:/";
    }

    // 상세보기
    @GetMapping("/detail")
    public String detailProc(Integer id , Model model) {
        DetailDTO detailDTO = searchService.detail(id);
        model.addAttribute("data", detailDTO);
        return "search/detail";
    }

    // 수정
    @GetMapping("/modify")
    public String modifyView(Integer id , Model model) {
        return "search/modify";
    }
    @PostMapping("/modify")
    public String modifyProc(DetailDTO detailDTO) {
        return "redirect:/";
    }

}
