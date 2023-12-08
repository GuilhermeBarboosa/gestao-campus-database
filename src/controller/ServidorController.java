/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Servidor;
import service.ServidorService;
import view.Gui;
import view.ServidorView;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class ServidorController {

    private final ServidorView servidorView = new ServidorView();

    private final ServidorService servidorService = new ServidorService();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("SERVIDOR");
        opcCrud = GUI.menu();
        Servidor servidor = new Servidor();
        List<Servidor> listServidor = servidorService.read();

        try {
            switch (opcCrud) {
                case 1:
                    servidor = servidorView.created();
                    if (servidor != null) {
                        servidorService.create(servidor);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    servidorView.printId(listServidor);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    servidor = servidorService.getById(id);
                    if (servidor != null) {
                        servidorService.update(servidorView.update(servidor));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    servidorView.printAll(listServidor);
                    break;
                case 4:
                    servidorView.printId(listServidor);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    if (id != 0) {
                        servidorService.delete(id);
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
