package com.zellur.brainstation23.service;

import com.zellur.brainstation23.entity.Post;
import com.zellur.brainstation23.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JdbcTemplate jdbcTemplate;

    public Post save(Post post) {
        return postRepository.save(post);
    }
    public List<Map<String, String>> findAll() {
//        return postRepository.findAll();
        String query = "select * from user u join post p on " +
                "u.id = p.user_id join location l on p.location_id = l.id";
        try {
            return jdbcTemplate.query(query, (resultSet, i) -> {
                Map<String, String> data = new HashMap<>();
                data.put("name", resultSet.getString("first_name"));
                data.put("location", resultSet.getString("location"));
                data.put("post", resultSet.getString("message"));
                return data;
            });
        } catch (DataAccessException de) {
            de.printStackTrace();
            return null;
        }
    }

}
