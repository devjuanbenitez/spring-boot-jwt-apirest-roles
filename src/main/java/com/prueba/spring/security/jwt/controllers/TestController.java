package com.prueba.spring.security.jwt.controllers;

import com.prueba.spring.security.jwt.models.Book;
import com.prueba.spring.security.jwt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	BookRepository bookRepository;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@GetMapping("/book")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Book> booksList() {
		return bookRepository.findAll();
	}
	@PostMapping("/book")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<String> booksCreate(@RequestBody Book bookName) {
		Book book=new Book();
		book.setBookName(bookName.getBookName());
		bookRepository.save(book);
		return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully");
	}
	@PutMapping("/book/{id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<String> booksUpdate(@PathVariable Long id,@RequestBody Book bookName ) {
		Book book=bookRepository.getReferenceById(id);
		book.setBookName(bookName.getBookName());
		bookRepository.save(book);
		return ResponseEntity.status(HttpStatus.OK).body("Book update");
	}
	@DeleteMapping("/book/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> booksDelete(@PathVariable Long id ) {
		Book book=bookRepository.getReferenceById(id);
		bookRepository.delete(book);
		return ResponseEntity.status(HttpStatus.OK).body("Book delete");
	}


}
