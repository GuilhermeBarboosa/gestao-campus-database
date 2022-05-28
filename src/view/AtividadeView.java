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

/**
 *
 * @author Gui
 */
public class AtividadeView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);
    static int id = 1;

    public Atividade criarAtividade(List<String> servidorVet) {
        try {
            Atividade at = new Atividade();
            at.setId(id);
            id++;

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }

            System.out.println("Insira o ID: ");
            at.setId_servidor(Integer.parseInt(ler.nextLine()));

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

    public Atividade modifAtividade(Atividade atAlt, List<String> servidorVet) {
        try {

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }

            System.out.println("Insira o ID: ");
            atAlt.setId_servidor(Integer.parseInt(ler.nextLine()));

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

    public void mostrarAtividades(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há atividades cadastradas");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }

    public Atividade createAula(Servidor servAux, Disciplina discAux) {
        Atividade at = new Atividade();
        at.setId_servidor(servAux.getId());
        at.setDescricao("Preparação da aula " + discAux.getNome());
        at.setHorasSemanais(discAux.getCargaHoraria());
        System.out.println("Data de inicio: ");
        at.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        at.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        at.setDtCriacao(LocalDate.now());
        return at;
    }


}
