/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.util.Scanner;
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
import dao.ReuniaoDAO;
import dao.ReuniaoPresenteDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;

import model.Servidor;

/**
 *
 * @author Gui
 */
public class gestaoIFTM {

    private Scanner ler = new Scanner(System.in);

    private CampusController campusController = new CampusController();
    private ServidorController servidorController = new ServidorController();
    private CursoController cursoController = new CursoController();
    private DisciplinaController disciplinaController = new DisciplinaController();
    private OfertaDisciplinaController ofertaDisciplinaController = new OfertaDisciplinaController();
    private OrientacaoController orientacaoController = new OrientacaoController();
    private AtividadeController atividadeController = new AtividadeController();
    private ComissaoController comissaoController = new ComissaoController();
//    private  VinculoServidorComissaoController vinculoServiComi = new VinculoServidorComissaoController();
//    private  UserComumController userComController = new UserComumController();
//    private  ReuniaoController reuniaoController = new ReuniaoController();
//    private  ReuniaoPresenteController reuniaoPresenteController = new ReuniaoPresenteController();
//    private  EncerrarComissaoController encerrarComissaoController = new EncerrarComissaoController();
//    private  RelatorioController relatorioController = new RelatorioController();

    private CampusDAO campusDAO = new CampusDAO();
    private ServidorDAO servidorDAO = new ServidorDAO();
    private CursoDAO cursoDAO = new CursoDAO();
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private OfertaDAO ofertaDAO = new OfertaDAO();
    private OrientacaoDAO orientacaoDAO = new OrientacaoDAO();
    private AtividadeDAO atividadeDAO = new AtividadeDAO();
    private ComissaoDAO comissaoDAO = new ComissaoDAO();
//    private  VinculoDAO vinculoDAO = new VinculoDAO();
//    private  ReuniaoDAO reuniaoDAO = new ReuniaoDAO();
//    private  ReuniaoPresenteDAO reuniaoPresenteDAO = new ReuniaoPresenteDAO();

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
        sistemaAdm();

//        try {
//            do {
//                String[] loginSenha = GUI.login();
//                servidorLogin = login(loginSenha[0], loginSenha[1]);
//                if (servidorLogin != null) {
//                    if (servidorLogin.getPerfil() == 1) {
//                        sistemaAdm();
//                    } else {
//                        sistemaComum();
//                    }
//                } else {
//                    System.out.println("Usuario não encontrado");
//                }
//            } while (servidorLogin == null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    public void sistemaComum() {
//        userComController.menu(campusDAO, servidorDAO, cursoDAO,
//                disciplinaDAO, ofertaDAO, orientacaoDAO, atividadeDAO,
//                comissaoDAO, vinculoDAO, reuniaoDAO, reuniaoPresenteDAO);
//        principal();
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
//                case 9:
//                    vinculoServiComi.menu(vinculoDAO, servidorDAO, comissaoDAO);
//                    break;
//                case 10:
//                    reuniaoController.menu(reuniaoDAO, servidorDAO, comissaoDAO);
//                    break;
//                case 11:
//                    reuniaoPresenteController.menu(reuniaoPresenteDAO, servidorDAO, comissaoDAO);
//                    break;
//                case 12:
//                    relatorioController.gerarRelat(campusDAO, servidorDAO, cursoDAO,
//                            disciplinaDAO, ofertaDAO, orientacaoDAO, atividadeDAO,
//                            comissaoDAO, vinculoDAO, reuniaoDAO, reuniaoPresenteDAO);
//                    break;
//                case 13:
//                    encerrarComissaoController.menu(comissaoDAO, vinculoDAO);
//                    break;
                default:
                    System.out.println("SAINDO DO MENU...");
            }
        } while (opc != 14);
        principal();
    }

//    public Servidor login(String login, String senha) {
//        Servidor responseLogin = servidorDAO.login(login, senha);
//        return responseLogin;
//    }
}
