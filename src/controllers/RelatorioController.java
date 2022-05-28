package controllers;

import dao.AtividadeDAO;
import dao.CampusDAO;
import dao.ComissaoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.OfertaDAO;
import dao.OrientacaoDAO;
import dao.RelatorioDAO;
import dao.ReuniaoDAO;
import dao.ReuniaoPresenteDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
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
