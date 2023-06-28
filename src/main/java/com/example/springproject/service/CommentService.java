package com.example.springproject.service;

import com.example.springproject.dto.CommentDto;
import com.example.springproject.entity.Article;
import com.example.springproject.entity.Comment;
import com.example.springproject.repository.ArticleRepository;
import com.example.springproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(article, dto);

        // 댓글엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long articleId, CommentDto dto) {
        Comment target = commentRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 해당 댓글이 존재하지 않습니다."));

        target.patch(dto);

        Comment updated = commentRepository.save(target);

        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long commentId) {
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 해당 댓글이 존재하지 않습니다."));

        if (target == null) {
            return null;
        }

        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}
