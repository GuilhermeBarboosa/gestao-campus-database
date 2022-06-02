/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Campus;
import model.Servidor;
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
import dao.Default;

/**
 *
 * @author Aluno
 */
public class UserComumController extends DefaultController implements Default{

    public void menu() throws Exception {
        int opcCrud = 0;
        do {
            opcCrud = GUI.menuComum();
            switch (opcCrud) {
                case 1:
                    List<Campus> responseCampus = campusDAO.read();
                    campV.mostrarTodosCampos(responseCampus);
                    break;

                case 2:
                    List<Servidor> responseServidor = servidorDAO.read();
                    servV.mostrarServidores(responseServidor);
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
