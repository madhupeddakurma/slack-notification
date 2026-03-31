package com.commit.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.tracker.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
