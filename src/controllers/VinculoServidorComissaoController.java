/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
import java.util.List;
import model.Comissao;
import model.Vinculo;
import view.VinculoView;

/**
 *
 * @author Aluno
 */
public class VinculoServidorComissaoController extends DefaultController {

    private final VinculoView vinculoView = new VinculoView();

    public void menu(VinculoDAO vinculoDAO, ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) throws Exception {
        System.out.println("VINCULO DE SERVIDOR A COMISSÃO");

        List<String> servidorVet = servidorDAO.readId();
        List<String> comissaoVet = comissaoDAO.readId();

        List<String> vetResultId = vinculoDAO.readId();
        List<String> vetResult = vinculoDAO.read();

        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Vinculo vinc = vinculoView.criarVinculo(servidorVet, comissaoVet);
                if (vinc != null) {
                    vinculoDAO.create(vinc);
                } else {
                    GUI.error();
                }
                break;
            case 2:
                vinculoView.mostrarTodosVinculos(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Vinculo vinAlt = vinculoDAO.find(auxLoc);
                if (vinAlt != null) {
                    vinculoDAO.update(vinculoView.modifVinculo(vinAlt, servidorVet, comissaoVet));
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                vinculoView.mostrarTodosVinculos(vetResult);
                break;
            case 4:
                vinculoView.mostrarTodosVinculos(vetResultId);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                if (auxLoc != 0) {
                    vinculoDAO.delete(auxLoc);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }

}
