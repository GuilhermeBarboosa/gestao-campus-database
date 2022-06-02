/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Reuniao;
import view.ReuniaoView;
import dao.Default;

/**
 *
 * @author Aluno
 */
public class ReuniaoController extends DefaultController implements Default{

    private final ReuniaoView reuniaoView = new ReuniaoView();

    public void menu() throws Exception {
        System.out.println("REUNIÃO");

        List<String> servidorVet = servidorDAO.readId();
        List<String> comissaoVet = comissaoDAO.readId();

        List<String> vetResultId = reuniaoDAO.readId();
        List<String> vetResult = reuniaoDAO.read();
        opcCrud = GUI.menu();
        try {
            switch (opcCrud) {
                case 1:
                    Reuniao reuniao = reuniaoView.criarReuniao(servidorVet, comissaoVet);
                    if (reuniao != null) {
                        reuniaoDAO.create(reuniao);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    reuniaoView.mostrarTodosReunioes(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Reuniao reunAlt = reuniaoDAO.find(auxLoc);
                    if (reunAlt != null) {
                        reuniaoDAO.update(reuniaoView.modifReuniao(reunAlt, servidorVet, comissaoVet));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    reuniaoView.mostrarTodosReunioes(vetResult);
                    break;
                case 4:
                    reuniaoView.mostrarTodosReunioes(vetResultId);
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
