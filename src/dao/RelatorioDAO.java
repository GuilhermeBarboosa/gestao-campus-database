/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.Default.comissaoDAO;
import static dao.Default.servidorDAO;
import java.sql.PreparedStatement;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Campus;
import model.Comissao;
import model.Oferta;
import model.Reuniao;
import model.Servidor;

/**
 *
 * @author Usuario
 */
public class RelatorioDAO implements Default {
    
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
                
                Comissao comissao = comissaoDAO.find(rset.getInt("comissao"));
                reuniao.setComissao(comissao);
                
                Servidor servidor = servidorDAO.find(rset.getInt("servidor_secre"));
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
    
    public List<String> relatorioCompleto() throws SQLException {
        
        String sql = "SELECT * FROM servidores AS s "
                + "INNER JOIN ofertas AS ofe ON s.id = ofe.servidor "
                + "INNER JOIN disciplinas AS d ON d.id = ofe.disciplina "
                + "INNER JOIN atividades AS ati ON s.id = ati.servidor "
                + "INNER JOIN orientacoes AS ori ON s.id = ori.servidor "
                + "INNER JOIN vinculos AS vinc ON s.id = vinc.servidor "
                + "INNER JOIN comissoes AS com ON com.id = vinc.comissao; ";
        
        List<String> vetResult = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnectionToMySql();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            while (rset.next()) {
                
                vetResult.add("Id: " + rset.getString("s.id") + "\n"
                        + "Nome: " + rset.getString("s.nome") + "\n"
                        + "Disciplina: " + rset.getString("d.disciplina") + " - " + rset.getString("d.carga_horaria") + " horas" + "\n"
                        + "Atividade: " + rset.getString("ati.atividade") + " - " + rset.getString("ati.horas_semanais") + " horas" + "\n"
                        + "Orientação: " + rset.getString("ori.tipo") + " - " + rset.getString("ori.horas_semanais") + " horas" + "\n"
                        + "Comissao: " + rset.getString("com.comissao") + " - " + rset.getString("com.horas_semanais") + " horas" + "\n");
                
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
        Campus campus = campusDAO.find(idCampus);
        
        List<Oferta> ofertaVet = ofertaDAO.read();
        
        for (Oferta oferta : ofertaVet) {
            if (oferta.getCurso().getCampus().getId() == campus.getId()) {
                vetResult.add(oferta);
            }
        }
        
        return vetResult;
        
    }
}
