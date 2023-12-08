/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Curso;
import service.CursoService;
import view.CursoView;
import view.Gui;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class CursoController {

    private final CursoService cursoService = new CursoService();
    private final CursoView cursoView = new CursoView();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("CURSO");
        opcCrud = GUI.menu();
        Curso curso = new Curso();
        List<Curso> listCurso = cursoService.read();

        try {
            switch (opcCrud) {
                case 1:
                    curso = cursoView.create();
                    if (curso != null) {
                        cursoService.create(curso);
                    } else {
                        GUI.error();
                    }

                    break;
                case 2:
                    cursoView.printId(listCurso);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    curso = cursoService.getById(id);
                    if (curso != null) {
                        cursoService.update(cursoView.update(curso));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3: {
                    cursoView.printAll(listCurso);
                }
                break;
                case 4:
                    cursoView.printId(listCurso);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    if (id != 0) {
                        cursoService.delete(id);
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
