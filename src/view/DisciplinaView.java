/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Disciplina;

/**
 *
 * @author Gui
 */
public class DisciplinaView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    int id = 0;

    public Disciplina criarDisciplina(List<String> cursoVet) {
        try {
            Disciplina disc = new Disciplina();

            disc.setId(id);
            id++;

            System.out.println("Nome: ");
            disc.setNome(ler.nextLine());
            System.out.println("Carga Horaria: ");
            disc.setCargaHoraria(Double.parseDouble(ler.nextLine()));
            System.out.println("Periodo: ");
            disc.setPeriodo(Integer.parseInt(ler.nextLine()));

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                for (String string : cursoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id um curso:");
            disc.setId_curso(Integer.parseInt(ler.nextLine()));

            disc.setDtCriacao(LocalDate.now());
            return disc;
        } catch (Exception e) {
            return null;
        }
    }

    public Disciplina modifDisciplina(Disciplina discAlt, List<String> cursoVet) {
        try {
            System.out.println("Nome: ");
            discAlt.setNome(ler.nextLine());
            System.out.println("Carga Horaria: ");
            discAlt.setCargaHoraria(Double.parseDouble(ler.nextLine()));
            System.out.println("Periodo: ");
            discAlt.setPeriodo(Integer.parseInt(ler.nextLine()));

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                for (String string : cursoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id um curso:");

            discAlt.setId_curso(Integer.parseInt(ler.nextLine()));

            discAlt.setDtModificacao(LocalDate.now());

            return discAlt;
        } catch (Exception e) {
            return null;
        }
    }

    public void mostrarTodasDisciplinas(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há disciplinas cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }

}
