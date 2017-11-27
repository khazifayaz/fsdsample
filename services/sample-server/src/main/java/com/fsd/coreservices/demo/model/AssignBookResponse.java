package com.fsd.coreservices.demo.model;

/**
 * Created by fayaz on 20-11-2017.
 */
public class AssignBookResponse {

    private Integer bookId;
    private Integer userId;
    private String message;

    public AssignBookResponse() {
    }

    public AssignBookResponse(Integer bookId, Integer userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public String getMessage() {
        return "Book " + bookId + " assigned to " + userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
