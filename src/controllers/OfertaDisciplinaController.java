/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Atividade;
import model.Disciplina;
import model.Oferta;
import model.Servidor;
import view.AtividadeView;
import view.OfertaView;
import dao.Default;

/**
 *
 * @author Aluno
 */
public class OfertaDisciplinaController extends DefaultController implements Default{

    private final OfertaView ofertaView = new OfertaView();
    private final AtividadeView atividadeView = new AtividadeView();

    public void menu() throws Exception {
        System.out.println("OFERTA DE DISCIPLINA");
        opcCrud = GUI.menu();

        List<String> servidorVet = servidorDAO.readId();
        List<String> disciplinaVet = disciplinaDAO.readId();
        List<String> cursoVet = cursoDAO.readId();

        List<String> vetResultId = ofertaDAO.readId();
        List<String> vetResult = ofertaDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Oferta of = ofertaView.criarOferta(servidorVet, cursoVet, disciplinaVet);
                    if (of != null) {
                        Servidor servAux = servidorDAO.find(of.getId_servidor());
                        Disciplina discAux = disciplinaDAO.find(of.getId_disciplina());

                        Atividade atividadeAux = atividadeView.createAula(servAux, discAux);
                        atividadeDAO.create(atividadeAux);

                        servidorDAO.updateHours(servAux, discAux.getCargaHoraria(), of.getId_servidor());
                        ofertaDAO.create(of);

                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    ofertaView.mostrarTodasOfertas(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Oferta ofertaAlt = ofertaDAO.find(auxLoc);
                    if (ofertaAlt != null) {
                        Servidor servAux = servidorDAO.find(ofertaAlt.getId_servidor());
                        Disciplina discAux = disciplinaDAO.find(ofertaAlt.getId_disciplina());
                        servidorDAO.removeHours(servAux, discAux.getCargaHoraria(), ofertaAlt.getId_servidor());

                        ofertaAlt = ofertaView.modifOferta(ofertaAlt, servidorVet, cursoVet, disciplinaVet);

                        servAux = servidorDAO.find(ofertaAlt.getId_servidor());
                        discAux = disciplinaDAO.find(ofertaAlt.getId_disciplina());
                        servidorDAO.updateHours(servAux, discAux.getCargaHoraria(), ofertaAlt.getId_servidor());

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

                    ofertaAlt = ofertaDAO.find(auxLoc);
                    if (ofertaAlt != null) {
                        Servidor servAux = servidorDAO.find(ofertaAlt.getId_servidor());
                        Disciplina discAux = disciplinaDAO.find(ofertaAlt.getId_disciplina());
                        servidorDAO.removeHours(servAux, discAux.getCargaHoraria(), ofertaAlt.getId_servidor());

                        ofertaDAO.delete(auxLoc);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
