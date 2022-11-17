package com.example.demo.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	// http://localhost:8088/posts
	@GetMapping
	public List<Post> retrieveAllPosts() {
		return postRepository.findAll();
	}

	@GetMapping("/{postId}")
	public EntityModel<Post> retrieveAllPosts(@PathVariable int postId) {
		Optional<Post> post = postRepository.findById(postId);

		if (!post.isPresent()) {
			throw new PostNotFoundException(String.format("PostId[%s] not found", postId));
		}

		EntityModel<Post> postModel = EntityModel.of(post.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllPosts());
		postModel.add(linkTo.withRel("all-posts"));

		return postModel;
	}

	@PostMapping
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{PostId}")
				.buildAndExpand(savedPost.getPostId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{postId}")
	public void deleteUser(@PathVariable int postId) {
		postRepository.deleteById(postId);
	}
}
