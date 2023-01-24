package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.model.User;
import com.tweteroo.api.repository.TweetRepository;
import com.tweteroo.api.repository.UserRepository;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    public void create(TweetDTO data, String username) {
        User user = userRepository.findByUsername(username);
        Tweet tweet = new Tweet(data, user.getAvatar(), user.getUsername());
        tweetRepository.save(tweet);
    }

    public Page<Tweet> listAll(int page) {
        int size = 5;
        Pageable filters = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Tweet> tweetsPage = tweetRepository.findAll(filters);

        return tweetsPage;
    }

    public List<Tweet> listAllByUsername(String username) {
        return tweetRepository.findAllByUsernameOrderByIdDesc(username);
    }
}
