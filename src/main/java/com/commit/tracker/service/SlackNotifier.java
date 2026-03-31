package com.commit.tracker.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.commit.tracker.entity.Author;

@Component
public class SlackNotifier {

	private static final String SLACK_WEBHOOK_URL ="https://fabricative-avoidable-cayla.ngrok-free.dev";
	
	
	public void sendCommit(Author author) {
		StringBuilder message = new StringBuilder();
		message.append("Author").append(author.getName()).append(" (")
		.append(author.getEmail()).append(")").append("Commits: ");
		
		
		author.getCommits().forEach(commits->{
			 message.append(" - ").append(commits.getMessage())
             .append(" at ").append(commits.getTimestamp()).append(" ");
  });
		
		
		RestTemplate  restTemplate = new RestTemplate();
		restTemplate.postForObject(SLACK_WEBHOOK_URL, new SlackMessage(message.toString()), String.class);
	}
	
	private static class SlackMessage{
		private String text;

		public SlackMessage(String text) {
			super();
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
		
	}
}
