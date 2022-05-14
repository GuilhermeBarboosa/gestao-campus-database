/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CampusDAO;
import dao.ServidorDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Campus;
import model.Servidor;
import view.ServidorView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class ServidorController extends DefaultController {

    private final ServidorView servV = new ServidorView();

    public void menu(ServidorDAO servDAO, CampusDAO campusDAO) throws Exception {
        opcCrud = GUI.menu();

        List<String> campusVet = campusDAO.readId();
        
        List<String> vetResultId = servDAO.readId();
        List<String> vetResult = servDAO.read();

        switch (opcCrud) {
            case 1:
                Servidor servidor = servV.criarServ(campusVet);
                servDAO.create(servidor);
                break;
            case 2:
                servV.mostrarServidores(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Servidor servAlt = servDAO.find(auxLoc);

                if (servAlt != null) {
                    servDAO.update(servV.modifServ(servAlt, campusVet));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                servV.mostrarServidores(vetResult);
                break;
            case 4:
                servV.mostrarServidores(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                if (auxLoc != 0) {
                    servDAO.delete(auxLoc);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }

}
