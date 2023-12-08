/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Orientacao;
import service.OrientacaoService;
import view.Gui;
import view.OrientacaoView;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class OrientacaoController {

    private final OrientacaoView orientacaoView = new OrientacaoView();
    private final OrientacaoService orientacaoService = new OrientacaoService();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("ORIENTAÇÃO");
        opcCrud = GUI.menu();
        Orientacao orientacao = new Orientacao();
        List<Orientacao> listOrientacao = orientacaoService.read();

        try {
            switch (opcCrud) {
                case 1:
                    orientacao = orientacaoView.create();

                    if (orientacao != null) {
                        orientacaoService.create(orientacao);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    orientacaoView.printId(listOrientacao);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    orientacao = orientacaoService.getById(id);

                    if (orientacao != null) {
                        orientacaoView.update(orientacao);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    orientacaoView.printAll(listOrientacao);
                    break;
                case 4:
                    orientacaoView.printId(listOrientacao);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());

                    orientacao = orientacaoService.getById(id);
                    if (orientacao != null) {
                        orientacaoService.delete(orientacao);
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
