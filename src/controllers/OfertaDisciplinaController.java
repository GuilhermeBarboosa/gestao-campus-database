/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AtividadeDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.OfertaDAO;
import dao.ServidorDAO;
import model.Oferta;
import view.AtividadeView;
import view.OfertaView;

/**
 *
 * @author Aluno
 */
public class OfertaDisciplinaController extends DefaultController {
    
    private final OfertaView ofertaView = new OfertaView();
    private final AtividadeView atividadeView = new AtividadeView();

    public void menu(OfertaDAO ofertaDAO, DisciplinaDAO disciplinaDAO, 
                     ServidorDAO servidorDAO, CursoDAO cursoDAO, AtividadeDAO atividadeDAO) {
        System.out.println("OFERTA DE DISCIPLINA");
        opcCrud = GUI.menu();
        Oferta[] ofVet = ofertaDAO.getAll();
        switch (opcCrud) {
            case 1:
                Oferta of = ofertaView.criarOferta(servidorDAO, cursoDAO, disciplinaDAO);
                ofertaDAO.setDisciplina(of);

                disciplinaAux = disciplinaDAO.getId(of.getDisciplina().getId());
                servidorAux = servidorDAO.getId(of.getServidor().getId());

                servidorDAO.aumentarHoras(servidorAux.getId(), of.getAulaSemanais());
                atividadeAux = atividadeView.criarAula(servidorAux, disciplinaAux, of.getAulaSemanais());
                atividadeDAO.setAtividade(atividadeAux);
                break;
            case 2:
                ofertaView.mostrarTodasOfertas(ofVet, servidorDAO, cursoDAO, disciplinaDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());

                Oferta ofertaAlt = ofertaDAO.getId(auxLoc);

                disciplinaAux = disciplinaDAO.getId(ofertaAlt.getDisciplina().getId());
                servidorAux = servidorDAO.getId(ofertaAlt.getServidor().getId());

                servidorDAO.removerHoras(servidorAux.getId(), disciplinaAux.getCargaHoraria());

                if (ofertaAlt != null) {

                    ofertaAlt = ofertaView.modifOferta(ofertaAlt, servidorDAO, cursoDAO, disciplinaDAO);
                    ofertaDAO.update(ofertaAlt);

                    disciplinaAux = disciplinaDAO.getId(ofertaAlt.getDisciplina().getId());
                    servidorAux = servidorDAO.getId(ofertaAlt.getServidor().getId());

                    servidorDAO.aumentarHoras(servidorAux.getId(), disciplinaAux.getCargaHoraria());
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                ofertaView.mostrarTodasOfertas(ofVet, servidorDAO, cursoDAO, disciplinaDAO);
                break;
            case 4:
                ofertaView.mostrarTodasOfertas(ofVet, servidorDAO, cursoDAO, disciplinaDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Oferta ofDelete = ofertaDAO.getId(auxLoc);
                if (ofDelete != null) {
                    ofertaDAO.delete(ofDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
