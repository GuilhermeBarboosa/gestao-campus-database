/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.Servidor;

/**
 *
 * @author Usuario
 */
public class ServidorView {

    Scanner ler = new Scanner(System.in);
    static int id = 1;

    public Servidor criarServ(List<String> campusVet) {
        try {
            Servidor serv = new Servidor();
            serv.setId(id);
            id++;
            System.out.println("Nome: ");
            serv.setNome(ler.nextLine());
            System.out.println("Email: ");
            serv.setEmail(ler.nextLine());
            System.out.println("Cargo: (1-TAE | 2-PBTT)");
            int aux = Integer.parseInt(ler.nextLine());
            if (aux == 1) {
                serv.setCargo("tae");
            } else {
                serv.setCargo("pbtt");
            }
            System.out.println("Login: ");
            serv.setLogin(ler.nextLine());
            System.out.println("Senha: ");
            serv.setSenha(ler.nextLine());
            System.out.println("Perfil(1-ADM | 2-COMUM) : ");
            serv.setPerfil(Integer.parseInt(ler.nextLine()));

            if (campusVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                for (String string : campusVet) {
                    System.out.println(string);
                }
            }

            System.out.println("Insira o ID: ");
            serv.setId_campus(Integer.parseInt(ler.nextLine()));

            serv.setDtCriacao(LocalDate.now());
            return serv;
        } catch (Exception e) {
            return null;
        }

    }

    public Servidor modifServ(Servidor servAlt, List<String> campusVet) {
        try {
            System.out.println("Nome: ");
            servAlt.setNome(ler.nextLine());
            System.out.println("Email: ");
            servAlt.setEmail(ler.nextLine());
            System.out.println("Cargo: (1-TAE | 2-PBTT)");
            int aux = Integer.parseInt(ler.nextLine());
            if (aux == 1) {
                servAlt.setCargo("tae");
            } else {
                servAlt.setCargo("pbtt");
            }
            System.out.println("Login: ");
            servAlt.setLogin(ler.nextLine());
            System.out.println("Senha: ");
            servAlt.setSenha(ler.nextLine());
            System.out.println("Perfil(1-ADM | 2-COMUM : ");
            servAlt.setPerfil(Integer.parseInt(ler.nextLine()));

            if (campusVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                for (String string : campusVet) {
                    System.out.println(string);
                }
            }

            System.out.println("Insira o ID: ");
            servAlt.setId_campus(Integer.parseInt(ler.nextLine()));

            servAlt.setDtModificacao(LocalDate.now());
            return servAlt;
        } catch (Exception e) {
            return null;
        }

    }

    public void mostrarServidores(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }

}
