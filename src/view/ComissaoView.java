/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Comissao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gui
 */
public class ComissaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    public Comissao create() {
        try {
            return inputInfoComissao(new Comissao());
        } catch (Exception e) {
            return null;
        }
    }

    private Comissao inputInfoComissao(Comissao comissao) {
        System.out.println("Comissao: ");
        comissao.setNameComissao(ler.nextLine());
        System.out.println("Horas Semanais:  ");
        comissao.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        comissao.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        comissao.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Estado: 1-Ativo 2-Inativo: ");
        int aux = Integer.parseInt(ler.nextLine());
        if (aux == 1) {
            comissao.setEstado("Ativo");
        } else {
            comissao.setEstado("Inativo");
        }
        comissao.setDtCriacao(LocalDate.now());
        return comissao;
    }

    public Comissao update(Comissao comissao) {
        try {
            return inputInfoComissao(comissao);
        } catch (Exception e) {
            return null;
        }

    }

    public void printAll(List<Comissao> listComissao) {
        if (listComissao.size() == 0) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (Comissao comissao : listComissao) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + comissao.getId());
                System.out.println("COMISSAO: " + comissao.getNameComissao());
                System.out.println("HORAS SEMANAIS: " + comissao.getHorasSemanais());
                System.out.println("DATA DE INICIO: " + comissao.getDtInicio());
                System.out.println("DATA DE TERMINO: " + comissao.getDtTermino());
                System.out.println("ESTADO: " + comissao.getEstado());
                System.out.println("DATA DE CRIAÇÃO: " + comissao.getDtCriacao());
                if (comissao.getDtModificacao() != null) {
                    System.out.println("DATA DE MODIFICAÇÃO: " + comissao.getDtModificacao());
                }
                System.out.println("--------------------------------------");
            }
        }
    }

    public void printId(List<Comissao> listComissao) {
        if (listComissao.size() == 0) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (Comissao comissao : listComissao) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + comissao.getId());
                System.out.println("COMISSAO: " + comissao.getNameComissao());
                System.out.println("--------------------------------------");
            }
        }
    }

}
