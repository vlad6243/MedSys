package com.example.MedSys.repository;

import com.example.MedSys.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page findAll(Pageable pageable);
}
