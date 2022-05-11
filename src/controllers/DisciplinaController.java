/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import model.Disciplina;
import view.DisciplinaView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class DisciplinaController extends DefaultController {
    
    private final DisciplinaView disciplinaView = new DisciplinaView();

    public void menu(DisciplinaDAO disciplinaDAO, CursoDAO cursoDAO ) {
        System.out.println("DISCIPLINA");
        Disciplina[] discVet = disciplinaDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Disciplina disc = disciplinaView.criarDisciplina(cursoDAO);
                disciplinaDAO.setDisciplina(disc);
                break;
            case 2:
                disciplinaView.mostrarTodasDisciplinas(discVet, cursoDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Disciplina discAlt = disciplinaDAO.getId(auxLoc);
                if (discAlt != null) {
                    disciplinaDAO.update(disciplinaView.modifDisciplina(discAlt, cursoDAO));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                disciplinaView.mostrarTodasDisciplinas(discVet, cursoDAO);
                break;
            case 4:
                disciplinaView.mostrarTodasDisciplinas(discVet, cursoDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Disciplina discDelete = disciplinaDAO.getId(auxLoc);
                if (discDelete != null) {
                    disciplinaDAO.delete(discDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }
}
