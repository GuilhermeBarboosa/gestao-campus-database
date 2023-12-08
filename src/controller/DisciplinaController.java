/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Disciplina;
import service.DisciplinaService;
import view.DisciplinaView;
import view.Gui;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class DisciplinaController {

    private final DisciplinaView disciplinaView = new DisciplinaView();
    private final DisciplinaService disciplinaService = new DisciplinaService();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("DISCIPLINA");
        Disciplina disciplina = null;
        List<Disciplina> listDisciplina = disciplinaService.read();

        opcCrud = GUI.menu();
        try {
            switch (opcCrud) {
                case 1:
                    disciplina = disciplinaView.create();
                    if (disciplina != null) {
                        disciplinaService.create(disciplina);
                    } else {
                        GUI.error();
                    }

                    break;
                case 2:
                    disciplinaView.printId(listDisciplina);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    Disciplina discAlt = disciplinaService.getById(id);
                    if (discAlt != null) {
                        disciplinaService.update(disciplinaView.update(discAlt));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    disciplinaView.printId(listDisciplina);
                    break;
                case 4:
                    disciplinaView.printId(listDisciplina);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    if (id != 0) {
                        disciplinaService.delete(id);
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
