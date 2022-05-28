package controllers;

import dao.CampusDAO;
import dao.RelatorioDAO;
import dao.ServidorDAO;
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
public class RelatorioController extends DefaultController {

    private final RelatorioView relatView = new RelatorioView();

    public void gerarRelat(CampusDAO campusDAO, ServidorDAO servidorDAO,RelatorioDAO relatorioDAO) throws Exception {
        int opRelat = 0;
        LocalDate[] filtro;
        opRelat = relatView.opMenuRelat();

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

    }

 

    
    
}
