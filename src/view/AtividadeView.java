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
import model.Atividade;
import model.Disciplina;
import model.Servidor;
import service.ServidorService;

/**
 *
 * @author Gui
 */
public class AtividadeView {

    private final ServidorService servidorService = new ServidorService();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    ServidorView servidorView = new ServidorView();
//    static int id = 1;

    public Atividade create() {
        try {
            Atividade atividade = new Atividade();

            List<Servidor> listServidor = servidorService.read();

            return inputInfoAtividade(atividade, listServidor);
        } catch (Exception e) {
            return null;
        }
    }

    public Atividade update(Atividade atividade) {
        try {
            List<Servidor> listServidor = servidorService.read();

            return inputInfoAtividade(atividade, listServidor);
        } catch (Exception e) {
            return null;
        }
    }

    private Atividade inputInfoAtividade(Atividade atividade, List<Servidor> listServidor) throws Exception {
        if (listServidor.size() == 0) {
            System.out.println("Nenhum servidor cadastrado...");
            return null;
        } else {
            servidorView.printId(listServidor);
        }

        System.out.println("Insira o ID: ");
        Servidor servidor = servidorService.getById(Integer.parseInt(ler.nextLine()));
        atividade.setServidor(servidor);

        System.out.println("Descricao: ");
        atividade.setDescricao(ler.nextLine());
        System.out.println("Horas Semanais:  ");
        atividade.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        atividade.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        atividade.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        atividade.setDtModificacao(LocalDate.now());
        return atividade;
    }

    public Atividade createAula(Servidor idServidor, Disciplina disciplina) throws Exception {
        Atividade atividade = new Atividade();
        ServidorService servidorService = new ServidorService();
        Servidor servidor = servidorService.getById(idServidor.getId());
        atividade.setServidor(servidor);
        atividade.setDescricao("Preparação da aula " + disciplina.getNome());
        atividade.setHorasSemanais(disciplina.getCargaHoraria());
        System.out.println("Data de inicio: ");
        atividade.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        atividade.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        atividade.setDtCriacao(LocalDate.now());
        return atividade;
    }

    public void printAll(List<Atividade> listAtividade) {
        if (listAtividade.size() == 0) {
            System.out.println("Não há atividades cadastradas");
        } else {
            for (Atividade atividade : listAtividade) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + atividade.getId());
                System.out.println("DESCRIÇÃO: " + atividade.getDescricao());
                System.out.println("SERVIDOR: " + atividade.getServidor().getNome());
                System.out.println("HORAS SEMANAIS: " + atividade.getHorasSemanais());
                System.out.println("DATA DE INICIO: " + atividade.getDtInicio());
                System.out.println("DATA DE TERMINO: " + atividade.getDtTermino());
                System.out.println("DATA DE CRIAÇÃO: " + atividade.getDtCriacao());
                if (atividade.getDtModificacao() != null) {
                    System.out.println("DATA DE MODIFCAÇÃO: " + atividade.getDtModificacao());
                }
                System.out.println("--------------------------------------");
            }
        }
    }

    public void printId(List<Atividade> listAtividade) {
        if (listAtividade.size() == 0) {
            System.out.println("Não há atividades cadastradas");
        } else {
            for (Atividade atividade : listAtividade) {
                System.out.println("--------------------------------------");
                System.out.println("ID: " + atividade.getId());
                System.out.println("DESCRIÇÃO: " + atividade.getDescricao());
                System.out.println("SERVIDOR: " + atividade.getServidor().getNome());
                System.out.println("--------------------------------------");
            }
        }
    }

}
