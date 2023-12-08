package service;

import dao.ServidorDAO;
import model.Servidor;

import java.util.List;

public class ServidorService {

    private final ServidorDAO servidorDAO = new ServidorDAO();

    public void create(Servidor servidor) throws Exception {
        servidorDAO.create(servidor);
    }

    public void update(Servidor servidor) throws Exception {
        servidorDAO.update(servidor);
    }

    public void delete(int id) throws Exception {
        servidorDAO.delete(id);
    }

    public Servidor getById(int id) throws Exception {
        return servidorDAO.getById(id);
    }

    public List<Servidor> read() throws Exception {
        return servidorDAO.read();
    }

    public void updateHours(Servidor servidor, double horasSemanais, int id) throws Exception {
        servidorDAO.updateHours(servidor, horasSemanais, id);
    }

    public void removeHours(Servidor servidor, double horasSemanais, int id) throws Exception {
        servidorDAO.removeHours(servidor, horasSemanais, id);
    }


}
