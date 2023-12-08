package service;

import dao.DisciplinaDAO;
import model.Disciplina;

import java.util.List;

public class DisciplinaService {
    private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    public List<Disciplina> read() throws Exception {
        return disciplinaDAO.read();
    }

    public void create(Disciplina disciplina) throws Exception {
        disciplinaDAO.create(disciplina);
    }

    public Disciplina getById(int id) throws Exception {
        return disciplinaDAO.getById(id);
    }

    public void update(Disciplina disciplina) throws Exception {
        disciplinaDAO.update(disciplina);
    }

    public void delete(int id) throws Exception {
        disciplinaDAO.delete(id);
    }
}
