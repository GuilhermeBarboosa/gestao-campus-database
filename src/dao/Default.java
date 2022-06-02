/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controllers.AtividadeController;
import controllers.CampusController;
import controllers.ComissaoController;
import controllers.CursoController;
import controllers.DisciplinaController;
import controllers.EncerrarComissaoController;
import controllers.OfertaDisciplinaController;
import controllers.OrientacaoController;
import controllers.RelatorioController;
import controllers.ReuniaoController;
import controllers.ReuniaoPresenteController;
import controllers.ServidorController;
import controllers.UserComumController;
import controllers.VinculoServidorComissaoController;
import view.AtividadeView;
import view.CampusView;
import view.ComissaoView;
import view.CursoView;
import view.DisciplinaView;
import view.OfertaView;
import view.OrientacaoView;
import view.ReuniaoPresenteView;
import view.ReuniaoView;
import view.ServidorView;
import view.VinculoView;

/**
 *
 * @author Gui
 */
public interface Default {

    public final CampusController campusController = new CampusController();
    public final ServidorController servidorController = new ServidorController();
    public final CursoController cursoController = new CursoController();
    public final DisciplinaController disciplinaController = new DisciplinaController();
    public final OfertaDisciplinaController ofertaDisciplinaController = new OfertaDisciplinaController();
    public final OrientacaoController orientacaoController = new OrientacaoController();
    public final AtividadeController atividadeController = new AtividadeController();
    public final ComissaoController comissaoController = new ComissaoController();
    public final VinculoServidorComissaoController vinculoServiComi = new VinculoServidorComissaoController();
    public final UserComumController userComController = new UserComumController();
    public final ReuniaoController reuniaoController = new ReuniaoController();
    public final ReuniaoPresenteController reuniaoPresenteController = new ReuniaoPresenteController();
    public final EncerrarComissaoController encerrarComissaoController = new EncerrarComissaoController();
    public final RelatorioController relatorioController = new RelatorioController();

    public final CampusDAO campusDAO = new CampusDAO();
    public final ServidorDAO servidorDAO = new ServidorDAO();
    public final CursoDAO cursoDAO = new CursoDAO();
    public final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    public final OfertaDAO ofertaDAO = new OfertaDAO();
    public final OrientacaoDAO orientacaoDAO = new OrientacaoDAO();
    public final AtividadeDAO atividadeDAO = new AtividadeDAO();
    public final ComissaoDAO comissaoDAO = new ComissaoDAO();
    public final VinculoDAO vinculoDAO = new VinculoDAO();
    public final ReuniaoDAO reuniaoDAO = new ReuniaoDAO();
    public final ReuniaoPresenteDAO reuniaoPresenteDAO = new ReuniaoPresenteDAO();
    public final RelatorioDAO relatorioDAO = new RelatorioDAO();

    public final CampusView campV = new CampusView();
    public final ServidorView servV = new ServidorView();
    public final CursoView cursoV = new CursoView();
    public final DisciplinaView discV = new DisciplinaView();
    public final OfertaView ofertaV = new OfertaView();
    public final OrientacaoView orientacaoV = new OrientacaoView();
    public final AtividadeView atV = new AtividadeView();
    public final ComissaoView comV = new ComissaoView();
    public final VinculoView vincV = new VinculoView();
    public final ReuniaoView reunV = new ReuniaoView();
    public final ReuniaoPresenteView reunPresenteV = new ReuniaoPresenteView();
}
