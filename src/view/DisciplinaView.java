/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CursoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Curso;
import model.Disciplina;

/**
 *
 * @author Gui
 */
public class DisciplinaView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public Disciplina criarDisciplina(CursoDAO cursoDAO) {
        String cursoRead = "";
        Disciplina disc = new Disciplina();
        System.out.println("Nome: ");
        disc.setNome(ler.nextLine());
        System.out.println("Carga Horaria: ");
        disc.setCargaHoraria(Double.parseDouble(ler.nextLine()));
        System.out.println("Periodo: ");
        disc.setPeriodo(ler.nextLine());
        
        Curso[] cursoAll = cursoDAO.getAll();
        for (Curso curso : cursoAll) {
            if (curso != null) {
                cursoRead += "ID: " + curso.getId() + " - NOME: " + curso.getNome() + "\n";
            }
        }
        
        System.out.println(cursoRead);  
        System.out.println("Escolha pelo id um curso:");
        Curso cursoAux = cursoDAO.getId(Integer.parseInt(ler.nextLine()));
        
        disc.setCurso(cursoAux);

        disc.setDtCriacao(LocalDate.now());
        return disc;
    }

    public Disciplina modifDisciplina(Disciplina discAlt, CursoDAO cursoDAO) {
        String cursoRead = "";
        System.out.println("Nome: ");
        discAlt.setNome(ler.nextLine());
        System.out.println("Carga Horaria: ");
        discAlt.setCargaHoraria(Double.parseDouble(ler.nextLine()));
        System.out.println("Periodo: ");
        discAlt.setPeriodo(ler.nextLine());
     
        Curso[] cursoAll = cursoDAO.getAll();
        for (Curso curso : cursoAll) {
            if (curso != null) {
                cursoRead += "ID: " + curso.getId() + " - NOME: " + curso.getNome() + "\n";
            }
        }
        System.out.println(cursoRead);  
        System.out.println("Escolha pelo id um curso:");
        Curso cursoAux = cursoDAO.getId(Integer.parseInt(ler.nextLine()));
        
        discAlt.setCurso(cursoAux);

        discAlt.setDtModificacao(LocalDate.now());
        return discAlt;
    }

    public void mostrarTodasDisciplinas(Disciplina[] disc, CursoDAO cursoDAO) {
        boolean aux = false;
        String stringResponse = "";
        for (int i = 0; i < disc.length; i++) {
            if (disc[i] != null) {
                aux = true;
            }
        }

        if (aux) {
            for (int i = 0; i < disc.length; i++) {
                if (disc[i] != null) {
                    System.out.println(disc[i].toString());
                }
            }
            System.out.println(stringResponse);
        } else {
            System.out.println("Não ha disciplina cadastradas.");
        }
    }

    public Disciplina setAleatorio1() {
        CursoDAO cursoDAO = new CursoDAO();
        Curso cursoAux = cursoDAO.getId(1);
        
        
        Disciplina disc = new Disciplina();
        disc.setNome("Algoritmo");
        disc.setCargaHoraria(4);
        disc.setPeriodo("Semestral");
        disc.setCurso(cursoAux);
        disc.setDtCriacao(LocalDate.now());
        return disc;
    }

    public Disciplina setAleatorio2() {
        CursoDAO cursoDAO = new CursoDAO();
        Curso cursoAux = cursoDAO.getId(0);
        
        Disciplina disc = new Disciplina();
        disc.setNome("Estrutura de Dados");
        disc.setCargaHoraria(5);
        disc.setPeriodo("Semestral");
        disc.setCurso(cursoAux);
        disc.setDtCriacao(LocalDate.now());
        return disc;
    }

}
