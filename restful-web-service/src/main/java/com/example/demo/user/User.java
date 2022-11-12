package com.example.demo.user;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private Integer id;

	@Size(min = 2)
	private String name;
	@Past
	private Date joinDate;
}
