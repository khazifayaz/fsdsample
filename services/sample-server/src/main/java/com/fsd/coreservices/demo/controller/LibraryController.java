package com.fsd.coreservices.demo.controller;

import com.fsd.coreservices.demo.MyResourceNotFoundException;
import com.fsd.coreservices.demo.entity.Book;
import com.fsd.coreservices.demo.entity.User;
import com.fsd.coreservices.demo.entity.UserBook;
import com.fsd.coreservices.demo.model.AssignBookRequest;
import com.fsd.coreservices.demo.model.AssignBookResponse;
import com.fsd.coreservices.demo.model.ReleaseBookRequest;
import com.fsd.coreservices.demo.model.ReleaseBookResponse;
import com.fsd.coreservices.demo.repository.BookRepository;
import com.fsd.coreservices.demo.repository.UserRepository;
import com.fsd.coreservices.demo.time.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.validation.Valid;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;

/**
 */
@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ClockService clockService;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/assignbook")
    public @ResponseBody
    ResponseEntity<AssignBookResponse> assignBookToUser(@Valid @RequestBody AssignBookRequest assignBookRequest) {
        Book book = bookRepository.findOne(assignBookRequest.getBookId());
        User user = userRepository.findOne(assignBookRequest.getUserId());
        if (book.getNum_of_copies() < 1) {
            throw new MyResourceNotFoundException("No of copies are less than 1");
            //return error response
        }

        if (!book.getWaitlist().isEmpty()) {
            throw new MyResourceNotFoundException("Cannot assign book since there is a waitlist for this book.");
        }
        Calendar cal = clockService.getCalendar();

        UserBook userBook = new UserBook(book, user, cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), 0);

        book.setWtUId(-1);
        book.setLast_available_date(null);
        entityManager.merge(book);//

        entityManager.persist(userBook);
        userBook.UserBookPersist(book, user);
        updateBookStatus(book.getBookId());


        return ResponseEntity.ok().body(new AssignBookResponse(book.getBookId(), user.getId()));
//TODO
    }

    public void updateBookStatus(Integer book_Id) {
        String userbook_query = "select ub from UserBook ub where ub.book = " + book_Id;
        List<UserBook> userBooks = entityManager.createQuery(userbook_query, UserBook.class).getResultList();
        Book book = entityManager.find(Book.class, book_Id);
        if (book.getNum_of_copies() == userBooks.size()) {
            System.out.println("changing status");
            book.setCurrent_status("Hold");
            System.out.println("after changing in update fn " + book.getCurrent_status());
        }
    }

    @Transactional
    @PostMapping("/releasebook")
    public @ResponseBody
    ResponseEntity<ReleaseBookResponse> releaseBookFromUser(@Valid @RequestBody ReleaseBookRequest releaseBookRequest) {

        return null;
    }
//TODO

}

