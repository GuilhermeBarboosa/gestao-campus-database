package service;

import dao.AtividadeDAO;
import dao.ServidorDAO;
import model.Atividade;
import model.Servidor;
import view.AtividadeView;

import java.sql.SQLException;
import java.util.List;

public class AtividadeService {

    private final ServidorService servidorService = new ServidorService();
    private final AtividadeDAO atividadeDAO = new AtividadeDAO();
    private final AtividadeView atividadeView = new AtividadeView();

    public void create(Atividade atividade) throws Exception {
        Servidor servidor = servidorService.getById(atividade.getServidor().getId());
        servidorService.updateHours(servidor, atividade.getHorasSemanais(), atividade.getServidor().getId());
        atividadeDAO.create(atividade);
    }

    public List<Atividade> read() throws Exception {
        return atividadeDAO.read();
    }

    public Atividade getById(int id) throws Exception {
        return atividadeDAO.getById(id);
    }

    public void update(Atividade atividade) throws Exception {
        Servidor servidor = servidorService.getById(atividade.getServidor().getId());
        servidorService.removeHours(servidor, atividade.getHorasSemanais(), atividade.getServidor().getId());

        atividade = atividadeView.update(atividade);

        servidor = servidorService.getById(atividade.getServidor().getId());
        servidorService.updateHours(servidor, atividade.getHorasSemanais(), atividade.getServidor().getId());

        atividadeDAO.update(atividade);
    }

    public void delete(Atividade atividade) throws Exception {
        Servidor servidor = servidorService.getById(atividade.getServidor().getId());
        servidorService.removeHours(servidor, atividade.getHorasSemanais(), atividade.getServidor().getId());

        atividadeDAO.delete(atividade.getId());
    }
}
