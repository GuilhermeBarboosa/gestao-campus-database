/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Default;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Atividade;
import model.Campus;
import model.Comissao;
import model.Reuniao;
import model.Oferta;
import model.Orientacao;
import model.Servidor;
import model.Vinculo;

/**
 *
 * @author Aluno
 */
public class RelatorioView implements Default {

    Scanner ler = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public int opMenuRelat() {
        System.out.println("Escolha um tipo de relatório: ");
        System.out.println("1- Relatório das atas por período");
        System.out.println("2- Relatório de servidores");
        System.out.println("3- Relatório de aulas do campus");
        return Integer.parseInt(ler.nextLine());
    }

    public LocalDate[] opMenuRelat1() {
        LocalDate[] filtro = new LocalDate[2];
        System.out.println("Escolha data de inicio: ");
        filtro[0] = LocalDate.parse("02/06/2022", formatter);
        System.out.println("Escolha data do fim: ");
        filtro[1] = LocalDate.parse("10/06/2022", formatter);
        return filtro;
    }

    public void relat1(LocalDate[] filtro) throws Exception {

        List<Comissao> comissaoVet = comissaoDAO.read();
        comissaoView.mostrarIdTodosComissao(comissaoVet);

        System.out.println("Qual comissao deseja: ");
        int aux = Integer.parseInt(ler.nextLine());

        List<Reuniao> reunioes = relatorioDAO.relatorioData(filtro[0], filtro[1]);

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo1-" + LocalDate.now() + ".pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));

            doc.open();
            Font f = new Font(FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.BLUE);
            Paragraph p = new Paragraph("RELATORIO DE REUNIÃO POR DATA\n\n", f);
            p.setAlignment(1);
            doc.add(p);

            p = new Paragraph("");
            doc.add(p);

            PdfPTable table = new PdfPTable(4);

            PdfPCell cel1 = new PdfPCell(new Paragraph("COMISSAO"));
            cel1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

            PdfPCell cel2 = new PdfPCell(new Paragraph("CONTEUDO"));
            cel2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

            PdfPCell cel3 = new PdfPCell(new Paragraph("SECRETARIO"));
            cel3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

            PdfPCell cel4 = new PdfPCell(new Paragraph("DATA"));
            cel4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            table.addCell(cel4);
            if (reunioes.isEmpty()) {
                System.out.println("Sem reunioes cadastradas...");
            } else {
                for (Reuniao reuniao : reunioes) {

                    if (reuniao.getComissao().getId() == aux) {
                        Date date = Date.valueOf(reuniao.getDtReuniao());
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String data = format.format(date);

                        cel1 = new PdfPCell(new Paragraph(reuniao.getComissao().getNameComissao()));
                        cel1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                        cel1.setPadding(10);

                        cel2 = new PdfPCell(new Paragraph(reuniao.getConteudoAta()));
                        cel2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                        cel2.setPadding(10);

                        cel3 = new PdfPCell(new Paragraph(reuniao.getServidorSecre().getNome()));
                        cel3.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                        cel3.setPadding(10);

                        cel4 = new PdfPCell(new Paragraph(data));
                        cel4.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                        cel4.setPadding(10);

                        table.addCell(cel1);
                        table.addCell(cel2);
                        table.addCell(cel3);
                        table.addCell(cel4);
                    }

                }
                doc.add(table);
                doc.close();

                Desktop.getDesktop().open(new File(arquivoPdf));
            }
        } catch (Exception e) {

        }

    }

    public void relat2() throws SQLException, Exception {

        List<Servidor> servidorVet = servidorDAO.read();
        List<Vinculo> vinculoVet = vinculoDAO.read();
        List<Atividade> atividadeVet = atividadeDAO.read();
        List<Orientacao> orientacaoVet = orientacaoDAO.read();
        List<Oferta> ofertaVet = ofertaDAO.read();

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo2-" + LocalDate.now() + ".pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));

            doc.open();
            Font f = new Font(FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.RED);
            Paragraph p = new Paragraph("RELATORIO DO TIPO 1\n\n", f);
            p.setAlignment(1);
            doc.add(p);

            p = new Paragraph("");

            doc.add(p);

            PdfPTable table = new PdfPTable(6);

            PdfPCell cel1 = new PdfPCell(new Paragraph("SERVIDOR"));
            cel1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel1.setPadding(5);
            PdfPCell cel2 = new PdfPCell(new Paragraph("ATIVIDADE"));
            cel2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel2.setPadding(5);
            PdfPCell cel3 = new PdfPCell(new Paragraph("COMISSAO"));
            cel3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel3.setPadding(5);
            PdfPCell cel4 = new PdfPCell(new Paragraph("ORIENTACAO"));
            cel4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel4.setPadding(5);
            PdfPCell cel5 = new PdfPCell(new Paragraph("AULA"));
            cel5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel5.setPadding(5);

            PdfPCell cel6 = new PdfPCell(new Paragraph("HORAS TOTAIS"));
            cel6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel6.setPadding(5);

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            table.addCell(cel4);
            table.addCell(cel5);
            table.addCell(cel6);
            if (servidorVet.isEmpty()) {
                System.out.println("Sem servidores cadastradas...");
            } else {
                for (Servidor servidor : servidorVet) {
                    String servidorString = "";
                    servidorString += servidor.getNome();

                    String atividadeString = "";
                    String vinculoString = "";
                    String orientacaoString = "";
                    String ofertaString = "";
                    double horasTotais = 0;
                    for (Atividade atividade : atividadeVet) {
                        if (atividade.getServidor().getId() == servidor.getId()) {
                            atividadeString += " - " + atividade.getDescricao() + " "
                                    + atividade.getHorasSemanais() + "h \n\n";
                            horasTotais += atividade.getHorasSemanais();
                        }
                    }
                    for (Vinculo vinculo : vinculoVet) {
                        if (vinculo.getServidor().getId() == servidor.getId()) {
                            vinculoString += " - " + vinculo.getComissao().getNameComissao() + " "
                                    + vinculo.getComissao().getHorasSemanais() + "h \n\n";
                            horasTotais += vinculo.getComissao().getHorasSemanais();
                        }
                    }
                    for (Orientacao orientacao : orientacaoVet) {
                        if (orientacao.getServidor().getId() == servidor.getId()) {
                            orientacaoString += " - " + orientacao.getTipo() + " "
                                    + orientacao.getHorasSemanais() + "h \n\n";
                            horasTotais += orientacao.getHorasSemanais();
                        }
                    }
                    for (Oferta oferta : ofertaVet) {
                        if (oferta.getServidor().getId() == servidor.getId()) {
                            ofertaString += " - " + oferta.getDisciplina().getNome() + " "
                                    + oferta.getDisciplina().getCargaHoraria() + "h \n\n";
                            horasTotais += oferta.getDisciplina().getCargaHoraria();
                        }
                    }

                    cel1 = new PdfPCell(new Paragraph(servidorString));
                    table.addCell(cel1);

                    if (atividadeString.equals("")) {
                        atividadeString = "N/A";
                    }
                    cel2 = new PdfPCell(new Paragraph(atividadeString));
                    table.addCell(cel2);

                    if (vinculoString.equals("")) {
                        vinculoString = "N/A";
                    }
                    cel3 = new PdfPCell(new Paragraph(vinculoString));
                    table.addCell(cel3);

                    if (orientacaoString.equals("")) {
                        orientacaoString = "N/A";
                    }
                    cel4 = new PdfPCell(new Paragraph(orientacaoString));
                    table.addCell(cel4);

                    if (ofertaString.equals("")) {
                        ofertaString = "N/A";
                    }
                    cel5 = new PdfPCell(new Paragraph(ofertaString));
                    table.addCell(cel5);

                    String horas = Double.toString(horasTotais);
                    cel6 = new PdfPCell(new Paragraph(horas));
                    table.addCell(cel6);
                }
                doc.add(table);
                doc.close();

                Desktop.getDesktop().open(new File(arquivoPdf));
            }
        } catch (Exception e) {

        }

    }

    public void relat3() throws Exception {

        List<Servidor> servidorVet = servidorDAO.read();

        List<Campus> campusVet = campusDAO.read();
        CampusView campusV = new CampusView();

        campusV.mostrarIdTodosCampos(campusVet);

        System.out.println("Insira o id: ");
        int aux = Integer.parseInt(ler.nextLine());

        Campus campus = campusDAO.find(aux);

        List<Oferta> aulas = relatorioDAO.relatorioAulas(aux);

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo3-" + LocalDate.now() + ".pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));

            doc.open();
            Font f = new Font(FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.GREEN);
            Paragraph p = new Paragraph("CAMPUS: " + campus.getNome() + "\n\n\n", f);
            p.setAlignment(1);
            doc.add(p);

            p = new Paragraph("");

            doc.add(p);

            PdfPTable table = new PdfPTable(2);

            PdfPCell cel1 = new PdfPCell(new Paragraph("PROFESSOR"));
            cel1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cel1.setPadding(10);

            PdfPCell cel2 = new PdfPCell(new Paragraph("DISCIPLINA"));
            cel2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cel2.setPadding(10);

            table.addCell(cel1);
            table.addCell(cel2);
            if (aulas.isEmpty()) {
                System.out.println("Sem aulas cadastradas...");
            } else {
                for (Servidor servidor : servidorVet) {
                    String disiciplinaString = "";
                    String servidorString = servidor.getNome();
                    for (Oferta aula : aulas) {

                        if (servidor.getId() == aula.getServidor().getId()) {
                            disiciplinaString += aula.getDisciplina().getNome() + "  "
                                    + aula.getAulaSemanais() + " aulas \n\n";
                        }

                    }

                    cel1 = new PdfPCell(new Paragraph(servidorString));
                    cel1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                    cel1.setPadding(10);

                    if (disiciplinaString.equals("")) {
                        disiciplinaString = "N/A";
                    }

                    cel2 = new PdfPCell(new Paragraph(disiciplinaString));
                    cel2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                    cel2.setPadding(10);

                    table.addCell(cel1);
                    table.addCell(cel2);

                }
                doc.add(table);
                doc.close();

                Desktop.getDesktop().open(new File(arquivoPdf));
            }
        } catch (Exception e) {

        }

    }
}
