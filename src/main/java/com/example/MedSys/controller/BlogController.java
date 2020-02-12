package com.example.MedSys.controller;

import com.example.MedSys.domain.Blog;
import com.example.MedSys.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List getAll(){
        return blogService.getAll();
    }

    @PostMapping
    public Blog add(@RequestBody Blog blog){
        return blogService.create(blog);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Blog blog) {
        blogService.delete(blog);
    }
}
