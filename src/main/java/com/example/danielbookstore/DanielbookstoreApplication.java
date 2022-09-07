package com.example.danielbookstore;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.danielbookstore.model.Book;
import com.example.danielbookstore.model.Category;
import com.example.danielbookstore.model.CategoryRepository;
import com.example.danielbookstore.model.BookRepository;

@SpringBootApplication
public class DanielbookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(DanielbookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(DanielbookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepo) {
		return (args) -> {
			log.info("save a couple of students");
			crepo.save(new Category("Fantasy"));
			crepo.save(new Category("Thriller"));
			crepo.save(new Category("Non-fiction"));
			
			repository.save(new Book("Harry Potter", "JK Rowling", 1999, "34421331", 20.0, crepo.findByName("Fantasy").get(0)));
			repository.save(new Book("Harry Potter", "JK Rowling", 1999, "34421331", 20.0, crepo.findByName("Fantasy").get(0)));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}