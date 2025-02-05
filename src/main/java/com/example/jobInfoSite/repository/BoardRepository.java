package com.example.jobInfoSite.repository;

import com.example.jobInfoSite.model.Board;
import com.example.jobInfoSite.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUser(User user);

    // 기존에 있는 메서드들
    Page<Board> findByTitleContainingOrContent(String title, String content, Pageable pageable);
}
