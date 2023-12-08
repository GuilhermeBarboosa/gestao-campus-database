package service;

import dao.OfertaDAO;
import model.Atividade;
import model.Disciplina;
import model.Oferta;
import model.Servidor;
import view.AtividadeView;
import view.OfertaView;

import java.util.List;

public class OfertaService {

    private OfertaDAO ofertaDAO = new OfertaDAO();
    private final OfertaView ofertaView = new OfertaView();
    private final ServidorService servidorService = new ServidorService();
    private final DisciplinaService disciplinaService = new DisciplinaService();
    private final AtividadeService atividadeService = new AtividadeService();
    private final AtividadeView atividadeView = new AtividadeView();

    public List<Oferta> read() throws Exception {
        return ofertaDAO.read();
    }

    public void create(Oferta oferta) throws Exception {
        Servidor servidor = servidorService.getById(oferta.getServidor().getId());
        Disciplina disciplina = disciplinaService.getById(oferta.getDisciplina().getId());

        Atividade atividadeAux = atividadeView.createAula(servidor, disciplina);
        atividadeService.create(atividadeAux);

        servidorService.updateHours(servidor, disciplina.getCargaHoraria(), oferta.getServidor().getId());

        ofertaDAO.create(oferta);
    }

    public Oferta getById(int id) throws Exception {
        return ofertaDAO.getById(id);
    }

    public void update(Oferta oferta) throws Exception {
        Servidor servidor = servidorService.getById(oferta.getServidor().getId());
        Disciplina disciplina = disciplinaService.getById(oferta.getDisciplina().getId());
        servidorService.removeHours(servidor, disciplina.getCargaHoraria(), oferta.getServidor().getId());

        oferta = ofertaView.update(oferta);

        servidor = servidorService.getById(oferta.getServidor().getId());
        disciplina = disciplinaService.getById(oferta.getDisciplina().getId());
        servidorService.updateHours(servidor, disciplina.getCargaHoraria(), oferta.getServidor().getId());

        ofertaDAO.update(oferta);
    }

    public void delete(Oferta oferta) throws Exception {
        Servidor servidor = servidorService.getById(oferta.getServidor().getId());
        Disciplina disciplina = disciplinaService.getById(oferta.getDisciplina().getId());
        servidorService.removeHours(servidor, disciplina.getCargaHoraria(), oferta.getServidor().getId());

        ofertaDAO.delete(oferta.getServidor().getId());
    }
}
