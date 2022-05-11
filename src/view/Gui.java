/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;

/**
 *
 * @author Gui
 */
public class Gui {

    Scanner ler = new Scanner(System.in);

    public int menu() {
        int opc;
        System.out.println("MENU DE OPÇÕES\n");
        System.out.println("1 - REGISTRAR");
        System.out.println("2 - MODIFICAR");
        System.out.println("3 - MOSTRAR");
        System.out.println("4 - EXCLUIR");
        System.out.println("ESCOLHA A OPÇÃO: ");
        opc = Integer.parseInt(ler.nextLine());
        return opc;
    }
    
    public String[] login() {
        String[] loginSenha = new String[2];
        System.out.println("Login:");
        loginSenha[0] = ler.nextLine();
        System.out.println("Senha:");
        loginSenha[1] = ler.nextLine();
        return loginSenha;
    }

    public int escolherCRUD() {
        int opc;
        System.out.println("MENU DE OPÇÕES\n");
        System.out.println("1 - CAMPUS");
        System.out.println("2 - SERVIDOR");
        System.out.println("3 - CURSO");
        System.out.println("4 - DISCIPLINA");
        System.out.println("5 - OFERTA DE DISCIPLINA");
        System.out.println("6 - ORIENTAÇÃO");
        System.out.println("7 - ATIVIDADE");
        System.out.println("8 - COMISSÕES");
        System.out.println("9 - VINCULO DE SERVIDORES A COMISSÃO");
        System.out.println("10 - REUNIÃO");
        System.out.println("11 - REUNIÃO E PRESENTES");
        System.out.println("12 - RELATÓRIOS");
        System.out.println("13 - ENCERRAR COMISSAO");
        System.out.println("14 - SAIR DO MENU");
        System.out.println("ESCOLHA A OPÇÃO: ");
        opc = Integer.parseInt(ler.nextLine());
        return opc;
    }
    
    public int menuComum() {
        int opc;
        System.out.println("MENU DE OPÇÕES\n");
        System.out.println("1 - CAMPUS");
        System.out.println("2 - SERVIDOR");
        System.out.println("3 - CURSO");
        System.out.println("4 - DISCIPLINA");
        System.out.println("5 - OFERTA DE DISCIPLINA");
        System.out.println("6 - ORIENTAÇÃO");
        System.out.println("7 - ATIVIDADE");
        System.out.println("8 - COMISSÕES");
        System.out.println("10 - REUNIÃO");
        System.out.println("11 - REUNIÃO E PRESENTES");
        System.out.println("12 - SAIR");
        opc = Integer.parseInt(ler.nextLine());
        return opc;
    }

    public void sucess() {
        System.out.println("OPERAÇÃO REALIZADA COM SUCERRO");
    }

    public void error() {
        System.out.println("OCORREU ERRO NESTA OPERAÇÃO");
    }

    public void printID() {
        System.out.println("SELECIONE O ID: ");
    }
}
