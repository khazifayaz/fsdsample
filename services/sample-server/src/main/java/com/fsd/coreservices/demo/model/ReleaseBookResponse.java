package com.fsd.coreservices.demo.model;

/**
 * Created by fayaz on 20-11-2017.
 */
public class ReleaseBookResponse {
    private Long bookId;
    private Long userId;
    private String message;

    public ReleaseBookResponse() {
    }

    public ReleaseBookResponse(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public String getMessage() {
        return "Book " + bookId + " released from " + userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
