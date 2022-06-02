/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Comissao;
import model.Servidor;
import model.Vinculo;
import view.VinculoView;
import dao.Default;

/**
 *
 * @author Aluno
 */
public class VinculoServidorComissaoController extends DefaultController implements Default{

    private final VinculoView vinculoView = new VinculoView();

    public void menu() throws Exception {
        System.out.println("VINCULO DE SERVIDOR A COMISSÃO");

        List<String> servidorVet = servidorDAO.readId();
        List<String> comissaoVet = comissaoDAO.readId();

        List<String> vetResultId = vinculoDAO.readId();
        List<String> vetResult = vinculoDAO.read();

        opcCrud = GUI.menu();

        try {
            switch (opcCrud) {
                case 1:
                    Vinculo vinc = vinculoView.criarVinculo(servidorVet, comissaoVet, servidorDAO);
                    if (vinc != null) {
                        Servidor servAux = servidorDAO.find(vinc.getId_servidor());
                        Comissao comAux = comissaoDAO.find(vinc.getId_comissao());
                        servidorDAO.updateHours(servAux, comAux.getHorasSemanais(), vinc.getId_servidor());

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
                        Servidor servAux = servidorDAO.find(vinAlt.getId_servidor());
                        Comissao comAux = comissaoDAO.find(vinAlt.getId_comissao());
                        servidorDAO.removeHours(servAux, comAux.getHorasSemanais(), vinAlt.getId_servidor());

                        vinculoDAO.update(vinculoView.modifVinculo(vinAlt, servidorVet, comissaoVet, servidorDAO));

                        servAux = servidorDAO.find(vinAlt.getId_servidor());
                        comAux = comissaoDAO.find(vinAlt.getId_comissao());
                        servidorDAO.updateHours(servAux, comAux.getHorasSemanais(), vinAlt.getId_servidor());

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

                    vinAlt = vinculoDAO.find(auxLoc);
                    if (vinAlt != null) {
                        Servidor servAux = servidorDAO.find(vinAlt.getId_servidor());
                        Comissao comAux = comissaoDAO.find(vinAlt.getId_comissao());
                        servidorDAO.removeHours(servAux, comAux.getHorasSemanais(), vinAlt.getId_servidor());

                        vinculoDAO.delete(auxLoc);
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
