/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.VinculoDAO;
import model.Comissao;
import view.ComissaoView;

/**
 *
 * @author Aluno
 */
public class EncerrarComissaoController extends DefaultController {
    
    private final ComissaoView comissaoView = new ComissaoView();

    public void menu(ComissaoDAO comissaoDAO, VinculoDAO vinculoDAO) {
        System.out.println("ENCERRAR COMISSAO");
        Comissao[] vetCom = comissaoDAO.getAll();
        comissaoView.mostrarTodosComissao(vetCom);
        GUI.printID();
        auxLoc = Integer.parseInt(ler.nextLine());

        Comissao comAux = comissaoDAO.getId(auxLoc);
        comAux = comissaoView.encerrarComissao(comAux);

        comissaoDAO.modificarEncerradomento(comAux);

        vinculoDAO.encerrarVinculos(comAux);
    }
}
