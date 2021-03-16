package com.mooc.moocServer.repository;

import com.mooc.moocServer.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>{
    List<Board> findByCategoryId(Long categoryId, Pageable pageable);
}