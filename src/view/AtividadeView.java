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
import model.Atividade;
import model.Disciplina;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class AtividadeView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    ServidorView servV = new ServidorView();
    static int id = 1;

    public Atividade criarAtividade(ServidorDAO servidorDAO) {
        try {
            Atividade at = new Atividade();
            at.setId(id);
            id++;

            List<Servidor> servidorVet = servidorDAO.read();

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                servV.mostrarIdServidores(servidorVet);
            }

            System.out.println("Insira o ID: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            at.setServidor(servidor);

            System.out.println("Descricao: ");
            at.setDescricao(ler.nextLine());
            System.out.println("Horas Semanais:  ");
            at.setHorasSemanais(Double.parseDouble(ler.nextLine()));
            System.out.println("Data de inicio: ");
            at.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de termino: ");
            at.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
            at.setDtCriacao(LocalDate.now());
            return at;
        } catch (Exception e) {
            return null;
        }
    }

    public Atividade modifAtividade(Atividade atAlt, ServidorDAO servidorDAO) {
        try {

            List<Servidor> servidorVet = servidorDAO.read();

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                servV.mostrarIdServidores(servidorVet);
            }

            System.out.println("Insira o ID: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            atAlt.setServidor(servidor);

            System.out.println("Descricao: ");
            atAlt.setDescricao(ler.nextLine());
            System.out.println("Horas Semanais:  ");
            atAlt.setHorasSemanais(Double.parseDouble(ler.nextLine()));
            System.out.println("Data de inicio: ");
            atAlt.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de termino: ");
            atAlt.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
            atAlt.setDtModificacao(LocalDate.now());
            return atAlt;

        } catch (Exception e) {
            return null;
        }
    }

    public Atividade createAula(Servidor servAux, Disciplina discAux) throws Exception {
        Atividade at = new Atividade();
        ServidorDAO servidorDAO = new ServidorDAO();
        Servidor servidor = servidorDAO.find(servAux.getId());
        at.setServidor(servidor);
        at.setDescricao("Preparação da aula " + discAux.getNome());
        at.setHorasSemanais(discAux.getCargaHoraria());
        System.out.println("Data de inicio: ");
        at.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        at.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        at.setDtCriacao(LocalDate.now());
        return at;
    }

    public void mostrarAtividades(List<Atividade> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há atividades cadastradas");
        } else {
            for (Atividade atividade : vetResult) {
                System.out.println("ID: " + atividade.getId());
                System.out.println("DESCRIÇÃO: " + atividade.getDescricao());
                System.out.println("SERVIDOR: " + atividade.getServidor().getNome());
                System.out.println("HORAS SEMANAIS: " + atividade.getHorasSemanais());
                System.out.println("DATA DE INICIO: " + atividade.getDtInicio());
                System.out.println("DATA DE TERMINO: " + atividade.getDtTermino());
                System.out.println("DATA DE CRIAÇÃO: " + atividade.getDtCriacao());
                System.out.println("DATA DE MODIFCAÇÃO: " + atividade.getDtModificacao());
            }
        }
    }

    public void mostrarIdAtividades(List<Atividade> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há atividades cadastradas");
        } else {
            for (Atividade atividade : vetResult) {
                System.out.println("ID: " + atividade.getId());
                System.out.println("DESCRIÇÃO: " + atividade.getDescricao());
                System.out.println("SERVIDOR: " + atividade.getServidor().getNome());
            }
        }
    }

}
