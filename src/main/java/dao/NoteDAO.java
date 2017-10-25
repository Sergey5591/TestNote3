package dao;

import model.Note;

import java.util.List;

    public interface NoteDAO {
        int addNote(Note note);
        Note updateNote(Note note);
        void deleteNote(int id);
        List<Note> getAllNotes();
        List<Note> filterByExecuted(Integer id);
        List<Note> sortNotes(Integer id);
        Note get(Integer id);
    }

