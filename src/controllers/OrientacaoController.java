/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.OrientacaoDAO;
import dao.ServidorDAO;
import java.util.List;
import model.Orientacao;
import model.Servidor;
import view.OrientacaoView;

/**
 *
 * @author Aluno
 */
public class OrientacaoController extends DefaultController {

    private final OrientacaoView orientacaoView = new OrientacaoView();

    public void menu(ServidorDAO servidorDAO, OrientacaoDAO orientacaoDAO) throws Exception {
        System.out.println("ORIENTAÇÃO");
        opcCrud = GUI.menu();

        List<String> servidorVet = servidorDAO.readId();

        List<String> vetResultId = orientacaoDAO.readId();
        List<String> vetResult = orientacaoDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Orientacao or = orientacaoView.criarOrientacao(servidorVet);

                    if (or != null) {
                        orientacaoDAO.create(or);
                        Servidor servAux = servidorDAO.find(or.getId_servidor());
                        servidorDAO.updateHours(servAux, or.getHorasSemanais(), or.getId_servidor());
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    orientacaoView.mostrarTodasOrientacoes(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Orientacao orAlt = orientacaoDAO.find(auxLoc);

                    if (orAlt != null) {
                        Servidor servAux = servidorDAO.find(orAlt.getId_servidor());
                        servidorDAO.removeHours(servAux, orAlt.getHorasSemanais(), orAlt.getId_servidor());

                        orientacaoDAO.update(orientacaoView.modifOrientacao(orAlt, servidorVet));

                        servAux = servidorDAO.find(orAlt.getId_servidor());
                        servidorDAO.updateHours(servAux, orAlt.getHorasSemanais(), orAlt.getId_servidor());
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    orientacaoView.mostrarTodasOrientacoes(vetResult);
                    break;
                case 4:
                    orientacaoView.mostrarTodasOrientacoes(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());

                    orAlt = orientacaoDAO.find(auxLoc);
                    if (orAlt != null) {
                        Servidor servAux = servidorDAO.find(orAlt.getId_servidor());
                        servidorDAO.removeHours(servAux, orAlt.getHorasSemanais(), orAlt.getId_servidor());

                        orientacaoDAO.delete(auxLoc);
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
