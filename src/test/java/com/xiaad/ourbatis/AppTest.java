package com.xiaad.ourbatis;

import static org.junit.Assert.assertTrue;

import com.xiaad.ourbatis.core.session.SqlSession;
import com.xiaad.ourbatis.core.session.SqlSessionFactoryBuilder;
import com.xiaad.ourbatis.dto.Blog;
import com.xiaad.ourbatis.mapper.BlogMapper;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void insert(){
        Blog blog = new Blog();
        blog.setBid(3);
        blog.setName("Cherry");
        blog.setAuthorId(3);

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSession sqlSession = builder.builder().openSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
//        mapper.insert(2,"Andy",2);
        mapper.insert(blog);
    }

    @Test
    public void select(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSession sqlSession = builder.builder().openSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        System.out.println(mapper.selectBlogByBid(2));
    }
}
