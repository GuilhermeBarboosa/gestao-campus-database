/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CampusDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Campus;
import model.Servidor;

/**
 *
 * @author Usuario
 */
public class ServidorView {

    Scanner ler = new Scanner(System.in);
    static int id = 1;

    public Servidor criarServ(CampusDAO campusDAO) {
        try {
            Servidor serv = new Servidor();
            CampusView campusView = new CampusView();

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

            List<Campus> campusVet = new ArrayList();

            if (campusVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                campusView.mostrarIdTodosCampos(campusVet);
            }

            System.out.println("Insira o ID: ");

            Campus campus = campusDAO.find(Integer.parseInt(ler.nextLine()));

            serv.setCampus(campus);

            serv.setDtCriacao(LocalDate.now());
            return serv;
        } catch (Exception e) {
            return null;
        }

    }

    public Servidor modifServ(Servidor servAlt, CampusDAO campusDAO) {
        try {
            CampusView campusView = new CampusView();

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

            List<Campus> campusVet = new ArrayList();

            if (campusVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                campusView.mostrarIdTodosCampos(campusVet);
            }

            System.out.println("Insira o ID: ");

            Campus campus = campusDAO.find(Integer.parseInt(ler.nextLine()));

            servAlt.setCampus(campus);

            servAlt.setDtModificacao(LocalDate.now());
            return servAlt;
        } catch (Exception e) {
            return null;
        }

    }

    public void mostrarServidores(List<Servidor> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (Servidor servidor : vetResult) {
                System.out.println("-----------------------------------------");
                System.out.println("ID: " + servidor.getId());
                System.out.println("CAMPUS: " + servidor.getCampus().getNome());
                System.out.println("NOME: " + servidor.getNome());
                System.out.println("EMAIL: " + servidor.getEmail());
                System.out.println("CARGO: " + servidor.getCargo());
                System.out.println("LOGIN: " + servidor.getLogin());
                System.out.println("SENHA: " + servidor.getSenha());
                System.out.println("PERFIL: " + servidor.getPerfil());
                System.out.println("HORAS TOTAIS: " + servidor.getHorasTotais());
                System.out.println("CRIAÇÃO: " + servidor.getDtCriacao());
                System.out.println("MODIFICAÇÃO: " + servidor.getDtModificacao());
                System.out.println("-----------------------------------------");
            }
        }
    }

    public void mostrarIdServidores(List<Servidor> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (Servidor servidor : vetResult) {
                System.out.println("ID: " + servidor.getId());
                System.out.println("NOME: " + servidor.getNome());
            }
        }
    }

}
