package com.example.demo.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BoardDaoService {
	private static List<Board> boards = new ArrayList<>();

	private static int boardsCount = 0;

	static {
		boards.add(new Board(1, null, "gong", "one", "hello", new Date()));
		boards.add(new Board(2, null, "kim", "two", "hello", new Date()));
		boards.add(new Board(3, null, "shin", "three", "hello", new Date()));
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

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public List<Board> getBoardList(String searchField, String searchText) {
		System.out.println("==> getBoardList() 기능 처리됨!");
		List<Board> boardList = new ArrayList<Board>(); // 가변 배열 객체 생성

		try {

			conn = JDBCUtil.getConnection();

			/*
			 * [중요] 게시물 검색 시 => '제목' or '작성자'로 검색 조건 제시하는 SQL 문장을 어떻게 작성할 것 인가? 하나의 sql 문장을
			 * 두가지 용도로 사용 1. 검색 조건이 없을 때 => 전체 검색 2. 검색 조건이 있을 때 => 조건 검색
			 */
			String where = "";
			if (searchField != null && searchText != null) {
				where = "where " + searchField + " like '%" + searchText + "%'";
			}

			System.out.println("where: " + where);

			String Condition_SQL = "select * from boards " + where + " order by board_Id desc";

			pstmt = conn.prepareStatement(Condition_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board board = new Board();
				board.setBoardId(rs.getInt("BOARD_Id"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setBoardDate(rs.getDate("BOARD_DATE"));
				boardList.add(board);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}

		return boardList;
	}

	public Board getBoard(Board boardDO) {
		System.out.println("==> getBoard() 처리됨");

		Board board = null;

		try {
			conn = JDBCUtil.getConnection();

			// 그런 다음 해당 게시글 가져오기
			String BOARD_GET = "select * from boards where BOARD_Id=?";
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, boardDO.getBoardId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();

				board.setBoardId(rs.getInt("BOARD_Id"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setBoardDate(rs.getDate("BOARD_DATE"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}

		return board;
	}

	// 게시글 수정 처리 메소드
	public int updateBoard(Board boardDO) {
		System.out.println("==> updateBoard() 처리됨!");

		int result = 0;
		try {
			conn = JDBCUtil.getConnection();

			String BOARD_UPDATE = "update boards set BOARD_TITLE=?, BOARD_CONTENT=? where BOARD_Id=?";

			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, boardDO.getBoardTitle());
			pstmt.setString(3, boardDO.getBoardContent());
			pstmt.setInt(4, boardDO.getBoardId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

		System.out.println(result);
		return result;
	}

	public void deleteBoard(Board boardDO) {
		System.out.println("==> deleteBoard() 처리됨!");

		try {
			conn = JDBCUtil.getConnection();

			String BOARD_DELETE = "delete from boards where BOARD_Id=?";

			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, boardDO.getBoardId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	public void insertBoard(Board boardDO) {
		System.out.println("==> insertBoard() 처리됨!");

		int result = 0;
		try {
			conn = JDBCUtil.getConnection();

			String BOARD_INSERT = "insert into boards(BOARD_Id, BOARD_TITLE, WRITER, BOARD_CONTENT) values((select nvl(max(board_Id),0)+1 from boards),?,?,?)";
			// 게시글이 없어도 boardId 는 1부터 시작해서 1씩 증가

			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, boardDO.getBoardTitle());
			pstmt.setString(2, boardDO.getWriter());
			pstmt.setString(3, boardDO.getBoardContent());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
}
