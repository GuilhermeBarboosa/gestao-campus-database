/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Reuniao;
import service.ReuniaoService;
import view.Gui;
import view.ReuniaoView;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class ReuniaoController {

    private final ReuniaoView reuniaoView = new ReuniaoView();
    private final ReuniaoService reuniaoService = new ReuniaoService();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("REUNIÃO");
        opcCrud = GUI.menu();
        Reuniao reuniao = new Reuniao();
        List<Reuniao> listReuniao = reuniaoService.read();

        try {
            switch (opcCrud) {
                case 1:
                    reuniao = reuniaoView.create();
                    if (reuniao != null) {
                        reuniaoService.create(reuniao);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    reuniaoView.printId(listReuniao);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    reuniao = reuniaoService.getById(id);
                    if (reuniao != null) {
                        reuniaoService.update(reuniaoView.update(reuniao));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    reuniaoView.printAll(listReuniao);
                    break;
                case 4:
                    reuniaoView.printId(listReuniao);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    if (id != 0) {
                        reuniaoService.delete(id);
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
