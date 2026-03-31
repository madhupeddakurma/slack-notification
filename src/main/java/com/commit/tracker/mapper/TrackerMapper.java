package com.commit.tracker.mapper;

import org.springframework.beans.BeanUtils;

import com.commit.tracker.dto.AuthorRequest;
import com.commit.tracker.dto.CommitRequest;
import com.commit.tracker.entity.Author;
import com.commit.tracker.entity.Commit;

public class TrackerMapper {

	public static Author toAuthorEntity(AuthorRequest AuthorRequest) {

		Author author = new Author();
		BeanUtils.copyProperties(AuthorRequest, author);

		return author;
	}

	public static AuthorRequest toAuthorRequestDto(Author author) {
		AuthorRequest authorRequest = new AuthorRequest();
		BeanUtils.copyProperties(author, authorRequest);

		return authorRequest;

	}

	public static Commit toCommitEntity(CommitRequest commitRequest) {
		Commit commit = new Commit();

		BeanUtils.copyProperties(commitRequest, commit);

		return commit;
	}

	public static CommitRequest toCommitRequestDto(Commit commit) {
		CommitRequest commitRequest = new CommitRequest();

		BeanUtils.copyProperties(commit, commitRequest);
		return commitRequest;

	}

}
