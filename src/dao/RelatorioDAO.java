/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import factory.ConnectionFactory;
import model.*;
import service.CampusService;
import service.ComissaoService;
import service.OfertaService;
import service.ServidorService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Usuario
 */
public class RelatorioDAO {

    private final ServidorService servidorService = new ServidorService();
    private final ComissaoService comissaoService = new ComissaoService();
    private final CampusService campusService = new CampusService();
    private final OfertaService ofertaService = new OfertaService();

    public List<Reuniao> relatorioData(LocalDate dtIncial, LocalDate dtFinal) throws SQLException {
        String sql = "SELECT * FROM reunioes WHERE dt_reuniao BETWEEN ? AND ?;";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        List<Reuniao> vetResult = new ArrayList<>();
        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            Date date = Date.valueOf(dtIncial);
            pstm.setDate(1, date);

            date = Date.valueOf(dtFinal);
            pstm.setDate(2, date);
            pstm.execute();

            rset = pstm.executeQuery();

            while (rset.next()) {
                Reuniao reuniao = new Reuniao();

                reuniao.setId(rset.getInt("id"));

                Comissao comissao = comissaoService.getById(rset.getInt("comissao"));
                reuniao.setComissao(comissao);

                Servidor servidor = servidorService.getById(rset.getInt("servidor_secre"));
                reuniao.setServidorSecre(servidor);

                reuniao.setConteudoAta(rset.getString("conteudo_ata"));

                date = rset.getDate("dt_reuniao");
                LocalDate dataAtualizada = date.toLocalDate();
                reuniao.setDtReuniao(dataAtualizada);

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                reuniao.setDtCriacao(dataAtualizada);
                reuniao.setId(0);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    reuniao.setDtModificacao(dataAtualizada);
                }

                vetResult.add(reuniao);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return vetResult;
    }

    public List<Oferta> relatorioAulas(int idCampus) throws SQLException, Exception {

        List<Oferta> vetResult = new ArrayList<>();
        Campus campus = campusService.getById(idCampus);

        List<Oferta> ofertaVet = ofertaService.read();

        for (Oferta oferta : ofertaVet) {
            if (oferta.getCurso().getCampus().getId() == campus.getId()) {
                vetResult.add(oferta);
            }
        }

        return vetResult;

    }
}
