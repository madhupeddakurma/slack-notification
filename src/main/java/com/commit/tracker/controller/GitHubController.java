package com.commit.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.commit.tracker.dto.GitHubPushPayload;
import com.commit.tracker.service.CommitTrackerService;

@RestController
@RequestMapping("/github/api")
public class GitHubController {

	@Autowired
	private CommitTrackerService commitTrackerService;
	
	@PostMapping("/push")
	public ResponseEntity<String> sendPush(GitHubPushPayload payloadRequest){
		commitTrackerService.sendPushRequest(payloadRequest);
		 return ResponseEntity.ok("Processed");
	}

	
	}

