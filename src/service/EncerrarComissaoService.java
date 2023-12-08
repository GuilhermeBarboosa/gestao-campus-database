package service;

import model.Comissao;

import java.sql.SQLException;

public class EncerrarComissaoService {

    private final ComissaoService comissaoService = new ComissaoService();
    private final VinculoService vinculoService = new VinculoService();

    public void encerrarComissaoVinculos(Comissao comissao) throws SQLException {
        vinculoService.encerrarVinculos(comissao);
        comissaoService.modificarEncerradomento(comissao);
    }
}
