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

    public Campus create() {
        try {
            return inputInfoCampus(new Campus());
        } catch (Exception e) {
            return null;
        }
    }

    public Campus update(Campus campus) {
        try {
            return inputInfoCampus(campus);
        } catch (Exception e) {
            return null;
        }

    }

    private Campus inputInfoCampus(Campus campus) {
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
    /////////////////////////////////////////////////////
    public void printAll(List<Campus> listCampus) {
        if (listCampus.size() == 0) {
            System.out.println("Não há campus cadastrados");
        } else {
            for (Campus campus : listCampus) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + campus.getId());
                System.out.println("NOME: " + campus.getNome());
                System.out.println("ABREVIAÇÃO: " + campus.getAbreviacao());
                System.out.println("DURAÇÃO DE AULA: " + campus.getDuracaoAula());
                System.out.println("DATA DE CRIAÇÃO DO CAMPUS: " + campus.getDtCriacaoCamp());
                System.out.println("CIDADE: " + campus.getCidade());
                System.out.println("BAIRRO: " + campus.getBairro());
                System.out.println("RUA: " + campus.getRua());
                System.out.println("CEP: " + campus.getCep());
                System.out.println("CRIAÇÃO: " + campus.getDtCriacao());
                if (campus.getDtModificacao() != null) {
                    System.out.println("MODIFICAÇÃO: " + campus.getDtModificacao());
                }
                System.out.println("--------------------------------------");
            }
        }
    }

    public void printId(List<Campus> listCampus) {
        if (listCampus.size() == 0) {
            System.out.println("Não há campus cadastrados");
        } else {
            for (Campus campus : listCampus) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + campus.getId());
                System.out.println("NOME: " + campus.getNome());
                System.out.println("ABREVIAÇÃO: " + campus.getAbreviacao());
                System.out.println("--------------------------------------");
            }
        }
    }

}
