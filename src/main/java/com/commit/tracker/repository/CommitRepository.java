package com.commit.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commit.tracker.entity.Commit;
@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
	//List<Commit> findByAuthorId(Long authorId);
	List<Commit> findByAuthorName(String name);
}
