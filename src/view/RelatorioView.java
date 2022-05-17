/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.AtividadeDAO;
import dao.CampusDAO;
import dao.ComissaoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.OfertaDAO;
import dao.OrientacaoDAO;
import dao.ReuniaoDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import model.Atividade;
import model.Campus;
import model.Comissao;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Orientacao;
import model.Reuniao;
import model.Servidor;
import model.Vinculo;

/**
 *
 * @author Aluno
 */
public class RelatorioView {
    
    Scanner ler = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public int opMenuRelat() {
        System.out.println("Escolha um tipo de relatório: ");
        System.out.println("1- Relatório das atas por período");
        System.out.println("2- Relatório de servidor cadastrado");
        System.out.println("3- Relatório de aulas do campus");
        return Integer.parseInt(ler.nextLine());
    }
    
    public LocalDate[] opMenuRelat1() {
        LocalDate[] filtro = new LocalDate[2];
        System.out.println("Escolha data de inicio: ");
        filtro[0] = LocalDate.parse(ler.nextLine(), formatter);
        System.out.println("Escolha data do fim: ");
        filtro[1] = LocalDate.parse(ler.nextLine(), formatter);
        return filtro;
    }

   public void relat1(LocalDate[] filtro, ReuniaoDAO reuniaoDAO,
            ComissaoDAO comissaoDAO, ServidorDAO servidorDAO) {
        Reuniao[] reuniao = reuniaoDAO.getAll();
        String relat = "";
        
        StringBuilder mensagem = new StringBuilder();
        System.out.println(Arrays.toString(filtro));

        for (Reuniao reun : reuniao) {
            if (reun != null) {
                if (reun.getDtReuniao().isBefore(filtro[1]) && reun.getDtReuniao().isAfter(filtro[0])) {
                    Comissao comissao = comissaoDAO.getById(reun.getComissao().getId());
                    Servidor servidor = servidorDAO.getById(reun.getServidorSecre().getId());

                     relat += "\tID: \t" + comissao.getId() + "\t Comissao: \t" + comissao.getNameComissao() + "\n"
                            + "\tID: \t" + servidor.getId() + "\t Servidor secretario: \t" + servidor.getNome() + "\n"
                            + "\tAta: \t" + reun.getConteudoAta() + "\n"
                            + "\tData de reuniao: \t" + reun.getDtReuniao().format(formatter) + "\n\n\n";
                     
                }
            }
        }

        mensagem.append(relat);
        JOptionPane.showMessageDialog(null, mensagem);
    }

   public void relat2(ServidorDAO servidorDAO, OfertaDAO ofertaDAO, AtividadeDAO atividadeDAO, VinculoDAO vinculoDAO, OrientacaoDAO orientacaoDAO, DisciplinaDAO disciplinaDAO, CursoDAO cursoDAO, ComissaoDAO comissaoDAO) {
        String relat = "";
        String servString = "";
        StringBuilder mensagem = new StringBuilder();
        Servidor[] servAll = servidorDAO.getAll();
        int somaHorasTotais = 0;
        for (Servidor servidor : servAll) {
            if (servidor != null) {
                servString += "ID: " + servidor.getId() + " NOME: " + servidor.getNome() + "\n\n";
            }
        }
        System.out.println(servString);
        System.out.println("Insira o id do servidor: ");
        int auxServ = Integer.parseInt(ler.nextLine());

        for (Servidor serv : servAll) {
            if (serv != null && serv.getId() == auxServ) {
                Oferta[] ofertas = ofertaDAO.findOfertaServidor(serv.getId());
                Atividade[] atividades = atividadeDAO.findAtividade(auxServ);
                Vinculo[] vinculos = vinculoDAO.findVinculo(auxServ);
                Orientacao[] orientacoes = orientacaoDAO.findOrientacao(auxServ);

                relat += "ID: " + serv.getId() + " NOME: " + serv.getNome() + " CARGO: " + serv.getCargo() + "\n";

                for (Oferta oferta : ofertas) {
                    if (oferta != null) {
                        Disciplina disciplina = disciplinaDAO.findDisciplina(oferta.getDisciplina().getId());
                        Curso curso = cursoDAO.findCurso(oferta.getCurso().getId());
                        relat += "DISCIPLINA: " + disciplina.getNome() + " HRS: " + disciplina.getCargaHoraria() + "\n"
                                + "tCURSO: " + curso.getNome() + "\n";
                        somaHorasTotais += disciplina.getCargaHoraria();
                    }
                }

                relat += "\n\n";
                for (Atividade atividade : atividades) {
                    if (atividade != null) {
                        relat += "ATIVIDADE: " + atividade.getDescricao() + " HRS: " + atividade.getHorasSemanais() + "\n";
                        somaHorasTotais += atividade.getHorasSemanais();
                    }
                }

                relat += "\n\n";
                for (Vinculo vinculo : vinculos) {
                    if (vinculo != null) {
                        Comissao comissao = comissaoDAO.findComissao(vinculo.getComissao().getId());
                        relat += "COMISSAO: " + comissao.getNameComissao() + " HRS: " + comissao.getHorasSemanais() + "\n";
                        somaHorasTotais += comissao.getHorasSemanais();
                    }
                }
                relat += "\n\n";

                for (Orientacao orientacao : orientacoes) {
                    if (orientacao != null) {
                        relat += "ORIENTACAO: " + orientacao.getTipo() + " HRS: " + orientacao.getHorasSemanais() + "\n";
                        somaHorasTotais += orientacao.getHorasSemanais();
                    }
                }
                relat += "\n\n";

            }

        }

        mensagem.append(relat);
        mensagem.append("CARGA HORARIA: " + somaHorasTotais + " HORAS");
        JOptionPane.showMessageDialog(null, mensagem);
    }


   public void relat3(CampusDAO campusDAO, OfertaDAO ofertaDAO, CursoDAO cursoDAO, DisciplinaDAO disciplinaDAO) {
        String ofertaAulas = "";
        String campusString = "";
        StringBuilder mensagem = new StringBuilder();

        Campus[] vetCampus = campusDAO.read();

        for (Campus vet : vetCampus) {
            if (vet != null) {
                campusString += "ID: " + vet.getId() + " NOME: " + vet.getNome() + "\n";
            }
        }
        System.out.println(campusString);
        System.out.println("Insira o campus: ");
        int auxCampus = Integer.parseInt(ler.nextLine());

        Oferta[] ofertaVet = ofertaDAO.getAll();
        Curso[] cursoVet = cursoDAO.findCursoCampus(auxCampus);
        Disciplina[] discVet = disciplinaDAO.getAll();
        for (Oferta oferta : ofertaVet) {
            if (oferta != null) {
                for (Curso curso : cursoVet) {
                    if (curso != null) {
                        for (Disciplina disc : discVet) {
                            if (disc != null && disc.getCurso().getId() == curso.getId() && curso.getCampus().getId() == auxCampus) {
                                ofertaAulas += "DISCIPLINA: " + disc.getNome()
                                        + " CURSO: " + curso.getNome()
                                        + " CAMPUS: " + vetCampus[auxCampus].getNome() + "\n\n";
                            }
                        }
                    }
                }
            }
        }
        mensagem.append(ofertaAulas);
        JOptionPane.showMessageDialog(null, mensagem);
    }

}
