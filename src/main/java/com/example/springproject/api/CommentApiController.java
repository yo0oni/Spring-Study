package com.example.springproject.api;

import com.example.springproject.dto.CommentDto;
import com.example.springproject.entity.Article;
import com.example.springproject.entity.Comment;
import com.example.springproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        List<CommentDto> dtos = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {

        CommentDto createdDto = commentService.create(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/articles/comments/{articleId}")
    public ResponseEntity<CommentDto> update(@PathVariable Long articleId, @RequestBody CommentDto dto) {

        CommentDto updatedDto = commentService.update(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/api/articles/comments/{commentId}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long commentId) {

        CommentDto deletedDto = commentService.delete(commentId);

        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
