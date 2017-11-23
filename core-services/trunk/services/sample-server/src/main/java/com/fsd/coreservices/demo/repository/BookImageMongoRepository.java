package com.fsd.coreservices.demo.repository;

import com.fsd.coreservices.demo.entity.BookDocument;

public interface BookImageMongoRepository extends MongoRepository<BookDocument, Integer> {
    public BookDocument findByBookId(Integer bookId);
}