package com.commit.tracker.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.commit.tracker.entity.Commit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GitHubPushPayload {
	private Pusher pusher;
	private Repository repository;
	private List<CommitInfo> commits;
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Pusher {
		private String name;
		private String email;
		private List<Commit> commits;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Repository {
		private String name;
		private String full_name;
		private String url;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CommitInfo {
		private String id;
		private String message;
		private OffsetDateTime timestamp;
		private Author author;

		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Author {
			private String name;
			private String email;
		}
	}
}
