package com.example.danielbookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.danielbookstore.model.Book;
import com.example.danielbookstore.model.BookRepository;
import com.example.danielbookstore.model.Category;
import com.example.danielbookstore.model.CategoryRepository;
import com.example.danielbookstore.model.User;
import com.example.danielbookstore.model.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
		
		@Autowired
		private BookRepository repository;
		
		@Autowired
		private CategoryRepository crepo;
		
		@Autowired
		private UserRepository urepo;
		
		@Test
		public void findByTitleShouldReturnBook() {
			List<Book> books = repository.findByTitle("TestBook");
			assertThat(books).hasSize(1);
			assertThat(books.get(0).getAuthor()).isEqualTo("Test Author");
		}
		
		@Test
		public void findByNameShouldReturnCategory() {
			List<Category> categories = crepo.findByName("History");
			assertThat(categories).hasSize(1);
			assertThat(categories.get(0).getName()).isEqualTo("History");
		}
		
		@Test
		public void findByUsernameShouldReturnUser() {
			User user = urepo.findByUsername("user");
			List<User> users = new ArrayList<>();
			users.add(user);
			assertThat(users).hasSize(1);
			assertThat(users.get(0).getUsername()).isEqualTo("user");
		}
		
		@Test
		public void createNewBookTest() {
			Book book = new Book("Test", "author test", 2000, "something", 10, crepo.findByName("History").get(0));
			repository.save(book);
			assertThat(book.getId()).isNotNull();
		}
		
		@Test
		public void createNewCategoryTest() {
			Category category = new Category("TestCat");
			crepo.save(category);
			assertThat(category.getCategoryid()).isNotNull();
		}
		
		@Test
		public void createNewUserTest() {
			User user = new User("testuser", "$2a$04$JUA2GU1tS6cQ7sjL3vfm0OSyqANkYDRGf6T0tIjkO36Gq02NGB/D.", "USER");
			urepo.save(user);
			assertThat(user.getId()).isNotNull();
		}
		
		@Test
		public void deleteBookTest() {
			List<Book> books = repository.findByTitle("TestBook");
			repository.deleteById(books.get(0).getId());
			List<Book> book = repository.findByTitle("TestBook");
			assertThat(book).hasSize(0);
		}
		
		@Test
		public void deleteCategoryTest() {
			List<Category> categories = crepo.findByName("History");
			crepo.deleteById(categories.get(0).getCategoryid());
			List<Category> category = crepo.findByName("History");
			assertThat(category).hasSize(0);
		}
		
		@Test
		public void deleteUserTest() {
			User user = urepo.findByUsername("user");
			urepo.deleteById(user.getId());
			List<User> users = new ArrayList<>();
			users.add(urepo.findByUsername("user"));
			assertThat(users.get(0)).isNull();
		}

}
