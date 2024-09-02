package com.example.jobInfoSite.controller;

import com.example.jobInfoSite.model.Board;
import com.example.jobInfoSite.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @Secured({"관리자", "개인회원","기업회원"})
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Long id) {
        log.info("@# deleteBoard()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Check if the current user is the owner of the post or an admin
        Board board = boardService.findById(id);
        if (board.getUser().getUsername().equals(currentUsername) || authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            boardService.delete(id);
        } else {
            throw new SecurityException("You do not have permission to delete this post");
        }
    }
}
