/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import java.util.List;
import java.util.Scanner;
import model.Disciplina;
import view.DisciplinaView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class DisciplinaController {

    private final DisciplinaView disciplinaView = new DisciplinaView();
    private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("DISCIPLINA");

        List<Disciplina> vetResult = disciplinaDAO.read();

        opcCrud = GUI.menu();
        try {
            switch (opcCrud) {
                case 1:
                    Disciplina disc = disciplinaView.criarDisciplina(cursoDAO);
                    if (disc != null) {
                        disciplinaDAO.create(disc);
                    } else {
                        GUI.error();
                    }

                    break;
                case 2:
                    disciplinaView.mostrarIdTodasDisciplinas(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Disciplina discAlt = disciplinaDAO.find(auxLoc);
                    if (discAlt != null) {
                        disciplinaDAO.update(disciplinaView.modifDisciplina(discAlt, cursoDAO));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    disciplinaView.mostrarTodasDisciplinas(vetResult);
                    break;
                case 4:
                    disciplinaView.mostrarIdTodasDisciplinas(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    if (auxLoc != 0) {
                        disciplinaDAO.delete(auxLoc);
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
