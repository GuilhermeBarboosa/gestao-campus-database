/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Reuniao;
import view.ReuniaoView;
import model.Default;

/**
 *
 * @author Aluno
 */
public class ReuniaoController extends DefaultController implements Default {

    private final ReuniaoView reuniaoView = new ReuniaoView();

    public void menu() throws Exception {
        System.out.println("REUNIÃO");
        opcCrud = GUI.menu();
        
        List<Reuniao> vetResult = reuniaoDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Reuniao reuniao = reuniaoView.criarReuniao(servidorDAO, comissaoDAO);
                    if (reuniao != null) {
                        reuniaoDAO.create(reuniao);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    reuniaoView.mostrarIdTodosReunioes(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Reuniao reunAlt = reuniaoDAO.find(auxLoc);
                    if (reunAlt != null) {
                        reuniaoDAO.update(reuniaoView.modifReuniao(reunAlt, servidorDAO, comissaoDAO));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    reuniaoView.mostrarTodosReunioes(vetResult);
                    break;
                case 4:
                    reuniaoView.mostrarIdTodosReunioes(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    if (auxLoc != 0) {
                        reuniaoDAO.delete(auxLoc);
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
