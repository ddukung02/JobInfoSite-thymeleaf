package com.example.jobInfoSite.service;

import com.example.jobInfoSite.model.Board;
import com.example.jobInfoSite.model.User;
import com.example.jobInfoSite.repository.BoardRepository;
import com.example.jobInfoSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(String username, Board board) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
        boardRepository.delete(board);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
    }
}
