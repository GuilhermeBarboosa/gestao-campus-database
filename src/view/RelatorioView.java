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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

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
        filtro[0] = LocalDate.parse("16/06/2022", formatter);
        System.out.println("Escolha data do fim: ");
        filtro[1] = LocalDate.parse("28/06/2022", formatter);
        return filtro;
    }

    public void relat1(LocalDate[] filtro, RelatorioDAO relatorioDAO) throws Exception {

        List<String> reunioes = relatorioDAO.relatorioData(filtro[0], filtro[1]);

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo1.pdf";

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

            if (reunioes.isEmpty()) {
                System.out.println("Sem reunioes cadastradas...");
            } else {
                for (String reuniao : reunioes) {
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
                System.out.println("Sem reunioes cadastradas...");
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

        List<String> campus = campusDAO.readId();

        for (String cam : campus) {
            System.out.println(cam);
        }
        System.out.println("Insira o id: ");
        int aux = Integer.parseInt(ler.nextLine());

        List<String> aulas = relatorioDAO.relatorioAulas(aux);

        Document doc = new Document();

        String arquivoPdf = "relatorioTipo1.pdf";

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));

            doc.open();
            Font f = new Font(FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.RED);
            Paragraph p = new Paragraph("RELATORIO DO TIPO 2\n\n", f);
            p.setAlignment(1);
            doc.add(p);

            p = new Paragraph("");

            doc.add(p);

            PdfPTable table = new PdfPTable(1);

            PdfPCell cel1 = new PdfPCell(new Paragraph(""));
            cel1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cel1.setPadding(5);
            table.addCell(cel1);

            if (aulas.isEmpty()) {
                System.out.println("Sem aulas cadastradas...");
            } else {
                for (String aula : aulas) {
                    cel1 = new PdfPCell(new Paragraph(aula));
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
}
