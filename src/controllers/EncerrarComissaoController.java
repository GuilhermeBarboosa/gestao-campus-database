/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DefaultDAO;
import java.util.List;
import model.Comissao;
import view.ComissaoView;

/**
 *
 * @author Aluno
 */
public class EncerrarComissaoController extends DefaultController implements DefaultDAO{

    private final ComissaoView comissaoView = new ComissaoView();

    public void menu() throws Exception {
        try {
            System.out.println("ENCERRAR COMISSAO");

            List<String> vetResultId = comissaoDAO.readId();

            comissaoView.mostrarTodosComissao(vetResultId);

            GUI.printID();
            System.out.println("Insira a comissao que deseja encerrar: ");
            auxLoc = Integer.parseInt(ler.nextLine());

            Comissao comAux = comissaoDAO.find(auxLoc);
            if (comAux != null) {
                comissaoDAO.modificarEncerradomento(comAux);
                vinculoDAO.encerrarVinculos(comAux);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
