/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.ReuniaoDAO;
import dao.ServidorDAO;
import model.Reuniao;
import view.ReuniaoView;

/**
 *
 * @author Aluno
 */
public class ReuniaoController extends DefaultController {
    
    private final ReuniaoView reuniaoView = new ReuniaoView();

    public void menu(ReuniaoDAO reuniaoDAO, ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {
        System.out.println("REUNIÃO");
        Reuniao[] reunVet = reuniaoDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Reuniao reuniao = reuniaoView.criarReuniao(servidorDAO, comissaoDAO);
                reuniaoDAO.setReuniao(reuniao);
                break;
            case 2:
                reuniaoView.mostrarTodosReunioes(reunVet, servidorDAO, comissaoDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Reuniao reunAlt = reuniaoDAO.getId(auxLoc);
                if (reunAlt != null) {
                    reuniaoDAO.update(reuniaoView.modifReuniao(reunAlt, servidorDAO, comissaoDAO));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                reuniaoView.mostrarTodosReunioes(reunVet, servidorDAO, comissaoDAO);
                break;
            case 4:
                reuniaoView.mostrarTodosReunioes(reunVet, servidorDAO, comissaoDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Reuniao reunDelete = reuniaoDAO.getId(auxLoc);
                if (reunDelete != null) {
                    reuniaoDAO.delete(reunDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
