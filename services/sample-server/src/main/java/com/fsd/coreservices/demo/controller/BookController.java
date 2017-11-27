package com.fsd.coreservices.demo.controller;

import com.fsd.coreservices.demo.MyResourceNotFoundException;
import com.fsd.coreservices.demo.entity.Book;
import com.fsd.coreservices.demo.model.BookPojo;
import com.fsd.coreservices.demo.repository.BookRepository;
import com.fsd.coreservices.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public List<Book> getCurrentBooksByUserId(@PathVariable(value = "id") Long userId) {
        return userRepository.findOne(userId).getCurrentBooks().stream().map(userBook -> {
            return userBook.getBook();
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long bookId) {
        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(book);
    }

    /**
     * @param book1
     * @param modelAndView
     * @return The HTTP response-body to be sent to the calling javascript method.
     */
    @RequestMapping(value = "/searchAllBooks", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    List<BookPojo> searchAllBooks(@ModelAttribute("book") Book book1, ModelAndView modelAndView) {
        List<Book> books = bookRepository.findAll();
        List<BookPojo> booksList = new ArrayList<>();
        for (Book book : books) {
            BookPojo bookPojo = toBookPojo(book);
            booksList.add(bookPojo);
        }
        return booksList;
    }

    @RequestMapping(value = "/paginatedsearch", params = {"page", "size"})
    public Page<BookPojo> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<Book> books = bookRepository.findAll(new PageRequest(page, size));
        Page<BookPojo> dtoPage = bookRepository.findAll(new PageRequest(page, size)).map(new Converter<Book, BookPojo>() {
            @Override
            public BookPojo convert(Book book) {
                return toBookPojo(book);
            }
        });
        if (page > dtoPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        return dtoPage;
    }

    @PostMapping("/")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long bookId, @RequestBody Book bookDetails) {
        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
//update the fields from bookDetails to Book

        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable(value = "id") Long bookId) {
        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        bookRepository.delete(book);
        return ResponseEntity.ok().build();
    }

    private BookPojo toBookPojo(Book book) {
        BookPojo bookPojo = new BookPojo();
        bookPojo.setTitle(book.getTitle());
        bookPojo.setAuthor(book.getAuthor());
        bookPojo.setBookId(book.getBookId());
        bookPojo.setCallNumber(book.getCallnumber());
        bookPojo.setCurrentStatus(book.getCurrent_status());
        bookPojo.setNumberOfCopies(book.getNum_of_copies());
        bookPojo.setPublisher(book.getPublisher());
        bookPojo.setYearOfPublication(book.getYear_of_publication());
        bookPojo.setKeywords(book.getKeywords());
        bookPojo.setIsbn(book.getIsbn());
        bookPojo.setLocation(book.getLocation());
        return bookPojo;
    }
}
