/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Comissao;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class ComissaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    int id = 0;

    public Comissao criarComissao() {
        try {

            Comissao com = new Comissao();
            com.setId(id);
            id++;
            System.out.println("Comissao: ");
            com.setNameComissao(ler.nextLine());
            System.out.println("Horas Semanais:  ");
            com.setHorasSemanais(Double.parseDouble(ler.nextLine()));
            System.out.println("Data de inicio: ");
            com.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de termino: ");
            com.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Estado: ");
            int aux = Integer.parseInt(ler.nextLine());
            if (aux == 1) {
                com.setEstado("Ativo");
            } else {
                com.setEstado("Inativo");
            }
            com.setDtCriacao(LocalDate.now());
            return com;
        } catch (Exception e) {
            return null;
        }
    }

    public Comissao modifComissao(Comissao comAlt) {
        try {
            System.out.println("Comissao: ");
            comAlt.setNameComissao(ler.nextLine());
            System.out.println("Horas Semanais:  ");
            comAlt.setHorasSemanais(Double.parseDouble(ler.nextLine()));
            System.out.println("Data de inicio: ");
            comAlt.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de termino: ");
            comAlt.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Estado: ");
            int aux = Integer.parseInt(ler.nextLine());
            if (aux == 1) {
                comAlt.setEstado("Ativo");
            } else {
                comAlt.setEstado("Inativo");
            }
            comAlt.setDtModificacao(LocalDate.now());
            return comAlt;
        } catch (Exception e) {
            return null;
        }

    }

    public void mostrarTodosComissao(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }

//    public Comissao encerrarComissao(Comissao comAux) {
//        comAux.setEstado("ENCERRADO");
//        return comAux;
//    }
}
