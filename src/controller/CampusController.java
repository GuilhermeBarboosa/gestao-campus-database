/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CampusDAO;

import java.util.List;
import java.util.Scanner;

import model.Campus;
import service.CampusService;
import view.CampusView;
import view.Gui;

/**
 * @author Aluno
 */
public class CampusController {

    private final Gui GUI = new Gui();
    private final CampusView campusView = new CampusView();
    private final CampusService campusService = new CampusService();

    public int opcCrud;
    public int id;
    public Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("CAMPUS");
        opcCrud = GUI.menu();
        Campus campus = new Campus();
        List<Campus> listCampus = campusService.read();

        try {
            switch (opcCrud) {
                case 1:
                    campus = campusView.create();
                    if (campus != null) {
                        campusService.create(campus);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    campusView.printId(listCampus);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    campus = campusService.getById(id);
                    if (campus != null) {
                        campusService.update(campusView.update(campus));
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    campusView.printAll(listCampus);
                    break;
                case 4:
                    campusView.printId(listCampus);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    campusService.delete(id);
                    if (id != 0) {
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
