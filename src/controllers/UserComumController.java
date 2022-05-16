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
import java.util.List;
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
            ComissaoDAO comDAO, VinculoDAO vincDAO, ReuniaoDAO reunDAO, ReuniaoPresenteDAO reunPresenteDAO) throws Exception {
        int op = 0;
        do {
            op = GUI.menuComum();
            switch (op) {
                case 1:
                    List<String> responseCampus = campusDAO.read();
                    campV.mostrarTodosCampos(responseCampus);
                    break;

                case 2:
                    List<String> responseServidor = servidorDAO.read();
                    serV.mostrarServidores(responseServidor);
                    break;

                case 3:
                    List<String> responseCurso = cursoDAO.read();
                    cursoV.mostrarCurso(responseCurso);
                    break;

                case 4:
                    List<String> responseDisciplina = discDAO.read();
                    discV.mostrarTodasDisciplinas(responseDisciplina);
                    break;

                case 5:
                    List<String> responseOferta = ofDAO.read();
                    ofertaV.mostrarTodasOfertas(responseOferta);
                    break;

                case 6:
                    List<String> responseOrientacao = orDAO.read();
                    orientacaoV.mostrarTodasOrientacoes(responseOrientacao);
                    break;

                case 7:
                    List<String> responseAtividade = atDAO.read();
                    atV.mostrarAtividades(responseAtividade);
                    break;

                case 8:
                    List<String> responseComissao = comDAO.read();
                    comV.mostrarTodosComissao(responseComissao);
                    break;

                case 9:
                    List<String> responseVinculo = comDAO.read();
                    vincV.mostrarTodosVinculos(responseVinculo);
                    break;

                case 10:
                    List<String> responseReuniao = comDAO.read();
                    reunV.mostrarTodosReunioes(responseReuniao);
                    break;
                case 11:
                    List<String> responseReuniaoPresente = comDAO.read();
                    reunPresenteV.mostrarTodosReunioesPresente(responseReuniaoPresente);
                    break;
            }
        } while (op != 12);
    }

}
