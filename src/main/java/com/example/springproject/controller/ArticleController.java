package com.example.springproject.controller;

import com.example.springproject.dto.ArticleForm;
import com.example.springproject.dto.CommentDto;
import com.example.springproject.entity.Article;
import com.example.springproject.repository.ArticleRepository;
import com.example.springproject.service.ArticleService;
import com.example.springproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO를 Entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB에 전달하도록 지시
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1: DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2: 엔티티를 DB로 저장
        // 2-1: DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId())
                .orElse(null);

        // 2-2: 기존 데이터가 있다면, 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        // 3: 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id,
                       Model model) {
        log.info("id = " + id);
        // 1: id로 데이터를 가져옴!
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        // 2: 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
        // 3: 보여줄 페이지를 설정!
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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id) {
        log.info("삭제 요청이 들어왔습니다!");

        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        if (target != null) {
            articleRepository.delete(target);
        }

        return "redirect:/articles";
    }
}
