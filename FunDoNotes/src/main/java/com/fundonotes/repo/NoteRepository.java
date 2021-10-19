package com.fundonotes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundonotes.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

}
