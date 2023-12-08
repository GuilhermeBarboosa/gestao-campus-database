/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Oferta;
import service.OfertaService;
import view.Gui;
import view.OfertaView;

import java.util.List;
import java.util.Scanner;

/**
 * @author Aluno
 */
public class OfertaDisciplinaController {

    private final OfertaView ofertaView = new OfertaView();

    private final OfertaService ofertaService = new OfertaService();


    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("OFERTA DE DISCIPLINA");
        opcCrud = GUI.menu();
        Oferta oferta = new Oferta();
        List<Oferta> listOferta = ofertaService.read();

        try {
            switch (opcCrud) {
                case 1:
                    oferta = ofertaView.create();
                    if (oferta != null) {
                        ofertaService.create(oferta);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    ofertaView.printId(listOferta);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());
                    oferta = ofertaService.getById(id);
                    if (oferta != null) {
                        ofertaService.update(oferta);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    ofertaView.printAll(listOferta);
                    break;
                case 4:
                    ofertaView.printId(listOferta);
                    GUI.printID();
                    id = Integer.parseInt(ler.nextLine());

                    oferta = ofertaService.getById(id);
                    if (oferta != null) {
                        ofertaService.delete(oferta);
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
