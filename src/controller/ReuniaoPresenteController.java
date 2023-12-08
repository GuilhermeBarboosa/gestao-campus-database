/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ReuniaoPresente;
import service.ReuniaoPresenteService;
import view.Gui;
import view.ReuniaoPresenteView;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class ReuniaoPresenteController {

    private final ReuniaoPresenteView reuniaoPresenteView = new ReuniaoPresenteView();
    private final ReuniaoPresenteService reuniaoPresenteService = new ReuniaoPresenteService();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("REUNIÃO E PRESENTES");
        opcCrud = GUI.menu();
        ReuniaoPresente reuniaoPresente = new ReuniaoPresente();
        List<ReuniaoPresente> listReuniaoPresente = reuniaoPresenteService.read();

        try {
            switch (opcCrud) {
                case 1:
                    reuniaoPresente = reuniaoPresenteView.create();
                    if (reuniaoPresente != null) {
                        reuniaoPresenteService.create(reuniaoPresente);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    reuniaoPresenteView.mostrarIdTodosReunioesPresente(listReuniaoPresente);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    reuniaoPresente = reuniaoPresenteService.getById(id);
                    if (reuniaoPresente != null) {
                        reuniaoPresenteService.update(reuniaoPresenteView.update(reuniaoPresente));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    reuniaoPresenteView.mostrarTodosReunioesPresente(listReuniaoPresente);
                    break;
                case 4:
                    reuniaoPresenteView.mostrarIdTodosReunioesPresente(listReuniaoPresente);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    if (id != 0) {
                        reuniaoPresenteService.delete(id);
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
