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
    Scanner ler = new Scanner(System.in);
    int id = 0;

    public Oferta criarOferta(List<String> servidorVet, List<String> cursoVet, List<String> disciplinaVet) {

        try {

            Oferta of = new Oferta();
            of.setId(id);
            id++;
            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidores cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id um servidor:");
            of.setId_servidor(Integer.parseInt(ler.nextLine()));

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                for (String string : cursoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id um curso:");
            of.setId_curso(Integer.parseInt(ler.nextLine()));

            if (disciplinaVet.size() == 0) {
                System.out.println("Nenhum disciplina cadastrado...");
                return null;
            } else {
                for (String string : disciplinaVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id uma disciplina:");
            of.setId_disciplina(Integer.parseInt(ler.nextLine()));

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

    public Oferta modifOferta(Oferta ofertaAlt, List<String> servidorVet, List<String> cursoVet, List<String> disciplinaVet) {
        try {

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidores cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id um servidor:");
            ofertaAlt.setId_servidor(Integer.parseInt(ler.nextLine()));

            if (cursoVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                for (String string : cursoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id um curso:");
            ofertaAlt.setId_curso(Integer.parseInt(ler.nextLine()));

            if (disciplinaVet.size() == 0) {
                System.out.println("Nenhum curso cadastrado...");
                return null;
            } else {
                for (String string : disciplinaVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id um disciplina:");
            ofertaAlt.setId_disciplina(Integer.parseInt(ler.nextLine()));

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

    public void mostrarTodasOfertas(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há ofertas cadastradas");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }

}
