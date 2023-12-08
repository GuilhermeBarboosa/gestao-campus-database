/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Scanner;
import model.Comissao;
import service.ComissaoService;
import service.EncerrarComissaoService;
import service.VinculoService;
import view.ComissaoView;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class EncerrarComissaoController {

    private final ComissaoView comissaoView = new ComissaoView();
    private final ComissaoService comissaoService = new ComissaoService();
    private final EncerrarComissaoService encerrarComissaoService = new EncerrarComissaoService();
    private Gui GUI = new Gui();
    private int opcCrud;
    private int id;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        try {
            System.out.println("ENCERRAR COMISSAO");

            List<Comissao> listComissao = comissaoService.read();

            comissaoView.printId(listComissao);

            GUI.printID();
            System.out.println("Insira a comissao que deseja encerrar: ");
            id = Integer.parseInt(ler.nextLine());

            Comissao comissao = comissaoService.getById(id);
            if (comissao != null) {
                encerrarComissaoService.encerrarComissaoVinculos(comissao);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
