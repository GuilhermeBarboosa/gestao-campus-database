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
import dao.RelatorioDAO;
import dao.ReuniaoDAO;
import dao.ReuniaoPresenteDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
import java.util.List;
import java.util.Scanner;
import model.Campus;
import model.Servidor;
import model.Atividade;
import model.Comissao;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Orientacao;
import model.Reuniao;
import model.ReuniaoPresente;
import model.Vinculo;
import view.AtividadeView;
import view.CampusView;
import view.ComissaoView;
import view.CursoView;
import view.DisciplinaView;
import view.Gui;
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
public class UserComumController {

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
    
    private final CampusView campusView = new CampusView();
    private final ServidorView servidorView = new ServidorView();
    private final CursoView cursoView = new CursoView();
    private final DisciplinaView disciplinaView = new DisciplinaView();
    private final OfertaView ofertaView = new OfertaView();
    private final OrientacaoView orientacaoView = new OrientacaoView();
    private final AtividadeView atividadeView = new AtividadeView();
    private final ComissaoView comissaoView = new ComissaoView();
    private final VinculoView vinculoView = new VinculoView();
    private final ReuniaoView reuniaoView = new ReuniaoView();
    private final ReuniaoPresenteView reunPresenteV = new ReuniaoPresenteView();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        int opcCrud = 0;
        do {
            opcCrud = GUI.menuComum();
            switch (opcCrud) {
                case 1:
                    List<Campus> responseCampus = campusDAO.read();
                    campusView.mostrarTodosCampos(responseCampus);
                    break;

                case 2:
                    List<Servidor> responseServidor = servidorDAO.read();
                    servidorView.mostrarServidores(responseServidor);
                    break;

                case 3:
                    List<Curso> responseCurso = cursoDAO.read();
                    cursoView.mostrarCurso(responseCurso);
                    break;

                case 4:
                    List<Disciplina> responseDisciplina = disciplinaDAO.read();
                    disciplinaView.mostrarTodasDisciplinas(responseDisciplina);
                    break;

                case 5:
                    List<Oferta> responseOferta = ofertaDAO.read();
                    ofertaView.mostrarTodasOfertas(responseOferta);
                    break;

                case 6:
                    List<Orientacao> responseOrientacao = orientacaoDAO.read();
                    orientacaoView.mostrarTodasOrientacoes(responseOrientacao);
                    break;

                case 7:
                    List<Atividade> responseAtividade = atividadeDAO.read();
                    atividadeView.mostrarAtividades(responseAtividade);
                    break;

                case 8:
                    List<Comissao> responseComissao = comissaoDAO.read();
                    comissaoView.mostrarTodosComissao(responseComissao);
                    break;

                case 9:
                    List<Vinculo> responseVinculo = vinculoDAO.read();
                    vinculoView.mostrarTodosVinculos(responseVinculo);
                    break;

                case 10:
                    List<Reuniao> responseReuniao = reuniaoDAO.read();
                    reuniaoView.mostrarTodosReunioes(responseReuniao);
                    break;
                case 11:
                    List<ReuniaoPresente> responseReuniaoPresente = reuniaoPresenteDAO.read();
                    reunPresenteV.mostrarTodosReunioesPresente(responseReuniaoPresente);
                    break;
            }
        } while (opcCrud != 12);
    }

}
