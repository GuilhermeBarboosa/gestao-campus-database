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
import model.Campus;

/**
 *
 * @author Aluno
 */
public class CampusView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    static int id = 1;

    public Campus criarCampus() {
        Campus campus = new Campus();
        campus.setId(id);
        id++;

        System.out.println("Nome: ");
        campus.setNome(ler.nextLine());
        System.out.println("Abreviacao:  ");
        campus.setAbreviacao(ler.nextLine());
        System.out.println("Duração de aula: ");
        campus.setDuracaoAula(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de criação do Campus: ");
        campus.setDtCriacaoCamp(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Cidade: ");
        campus.setCidade(ler.nextLine());
        System.out.println("Bairro: ");
        campus.setBairro(ler.nextLine());
        System.out.println("Rua: ");
        campus.setRua(ler.nextLine());
        System.out.println("Cep: ");
        campus.setCep(ler.nextLine());
        campus.setDtCriacao(LocalDate.now());
        return campus;
    }

    public Campus modifCampus(Campus campusAlt) {
        System.out.println("Nome: ");
        campusAlt.setNome(ler.nextLine());
        System.out.println("Abreviacao:  ");
        campusAlt.setAbreviacao(ler.nextLine());
        System.out.println("Duração de aula: ");
        campusAlt.setDuracaoAula(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de criação do Campus: ");
        campusAlt.setDtCriacaoCamp(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Cidade: ");
        campusAlt.setCidade(ler.nextLine());
        System.out.println("Bairro: ");
        campusAlt.setBairro(ler.nextLine());
        System.out.println("Rua: ");
        campusAlt.setRua(ler.nextLine());
        System.out.println("Cep: ");
        campusAlt.setCep(ler.nextLine());
        campusAlt.setDtModificacao(LocalDate.now());
        return campusAlt;
    }

    /////////////////////////////////////////////////////
    public void mostrarTodosCampos(List<String> vetCampus) {
       for (int i = 0; i < vetCampus.size(); i++) {
              System.out.println(vetCampus.get(i));
        }
    }

    public void mostrarIdCampus(List<String> vetCampus) {
        for (int i = 0; i < vetCampus.size(); i++) {
              System.out.println(vetCampus.get(i));
        }
    }

}