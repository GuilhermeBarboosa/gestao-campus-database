/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.DefaultDAO;
import dao.ReuniaoPresenteDAO;
import dao.ServidorDAO;
import java.util.List;
import model.ReuniaoPresente;
import view.ReuniaoPresenteView;

/**
 *
 * @author Aluno
 */
public class ReuniaoPresenteController extends DefaultController implements DefaultDAO{

    private final ReuniaoPresenteView reuniaoPresenteView = new ReuniaoPresenteView();

    public void menu() throws Exception {
        System.out.println("REUNIÃO E PRESENTES");
        opcCrud = GUI.menu();

        List<String> servidorVet = servidorDAO.readId();
        List<String> comissaoVet = comissaoDAO.readId();

        List<String> vetResultId = reuniaoPresenteDAO.readId();
        List<String> vetResult = reuniaoPresenteDAO.read();

        try {
             switch (opcCrud) {
            case 1:
                ReuniaoPresente reuniaoPresente = reuniaoPresenteView.criarReuniaoPresente(servidorVet, comissaoVet);
                if (reuniaoPresente != null) {
                    reuniaoPresenteDAO.create(reuniaoPresente);
                } else {
                    GUI.error();
                }
                break;
            case 2:
                reuniaoPresenteView.mostrarTodosReunioesPresente(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                ReuniaoPresente reunPresenteAlt = reuniaoPresenteDAO.find(auxLoc);
                if (reunPresenteAlt != null) {
                    reuniaoPresenteDAO.update(reuniaoPresenteView.modifReuniaoPresente(reunPresenteAlt, servidorVet, comissaoVet));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                reuniaoPresenteView.mostrarTodosReunioesPresente(vetResult);
                break;
            case 4:
                reuniaoPresenteView.mostrarTodosReunioesPresente(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                if (auxLoc != 0) {
                    reuniaoPresenteDAO.delete(auxLoc);
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
