/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Campus;

/**
 *
 * @author Gui
 */
public class CampusDAO {

    public void create(Campus camp) throws Exception {
        String sql = "INSERT INTO campus(nome, abreviacao, duracao_aula, dt_criacao_camp, cidade, bairro, rua, cep, cadastrado) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, camp.getNome());
            pstm.setString(2, camp.getAbreviacao());
            pstm.setDouble(3, camp.getDuracaoAula());

            Date date = Date.valueOf(camp.getDtCriacaoCamp());
            pstm.setDate(4, date);

            pstm.setString(5, camp.getCidade());
            pstm.setString(6, camp.getBairro());
            pstm.setString(7, camp.getRua());
            pstm.setString(8, camp.getCep());

            date = Date.valueOf(LocalDate.now());
            pstm.setDate(9, date);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> read() throws Exception {
        String sql = "SELECT * FROM campus as c";

        List<String> vetResult = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                
                  vetResult.add("Id: \t\t" + rset.getString(1) + "\n"
                        + "Nome: \t\t" + rset.getString(2) + "\n"
                        + "Abreviação: \t\t" + rset.getString(3) + "\n"
                        + "Duração de aula: \t" + rset.getString(4) + "\n"
                        + "Criação do camp: \t" + rset.getString(5) + "\n"
                        + "Cidade: \t\t" + rset.getString(6) + "\n"
                        + "Bairro: \t\t" + rset.getString(7) + "\n"
                        + "Rua: \t\t" + rset.getString(8) + "\n"
                        + "CEP: \t\t" + rset.getString(9) + "\n"
                        + "Cadastrado: \t\t" + rset.getString(10) + "\n"
                        + "Modificado: \t\t" + rset.getString(11) + "\n"
                        + "----------------------------------------------------------");
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

    public List<String> readId() throws Exception {
        String sql = "SELECT * FROM campus as c";

        List<String> vetResult = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                
                vetResult.add("=========================\n"
                        + "Id: \t" + rset.getString(1) + "\n"
                        + "Nome: \t" + rset.getString(2) + "\n"
                        + "Abreviação: \t" + rset.getString(3) + "\n"
                        + "=========================" + "\n");
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

    
    public void update(Campus altCampus) throws Exception {;
        String sql = "UPDATE campus SET nome=?, abreviacao=?, duracao_aula=?, "
                + "dt_criacao_camp=?, cidade=?, bairro=?, rua=?, cep=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, altCampus.getNome());
            pstm.setString(2, altCampus.getAbreviacao());
            pstm.setDouble(3, altCampus.getDuracaoAula());

            Date date = Date.valueOf(altCampus.getDtCriacaoCamp());
            pstm.setDate(4, date);

            pstm.setString(5, altCampus.getCidade());
            pstm.setString(6, altCampus.getBairro());
            pstm.setString(7, altCampus.getRua());
            pstm.setString(8, altCampus.getCep());

            date = Date.valueOf(LocalDate.now());
            pstm.setDate(9, date);

            pstm.setInt(10, altCampus.getId());
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void delete(int idCampus) throws Exception {
      String sql = "DELETE FROM campus WHERE id = ?";
       Connection conn = null;
       PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idCampus);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    } 
    
    public Campus find(int idCampus) throws Exception {
        String sql = "SELECT * FROM campus WHERE id = '"+idCampus+"'";

   
        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
    
                Campus camp = new Campus();

                camp.setId(rset.getInt("id"));
                camp.setNome(rset.getString("nome"));
                camp.setAbreviacao(rset.getString("abreviacao"));
                camp.setDuracaoAula(rset.getDouble("duracao_aula"));

                Date date = rset.getDate("dt_criacao_camp");
                LocalDate dataAtualizada = date.toLocalDate();
                camp.setDtCriacaoCamp(dataAtualizada);

                camp.setCidade(rset.getString("cidade"));
                camp.setBairro(rset.getString("bairro"));
                camp.setRua(rset.getString("rua"));
                camp.setCep(rset.getString("cep"));

                date = rset.getDate("cadastrado");
                dataAtualizada = date.toLocalDate();
                camp.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    camp.setDtModificacao(dataAtualizada);
                }
                return camp;
 
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
        return null;
    }

}
