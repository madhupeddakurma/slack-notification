package com.commit.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commit.tracker.dto.AuthorRequest;
import com.commit.tracker.dto.CommitRequest;
import com.commit.tracker.mapper.TrackerMapper;
import com.commit.tracker.service.CommitTrackerService;

@RestController
@RequestMapping("/github/api")
public class GitHubController {
	
	@Autowired
	private CommitTrackerService commitTrackerService;
	
	@PostMapping("/saveauthor")
	public ResponseEntity<AuthorRequest> handlePushRequest(@RequestBody AuthorRequest authorRequest) {
		AuthorRequest request=commitTrackerService.saveauthorWithCommits(authorRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(request);
		
	}
	
	@PostMapping("/savecommit")
	public ResponseEntity<CommitRequest> handleCommitRequest(@RequestBody CommitRequest commitRequest){
		CommitRequest request=commitTrackerService.saveCommit(commitRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(request);
	}
	
	   @GetMapping("/author/{authorId}")
	public List<CommitRequest> getAllCommits(@PathVariable Long authorId){
		return commitTrackerService.getCommitsByAuthor(authorId).stream()
				.map(m->TrackerMapper.toCommitRequestDto(m)).toList();
		
	}
}
