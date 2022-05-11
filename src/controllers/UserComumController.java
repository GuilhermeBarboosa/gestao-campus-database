/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import model.Atividade;
import model.Campus;
import model.Comissao;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Orientacao;
import model.Reuniao;
import model.ReuniaoPresente;
import model.Servidor;
import model.Vinculo;
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
 * @author Aluno
 */
public class UserComumController extends DefaultController {

    private final CampusView campV = new CampusView();
    private final ServidorView serV = new ServidorView();
    private final CursoView cursoV = new CursoView();
    private final DisciplinaView discV = new DisciplinaView();
    private final OfertaView ofertaV = new OfertaView();
    private final OrientacaoView orientacaoV = new OrientacaoView();
    private final AtividadeView atV = new AtividadeView();
    private final ComissaoView comV = new ComissaoView();
    private final VinculoView vincV = new VinculoView();
    private final ReuniaoView reunV = new ReuniaoView();
    private final ReuniaoPresenteView reunPresenteV = new ReuniaoPresenteView();

    public void menu(CampusDAO campusDAO, ServidorDAO servidorDAO, CursoDAO cursoDAO,
            DisciplinaDAO discDAO, OfertaDAO ofDAO, OrientacaoDAO orDAO, AtividadeDAO atDAO,
            ComissaoDAO comDAO, VinculoDAO vincDAO, ReuniaoDAO reunDAO, ReuniaoPresenteDAO reunPresenteDAO) {
        int op = 0;
        do {
            op = GUI.menuComum();
            switch (op) {
                case 1:
                    Campus[] responseCampus = campusDAO.read();
                    campV.mostrarTodosCampos(responseCampus);
                    break;

                case 2:
                    Servidor[] responseServidor = servidorDAO.getAll();
                    serV.mostrarTodosServidores(responseServidor, campusDAO);
                    break;

                case 3:
                    Curso[] cursoVet = cursoDAO.getAll();
                    cursoV.mostrarTodosCursos(cursoVet, campusDAO);
                    break;

                case 4:
                    Disciplina[] discVet = discDAO.getAll();
                    discV.mostrarTodasDisciplinas(discVet, cursoDAO);
                    break;

                case 5:
                    Oferta[] ofVet = ofDAO.getAll();
                    ofertaV.mostrarTodasOfertas(ofVet, servidorDAO, cursoDAO, discDAO);
                    break;

                case 6:
                    Orientacao[] orVet = orDAO.getAll();
                    orientacaoV.mostrarTodasOrientacoes(orVet, servidorDAO);
                    break;

                case 7:
                    Atividade[] atVet = atDAO.getAll();
                    atV.mostrarTodasAtividades(atVet);
                    break;

                case 8:
                    Comissao[] comVet = comDAO.getAll();
                    comV.mostrarTodosComissao(comVet);
                    break;

                case 9:
                    Vinculo[] vincVet = vincDAO.getAll();
                    vincV.mostrarTodosVinculos(vincVet, comDAO, servidorDAO);
                    break;

                case 10:
                    Reuniao[] reunVet = reunDAO.getAll();
                    reunV.mostrarTodosReunioes(reunVet, servidorDAO, comDAO);
                    break;
                case 11:
                    ReuniaoPresente[] reunPresenteVet = reunPresenteDAO.getAll();
                    reunPresenteV.mostrarTodosReunioesPresente(reunPresenteVet, servidorDAO, comDAO);
                    break;
            }
        } while (op != 12);
    }

}
