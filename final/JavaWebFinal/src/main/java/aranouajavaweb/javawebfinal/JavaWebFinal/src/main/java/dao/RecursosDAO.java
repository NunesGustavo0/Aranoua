package dao;

import jakarta.validation.constraints.Null;
import model.Recursos;
import util.ConexaoUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecursosDAO {

    public void inserir(Recursos recursos) throws SQLException {

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "insert into recursos" +
                "(equipamento, marca, modelo)" +
                "values" +
                " ('"+recursos.getEquipamento()+"', '"+recursos.getMarca()+"', '"+recursos.getModelo()+"')";

        System.out.println("SQL: " +sql);

        instrucao.execute(sql);
    }

    public void alterar(Recursos recursos) throws SQLException {

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "update recursos" +
                " set equipamento = "+recursos.getEquipamento()+"','" +
                " set marca = "+recursos.getMarca()+"','" +
                " set modelo = "+recursos.getModelo()+"''" +
                " where id = "+recursos.getNumeroTombo();

        System.out.println("SQL: " +sql);

        instrucao.execute(sql);
    }

    public void excluir(Long NumeroTombo) throws SQLException {

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "delete from recursos" +
                " where numeroTombo = " +NumeroTombo;

        System.out.println("SQL: " +sql);

        instrucao.execute(sql);
    }

    public List<Recursos> listar() throws SQLException {

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "select  numeroTombo, equipamento, marca, modelo from recursos";

        System.out.println("SQL:"+sql);

        boolean resultado = instrucao.execute(sql);

        List<Recursos> recursos = new ArrayList<>();
        Recursos recurso;

        if(resultado){
            ResultSet resultados = instrucao.getResultSet();
            while(resultados.next()){
                recurso = new Recursos();
                recurso.setNumeroTombo(resultados.getInt(1));
                recurso.setEquipamento(resultados.getString( 2));
                recurso.setMarca(resultados.getString(3));
                recurso.setModelo(resultados.getString(4));
                recursos.add(recurso);
            }
        }
        return recursos;
    }

    public Recursos consultar(Long NumeroTombo) throws SQLException {

        ConexaoUtil conexaoUtil = new ConexaoUtil();

        Connection conexao = conexaoUtil.getConexao();

        Statement instrucao = conexao.createStatement();

        String sql = "select  numeroTombo, equipamento, marca, modelo from recursos where numeroTombo= " +NumeroTombo;

        System.out.println("SQL:"+sql);

        boolean resultado = instrucao.execute(sql);

        Recursos recurso;

        if(resultado){
            ResultSet resultados = instrucao.getResultSet();
            while(resultados.next()){
                recurso = new Recursos();
                recurso.setNumeroTombo(resultados.getInt(1));
                recurso.setEquipamento(resultados.getString(2));
                recurso.setMarca(resultados.getString(3));
                recurso.setModelo(resultados.getString(4));
                return recurso;
            }
        }
        return null;
    }


}