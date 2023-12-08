package service;


import dao.ReuniaoDAO;
import model.Reuniao;

import java.util.List;

public class ReuniaoService {
    private final ReuniaoDAO reuniaoDAO = new ReuniaoDAO();

    public void create(Reuniao reuniao) throws Exception {
        reuniaoDAO.create(reuniao);
    }

    public List<Reuniao> read() throws Exception {
        return reuniaoDAO.read();
    }

    public Reuniao getById(int id) throws Exception {
        return reuniaoDAO.getById(id);
    }

    public void update(Reuniao reuniao) throws Exception {
        reuniaoDAO.update(reuniao);
    }

    public void delete(int id) throws Exception {
        reuniaoDAO.delete(id);
    }

}
