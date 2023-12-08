package service;

import dao.CampusDAO;
import dao.ComissaoDAO;
import model.Comissao;

import java.sql.SQLException;
import java.util.List;

public class ComissaoService {

    private final ComissaoDAO comissaoDAO = new ComissaoDAO();

    public void create(Comissao comissao) throws Exception {
        comissaoDAO.create(comissao);
    }

    public List<Comissao> read() throws Exception {
        return comissaoDAO.read();
    }

    public Comissao getById(int id) throws Exception {
        return comissaoDAO.getById(id);
    }

    public void update(Comissao comissao) throws Exception {
        comissaoDAO.update(comissao);
    }

    public void delete(int id) throws Exception {
        comissaoDAO.delete(id);
    }


    public void modificarEncerradomento(Comissao comissao) throws SQLException {
        comissaoDAO.modificarEncerradomento(comissao);
    }
}
