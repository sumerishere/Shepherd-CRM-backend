package com.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.template.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}