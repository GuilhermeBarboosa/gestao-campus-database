/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.CampusDAO;
import dao.RelatorioDAO;
import dao.ServidorDAO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Campus;
import model.Reuniao;
import model.Oferta;

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
        filtro[0] = LocalDate.parse("02/06/2022", formatter);
        System.out.println("Escolha data do fim: ");
        filtro[1] = LocalDate.parse("10/06/2022", formatter);
        return filtro;
    }

    public void relat1(LocalDate[] filtro, RelatorioDAO relatorioDAO) throws Exception {

        List<Reuniao> reunioes = relatorioDAO.relatorioData(filtro[0], filtro[1]);

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo1.pdf";

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
                doc.add(table);
                doc.close();

                Desktop.getDesktop().open(new File(arquivoPdf));
            }
        } catch (Exception e) {

        }

    }

    public void relat2(ServidorDAO servidorDAO, RelatorioDAO relatorioDAO) throws SQLException {

        List<String> relatorioServidor = relatorioDAO.relatorioCompleto();

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo2.pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));

            doc.open();
            Font f = new Font(FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.RED);
            Paragraph p = new Paragraph("RELATORIO DO TIPO 1\n\n", f);
            p.setAlignment(1);
            doc.add(p);

            p = new Paragraph("");

            doc.add(p);

            PdfPTable table = new PdfPTable(1);

            PdfPCell cel1 = new PdfPCell(new Paragraph(""));
            cel1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel1.setPadding(5);
            table.addCell(cel1);

            if (relatorioServidor.isEmpty()) {
                System.out.println("Sem servidores cadastradas...");
            } else {
                for (String reuniao : relatorioServidor) {
                    cel1 = new PdfPCell(new Paragraph(reuniao));
                    cel1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                    cel1.setPadding(10);
                    table.addCell(cel1);
                }
                doc.add(table);
                doc.close();

                Desktop.getDesktop().open(new File(arquivoPdf));
            }
        } catch (Exception e) {

        }

    }

    public void relat3(CampusDAO campusDAO, RelatorioDAO relatorioDAO) throws Exception {

        List<Campus> campusVet = campusDAO.read();
        CampusView campusV = new CampusView();

        campusV.mostrarIdTodosCampos(campusVet);

        System.out.println("Insira o id: ");
        int aux = Integer.parseInt(ler.nextLine());

        Campus campus = campusDAO.find(aux);

        List<Oferta> aulas = relatorioDAO.relatorioAulas(aux);

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo3.pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));

            doc.open();
            Font f = new Font(FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.GREEN);
            Paragraph p = new Paragraph("CAMPUS: " + aulas.get(0).getCurso().getCampus().getNome() + "\n\n\n", f);
            p.setAlignment(1);
            doc.add(p);

            p = new Paragraph("");

            doc.add(p);

            PdfPTable table = new PdfPTable(3);

            PdfPCell cel1 = new PdfPCell(new Paragraph("CURSO"));
            cel1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cel1.setPadding(10);

            PdfPCell cel2 = new PdfPCell(new Paragraph("DISCIPLINA"));
            cel2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cel2.setPadding(10);

            PdfPCell cel3 = new PdfPCell(new Paragraph("PROFESSOR"));
            cel3.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
            cel3.setPadding(10);

            table.addCell(cel1);
            table.addCell(cel2);
            table.addCell(cel3);
            if (aulas.isEmpty()) {
                System.out.println("Sem aulas cadastradas...");
            } else {
                for (Oferta aula : aulas) {
                    cel1 = new PdfPCell(new Paragraph(aula.getCurso().getNome()));
                    cel1.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                    cel1.setPadding(10);

                    cel2 = new PdfPCell(new Paragraph(aula.getDisciplina().getNome()));
                    cel2.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                    cel2.setPadding(10);

                    cel3 = new PdfPCell(new Paragraph(aula.getServidor().getNome()));
                    cel3.setHorizontalAlignment(PdfPCell.ALIGN_JUSTIFIED);
                    cel3.setPadding(10);

                    table.addCell(cel1);
                    table.addCell(cel2);
                    table.addCell(cel3);
                }
                doc.add(table);
                doc.close();

                Desktop.getDesktop().open(new File(arquivoPdf));
            }
        } catch (Exception e) {

        }

    }
}
