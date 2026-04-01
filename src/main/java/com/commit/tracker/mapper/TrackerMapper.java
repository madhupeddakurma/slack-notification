package com.commit.tracker.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

import com.commit.tracker.dto.AuthorDto;
import com.commit.tracker.dto.CommitDto;
import com.commit.tracker.entity.Author;
import com.commit.tracker.entity.Commit;

public class TrackerMapper {
	public static Commit toCommitEntity(CommitDto commitRequest) {
		Commit commit = new Commit();
		BeanUtils.copyProperties(commitRequest, commit);
		return commit;
	}

	public static List<CommitDto> toCommitRequestDto(List<Commit> commits) {
		return commits.stream().map(commit -> {
			Author author = commit.getAuthor();
			AuthorDto authorDto = new AuthorDto(author.getName(), author.getEmail());

			return new CommitDto(commit.getId(), commit.getMessage(), commit.getTimestamp(), authorDto);
		}).collect(Collectors.toList());
	}
}
