/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AtividadeDAO;
import dao.ServidorDAO;
import java.util.List;
import java.util.Scanner;
import model.Atividade;
import model.Servidor;
import view.AtividadeView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class AtividadeController {

    private final ServidorDAO servidorDAO = new ServidorDAO();
    private final AtividadeView atividadeView = new AtividadeView();
    private final AtividadeDAO atividadeDAO = new AtividadeDAO();
    
    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("ATIVIDADE");
        opcCrud = GUI.menu();

        List<Atividade> vetResult = atividadeDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Atividade at = atividadeView.criarAtividade(servidorDAO);

                    if (at != null) {
                        Servidor servAux = servidorDAO.find(at.getServidor().getId());
                        servidorDAO.updateHours(servAux, at.getHorasSemanais(), at.getServidor().getId());

                        atividadeDAO.create(at);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    atividadeView.mostrarIdAtividades(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Atividade atAlt = atividadeDAO.find(auxLoc);

                    if (atAlt != null) {
                        Servidor servAux = servidorDAO.find(atAlt.getServidor().getId());
                        servidorDAO.removeHours(servAux, atAlt.getHorasSemanais(), atAlt.getServidor().getId());

                        atAlt = atividadeView.modifAtividade(atAlt, servidorDAO);

                        servAux = servidorDAO.find(atAlt.getServidor().getId());
                        servidorDAO.updateHours(servAux, atAlt.getHorasSemanais(), atAlt.getServidor().getId());

                        atividadeDAO.update(atAlt);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    atividadeView.mostrarAtividades(vetResult);
                    break;
                case 4:
                    atividadeView.mostrarIdAtividades(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());

                    Atividade removeAtividade = atividadeDAO.find(auxLoc);
                    if (removeAtividade != null) {
                        Servidor servAux = servidorDAO.find(removeAtividade.getServidor().getId());
                        servidorDAO.removeHours(servAux, removeAtividade.getHorasSemanais(), removeAtividade.getServidor().getId());

                        atividadeDAO.delete(auxLoc);
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
