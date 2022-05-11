/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AtividadeDAO;
import dao.ServidorDAO;
import model.Atividade;
import view.AtividadeView;

/**
 *
 * @author Aluno
 */
public class AtividadeController extends DefaultController {

    private final AtividadeView atividadeView = new AtividadeView();

    public void menu(AtividadeDAO atividadeDAO, ServidorDAO servidorDAO) {
        System.out.println("ATIVIDADE");
        Atividade[] atVet = atividadeDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Atividade at = atividadeView.criarAtividade(servidorDAO);
                atividadeDAO.setAtividade(at);
                servidorAux = servidorDAO.getId(at.getServidor().getId());
                servidorDAO.aumentarHoras(servidorAux.getId(), at.getHorasSemanais());
                break;
            case 2:
                atividadeView.mostrarTodasAtividades(atVet);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Atividade atAlt = atividadeDAO.getId(auxLoc);
                servidorAux = servidorDAO.getId(atAlt.getServidor().getId());
                servidorDAO.removerHoras(servidorAux.getId(), atAlt.getHorasSemanais());
                if (atAlt != null) {
                    atAlt = atividadeView.modifAtividade(atAlt, servidorDAO);
                    atividadeDAO.update(atAlt);
                    servidorAux = servidorDAO.getId(atAlt.getServidor().getId());
                    servidorDAO.aumentarHoras(servidorAux.getId(), atAlt.getHorasSemanais());
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                atividadeView.mostrarTodasAtividades(atVet);
                break;
            case 4:
                atividadeView.mostrarTodasAtividades(atVet);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Atividade atDelete = atividadeDAO.getId(auxLoc);
                if (atDelete != null) {
                    atividadeDAO.delete(atDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
