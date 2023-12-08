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
import service.CampusService;

/**
 *
 * @author Usuario
 */
public class CursoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    CampusView campusView = new CampusView();
    CampusService campusService = new CampusService();

    public Curso create() {
        try {

            return inputInfoCurso(new Curso());
        } catch (Exception e) {
            return null;
        }

    }

    public Curso update(Curso curso) {
        try {
            return inputInfoCurso(curso);
        } catch (Exception e) {
            return null;
        }

    }

    private Curso inputInfoCurso(Curso curso) throws Exception {
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

        List<Campus> listCurso = campusService.read();

        if (listCurso.size() == 0) {
            System.out.println("Nenhum campus cadastrado...");
            return null;
        } else {
            campusView.printId(listCurso);
        }

        System.out.println("Insira o ID: ");

        Campus campus = campusService.getById(Integer.parseInt(ler.nextLine()));
        curso.setCampus(campus);

        curso.setDtCriacao(LocalDate.now());
        return curso;
    }


    public void printAll(List<Curso> listCurso) {
        for (Curso curso : listCurso) {
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

    public void printId(List<Curso> listCurso) {
        for (Curso curso : listCurso) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + curso.getId());
            System.out.println("CURSO: " + curso.getNome());
            System.out.println("CAMPUS: " + curso.getCampus().getNome());
            System.out.println("-----------------------------------");
        }
    }
}
