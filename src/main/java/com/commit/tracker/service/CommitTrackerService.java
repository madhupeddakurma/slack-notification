package com.commit.tracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commit.tracker.dto.CommitDto;
import com.commit.tracker.dto.GitHubPushPayload;
import com.commit.tracker.entity.Author;
import com.commit.tracker.entity.Commit;
import com.commit.tracker.mapper.TrackerMapper;
import com.commit.tracker.repository.AuthorRepository;
import com.commit.tracker.repository.CommitRepository;

import jakarta.transaction.Transactional;

@Service
public class CommitTrackerService {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private CommitRepository commitRepository;

	@Autowired
	private SlackNotifier slackNotifier;

	@Transactional
	public void sendPushRequest(GitHubPushPayload payloadReuest) {
		System.out.println("payloadRequest=> " + payloadReuest);

		if (payloadReuest.getPusher() == null) {
			throw new IllegalArgumentException("Payload missing pusher info");
		}
		Author authorObj = new Author();

		authorObj.setName(payloadReuest.getPusher().getName());
		authorObj.setEmail(payloadReuest.getPusher().getEmail());
		authorObj.setCommits(payloadReuest.getPusher().getCommits());

		List<Commit> commits = payloadReuest.getCommits().stream().map(c -> {
			Commit commit = new Commit();
			commit.setAuthor(authorObj);
			commit.setMessage(c.getMessage());
			commit.setTimestamp(c.getTimestamp());
			return commit;
		}).collect(Collectors.toList());

		authorObj.setCommits(commits);

		authorRepository.save(authorObj);
		slackNotifier.sendPushSummary(authorObj);

	}
	
	public List<CommitDto> findCommitsByAuthor(Long authorId){
		List<Commit>commits=commitRepository.findByAuthorId(authorId);
		List<CommitDto> dto=TrackerMapper.toCommitRequestDto(commits);
		return dto;
	}

}
