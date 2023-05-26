package com.example.springproject.repository;

import com.example.springproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    // iterable 대신 ArrayList로 오버라이딩
    @Override
    ArrayList<Article> findAll();
}
