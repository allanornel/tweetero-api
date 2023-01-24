package com.tweteroo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.service.TweetService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid TweetDTO req,
            @RequestHeader(value = "User") String username) {
        service.create(req, username);

        return new ResponseEntity<String>("OK", HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Page<Tweet>> getAllTweets(@RequestParam(defaultValue = "0") int page) {
        Page<Tweet> tweets = service.listAll(page);

        return ResponseEntity.ok().body(tweets);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Tweet>> getTweetsByUser(@PathVariable String username) {
        List<Tweet> tweets = service.listAllByUsername(username);

        return ResponseEntity.ok().body(tweets);
    }

}
