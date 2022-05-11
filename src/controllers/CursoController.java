/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CampusDAO;
import dao.CursoDAO;
import java.util.Scanner;
import model.Curso;
import view.CursoView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class CursoController extends DefaultController{
    
    private final CursoView cursoView = new CursoView();

    public void menu(CursoDAO cursoDAO, CampusDAO campusDAO) {
        System.out.println("CURSO");
        Curso[] cursoVet = cursoDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Curso curso = cursoView.criarCurso(campusDAO);
                cursoDAO.setCurso(curso);
                break;
            case 2:
                cursoView.mostrarTodosCursos(cursoVet, campusDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Curso cursoAlt = cursoDAO.getId(auxLoc);
                if (cursoAlt != null) {
                    cursoDAO.update(cursoView.modifCurso(cursoAlt, campusDAO));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                cursoView.mostrarTodosCursos(cursoVet, campusDAO);
                break;
            case 4:
                cursoView.mostrarTodosCursos(cursoVet, campusDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Curso cursoDelete = cursoDAO.getId(auxLoc);
                if (cursoDelete != null) {
                    cursoDAO.delete(cursoDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
