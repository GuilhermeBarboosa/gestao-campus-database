/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Comissao;
import view.ComissaoView;
import dao.Default;

/**
 *
 * @author Aluno
 */
public class EncerrarComissaoController extends DefaultController implements Default{

    private final ComissaoView comissaoView = new ComissaoView();

    public void menu() throws Exception {
        try {
            System.out.println("ENCERRAR COMISSAO");

            List<Comissao> vetResult = comissaoDAO.read();

            comissaoView.mostrarIdTodosComissao(vetResult);

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
