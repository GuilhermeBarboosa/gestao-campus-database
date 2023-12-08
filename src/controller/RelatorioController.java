package controller;

import view.RelatorioView;

import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Aluno
 */
public class RelatorioController {

    private final RelatorioView relatView = new RelatorioView();

    public void gerarRelatorio() {
        int opRelat = 0;
        LocalDate[] filtro;
        opRelat = relatView.opcaoMenuRelatorio();

        try {
            switch (opRelat) {
                case 1:
                    filtro = relatView.paramsRelatorio();
                    relatView.relatorioTipo1(filtro);
                    break;
                case 2:
                    relatView.relatorioTipo2();
                    break;
                case 3:
                    relatView.relatorioTipo3();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
