package controllers;

import java.time.LocalDate;
import java.util.Scanner;
import view.Gui;
import view.RelatorioView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aluno
 */
public class RelatorioController{

    private final RelatorioView relatView = new RelatorioView();
    
    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);
    
    public void gerarRelat() throws Exception {
        int opRelat = 0;
        LocalDate[] filtro;
        opRelat = relatView.opMenuRelat();

        try {
            switch (opRelat) {
                case 1:
                    filtro = relatView.opMenuRelat1();
                    relatView.relat1(filtro);
                    break;
                case 2:
                    relatView.relat2();
                    break;
                case 3:
                    relatView.relat3();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
