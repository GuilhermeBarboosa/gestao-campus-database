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
import model.Servidor;
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
                    Servidor servAux = servidorDAO.find(at.getId_servidor());
                    servidorDAO.updateHours(servAux, at.getHorasSemanais(), at.getId_servidor());
                } else {
                    GUI.error();
                }
                break;
            case 2:
                atividadeView.mostrarAtividades(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Atividade atAlt = atividadeDAO.find(auxLoc);

                Servidor servAux = servidorDAO.find(atAlt.getId_servidor());
                servidorDAO.removeHours(servAux, atAlt.getHorasSemanais(), atAlt.getId_servidor());
                if (atAlt != null) {
                    servAux = servidorDAO.find(atAlt.getId_servidor());
                    servidorDAO.updateHours(servAux, atAlt.getHorasSemanais(), atAlt.getId_servidor());

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

                Atividade removeAtividade = atividadeDAO.find(auxLoc);
                if (removeAtividade != null) {
                    atividadeDAO.delete(auxLoc);

                    servAux = servidorDAO.find(removeAtividade.getId_servidor());
                    servidorDAO.removeHours(servAux, removeAtividade.getHorasSemanais(), removeAtividade.getId_servidor());
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
