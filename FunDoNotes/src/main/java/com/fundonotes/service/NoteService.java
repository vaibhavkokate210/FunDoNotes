package com.fundonotes.service;

import java.util.List;

import com.fundonotes.dto.NoteDto;
import com.fundonotes.entity.Note;

public interface NoteService {
	public Note createNote(NoteDto nodeDto ,String token);
	public List<Note> viewNotes(String token);
	public void pinNote(String token, int noteId);
	public void archieveNote(String token, int noteId);
	public String deleteNote(String token, int noteId);
	public void toggleTrashNote(String token, int noteId);
	String addLabelToNote(String token, int noteId, String labelName);
	String deleteLabelFromNote(String token, int noteId, String labelName);
	
}
