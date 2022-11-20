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
@RequestMapping("/boards")
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;

	// http://localhost:8088/posts
	@GetMapping
	public List<Board> retrieveAllBoards() {
		return boardRepository.findAll();
	}

	@GetMapping("/{boardId}")
	public EntityModel<Board> retrieveAllBoards(@PathVariable int boardId) {
		Optional<Board> board = boardRepository.findById(boardId);

		if (!board.isPresent()) {
			throw new BoardNotFoundException(String.format("BoardId[%s] not found", boardId));
		}

		EntityModel<Board> boardModel = EntityModel.of(board.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllBoards());
		boardModel.add(linkTo.withRel("all-boards"));

		return boardModel;
	}

	@PostMapping
	public ResponseEntity<Board> createBoard(@Valid @RequestBody Board board) {
		Board savedBoard = boardRepository.save(board);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{BoardId}")
				.buildAndExpand(savedBoard.getBoardId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{boardId}")
	public void deleteBoard(@PathVariable int boardId) {
		boardRepository.deleteById(boardId);
	}
}
