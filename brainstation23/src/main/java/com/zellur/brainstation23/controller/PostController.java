package com.zellur.brainstation23.controller;

import com.zellur.brainstation23.entity.Post;
import com.zellur.brainstation23.service.PostService;
import com.zellur.brainstation23.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public Callable<ResponseEntity<Post>> savePost(@RequestBody Post post) {
        post.setUserId(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        log.debug("Received request for locations: ");
        return () -> ResponseEntity.ok(postService.save(post));
    }
    @RequestMapping(value = "/getPostList", method = RequestMethod.GET)
    public Callable<ResponseEntity<List<Map<String, String>>>> getPostList() {
        log.debug("Received request for locations: ");
        return () -> ResponseEntity.ok(postService.findAll());
    }
}
