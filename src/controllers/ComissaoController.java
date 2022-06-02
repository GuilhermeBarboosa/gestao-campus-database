/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Comissao;
import view.ComissaoView;
import dao.Default;

/**
 *
 * @author Aluno
 */
public class ComissaoController extends DefaultController implements Default{

    private final ComissaoView comissaoView = new ComissaoView();

    public void menu() throws Exception {
        System.out.println("COMISSÃO");
        opcCrud = GUI.menu();

        List<String> vetResultId = comissaoDAO.readId();
        List<String> vetResult = comissaoDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Comissao com = comissaoView.criarComissao();
                    if (com != null) {
                        comissaoDAO.create(com);
                    } else {
                        GUI.error();
                    }

                    break;
                case 2:
                    comissaoView.mostrarTodosComissao(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Comissao comAlt = comissaoDAO.find(auxLoc);

                    if (comAlt != null) {
                        comissaoDAO.update(comissaoView.modifComissao(comAlt));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    comissaoView.mostrarTodosComissao(vetResult);
                    break;
                case 4:
                    comissaoView.mostrarTodosComissao(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    if (auxLoc != 0) {
                        comissaoDAO.delete(auxLoc);
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
