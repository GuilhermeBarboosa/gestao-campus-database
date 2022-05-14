/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AtividadeDAO;
import dao.ServidorDAO;
import java.util.List;
import model.Atividade;
import view.AtividadeView;

/**
 *
 * @author Aluno
 */
public class AtividadeController extends DefaultController {

    private final AtividadeView atividadeView = new AtividadeView();

    public void menu(AtividadeDAO atividadeDAO, ServidorDAO servidorDAO) throws Exception {
        System.out.println("ATIVIDADE");
        opcCrud = GUI.menu();

        List<String> servidorVet = servidorDAO.readId();

        List<String> vetResultId = atividadeDAO.readId();
        List<String> vetResult = atividadeDAO.read();
        switch (opcCrud) {
            case 1:
                Atividade at = atividadeView.criarAtividade(servidorVet);
                if (at != null) {
                    atividadeDAO.create(at);
                } else {
                    GUI.error();
                }
                break;
            case 2:
                atividadeView.mostrarAtividades(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Atividade atAlt = atividadeDAO.find(auxLoc);

                if (atAlt != null) {
                    atAlt = atividadeView.modifAtividade(atAlt, servidorVet);
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
                atividadeView.mostrarAtividades(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                if (auxLoc != 0) {
                    atividadeDAO.delete(auxLoc);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
