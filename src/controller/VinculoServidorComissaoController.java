/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import model.Vinculo;
import service.VinculoService;
import view.Gui;
import view.VinculoView;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class VinculoServidorComissaoController {

    private final VinculoView vinculoView = new VinculoView();
    private final VinculoService vinculoService = new VinculoService();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("VINCULO DE SERVIDOR A COMISSÃO");

        List<Vinculo> listVinculo = vinculoService.read();
        Vinculo vinculo = new Vinculo();
        opcCrud = GUI.menu();

        try {
            switch (opcCrud) {
                case 1:
                    vinculo = vinculoView.create();
                    if (vinculo != null) {
                        vinculoService.create(vinculo);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    vinculoView.printId(listVinculo);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    vinculo = vinculoService.getById(id);
                    if (vinculo != null) {
                        vinculoService.update(vinculo);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    vinculoView.printAll(listVinculo);
                    break;
                case 4:
                    vinculoView.printId(listVinculo);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    vinculo = vinculoService.getById(id);
                    if (vinculo != null) {
                        vinculoService.delete(vinculo);
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
