/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CampusDAO;
import dao.DefaultDAO;
import java.util.List;
import model.Campus;
import view.CampusView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class CampusController extends DefaultController implements DefaultDAO{

    private final Gui GUI = new Gui();
    private final CampusView campusView = new CampusView();

    public void menu() throws Exception {
        System.out.println("CAMPUS");
        opcCrud = GUI.menu();

        CampusDAO campusDAO = new CampusDAO();
        
        List<String> vetResultId = campusDAO.readId();
        List<String> vetResult = campusDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Campus campus = campusView.criarCampus();
                    if (campus != null) {
                        campusDAO.create(campus);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    campusView.mostrarTodosCampos(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Campus CampusAlt = campusDAO.find(auxLoc);
                    if (CampusAlt != null) {
                        campusDAO.update(campusView.modifCampus(CampusAlt));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    campusView.mostrarTodosCampos(vetResult);
                    break;
                case 4:
                    campusView.mostrarTodosCampos(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    campusDAO.delete(auxLoc);
                    if (auxLoc != 0) {
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
