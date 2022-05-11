/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
import model.Comissao;
import model.Vinculo;
import view.VinculoView;

/**
 *
 * @author Aluno
 */
public class VinculoServidorComissaoController extends DefaultController {

    private final VinculoView vinculoView = new VinculoView();

    public void menu(VinculoDAO vinculoDAO, ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {
        System.out.println("VINCULO DE SERVIDOR A COMISSÃO");
        Vinculo[] vincVet = vinculoDAO.getAll();
        opcCrud = GUI.menu();
        switch (opcCrud) {
            case 1:
                Vinculo vinc = vinculoView.criarVinculo(servidorDAO, comissaoDAO);
                vinculoDAO.setVinculo(vinc);
                Comissao auxVinc = comissaoDAO.getId(vinc.getComissao().getId());
                servidorDAO.aumentarHoras(vinc.getServidor().getId(), auxVinc.getHorasSemanais());
                break;
            case 2:
                vinculoView.mostrarTodosVinculos(vincVet, comissaoDAO, servidorDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Vinculo vinAlt = vinculoDAO.getId(auxLoc);
                auxVinc = comissaoDAO.getId(vinAlt.getComissao().getId());
                servidorDAO.removerHoras(vinAlt.getServidor().getId(), auxVinc.getHorasSemanais());
                if (vinAlt != null) {
                    vinculoDAO.update(vinculoView.modifVinculo(vinAlt, servidorDAO, comissaoDAO));
                    auxVinc = comissaoDAO.getId(vinAlt.getComissao().getId());
                    servidorDAO.aumentarHoras(vinAlt.getServidor().getId(), auxVinc.getHorasSemanais());
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
            case 3:
                vinculoView.mostrarTodosVinculos(vincVet, comissaoDAO, servidorDAO);
                break;
            case 4:
                vinculoView.mostrarTodosVinculos(vincVet, comissaoDAO, servidorDAO);
                GUI.printID();
                auxLoc = Integer.parseInt(ler.nextLine());
                Vinculo vincDelete = vinculoDAO.getId(auxLoc);
                if (vincDelete != null) {
                    vinculoDAO.delete(vincDelete);
                    GUI.sucess();
                } else {
                    GUI.error();
                }
                break;
        }
    }

}
