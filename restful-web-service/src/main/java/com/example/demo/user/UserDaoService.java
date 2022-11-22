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
public class UserDaoService {
	private static List<User> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new User(1, "Kennth", new Date(), "pass1", "701010-1111111"));
		users.add(new User(2, "Alice", new Date(), "pass2", "801010-1111111"));
		users.add(new User(3, "Elena", new Date(), "pass3", "901010-1111111"));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getUserId() == null) {
			user.setUserId(++usersCount);
		}

		users.add(user);
		return user;
	}

	public User findOne(int userId) {
		for (User user : users) {
			if (user.getUserId() == userId)
				return user;
		}

		return null;
	}

	public User updateUser(Integer userId, User user) {
		users.stream().forEach(c -> {
			if (c.getUserId() == userId) {
				c.setName(user.getName());
				c.setJoinDate(user.getJoinDate());
			}
		});
		return user;
	}

	public User deleteById(int userId) {
		Iterator<User> iterator = users.iterator();

		while (iterator.hasNext()) {
			User user = iterator.next();

			if (user.getUserId() == userId) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// SQL 명령어
	private final String USER_GET = "select name, password from users where name=? and password=?";

	public User getUser(User userObj) {
		User user = null;

		try {
			System.out.println("===> JDBC로 getUser() 기능 처리됨 ! ");

			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, userObj.getName());
			pstmt.setString(2, userObj.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("NAME"));
				user.setPassword(rs.getString("PASSWORD"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;
	}

	public void insertUser(User userObj) {
		System.out.println("==> insertUser() 처리됨!");

		try {
			conn = JDBCUtil.getConnection();

			String USER_INSERT = "insert into users(USER_ID, NAME, PASSWORD, SSN, JOIN_DATE) values((select nvl(max(USER_ID),0)+1 from users),?,?,?,NOW())";

			pstmt = conn.prepareStatement(USER_INSERT);
			pstmt.setString(1, userObj.getName());
			pstmt.setString(2, userObj.getPassword());
			pstmt.setString(3, userObj.getSsn());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
}
