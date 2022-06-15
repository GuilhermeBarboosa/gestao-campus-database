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
import java.util.Scanner;
import model.Atividade;
import model.Disciplina;
import model.Oferta;
import model.Servidor;
import view.AtividadeView;
import view.Gui;
import view.OfertaView;

/**
 *
 * @author Aluno
 */
public class OfertaDisciplinaController {

    private final OfertaView ofertaView = new OfertaView();
    private final AtividadeView atividadeView = new AtividadeView();
    private final OfertaDAO ofertaDAO = new OfertaDAO();
    private final ServidorDAO servidorDAO = new ServidorDAO();
    private final CursoDAO cursoDAO = new CursoDAO();
    private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
private final AtividadeDAO atividadeDAO = new AtividadeDAO();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("OFERTA DE DISCIPLINA");
        opcCrud = GUI.menu();

        List<Oferta> vetResult = ofertaDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Oferta of = ofertaView.criarOferta(servidorDAO, cursoDAO, disciplinaDAO);
                    if (of != null) {
                        Servidor servAux = servidorDAO.find(of.getServidor().getId());
                        Disciplina discAux = disciplinaDAO.find(of.getDisciplina().getId());

                        Atividade atividadeAux = atividadeView.createAula(servAux, discAux);
                        atividadeDAO.create(atividadeAux);

                        servidorDAO.updateHours(servAux, discAux.getCargaHoraria(), of.getServidor().getId());
                        ofertaDAO.create(of);

                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    ofertaView.mostrarIdTodasOfertas(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Oferta ofertaAlt = ofertaDAO.find(auxLoc);
                    if (ofertaAlt != null) {
                        Servidor servAux = servidorDAO.find(ofertaAlt.getServidor().getId());
                        Disciplina discAux = disciplinaDAO.find(ofertaAlt.getDisciplina().getId());
                        servidorDAO.removeHours(servAux, discAux.getCargaHoraria(), ofertaAlt.getServidor().getId());

                        ofertaAlt = ofertaView.modifOferta(ofertaAlt, servidorDAO, cursoDAO, disciplinaDAO);

                        servAux = servidorDAO.find(ofertaAlt.getServidor().getId());
                        discAux = disciplinaDAO.find(ofertaAlt.getDisciplina().getId());
                        servidorDAO.updateHours(servAux, discAux.getCargaHoraria(), ofertaAlt.getServidor().getId());

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
                    ofertaView.mostrarIdTodasOfertas(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());

                    ofertaAlt = ofertaDAO.find(auxLoc);
                    if (ofertaAlt != null) {
                        Servidor servAux = servidorDAO.find(ofertaAlt.getServidor().getId());
                        Disciplina discAux = disciplinaDAO.find(ofertaAlt.getDisciplina().getId());
                        servidorDAO.removeHours(servAux, discAux.getCargaHoraria(), ofertaAlt.getServidor().getId());

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
