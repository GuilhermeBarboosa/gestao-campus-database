/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CampusDAO;
import dao.CursoDAO;
import java.util.List;
import model.Curso;
import view.CursoView;

/**
 *
 * @author Aluno
 */
public class CursoController extends DefaultController {

    private final CursoView cursoView = new CursoView();

    public void menu(CursoDAO cursoDAO, CampusDAO campusDAO) throws Exception {
        System.out.println("CURSO");
        opcCrud = GUI.menu();
        List<String> campusVet = campusDAO.readId();

        List<String> vetResultId = cursoDAO.readId();
        List<String> vetResult = cursoDAO.read();
        switch (opcCrud) {
            case 1:
                Curso curso = cursoView.criarCurso(campusVet);
                if(curso != null){
                    cursoDAO.create(curso);
                }else{
                   GUI.error();
                }
                
                break;
            case 2:
                cursoView.mostrarCurso(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Curso cursoAlt = cursoDAO.find(auxLoc);
                if (cursoAlt != null) {
                    cursoDAO.update(cursoView.modifCurso(cursoAlt, campusVet));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                cursoView.mostrarCurso(vetResult);
                break;
            case 4:
                cursoView.mostrarCurso(vetResultId);
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
    }
}
