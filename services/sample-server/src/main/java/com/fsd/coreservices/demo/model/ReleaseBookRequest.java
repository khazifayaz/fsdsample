package com.fsd.coreservices.demo.model;

/**
 * Created by fayaz on 20-11-2017.
 */
public class ReleaseBookRequest {

    private Long bookId;
    private Long userId;

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
