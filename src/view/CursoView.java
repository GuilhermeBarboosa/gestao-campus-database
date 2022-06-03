/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CampusDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Campus;
import model.Curso;

/**
 *
 * @author Usuario
 */
public class CursoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    CampusView campusView = new CampusView();
    int id = 0;

    public Curso criarCurso(CampusDAO campusDAO) {
        try {

            Curso curso = new Curso();

            curso.setId(id);
            id++;

            System.out.println("Nome: ");
            curso.setNome(ler.nextLine());
            System.out.println("Estado 1-ATIVO 2-INATIVO: ");
            int aux = Integer.parseInt(ler.nextLine());

            if (aux == 1) {
                curso.setEstado("ativo");
            } else {
                curso.setEstado("inativo");
            }

            System.out.println("Ano de inicio: ");
            curso.setAnoInicio(Integer.parseInt(ler.nextLine()));
            System.out.println("Ano de término: ");
            curso.setAnoTermino(Integer.parseInt(ler.nextLine()));

            List<Campus> campusVet = campusDAO.read();

            if (campusVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                campusView.mostrarIdTodosCampos(campusVet);
            }

            System.out.println("Insira o ID: ");

            Campus campus = campusDAO.find(Integer.parseInt(ler.nextLine()));
            curso.setCampus(campus);

            curso.setDtCriacao(LocalDate.now());
            return curso;
        } catch (Exception e) {
            return null;
        }

    }

    public Curso modifCurso(Curso cursoAlt, CampusDAO campusDAO) {
        try {
            System.out.println("Nome: ");
            cursoAlt.setNome(ler.nextLine());
            System.out.println("Estado 1-ATIVO 2-INATIVO: ");
            int aux = Integer.parseInt(ler.nextLine());
            if (aux == 1) {
                cursoAlt.setEstado("ativo");
            } else {
                cursoAlt.setEstado("inativo");
            }
            System.out.println("Data de inicio: ");
            cursoAlt.setAnoInicio(Integer.parseInt(ler.nextLine()));
            System.out.println("Data de término: ");
            cursoAlt.setAnoTermino(Integer.parseInt(ler.nextLine()));

            List<Campus> campusVet = campusDAO.read();

            if (campusVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                campusView.mostrarIdTodosCampos(campusVet);
            }

            System.out.println("Insira o ID: ");

            Campus campus = campusDAO.find(Integer.parseInt(ler.nextLine()));
            cursoAlt.setCampus(campus);

            cursoAlt.setDtModificacao(LocalDate.now());
            return cursoAlt;
        } catch (Exception e) {
            return null;
        }

    }

    public void mostrarCurso(List<Curso> vetResult) {
        for (Curso curso : vetResult) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + curso.getId());
            System.out.println("CURSO: " + curso.getNome());
            System.out.println("CAMPUS: " + curso.getCampus().getNome());
            System.out.println("ESTADO: " + curso.getEstado());
            System.out.println("ANO DE INICIO: " + curso.getAnoInicio());
            System.out.println("ANO DE TERMINO: " + curso.getAnoTermino());
            System.out.println("DATA DE CRIAÇÂO: " + curso.getDtCriacao());
            if (curso.getDtModificacao() != null) {
                System.out.println("DATA DE MODIFICAÇÂO: " + curso.getDtModificacao());
            }
            System.out.println("-----------------------------------");
        }
    }

    public void mostrarIdCurso(List<Curso> vetResult) {
        for (Curso curso : vetResult) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + curso.getId());
            System.out.println("CURSO: " + curso.getNome());
            System.out.println("CAMPUS: " + curso.getCampus().getNome());
            System.out.println("-----------------------------------");
        }
    }
}
