package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PostDaoService {
	private static List<Post> posts = new ArrayList<>();

	private static int postsCount = 0;

	static {
		posts.add(new Post(1, null, "one", "hello", new Date()));
		posts.add(new Post(2, null, "two", "hello", new Date()));
		posts.add(new Post(3, null, "three", "hello", new Date()));
	}
	
	public List<Post> findAll() {
		return posts;
	}

	public Post save(Post post) {
		if (post.getPostId() == null) {
			post.setPostId(++postsCount);
		}

		posts.add(post);
		return post;
	}

	public Post deleteById(int postId) {
		Iterator<Post> iterator = posts.iterator();

		while (iterator.hasNext()) {
			Post post = iterator.next();

			if (post.getPostId() == postId) {
				iterator.remove();
				return post;
			}
		}
		return null;
	}
}
