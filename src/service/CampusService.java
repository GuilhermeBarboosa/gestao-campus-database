package service;

import dao.CampusDAO;
import model.Campus;

import java.util.List;

public class CampusService {

    private final CampusDAO campusDAO = new CampusDAO();

    public void create(Campus campus) throws Exception {
        campusDAO.create(campus);
    }

    public List<Campus> read() throws Exception {
        return campusDAO.read();
    }

    public Campus getById(int id) throws Exception {
        return campusDAO.getById(id);
    }

    public void update(Campus campus) throws Exception {
        campusDAO.update(campus);
    }

    public void delete(int id) throws Exception {
        campusDAO.delete(id);
    }
}
