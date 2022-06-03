/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class OfertaView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    ServidorView servV = new ServidorView();
    CursoView cursoV = new CursoView();
    DisciplinaView disciplinaV = new DisciplinaView();

    Scanner ler = new Scanner(System.in);
    int id = 0;

    public Oferta criarOferta(ServidorDAO servidorDAO, CursoDAO cursoDAO, DisciplinaDAO disciplinaDAO) {

        try {

            Oferta of = new Oferta();
            of.setId(id);
            id++;

            List<Servidor> servidorVet = servidorDAO.read();

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidores cadastrado...");
                return null;
            } else {
                servV.mostrarIdServidores(servidorVet);
            }
            System.out.println("Escolha pelo id um servidor:");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            of.setServidor(servidor);

            List<Curso> cursoVet = cursoDAO.read();

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                cursoV.mostrarIdCurso(cursoVet);
            }
            System.out.println("Escolha pelo id um curso:");
            Curso curso = cursoDAO.find(Integer.parseInt(ler.nextLine()));
            of.setCurso(curso);

            List<Disciplina> disciplinaVet = disciplinaDAO.read();
            if (disciplinaVet.size() == 0) {
                System.out.println("Nenhum disciplina cadastrado...");
                return null;
            } else {
                disciplinaV.mostrarIdTodasDisciplinas(disciplinaVet);
            }
            System.out.println("Escolha pelo id uma disciplina:");
            Disciplina disciplina = disciplinaDAO.find(Integer.parseInt(ler.nextLine()));
            of.setDisciplina(disciplina);

            System.out.println("Ano: ");
            of.setAno(Integer.parseInt(ler.nextLine()));
            System.out.println("Semestre: ");
            of.setSemestre(Integer.parseInt(ler.nextLine()));
            System.out.println("Aulas Semanais: ");
            of.setAulaSemanais(Integer.parseInt(ler.nextLine()));
            of.setDtCriacao(LocalDate.now());
            return of;
        } catch (Exception e) {
            return null;
        }
    }

    public Oferta modifOferta(Oferta ofertaAlt, ServidorDAO servidorDAO, CursoDAO cursoDAO, DisciplinaDAO disciplinaDAO) {
        try {

            List<Servidor> servidorVet = servidorDAO.read();

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidores cadastrado...");
                return null;
            } else {
                servV.mostrarIdServidores(servidorVet);
            }
            System.out.println("Escolha pelo id um servidor:");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            ofertaAlt.setServidor(servidor);

            List<Curso> cursoVet = cursoDAO.read();

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                cursoV.mostrarIdCurso(cursoVet);
            }
            System.out.println("Escolha pelo id um curso:");
            Curso curso = cursoDAO.find(Integer.parseInt(ler.nextLine()));
            ofertaAlt.setCurso(curso);

            List<Disciplina> disciplinaVet = disciplinaDAO.read();
            if (disciplinaVet.size() == 0) {
                System.out.println("Nenhum disciplina cadastrado...");
                return null;
            } else {
                disciplinaV.mostrarIdTodasDisciplinas(disciplinaVet);
            }
            System.out.println("Escolha pelo id uma disciplina:");
            Disciplina disciplina = disciplinaDAO.find(Integer.parseInt(ler.nextLine()));
            ofertaAlt.setDisciplina(disciplina);

            System.out.println("Ano: ");
            ofertaAlt.setAno(Integer.parseInt(ler.nextLine()));
            System.out.println("Semestre: ");
            ofertaAlt.setSemestre(Integer.parseInt(ler.nextLine()));
            System.out.println("Aulas Semanais: ");
            ofertaAlt.setAulaSemanais(Integer.parseInt(ler.nextLine()));
            ofertaAlt.setDtMoficacao(LocalDate.now());
            return ofertaAlt;
        } catch (Exception e) {
            return null;
        }
    }

    public void mostrarTodasOfertas(List<Oferta> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há ofertas cadastradas");
        } else {
            for (Oferta oferta : vetResult) {
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

    public void mostrarIdTodasOfertas(List<Oferta> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há ofertas cadastradas");
        } else {
            for (Oferta oferta : vetResult) {
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
