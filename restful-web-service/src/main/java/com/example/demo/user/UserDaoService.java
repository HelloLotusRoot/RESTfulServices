package com.example.demo.user;

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
	
	public int login(String name, String password) {
		try {
			for (User user : users) {
				if (user.getName() == name)
					if (user.getPassword() == password)
						return 1; //로그인 성공
					else
						return 0; //비밀번호 불일치
				return -1; // 아이디가 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
	}
}
