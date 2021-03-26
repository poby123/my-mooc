package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.BoardDto;
import com.mooc.moocServer.entity.Board;
import com.mooc.moocServer.entity.Category;
import com.mooc.moocServer.entity.Member;
import com.mooc.moocServer.entity.UploadFile;
import com.mooc.moocServer.mapper.BoardMapper;
import com.mooc.moocServer.repository.BoardRepository;
import com.mooc.moocServer.repository.CategoryRepository;
import com.mooc.moocServer.repository.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final BoardMapper boardMapper;
    private final FileService fileService;

    @Transactional
    public BoardDto.Response addBoard(@NonNull String memberId, @NonNull Long categoryId, @NonNull String content, MultipartFile[] files) throws NullPointerException {

        // 멤버 가져오기
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new NullPointerException("[보드 추가시] 해당 멤버가 존재하지 않습니다."));

        // 카테고리 가져오기
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category category = categoryOptional.orElseThrow(() -> new NullPointerException("[보드 추가시] 해당 카테고리가 존재하지 않습니다."));

        // 글 만들기
        Board board = Board.createBoard(member, category, content);

        // 첨부 파일 저장.
        if(files != null && files.length > 0 && !files[0].isEmpty()){
            List<UploadFile> uploadedFiles = Arrays.asList(files).stream().map(file -> fileService.store(file, board)).collect(Collectors.toList());
            board.setFiles(uploadedFiles);
        }

        member.addBoard(board);
        category.addBoard(board);
        boardRepository.save(board);

        return boardMapper.boardToBoardResponse(board);
    }

    // 카테고리로 글 가져오기
    public List<BoardDto.Response> getBoards(@NonNull Long categoryId, Pageable pageable) throws NullPointerException {
        List<Board> boards = boardRepository.findByCategoryId(categoryId, pageable);
        return boardMapper.boardToBoardResponseList(boards);
    }

    // 아이디로 글 가져오기
    public BoardDto.Response getBoard(@NonNull Long id) throws NullPointerException {
        Optional<Board> boardOptional = boardRepository.findById(id);
        Board board = boardOptional.orElseThrow(() -> new NullPointerException("존재하지 않는 글입니다."));

        return boardMapper.boardToBoardResponse(board);
    }
}
