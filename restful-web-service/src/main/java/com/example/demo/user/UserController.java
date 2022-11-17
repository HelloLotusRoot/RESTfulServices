package com.example.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	private UserDaoService service;

	public UserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping(value = "/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// GET /users/1 or /users/10 -> String
	@GetMapping(value = "/users/{userId}")
	public EntityModel<User> retrieveUser(@PathVariable int userId) {

		User user = service.findOne(userId);

		if (user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", userId));
		}

		// HATEOAS
		// "all-users", SERVER_PATH + "/users"
		// retieveAllUsers
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/users/{userId}")
	public User putUser(@PathVariable int userId, @RequestBody User user) {
		service.updateUser(userId, user);
		return user;
	}

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		User user = service.deleteById(userId);

		if (user == null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", userId));
		}
	}

}
