package com.xiaad.ourbatis.mapper;

import com.xiaad.ourbatis.dto.Blog;

public interface BlogMapper {

    Blog selectBlogByBid(Integer bid);

//    void insert(Integer bid,String name,Integer authorId);
    void insert(Blog blog);
}
