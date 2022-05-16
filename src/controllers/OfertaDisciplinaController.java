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
import java.util.List;
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
            ServidorDAO servidorDAO, CursoDAO cursoDAO, AtividadeDAO atividadeDAO) throws Exception {
        System.out.println("OFERTA DE DISCIPLINA");
        opcCrud = GUI.menu();

        List<String> servidorVet = servidorDAO.readId();
        List<String> disciplinaVet = disciplinaDAO.readId();
        List<String> cursoVet = cursoDAO.readId();

        List<String> vetResultId = ofertaDAO.readId();
        List<String> vetResult = ofertaDAO.read();

        switch (opcCrud) {
            case 1:
                Oferta of = ofertaView.criarOferta(servidorVet, cursoVet, disciplinaVet);
                if(of != null){
                    ofertaDAO.create(of);
                }else{
                    GUI.error();
                }
                break;
            case 2:
                ofertaView.mostrarTodasOfertas(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Oferta ofertaAlt = ofertaDAO.find(auxLoc);
                if (ofertaAlt != null) {
                    ofertaAlt = ofertaView.modifOferta(ofertaAlt, servidorVet, cursoVet, disciplinaVet);
                    ofertaDAO.update(ofertaAlt);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                ofertaView.mostrarTodasOfertas(vetResult);
                break;
            case 4:
                ofertaView.mostrarTodasOfertas(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                if (auxLoc != 0) {
                    ofertaDAO.delete(auxLoc);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
