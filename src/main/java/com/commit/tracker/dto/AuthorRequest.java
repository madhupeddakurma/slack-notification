package com.commit.tracker.dto;

import java.util.ArrayList;
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
public class AuthorRequest {
	
	private Long id;
	private String name;
	private String email;
	
	
	private List<Commit>commits = new ArrayList();
}
