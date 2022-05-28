package controllers;

import dao.DefaultDAO;
import java.time.LocalDate;
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
public class RelatorioController extends DefaultController implements DefaultDAO{

    private final RelatorioView relatView = new RelatorioView();

    public void gerarRelat() throws Exception {
        int opRelat = 0;
        LocalDate[] filtro;
        opRelat = relatView.opMenuRelat();

        try {
            switch (opRelat) {
                case 1:
                    filtro = relatView.opMenuRelat1();
                    relatView.relat1(filtro, relatorioDAO);
                    break;
                case 2:
                    relatView.relat2(servidorDAO, relatorioDAO);
                    break;
                case 3:
                    relatView.relat3(campusDAO, relatorioDAO);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
