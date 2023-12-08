/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Curso;
import model.Disciplina;
import service.CursoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gui
 */
public class DisciplinaView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    CursoView cursoView = new CursoView();
    CursoService cursoService = new CursoService();
    Scanner ler = new Scanner(System.in);
    int id = 0;

    public Disciplina create() {
        try {
            Disciplina disciplina = new Disciplina();

            System.out.println("Nome: ");
            disciplina.setNome(ler.nextLine());
            System.out.println("Carga Horaria: ");
            disciplina.setCargaHoraria(Double.parseDouble(ler.nextLine()));
            System.out.println("Periodo: ");
            disciplina.setPeriodo(Integer.parseInt(ler.nextLine()));

            List<Curso> listCurso = cursoService.read();

            if (listCurso.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                cursoView.printId(listCurso);
            }

            System.out.println("Insira o ID: ");
            Curso curso = cursoService.getById(Integer.parseInt(ler.nextLine()));
            disciplina.setCurso(curso);

            disciplina.setDtCriacao(LocalDate.now());
            return disciplina;
        } catch (Exception e) {
            return null;
        }
    }

    public Disciplina update(Disciplina disciplina) {
        try {
            System.out.println("Nome: ");
            disciplina.setNome(ler.nextLine());
            System.out.println("Carga Horaria: ");
            disciplina.setCargaHoraria(Double.parseDouble(ler.nextLine()));
            System.out.println("Periodo: ");
            disciplina.setPeriodo(Integer.parseInt(ler.nextLine()));

            List<Curso> listCurso = cursoService.read();

            if (listCurso.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                cursoView.printId(listCurso);
            }

            System.out.println("Insira o ID: ");
            Curso curso = cursoService.getById(Integer.parseInt(ler.nextLine()));
            disciplina.setCurso(curso);

            disciplina.setDtModificacao(LocalDate.now());

            return disciplina;
        } catch (Exception e) {
            return null;
        }
    }

    public void printAll(List<Disciplina> listDisciplina) {
        if (listDisciplina.size() == 0) {
            System.out.println("Não há disciplinas cadastrados");
        } else {
            for (Disciplina disciplina : listDisciplina) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + disciplina.getId());
                System.out.println("DISCIPLINA: " + disciplina.getNome());
                System.out.println("CURSO: " + disciplina.getCurso().getNome());
                System.out.println("CARGA HORARIA: " + disciplina.getCargaHoraria());
                System.out.println("PERIODO: " + disciplina.getPeriodo());
                System.out.println("DATA DE CRIAÇÃO: " + disciplina.getDtCriacao());
                if (disciplina.getDtModificacao() != null) {
                    System.out.println("DATA DE MODIFICAÇÃO: " + disciplina.getDtModificacao());
                }
                System.out.println("-----------------------------------");
            }
        }
    }

    public void printId(List<Disciplina> listDisciplina) {
        if (listDisciplina.size() == 0) {
            System.out.println("Não há disciplinas cadastrados");
        } else {
            for (Disciplina disciplina : listDisciplina) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + disciplina.getId());
                System.out.println("DISCIPLINA: " + disciplina.getNome());
                System.out.println("CURSO: " + disciplina.getCurso().getNome());
                System.out.println("-----------------------------------");
            }
        }
    }

}
