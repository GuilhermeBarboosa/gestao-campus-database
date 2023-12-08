/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Servidor;
import service.CursoService;
import service.DisciplinaService;
import service.ServidorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gui
 */
public class OfertaView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ServidorService servidorService = new ServidorService();
    CursoService cursoService = new CursoService();
    DisciplinaService disciplinaService = new DisciplinaService();
    ServidorView servidorView = new ServidorView();
    CursoView cursoView = new CursoView();
    DisciplinaView disciplinaView = new DisciplinaView();

    Scanner ler = new Scanner(System.in);

    public Oferta create() {
        try {
            return inputInfoOferta(new Oferta());
        } catch (Exception e) {
            return null;
        }
    }

    public Oferta update(Oferta oferta) {
        try {
            return inputInfoOferta(oferta);
        } catch (Exception e) {
            return null;
        }
    }

    private Oferta inputInfoOferta(Oferta oferta) throws Exception {
        List<Servidor> listServidor = servidorService.read();

        if (listServidor.size() == 0) {
            System.out.println("Nenhum servidores cadastrado...");
            return null;
        } else {
            servidorView.printId(listServidor);
        }
        System.out.println("Escolha pelo id um servidor:");
        Servidor servidor = servidorService.getById(Integer.parseInt(ler.nextLine()));
        oferta.setServidor(servidor);

        List<Curso> cursoVet = cursoService.read();

        if (cursoVet.size() == 0) {
            System.out.println("Nenhum curso cadastrado...");
            return null;
        } else {
            cursoView.printId(cursoVet);
        }
        System.out.println("Escolha pelo id um curso:");
        Curso curso = cursoService.getById(Integer.parseInt(ler.nextLine()));
        oferta.setCurso(curso);

        List<Disciplina> disciplinaVet = disciplinaService.read();
        if (disciplinaVet.size() == 0) {
            System.out.println("Nenhum disciplina cadastrado...");
            return null;
        } else {
            disciplinaView.printId(disciplinaVet);
        }
        System.out.println("Escolha pelo id uma disciplina:");
        Disciplina disciplina = disciplinaService.getById(Integer.parseInt(ler.nextLine()));
        oferta.setDisciplina(disciplina);

        System.out.println("Ano: ");
        oferta.setAno(Integer.parseInt(ler.nextLine()));
        System.out.println("Semestre: ");
        oferta.setSemestre(Integer.parseInt(ler.nextLine()));
        System.out.println("Aulas Semanais: ");
        oferta.setAulaSemanais(Integer.parseInt(ler.nextLine()));
        oferta.setDtCriacao(LocalDate.now());
        return oferta;
    }

    public void printAll(List<Oferta> listOferta) {
        if (listOferta.size() == 0) {
            System.out.println("Não há ofertas cadastradas");
        } else {
            for (Oferta oferta : listOferta) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + oferta.getId());
                System.out.println("CURSO: " + oferta.getCurso().getNome());
                System.out.println("DISCIPLINA: " + oferta.getDisciplina().getNome());
                System.out.println("SERVIDOR: " + oferta.getServidor().getNome());
                System.out.println("ANO: " + oferta.getAno());
                System.out.println("SEMESTRE: " + oferta.getSemestre());
                System.out.println("AULA SEMANAL: " + oferta.getAulaSemanais());
                System.out.println("DATA DE CRIAÇÃO: " + oferta.getDtCriacao());
                if (oferta.getDtMoficacao() != null) {
                    System.out.println("DATA DE MODIFICAÇÃO: " + oferta.getDtMoficacao());
                }
                System.out.println("-----------------------------------");
            }
        }
    }

    public void printId(List<Oferta> listOferta) {
        if (listOferta.size() == 0) {
            System.out.println("Não há ofertas cadastradas");
        } else {
            for (Oferta oferta : listOferta) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + oferta.getId());
                System.out.println("CURSO: " + oferta.getCurso().getNome());
                System.out.println("DISCIPLINA: " + oferta.getDisciplina().getNome());
                System.out.println("SERVIDOR: " + oferta.getServidor().getNome());
                System.out.println("-----------------------------------");
            }
        }
    }

}
