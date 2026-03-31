package com.commit.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommitRequest {
	  private Long commitId;
	    private String message;
	    private String timestamp;
	    private Long authorId;
}
