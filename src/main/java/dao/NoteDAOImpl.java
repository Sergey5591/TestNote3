package dao;

import model.Note;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDAOImpl implements NoteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addNote(Note note) {
        return (int) sessionFactory.getCurrentSession().save(note);
    }

    @Override
    public Note updateNote(Note note) {
        sessionFactory.getCurrentSession().update(note);
        return note;
    }

    @Override
    public void deleteNote(int id) {
        Note note = sessionFactory.getCurrentSession().get(Note.class,id);
        sessionFactory.getCurrentSession().delete(note);
    }

    @Override
    public Note get(Integer id) {
        return sessionFactory.getCurrentSession().get(Note.class,id);
    }

    @Override
    public List<Note> getAllNotes() {
        return sessionFactory.getCurrentSession().createQuery("FROM "+Note.class.getName()).list();
    }

    @Override
    public List<Note> sortNotes(Integer id) {
        String queryText;
        switch(id) {
            case 1: {
                queryText = " ORDER BY createdDate";
                break;
            }
            case 0: {
                queryText = " ORDER BY id";
                break;
            }
            default:queryText = "";
        }
        return sessionFactory.getCurrentSession().createQuery("FROM "+Note.class.getName()+queryText).list();
    }

    @Override
    public List<Note> filterByExecuted(Integer id) {
        String queryText;
        switch(id) {
            case 1: {
                queryText = " WHERE isDone = 1";
                break;
            }
            case 0: {
                queryText = " WHERE isDone = 0";
                break;
            }
            default:queryText = "";
        }
        return sessionFactory.getCurrentSession().createQuery("FROM "+Note.class.getName()+queryText).list();
    }
}