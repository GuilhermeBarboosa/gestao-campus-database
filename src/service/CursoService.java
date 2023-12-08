package service;

import dao.CursoDAO;
import model.Curso;

import java.util.List;

public class CursoService {
    private final CursoDAO cursoDAO = new CursoDAO();

    public List<Curso> read() throws Exception {
        return cursoDAO.read();
    }

    public void create(Curso curso) throws Exception {
        cursoDAO.create(curso);
    }

    public Curso getById(int id) throws Exception {
        return cursoDAO.getById(id);
    }

    public void update(Curso curso) throws Exception {
        cursoDAO.update(curso);
    }

    public void delete(int id) throws Exception {
        cursoDAO.delete(id);
    }
}
