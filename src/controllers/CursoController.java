/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CampusDAO;
import dao.CursoDAO;
import java.util.List;
import java.util.Scanner;
import model.Curso;
import view.CursoView;
import model.Campus;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class CursoController {

    private final CursoDAO cursoDAO = new CursoDAO();
    private final CursoView cursoView = new CursoView();
    private final CampusDAO campusDAO = new CampusDAO();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("CURSO");
        opcCrud = GUI.menu();

        List<Curso> vetResult = cursoDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Curso curso = cursoView.criarCurso(campusDAO);
                    if (curso != null) {
                        cursoDAO.create(curso);
                    } else {
                        GUI.error();
                    }

                    break;
                case 2:
                    cursoView.mostrarIdCurso(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Curso cursoAlt = cursoDAO.find(auxLoc);
                    if (cursoAlt != null) {
                        cursoDAO.update(cursoView.modifCurso(cursoAlt, campusDAO));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3: {
                    cursoView.mostrarCurso(vetResult);
                }
                break;
                case 4:
                    cursoView.mostrarIdCurso(vetResult);
                    ;
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    if (auxLoc != 0) {
                        cursoDAO.delete(auxLoc);
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
