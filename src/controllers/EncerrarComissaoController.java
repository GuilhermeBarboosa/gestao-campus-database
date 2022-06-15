/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.VinculoDAO;
import java.util.List;
import java.util.Scanner;
import model.Comissao;
import view.ComissaoView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class EncerrarComissaoController {

    private final ComissaoView comissaoView = new ComissaoView();
    private final ComissaoDAO comissaoDAO = new ComissaoDAO();
    private final VinculoDAO vinculoDAO = new VinculoDAO();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

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
