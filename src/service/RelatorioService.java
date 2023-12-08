package service;

import dao.RelatorioDAO;
import model.Oferta;
import model.Reuniao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RelatorioService {

    RelatorioDAO relatorioDAO = new RelatorioDAO();

    public List<Reuniao> relatorioData(LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        return relatorioDAO.relatorioData(dataInicio, dataFim);
    }

    public List<Oferta> relatorioAulas(int id) throws Exception {
        return relatorioDAO.relatorioAulas(id);
    }
}
