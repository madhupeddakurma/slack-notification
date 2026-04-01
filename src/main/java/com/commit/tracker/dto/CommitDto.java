package com.commit.tracker.dto;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitDto {
	private Long id;
	private String message;
	private OffsetDateTime timestamp;
	private AuthorDto author;
}