package com.example.springproject.controller;

import com.example.springproject.dto.ArticleForm;
import com.example.springproject.entity.Article;
import com.example.springproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {
    // new를 사용하여 레포지토리 객체를 만들어주지 않아도 되는 이유 : 스프링부트가 알아서 해줌
    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createAritcle(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB에 전달하도록 지시
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }


    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 아티클을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 아티클 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3. 보여줄 페이지를 설정
        return "articles/index";
    }
}
