package com.commit.tracker.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.commit.tracker.entity.Author;

@Component
public class SlackNotifier {

	@Value("${slack.webhook.url}")
	private String slackWebhookUrl;

	public void sendPushSummary(Author author) {
		StringBuilder message = new StringBuilder();
		message.append("*New Push Detected!*\n");
		message.append("Author: ").append(author.getName()).append("\n");
		message.append("Commits:\n");
		author.getCommits().forEach(c -> message.append("- ").append(c.getMessage()).append("\n"));

		// Send to Slack
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> payload = Map.of("text", message.toString());
		restTemplate.postForEntity(slackWebhookUrl, payload, String.class);
	}

}
