package controllers;

import dao.AtividadeDAO;
import dao.CampusDAO;
import dao.ComissaoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.OfertaDAO;
import dao.OrientacaoDAO;
import dao.ReuniaoDAO;
import dao.ReuniaoPresenteDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
import java.time.LocalDate;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.text.html.HTML.Attribute.DATA;
import model.Atividade;
import model.Campus;
import model.Comissao;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Orientacao;
import model.Reuniao;
import model.Servidor;
import model.Vinculo;
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

    public void gerarRelat(CampusDAO campusDAO, ServidorDAO servidorDAO, CursoDAO cursoDAO,
            DisciplinaDAO disciplinaDAO, OfertaDAO ofertaDAO, OrientacaoDAO orientacaoDAO, AtividadeDAO ativiDAO,
            ComissaoDAO comissaoDAO, VinculoDAO vincDAO, ReuniaoDAO reuniaoDAO, ReuniaoPresenteDAO reunPresenteDAO) {
        int opRelat = 0;
        LocalDate[] filtro;
        opRelat = relatView.opMenuRelat();

        switch (opRelat) {
            case 1:
                filtro = relatView.opMenuRelat1();
                relatView.relat1(filtro, reuniaoDAO, comissaoDAO, servidorDAO);
                break;
            case 2:
                relatView.relat2(servidorDAO, ofertaDAO, ativiDAO, vincDAO, orientacaoDAO, disciplinaDAO, cursoDAO, comissaoDAO);
                break;
            case 3:
                relatView.relat3(campusDAO, ofertaDAO, cursoDAO, disciplinaDAO);
                break;
            default:
                break;
        }

    }

 

    
    
}
