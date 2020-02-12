package com.example.MedSys.service;

import com.example.MedSys.domain.Blog;
import com.example.MedSys.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List getAll(){
        return blogRepository.findAll();
    }

    public Blog create(Blog blog){
        return blogRepository.save(blog);
    }

    public void delete(Blog blog){
        blogRepository.delete(blog);
    }


}
