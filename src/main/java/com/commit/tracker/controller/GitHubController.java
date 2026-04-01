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

import com.commit.tracker.dto.CommitDto;
import com.commit.tracker.dto.GitHubPushPayload;
import com.commit.tracker.entity.Commit;
import com.commit.tracker.service.CommitTrackerService;

@RestController
@RequestMapping("/github/api")
public class GitHubController {

	@Autowired
	private CommitTrackerService commitTrackerService;

	@PostMapping("/push")
	public ResponseEntity<String> sendPush(@RequestBody GitHubPushPayload payloadRequest) {
		System.out.println("payloadRequest=> " + payloadRequest);
		commitTrackerService.sendPushRequest(payloadRequest);
		return ResponseEntity.ok("Processed");
	}

	@GetMapping("/author/{authorName}")
	public ResponseEntity<List<CommitDto>> getAllCommits(@PathVariable String authorName) {
		List<CommitDto> commits = commitTrackerService.findCommitsByAuthor(authorName);
		return ResponseEntity.status(HttpStatus.OK).body(commits);

	}
}
