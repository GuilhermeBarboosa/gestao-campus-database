/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Campus;
import model.Servidor;
import model.Default;
import model.Atividade;
import model.Comissao;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Orientacao;
import model.Reuniao;
import model.ReuniaoPresente;
import model.Vinculo;

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
