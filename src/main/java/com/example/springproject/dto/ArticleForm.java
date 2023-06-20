package com.example.springproject.dto;

import com.example.springproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    public Article toEntity(){
        // Entity인 Article 객체를 반환
        return new Article(id, title, content);
    }

}