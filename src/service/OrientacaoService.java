package service;

import dao.OrientacaoDAO;
import model.Orientacao;
import model.Servidor;
import view.OrientacaoView;

import java.util.List;

public class OrientacaoService {

    private final OrientacaoDAO orientacaoDAO = new OrientacaoDAO();
    private final OrientacaoView orientacaoView = new OrientacaoView();
    private final ServidorService servidorService = new ServidorService();

    public void create(Orientacao orientacao) throws Exception {
        Servidor servidor = servidorService.getById(orientacao.getServidor().getId());
        servidorService.updateHours(servidor, orientacao.getHorasSemanais(), orientacao.getServidor().getId());
        orientacaoDAO.create(orientacao);
    }

    public List<Orientacao> read() throws Exception {
        return orientacaoDAO.read();
    }

    public Orientacao getById(int id) throws Exception {
        return orientacaoDAO.getById(id);
    }

    public void update(Orientacao orientacao) throws Exception {

        Servidor servidor = servidorService.getById(orientacao.getServidor().getId());
        servidorService.removeHours(servidor, orientacao.getHorasSemanais(), orientacao.getServidor().getId());


        servidor = servidorService.getById(orientacao.getServidor().getId());
        servidorService.updateHours(servidor, orientacao.getHorasSemanais(), orientacao.getServidor().getId());
        orientacaoDAO.update(orientacaoView.update(orientacao));
    }

    public void delete(Orientacao orientacao) throws Exception {
        Servidor servidor = servidorService.getById(orientacao.getServidor().getId());
        servidorService.removeHours(servidor, orientacao.getHorasSemanais(), orientacao.getServidor().getId());

        orientacaoDAO.delete(orientacao.getId());
    }
}
