package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BoardtDaoService {
	private static List<Board> boards = new ArrayList<>();

	private static int boardsCount = 0;

	static {
		boards.add(new Board(1, null, "one", "hello", new Date()));
		boards.add(new Board(2, null, "two", "hello", new Date()));
		boards.add(new Board(3, null, "three", "hello", new Date()));
	}
	
	public List<Board> findAll() {
		return boards;
	}

	public Board save(Board board) {
		if (board.getBoardId() == null) {
			board.setBoardId(++boardsCount);
		}

		boards.add(board);
		return board;
	}

	public Board deleteById(int boardId) {
		Iterator<Board> iterator = boards.iterator();

		while (iterator.hasNext()) {
			Board board = iterator.next();

			if (board.getBoardId() == boardId) {
				iterator.remove();
				return board;
			}
		}
		return null;
	}
}
