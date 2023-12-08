/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import service.*;
import view.*;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class UserComumController {

    private final CampusService campusService = new CampusService();
    private final ServidorService servidorService = new ServidorService();
    private final CursoService cursoService = new CursoService();
    private final DisciplinaService disciplinaService = new DisciplinaService();
    private final OfertaService ofertaService = new OfertaService();
    private final OrientacaoService orientacaoService = new OrientacaoService();
    private final AtividadeService atividadeService = new AtividadeService();
    private final ComissaoService comissaoService = new ComissaoService();
    private final VinculoService vinculoService = new VinculoService();
    private final ReuniaoService reuniaoService = new ReuniaoService();
    private final ReuniaoPresenteService reuniaoPresenteService = new ReuniaoPresenteService();

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

    public void menu() throws Exception {
        int opcCrud = 0;
        do {
            opcCrud = GUI.menuComum();
            switch (opcCrud) {
                case 1:
                    List<Campus> listCampus = campusService.read();
                    campusView.printAll(listCampus);
                    break;

                case 2:
                    List<Servidor> listServidor = servidorService.read();
                    servidorView.printAll(listServidor);
                    break;

                case 3:
                    List<Curso> listCurso = cursoService.read();
                    cursoView.printAll(listCurso);
                    break;

                case 4:
                    List<Disciplina> listDisciplina = disciplinaService.read();
                    disciplinaView.printAll(listDisciplina);
                    break;

                case 5:
                    List<Oferta> listOferta = ofertaService.read();
                    ofertaView.printAll(listOferta);
                    break;

                case 6:
                    List<Orientacao> listOrientacao = orientacaoService.read();
                    orientacaoView.printAll(listOrientacao);
                    break;

                case 7:
                    List<Atividade> listAtividade = atividadeService.read();
                    atividadeView.printAll(listAtividade);
                    break;

                case 8:
                    List<Comissao> listComissao = comissaoService.read();
                    comissaoView.printAll(listComissao);
                    break;

                case 9:
                    List<Vinculo> listVinculo = vinculoService.read();
                    vinculoView.printAll(listVinculo);
                    break;

                case 10:
                    List<Reuniao> listReuniao = reuniaoService.read();
                    reuniaoView.printAll(listReuniao);
                    break;
                case 11:
                    List<ReuniaoPresente> listReuniaoPresente = reuniaoPresenteService.read();
                    reunPresenteV.mostrarTodosReunioesPresente(listReuniaoPresente);
                    break;
            }
        } while (opcCrud != 12);
    }

}
