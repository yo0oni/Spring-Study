package com.example.springproject.service;

import com.example.springproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        Article artcile = articleService.show(id);

        assertEquals(expected.toString(), artcile.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        Long id = -1L;
        Article expected = new Article(id, "가가가가", "1111");
        Article artcile = articleService.show(id);

        assertEquals(expected.toString(), artcile.toString());
    }

    @Test
    void create_성공() {
        Long id = -1L;
        Article expected = new Article(id, "가가가가", "1111");
        Article artcile = articleService.create(dto)

        assertEquals(expected.toString(), artcile.toString());
    }

    @Test
    void create_실패() {
    }
}