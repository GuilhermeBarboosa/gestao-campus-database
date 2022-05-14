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
    int id = 0;

    public Curso criarCurso(List<String> campusVet) {

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

        for (String string : campusVet) {
            System.out.println(string);
        }

        System.out.println("Insira o ID: ");
        curso.setId_campus(Integer.parseInt(ler.nextLine()));

        curso.setDtCriacao(LocalDate.now());
        return curso;
    }

    public Curso modifCurso(Curso cursoAlt, List<String> campusVet) {

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

        for (String string : campusVet) {
            System.out.println(string);
        }

        System.out.println("Insira o ID: ");
        cursoAlt.setId_campus(Integer.parseInt(ler.nextLine()));

        cursoAlt.setDtModificacao(LocalDate.now());
        return cursoAlt;
    }

    public void mostrarCurso(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há cursos cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }

    }

}
