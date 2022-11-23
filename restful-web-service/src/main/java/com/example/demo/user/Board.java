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
//@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "게시판 도메인 객체")
@Table(name = "boards")
public class Board {

	@Id
	@GeneratedValue
	private int boardId;

	// User : Board -> 1 : (0~N), Main : Sub -> Parent : Child
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	private String writer;

	private String boardTitle;

	private String boardContent;

	@Past
	private Date boardDate;

	public Board(int boardId, User user, String writer, String boardTitle, String boardContent, Date boardDate) {
		super();
		this.boardId = boardId;
		this.user = user;
		this.writer = writer;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
	}

	

}
