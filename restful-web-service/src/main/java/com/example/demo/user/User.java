package com.example.demo.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value= {"password", "ssn"})
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private Integer userId;

	@Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
	@ApiModelProperty(notes = "사용자 이름을 입력해 주세요")
	private String name;
	@Past
	@ApiModelProperty(notes = "사용자 등록일을 입력해 주세요")
	private Date joinDate;

	@ApiModelProperty(notes = "사용자 패스워드를 입력해 주세요")
	private String password;
	@ApiModelProperty(notes = "사용자 학번를 입력해 주세요")
	private String ssn;
	
	@OneToMany(mappedBy = "user")
	private List<Board> boards;
	
	public User(int userId, String name, Date joinDate, String password, String ssn) {
		this.userId = userId;
		this.name = name;
		this.joinDate = joinDate;
		this.password = password;
		this.ssn = ssn;
	}
}
