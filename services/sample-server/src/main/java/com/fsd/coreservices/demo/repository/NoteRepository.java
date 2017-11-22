package com.fsd.coreservices.demo.repository;

import com.fsd.coreservices.demo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

}
