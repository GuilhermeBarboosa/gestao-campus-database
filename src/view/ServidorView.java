/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Campus;
import model.Servidor;
import service.CampusService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * @author Usuario
 */
public class ServidorView {

    Scanner ler = new Scanner(System.in);
    CampusView campusView = new CampusView();
    CampusService campusService = new CampusService();

    public Servidor created() {
        try {
            return inputInfoServidor(new Servidor());
        } catch (Exception e) {
            return null;
        }

    }

    public Servidor update(Servidor servidor) {
        try {
            return inputInfoServidor(servidor);
        } catch (Exception e) {
            return null;
        }

    }

    private Servidor inputInfoServidor(Servidor servidor) throws Exception {
        System.out.println("Nome: ");
        servidor.setNome(ler.nextLine());
        System.out.println("Email: ");
        servidor.setEmail(ler.nextLine());
        System.out.println("Cargo: (1-TAE | 2-PBTT)");
        int aux = Integer.parseInt(ler.nextLine());
        if (aux == 1) {
            servidor.setCargo("tae");
        } else {
            servidor.setCargo("pbtt");
        }
        System.out.println("Login: ");
        servidor.setLogin(ler.nextLine());
        System.out.println("Senha: ");
        servidor.setSenha(ler.nextLine());
        System.out.println("Perfil(1-ADM | 2-COMUM) : ");
        servidor.setPerfil(Integer.parseInt(ler.nextLine()));

        List<Campus> campusVet = campusService.read();

        if (campusVet.size() == 0) {
            System.out.println("Nenhum campus cadastrado...");
            return null;
        } else {
            campusView.printId(campusVet);
        }

        System.out.println("Insira o ID: ");
        Campus campus = campusService.getById(Integer.parseInt(ler.nextLine()));

        servidor.setCampus(campus);

        servidor.setDtCriacao(LocalDate.now());
        return servidor;
    }

    public void printAll(List<Servidor> listServidor) {
        if (listServidor.isEmpty()) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (Servidor servidor : listServidor) {
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

                if (servidor.getDtModificacao() != null) {
                    System.out.println("MODIFICAÇÃO: " + servidor.getDtModificacao());
                }

                System.out.println("-----------------------------------------");
            }
        }
    }

    public void printId(List<Servidor> listServidor) {
        if (listServidor.size() == 0) {
            System.out.println("Não há servidores cadastrados");
        } else {
            for (Servidor servidor : listServidor) {
                System.out.println("ID: " + servidor.getId());
                System.out.println("NOME: " + servidor.getNome());
            }
        }
    }

}
