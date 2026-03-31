package com.commit.tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commit.tracker.dto.AuthorRequest;
import com.commit.tracker.dto.CommitRequest;
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
	public AuthorRequest saveauthorWithCommits(AuthorRequest authorRequest) {
		Author author = TrackerMapper.toAuthorEntity(authorRequest);
		author.getCommits().forEach(commit -> commit.setAuthor(author));

		Author savedAuthor = authorRepository.save(author);
		slackNotifier.sendCommit(savedAuthor);

		return TrackerMapper.toAuthorRequestDto(savedAuthor);

	}

	@Transactional
	public CommitRequest saveCommit(CommitRequest commitRequest) {
		Author author = authorRepository.findById(commitRequest.getAuthorId())
				.orElseThrow(() -> new RuntimeException("Author not found"));

		Commit commit = TrackerMapper.toCommitEntity(commitRequest);
		commit.setAuthor(author);
		
		Commit savedCommit = commitRepository.save(commit);
		slackNotifier.sendCommit(author);

		return TrackerMapper.toCommitRequestDto(savedCommit);

	}

	public List<Commit> getCommitsByAuthor(Long authorId) {
		return commitRepository.findByAuthorId(authorId);
	}
}
