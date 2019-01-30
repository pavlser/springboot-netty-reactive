package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostsHandler {
	
	static List<Post> posts;
	
	static {
		posts = new ArrayList<Post>();
		posts.add(new Post(1, "Post body 1", new Date()));
		posts.add(new Post(2, "Post body 2", new Date()));
		posts.add(new Post(3, "Post body 3", new Date()));
	}
	
	public Mono<ServerResponse> findOne(ServerRequest req) {
		try {
			long id = Long.parseLong(req.pathVariable("id"));
			return ServerResponse.ok().body(Mono.just(getById(id)), Post.class);
		} catch (Exception ex) {
			return ServerResponse.ok().body(Mono.just("No entry"), String.class);
		}
	}
	
	public Mono<ServerResponse> findAll(ServerRequest req) {
		return ServerResponse.ok().body(Flux.fromIterable(posts), Post.class);
	}
	
	Post getById(long id) {
		return posts.stream().filter(p -> p.id == id).findFirst().orElse(null);
	}

}

class Post {
	public Long id;
	public String message;
	public Date date;
	
	public Post() {}
	
	public Post(long id, String message, Date date) {
		this.id = id;
		this.message = message;
		this.date = date;
	}
}

