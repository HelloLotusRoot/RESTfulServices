package com.example.demo.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "게시판 도메인 객체")
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue
	private Integer postId;

	// User : Post -> 1 : (0~N), Main : Sub -> Parent : Child
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	private String postTitle;

	private String postContent;

	@Past
	private Date postDate;

	public Post(int postId, User user, String postTitle, String postContent, Date postDate) {
		this.postId = postId;
		this.user = user;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postDate = postDate;
	}

}
