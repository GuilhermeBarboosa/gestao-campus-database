/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import view.Gui;

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

import dao.AtividadeDAO;
import dao.CampusDAO;
import dao.ComissaoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.OfertaDAO;
import dao.OrientacaoDAO;
import dao.RelatorioDAO;
import dao.ReuniaoDAO;
import dao.ReuniaoPresenteDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
import java.sql.SQLException;

import model.Servidor;

public class gestaoIFTM {
    
    private final CampusController campusController = new CampusController();
    private final ServidorController servidorController = new ServidorController();
    private final CursoController cursoController = new CursoController();
    private final DisciplinaController disciplinaController = new DisciplinaController();
    private final OfertaDisciplinaController ofertaDisciplinaController = new OfertaDisciplinaController();
    private final OrientacaoController orientacaoController = new OrientacaoController();
    private final AtividadeController atividadeController = new AtividadeController();
    private final ComissaoController comissaoController = new ComissaoController();
    private final VinculoServidorComissaoController vinculoServiComi = new VinculoServidorComissaoController();
    private final UserComumController userComController = new UserComumController();
    private final ReuniaoController reuniaoController = new ReuniaoController();
    private final ReuniaoPresenteController reuniaoPresenteController = new ReuniaoPresenteController();
    private final EncerrarComissaoController encerrarComissaoController = new EncerrarComissaoController();
    private final RelatorioController relatorioController = new RelatorioController();

    private final CampusDAO campusDAO = new CampusDAO();
    private final ServidorDAO servidorDAO = new ServidorDAO();
    private final CursoDAO cursoDAO = new CursoDAO();
    private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private final OfertaDAO ofertaDAO = new OfertaDAO();
    private final OrientacaoDAO orientacaoDAO = new OrientacaoDAO();
    private final AtividadeDAO atividadeDAO = new AtividadeDAO();
    private final ComissaoDAO comissaoDAO = new ComissaoDAO();
    private final VinculoDAO vinculoDAO = new VinculoDAO();
    private final ReuniaoDAO reuniaoDAO = new ReuniaoDAO();
    private final ReuniaoPresenteDAO reuniaoPresenteDAO = new ReuniaoPresenteDAO();
    private final RelatorioDAO relatorioDAO = new RelatorioDAO();
    int opcCrud;
    boolean sair = false;
    int auxLoc;
    int locServ;
    Servidor servidorLogin;
    Gui GUI = new Gui();

    public static void main(String[] args) throws Exception {
        new gestaoIFTM();
    }

    private gestaoIFTM() throws Exception {
        principal();
    }

    private void principal() throws Exception {
        try {
            do {
                String[] loginSenha = GUI.login();
                servidorLogin = login(loginSenha[0], loginSenha[1]);
                if (servidorLogin != null) {
                    if (servidorLogin.getPerfil() == 1) {
                        sistemaAdm();
                    } else {
                        sistemaComum();
                    }
                } else {
                    System.out.println("Usuario não encontrado");
                }
            } while (servidorLogin == null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sistemaComum() throws Exception {
        userComController.menu(campusDAO, servidorDAO, cursoDAO,
                disciplinaDAO, ofertaDAO, orientacaoDAO, atividadeDAO,
                comissaoDAO, vinculoDAO, reuniaoDAO, reuniaoPresenteDAO);
        principal();
    }

    public void sistemaAdm() throws Exception {
        int opc = 0;
        do {
            opc = GUI.escolherCRUD();
            switch (opc) {
                case 1:
                    campusController.menu(campusDAO);
                    break;
                case 2:
                    servidorController.menu(servidorDAO, campusDAO);
                    break;
                case 3:
                    cursoController.menu(cursoDAO, campusDAO);
                    break;
                case 4:
                    disciplinaController.menu(disciplinaDAO, cursoDAO);
                    break;
                case 5:
                    ofertaDisciplinaController.menu(ofertaDAO, disciplinaDAO, servidorDAO, cursoDAO, atividadeDAO);
                    break;
                case 6:
                    orientacaoController.menu(servidorDAO, orientacaoDAO);
                    break;
                case 7:
                    atividadeController.menu(atividadeDAO, servidorDAO);
                    break;
                case 8:
                    comissaoController.menu(comissaoDAO);
                    break;
                case 9:
                    vinculoServiComi.menu(vinculoDAO, servidorDAO, comissaoDAO);
                    break;
                case 10:
                    reuniaoController.menu(reuniaoDAO, servidorDAO, comissaoDAO);
                    break;
                case 11:
                    reuniaoPresenteController.menu(reuniaoPresenteDAO, servidorDAO, comissaoDAO);
                    break;
                case 12:
                    relatorioController.gerarRelat(campusDAO, servidorDAO, relatorioDAO);
                    break;
                case 13:
                    encerrarComissaoController.menu(comissaoDAO, vinculoDAO);
                    break;
                default:
                    System.out.println("SAINDO DO MENU...");
            }
        } while (opc != 14);
        principal();
    }

    public Servidor login(String login, String senha) throws SQLException {
        Servidor responseLogin = servidorDAO.login(login, senha);
        return responseLogin;
    }
}
