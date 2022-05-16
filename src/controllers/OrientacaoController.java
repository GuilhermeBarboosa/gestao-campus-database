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

        switch (opcCrud) {
            case 1:
                Orientacao or = orientacaoView.criarOrientacao(servidorVet);
                if (or != null) {
                    orientacaoDAO.create(or);
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
                    orientacaoDAO.update(orientacaoView.modifOrientacao(orAlt, servidorVet));
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
                if (auxLoc != 0) {
                    orientacaoDAO.delete(auxLoc);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
