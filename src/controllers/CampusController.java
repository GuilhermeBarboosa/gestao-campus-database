/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CampusDAO;
import java.util.List;
import model.Campus;
import view.CampusView;
import view.Gui;
import model.Default;

/**
 *
 * @author Aluno
 */
public class CampusController extends DefaultController implements Default{

    private final Gui GUI = new Gui();
    private final CampusView campusView = new CampusView();

    public void menu() throws Exception {
        System.out.println("CAMPUS");
        opcCrud = GUI.menu();

        CampusDAO campusDAO = new CampusDAO();
      
        List<Campus> vetResult = campusDAO.read();

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
                    campusView.mostrarIdTodosCampos(vetResult);
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
                    campusView.mostrarIdTodosCampos(vetResult);
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
