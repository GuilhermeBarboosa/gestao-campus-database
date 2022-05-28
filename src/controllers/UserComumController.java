/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DefaultDAO;
import java.util.List;
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
public class UserComumController extends DefaultController implements DefaultDAO{

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

    public void menu() throws Exception {
        int opcCrud = 0;
        do {
            opcCrud = GUI.menuComum();
            switch (opcCrud) {
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
                    List<String> responseDisciplina = disciplinaDAO.read();
                    discV.mostrarTodasDisciplinas(responseDisciplina);
                    break;

                case 5:
                    List<String> responseOferta = ofertaDAO.read();
                    ofertaV.mostrarTodasOfertas(responseOferta);
                    break;

                case 6:
                    List<String> responseOrientacao = orientacaoDAO.read();
                    orientacaoV.mostrarTodasOrientacoes(responseOrientacao);
                    break;

                case 7:
                    List<String> responseAtividade = atividadeDAO.read();
                    atV.mostrarAtividades(responseAtividade);
                    break;

                case 8:
                    List<String> responseComissao = comissaoDAO.read();
                    comV.mostrarTodosComissao(responseComissao);
                    break;

                case 9:
                    List<String> responseVinculo = vinculoDAO.read();
                    vincV.mostrarTodosVinculos(responseVinculo);
                    break;

                case 10:
                    List<String> responseReuniao = reuniaoDAO.read();
                    reunV.mostrarTodosReunioes(responseReuniao);
                    break;
                case 11:
                    List<String> responseReuniaoPresente = reuniaoPresenteDAO.read();
                    reunPresenteV.mostrarTodosReunioesPresente(responseReuniaoPresente);
                    break;
            }
        } while (opcCrud != 12);
    }

}
