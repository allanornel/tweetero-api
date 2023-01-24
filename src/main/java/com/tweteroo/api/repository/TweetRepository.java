package com.tweteroo.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tweteroo.api.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, String> {
    Page<Tweet> findAll(Pageable page);

    List<Tweet> findAllByUsernameOrderByIdDesc(String username);
}
