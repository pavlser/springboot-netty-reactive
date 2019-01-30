package com.example.demo;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Application {
	
	@Autowired
	PostsHandler postHandler;
	
	@Bean
	RouterFunction<?> routes() {
		return RouterFunctions
			.route(GET("/"), req -> ok()
				.contentType(MediaType.TEXT_HTML)
				.body(Flux.fromIterable(welcome()), String.class))
			.andRoute(GET("/echo"), req -> ServerResponse.ok()
				.body(Flux.just("Echo: ").concatWith(Mono.just(req.queryParams().toString())), String.class))
			.andRoute(GET("/server-events"), req -> ok()
				.body(BodyInserters.fromServerSentEvents(Flux.interval(
					Duration.ofSeconds(1))
						.take(10)
						.map(t -> ServerSentEvent.builder(t)
							.id(String.valueOf(t))
							.comment("event"+t)
							.build()))))
			.andRoute(GET("/post/{id}"), postHandler::findOne)
			.andRoute(GET("/posts"), postHandler::findAll);
	}
	
	List<String> welcome() {
		List<String> res = new ArrayList<String>();
		res.add("Hello from Netty!");
		res.add("<p>Mapped paths:</p>");
		res.add("<li><a href=\"/\">/</a></li>");
		res.add("<li><a href=\"/echo?a=1&b=2&c=3\">echo service</a></li>");
		res.add("<li><a href=\"/posts\">all posts</a></li>");
		res.add("<li><a href=\"/post/1\">post 1</a></li>");
		res.add("<li><a href=\"/post/2\">post 2</a></li>");
		res.add("<li><a href=\"/post/3\">post 3</a></li>");
		res.add("<li><a href=\"/post/4\">post 4</a></li>");
		res.add("<li><a href=\"/server-events\">server events</a></li>");
		return res;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
