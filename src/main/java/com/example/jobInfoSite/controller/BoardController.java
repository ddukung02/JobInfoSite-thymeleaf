package com.example.jobInfoSite.controller;

import com.example.jobInfoSite.model.Board;
import com.example.jobInfoSite.repository.BoardRepository;
import com.example.jobInfoSite.service.BoardService;
import com.example.jobInfoSite.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
        log.info("@# list()");
        log.info("@# searchText=>" + searchText);
        log.info("@# pageable page number => " + pageable.getPageNumber());

        Page<Board> boards = boardRepository.findByTitleContainingOrContent(searchText, searchText, pageable);

        int totalPages = boards.getTotalPages();
        int currentPage = boards.getPageable().getPageNumber() + 1; // 현재 페이지 번호는 0부터 시작하므로 1을 더해줍니다.

        // 최소 한 페이지가 표시되도록 보장
        int startPage = Math.max(1, currentPage - 4);
        int endPage = totalPages == 0 ? 1 : Math.min(totalPages, currentPage + 4);

        log.info("@# startPage => " + startPage);
        log.info("@# endPage => " + endPage);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        log.info("@# GetMapping form()");
        log.info("@# id=>" + id);

        if (id != null) {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
            log.info("@# board=>" + board);
        } else {
            model.addAttribute("board", new Board());
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String form(@Valid Board board, BindingResult bindingResult) {
        // 서버단에서 validation 체크
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        // 스프링 시큐리티 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // boardRepository.save(board);
        boardService.save(username, board);

        return "redirect:/board/list";
    }
}
