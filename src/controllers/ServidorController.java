/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CampusDAO;
import dao.ServidorDAO;
import java.util.List;
import java.util.Scanner;
import model.Campus;
import model.Servidor;
import view.Gui;
import view.ServidorView;

/**
 *
 * @author Aluno
 */
public class ServidorController {

    private final ServidorView servidorView = new ServidorView();

    private final ServidorDAO servidorDAO = new ServidorDAO();
    private final CampusDAO campusDAO = new CampusDAO();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("SERVIDOR");
        opcCrud = GUI.menu();

        List<Servidor> vetResult = servidorDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Servidor servidor = servidorView.criarServ(campusDAO);
                    if (servidor != null) {
                        servidorDAO.create(servidor);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    servidorView.mostrarIdServidores(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Servidor servAlt = servidorDAO.find(auxLoc);

                    if (servAlt != null) {
                        servidorDAO.update(servidorView.modifServ(servAlt, campusDAO));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    servidorView.mostrarServidores(vetResult);
                    break;
                case 4:
                    servidorView.mostrarIdServidores(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    if (auxLoc != 0) {
                        servidorDAO.delete(auxLoc);
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
