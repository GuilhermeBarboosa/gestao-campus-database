/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CursoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Curso;
import model.Disciplina;

/**
 *
 * @author Gui
 */
public class DisciplinaView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    CursoView cursoV = new CursoView();
    Scanner ler = new Scanner(System.in);
    int id = 0;

    public Disciplina criarDisciplina(CursoDAO cursoDAO) {
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

            List<Curso> cursoVet = cursoDAO.read();

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                cursoV.mostrarIdCurso(cursoVet);
            }

            System.out.println("Insira o ID: ");
            Curso curso = cursoDAO.find(Integer.parseInt(ler.nextLine()));
            disc.setCurso(curso);

            disc.setDtCriacao(LocalDate.now());
            return disc;
        } catch (Exception e) {
            return null;
        }
    }

    public Disciplina modifDisciplina(Disciplina discAlt, CursoDAO cursoDAO) {
        try {
            System.out.println("Nome: ");
            discAlt.setNome(ler.nextLine());
            System.out.println("Carga Horaria: ");
            discAlt.setCargaHoraria(Double.parseDouble(ler.nextLine()));
            System.out.println("Periodo: ");
            discAlt.setPeriodo(Integer.parseInt(ler.nextLine()));

            List<Curso> cursoVet = cursoDAO.read();

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                cursoV.mostrarIdCurso(cursoVet);
            }

            System.out.println("Insira o ID: ");
            Curso curso = cursoDAO.find(Integer.parseInt(ler.nextLine()));
            discAlt.setCurso(curso);

            discAlt.setDtModificacao(LocalDate.now());

            return discAlt;
        } catch (Exception e) {
            return null;
        }
    }

    public void mostrarTodasDisciplinas(List<Disciplina> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há disciplinas cadastrados");
        } else {
            for (Disciplina disciplina : vetResult) {
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

    public void mostrarIdTodasDisciplinas(List<Disciplina> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há disciplinas cadastrados");
        } else {
            for (Disciplina disciplina : vetResult) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + disciplina.getId());
                System.out.println("DISCIPLINA: " + disciplina.getNome());
                System.out.println("CURSO: " + disciplina.getCurso().getNome());
                System.out.println("-----------------------------------");
            }
        }
    }

}
