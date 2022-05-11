/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import model.Comissao;
import view.ComissaoView;

/**
 *
 * @author Aluno
 */
public class ComissaoController extends DefaultController {
    
    private final ComissaoView comissaoView = new ComissaoView();

    public void menu(ComissaoDAO comissaoDAO) {
        System.out.println("COMISSÃO");
        Comissao[] comVet = comissaoDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Comissao com = comissaoView.criarComissao();
                comissaoDAO.setComissao(com);
                break;
            case 2:
                comissaoView.mostrarTodosComissao(comVet);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Comissao comAlt = comissaoDAO.getId(auxLoc);
                if (comAlt != null) {
                    comissaoDAO.update(comissaoView.modifComissao(comAlt));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                comissaoView.mostrarTodosComissao(comVet);
                break;
            case 4:
                comissaoView.mostrarTodosComissao(comVet);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Comissao comDelete = comissaoDAO.getId(auxLoc);
                if (comDelete != null) {
                    comissaoDAO.delete(comDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }

}
