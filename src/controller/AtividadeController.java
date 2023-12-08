/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AtividadeDAO;
import dao.ServidorDAO;
import java.util.List;
import java.util.Scanner;
import model.Atividade;
import model.Servidor;
import service.AtividadeService;
import view.AtividadeView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class AtividadeController {

    private final AtividadeView atividadeView = new AtividadeView();
    private final AtividadeService atividadeService = new AtividadeService();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("ATIVIDADE");
        opcCrud = GUI.menu();
        Atividade atividade = null;
        List<Atividade> listAtividade = atividadeService.read();

        try {
            switch (opcCrud) {
                case 1:
                    atividade = atividadeView.create();
                    if (atividade != null) {
                        atividadeService.create(atividade);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    atividadeView.printId(listAtividade);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    atividade = atividadeService.getById(id);

                    if (atividade != null) {
                        atividadeService.update(atividade);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    atividadeView.printAll(listAtividade);
                    break;
                case 4:
                    atividadeView.printId(listAtividade);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    atividade = atividadeService.getById(id);

                    if (atividade != null) {
                        atividadeService.delete(atividade);
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
