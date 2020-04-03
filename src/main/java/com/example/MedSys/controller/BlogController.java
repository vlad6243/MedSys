package com.example.MedSys.controller;

import com.example.MedSys.domain.Blog;
import com.example.MedSys.domain.User;
import com.example.MedSys.repository.BlogRepository;
import com.example.MedSys.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/blog")
public class BlogController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping
    public List<Blog> getAll(@PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC)Pageable pageable){
        return blogService.getAll(pageable);
    }

    @PostMapping("/add")
    public Blog add(@AuthenticationPrincipal User user,
                    @RequestParam(required = false, name = "file") MultipartFile file,
                    @Valid @RequestBody Blog blog) throws IOException {
        blog.setAuthor(user);
        saveFile(blog, file);

        return blogRepository.save(blog);
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
