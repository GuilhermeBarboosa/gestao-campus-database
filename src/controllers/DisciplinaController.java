/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Disciplina;
import view.DisciplinaView;
import dao.Default;

/**
 *
 * @author Aluno
 */
public class DisciplinaController extends DefaultController implements Default{

    private final DisciplinaView disciplinaView = new DisciplinaView();

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
