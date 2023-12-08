package service;

import dao.ReuniaoPresenteDAO;
import model.ReuniaoPresente;

import java.util.List;

public class ReuniaoPresenteService {

    private final ReuniaoPresenteDAO reuniaoPresenteDAO = new ReuniaoPresenteDAO();

    public void create(ReuniaoPresente reuniaoPresente) throws Exception {
        reuniaoPresenteDAO.create(reuniaoPresente);
    }

    public List<ReuniaoPresente> read() throws Exception {
        return reuniaoPresenteDAO.read();
    }

    public ReuniaoPresente getById(int id) throws Exception {
        return reuniaoPresenteDAO.getById(id);
    }

    public void update(ReuniaoPresente reuniaoPresente) throws Exception {
        reuniaoPresenteDAO.update(reuniaoPresente);
    }

    public void delete(int id) throws Exception {
        reuniaoPresenteDAO.delete(id);
    }
}
