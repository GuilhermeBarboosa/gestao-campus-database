/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Campus;
import model.Servidor;
import view.ServidorView;
import dao.Default;
/**
 *
 * @author Aluno
 */
public class ServidorController extends DefaultController implements Default{


    public void menu() throws Exception {
        System.out.println("SERVIDOR");
        opcCrud = GUI.menu();
        
        
        
        List<Servidor> vetResult = servidorDAO.read();

        try {
            switch (opcCrud) {
            case 1:
                Servidor servidor = servV.criarServ(campusDAO);
                if(servidor != null){
                     servidorDAO.create(servidor);
                }else{
                    GUI.error();
                }
                break;
            case 2:
                servV.mostrarIdServidores(vetResult);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Servidor servAlt = servidorDAO.find(auxLoc);

                if (servAlt != null) {
                    servidorDAO.update(servV.modifServ(servAlt, campusDAO));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                servV.mostrarServidores(vetResult);
                break;
            case 4:
                servV.mostrarIdServidores(vetResult);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                if (auxLoc != 0) {
                    servidorDAO.delete(auxLoc);
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
