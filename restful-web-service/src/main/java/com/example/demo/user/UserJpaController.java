package com.example.demo.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	// http://localhost:8088/jpa/users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{userId}")
	public EntityModel<User> retrieveAllUsers(@PathVariable int userId) {
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID[%s] not found", userId));
		}

		EntityModel<User> userModel = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		userModel.add(linkTo.withRel("all-users"));

		return userModel;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUserId()).toUri();

		return ResponseEntity.created(location).build();
	}

	/*
	 * @PutMapping("/users/{id}") public ResponseEntity<User> putUser(@PathVariable
	 * int id, @RequestBody User user) { User savedUser = userRepository.save(user);
	 * 
	 * URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (savedUser.getId()) .toUri();
	 * 
	 * return ResponseEntity.created(location).build();
	 * 
	 * }
	 */

	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userRepository.deleteById(userId);
	}

	// /jpa/users/90001/posts
	@GetMapping("/users/{userId}/posts")
	public List<Post> retrieveAllPostsByUser(@PathVariable int userId) {
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID[%s] not found", userId));
		}
		return user.get().getPosts();
	}

	@PostMapping("/users/{userId}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post) {
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new UserNotFoundException(String.format("ID[%s] not found", userId));
		}

		post.setUser(user.get());
		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getPostId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
