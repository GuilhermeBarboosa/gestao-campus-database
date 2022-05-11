/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.ReuniaoPresenteDAO;
import dao.ServidorDAO;
import model.ReuniaoPresente;
import view.ReuniaoPresenteView;

/**
 *
 * @author Aluno
 */
public class ReuniaoPresenteController extends DefaultController {
    
    private final ReuniaoPresenteView reuniaoPresenteView = new ReuniaoPresenteView();
    public void menu(ReuniaoPresenteDAO reuniaoPresenteDAO, ServidorDAO servidorDAO,
                     ComissaoDAO comissaoDAO) {
        System.out.println("REUNIÃO E PRESENTES");
        ReuniaoPresente[] reunPresenteVet = reuniaoPresenteDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                ReuniaoPresente reuniaoPresente = reuniaoPresenteView.criarReuniaoPresente(servidorDAO, comissaoDAO);
                reuniaoPresenteDAO.setReuniaoPresente(reuniaoPresente);
                break;
            case 2:
                reuniaoPresenteView.mostrarTodosReunioesPresente(reunPresenteVet, servidorDAO, comissaoDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                ReuniaoPresente reunPresenteAlt = reuniaoPresenteDAO.getId(auxLoc);
                if (reunPresenteAlt != null) {
                    reuniaoPresenteDAO.update(reuniaoPresenteView.modifReuniaoPresente(reunPresenteAlt, servidorDAO, comissaoDAO));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                reuniaoPresenteView.mostrarTodosReunioesPresente(reunPresenteVet, servidorDAO, comissaoDAO);
                break;
            case 4:
                reuniaoPresenteView.mostrarTodosReunioesPresente(reunPresenteVet, servidorDAO, comissaoDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                ReuniaoPresente reunPresenteDelete = reuniaoPresenteDAO.getId(auxLoc);
                if (reunPresenteDelete != null) {
                    reuniaoPresenteDAO.delete(reunPresenteDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }

}
