package com.xiaad.ourbatis;

import com.xiaad.ourbatis.core.session.SqlSession;
import com.xiaad.ourbatis.core.session.SqlSessionFactoryBuilder;
import com.xiaad.ourbatis.mapper.BlogMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSession sqlSession = builder.builder().openSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        System.out.println(mapper.selectBlogByBid(1));
    }
}
