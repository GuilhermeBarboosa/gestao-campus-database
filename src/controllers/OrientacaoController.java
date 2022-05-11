/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.OrientacaoDAO;
import dao.ServidorDAO;
import model.Orientacao;
import view.OrientacaoView;

/**
 *
 * @author Aluno
 */
public class OrientacaoController extends DefaultController{
    
    private final OrientacaoView orientacaoView = new OrientacaoView();
    
    public void menu(ServidorDAO servidorDAO, OrientacaoDAO orientacaoDAO) {
        System.out.println("ORIENTAÇÃO");
        Orientacao[] orVet = orientacaoDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Orientacao or = orientacaoView.criarOrientacao(servidorDAO);
                orientacaoDAO.setOrientacao(or);
                servidorAux = servidorDAO.getId(or.getServidor().getId());
                servidorDAO.aumentarHoras(servidorAux.getId(), or.getHorasSemanais());
                break;
            case 2:
                orientacaoView.mostrarTodasOrientacoes(orVet, servidorDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Orientacao orAlt = orientacaoDAO.getId(auxLoc);
                servidorAux = servidorDAO.getId(orAlt.getServidor().getId());
                servidorDAO.removerHoras(servidorAux.getId(), orAlt.getHorasSemanais());
                if (orAlt != null) {
                    orAlt = orientacaoView.modifOrientacao(orAlt, servidorDAO);
                    orientacaoDAO.update(orAlt);
                    servidorAux = servidorDAO.getId(orAlt.getServidor().getId());
                    servidorDAO.aumentarHoras(servidorAux.getId(), orAlt.getHorasSemanais());
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                orientacaoView.mostrarTodasOrientacoes(orVet, servidorDAO);
                break;
            case 4:
                orientacaoView.mostrarTodasOrientacoes(orVet, servidorDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Orientacao orDelete = orientacaoDAO.getId(auxLoc);
                if (orDelete != null) {
                    orientacaoDAO.delete(orDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
