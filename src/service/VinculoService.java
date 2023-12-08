package service;

import dao.VinculoDAO;
import model.Comissao;
import model.Servidor;
import model.Vinculo;

import java.sql.SQLException;
import java.util.List;

public class VinculoService {
    private final VinculoDAO vinculoDAO = new VinculoDAO();
    private final ServidorService servidorService = new ServidorService();
    private final ComissaoService comissaoService = new ComissaoService();

    public void create(Vinculo vinculo) throws Exception {
        Servidor servAux = servidorService.getById(vinculo.getServidor().getId());
        Comissao comAux = comissaoService.getById(vinculo.getComissao().getId());
        servidorService.updateHours(servAux, comAux.getHorasSemanais(), vinculo.getServidor().getId());

        vinculoDAO.create(vinculo);
    }

    public List<Vinculo> read() throws Exception {
        return vinculoDAO.read();
    }

    public Vinculo getById(int id) throws Exception {
        return vinculoDAO.getById(id);
    }

    public void update(Vinculo vinculo) throws Exception {
        Servidor servAux = servidorService.getById(vinculo.getServidor().getId());
        Comissao comAux = comissaoService.getById(vinculo.getComissao().getId());
        servidorService.removeHours(servAux, comAux.getHorasSemanais(), vinculo.getServidor().getId());

        servAux = servidorService.getById(vinculo.getServidor().getId());
        comAux = comissaoService.getById(vinculo.getComissao().getId());
        servidorService.updateHours(servAux, comAux.getHorasSemanais(), vinculo.getServidor().getId());
        vinculoDAO.update(vinculo);
    }

    public void delete(Vinculo vinculo) throws Exception {
        Servidor servAux = servidorService.getById(vinculo.getServidor().getId());
        Comissao comAux = comissaoService.getById(vinculo.getComissao().getId());
        servidorService.removeHours(servAux, comAux.getHorasSemanais(), vinculo.getServidor().getId());

        vinculoDAO.delete(vinculo.getId());
    }


    public void encerrarVinculos(Comissao comissao) throws SQLException {
        vinculoDAO.encerrarVinculos(comissao);
    }
}
