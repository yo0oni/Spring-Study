package com.example.springproject.api;

import com.example.springproject.dto.ArticleForm;
import com.example.springproject.entity.Article;
import com.example.springproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@Slf4j
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> modify(@PathVariable Long id, @RequestBody ArticleForm dto) {

        Article article = dto.toEntity();

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }
}
