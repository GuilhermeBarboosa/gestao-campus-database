/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Comissao;
import service.ComissaoService;
import view.ComissaoView;
import view.Gui;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class ComissaoController {

    public final ComissaoService comissaoService = new ComissaoService();
    private final ComissaoView comissaoView = new ComissaoView();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("COMISSÃO");
        opcCrud = GUI.menu();
        Comissao comissao = new Comissao();
        List<Comissao> vetResult = comissaoService.read();

        try {
            switch (opcCrud) {
                case 1:
                    comissao = comissaoView.create();
                    if (comissao != null) {
                        comissaoService.create(comissao);
                    } else {
                        GUI.error();
                    }

                    break;
                case 2:
                    comissaoView.printId(vetResult);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    comissao = comissaoService.getById(id);

                    if (comissao != null) {
                        comissaoService.update(comissaoView.update(comissao));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    comissaoView.printAll(vetResult);
                    break;
                case 4:
                    comissaoView.printId(vetResult);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    if (id != 0) {
                        comissaoService.delete(id);
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
