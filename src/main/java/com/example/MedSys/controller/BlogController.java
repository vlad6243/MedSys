package com.example.MedSys.controller;

import com.example.MedSys.domain.Blog;
import com.example.MedSys.repository.BlogRepository;
import com.example.MedSys.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/all")
    public Page<Blog> getAll(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
        //(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
        return blogRepository.findAll(pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam(required = false, name = "image") MultipartFile file,
                                 @RequestParam("header") String header,
                                 @RequestParam("text") String text
                                 ) throws IOException {

        Blog blog = Blog.builder()
                .header(header)
                .text(text)
                .build();

        saveFile(blog, file);
        try {
            blogRepository.save(blog);

            Map<Object, Object> response = new HashMap<>();
            response.put("blog.id", blog.getId());

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad inputs value");
        }

    }

    private void saveFile(@Valid Blog blog, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            blog.setImgUrl(resultFilename);
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Blog blog) {
        blogService.delete(blog);
    }
}
