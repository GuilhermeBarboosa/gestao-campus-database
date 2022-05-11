/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public Comissao criarComissao() {
        Comissao com = new Comissao();
        System.out.println("Comissao: ");
        com.setNameComissao(ler.nextLine());
        System.out.println("Horas Semanais:  ");
        com.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        com.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        com.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Estado: ");
        com.setEstado(ler.nextLine());
        com.setDtCriacao(LocalDate.now());
        return com;
    }

    public Comissao modifComissao(Comissao comAlt) {
        System.out.println("Comissao: ");
        comAlt.setNameComissao(ler.nextLine());
        System.out.println("Horas Semanais:  ");
        comAlt.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        comAlt.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        comAlt.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Estado: ");
        comAlt.setEstado(ler.nextLine());
        comAlt.setDtModificacao(LocalDate.now());
        return comAlt;
    }


    public void mostrarTodosComissao(Comissao[] comissoes) {
        boolean aux = false;
        for (int i = 0; i < comissoes.length; i++) {
            if (comissoes[i] != null) {
                aux = true;
            }
        }

        if (aux) {
            for (int i = 0; i < comissoes.length; i++) {
                if (comissoes[i] != null) {
                    System.out.println(comissoes[i].toString());
                }

            }
        } else {
            System.out.println("Não ha comissoes cadastrados.");
        }
    }
    
    
        ///////////////////////////////////////////////
    //SO PARA TESTES
    public Comissao setAleatorio1() {
        Comissao com = new Comissao();
        com.setNameComissao("Comissao A");
        com.setHorasSemanais(3);
        com.setDtInicio(LocalDate.parse("17/03/2022", formatter));
        com.setDtTermino(LocalDate.parse("28/03/2022", formatter));
        com.setEstado("Ativo");
        com.setDtCriacao(LocalDate.now());
        return com;
    }

    public Comissao setAleatorio2() {
        Comissao com = new Comissao();
        com.setNameComissao("Comissao B");
        com.setHorasSemanais(5);
        com.setDtInicio(LocalDate.parse("16/04/2022", formatter));
        com.setDtTermino(LocalDate.parse("25/05/2022", formatter));
        com.setEstado("Inativo");
        com.setDtCriacao(LocalDate.now());
        return com;
    }

    public Comissao encerrarComissao(Comissao comAux) {
        comAux.setEstado("ENCERRADO");
        return comAux;
    }
    
    public Comissao setAleatorio3() {
         Comissao com = new Comissao();
        com.setNameComissao("Comissao C");
        com.setHorasSemanais(8);
        com.setDtInicio(LocalDate.parse("17/05/2022", formatter));
        com.setDtTermino(LocalDate.parse("03/07/2022", formatter));
        com.setEstado("Ativo");
        com.setDtCriacao(LocalDate.now());
        return com;
    }
    /////////////////////////////////////////////////////

    

}
