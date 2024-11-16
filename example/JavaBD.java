package org.example;

import org.example.Pessoa;

import java.sql.*;

public class JavaBD {
    public static void main(String [] args){

        String url ="jdbc:mysql://127.0.0.1:3306/aranoua_java_web";
        String user = "root";
        String password = "#A30des42";

        try{
            Pessoa pessoa = new Pessoa("Carlos", 456, "carlos@ifam.edu");

            Connection conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexâo realizada!");

            Statement instrucao = conexao.createStatement();

            String sqlInserir = "insert into pessoa" +
                    "(nome, telefone, email)" +
                    "values" +
                    " ('"+pessoa.getNome()+"', '"+pessoa.getTelefone()+"', '"+pessoa.getEmail()+"')";

            String sqlAlterar = "update pessoa" +
                    " set nome = '"+pessoa.getNome()+"'" +
                    " where id = "+pessoa.getId();

            String sqlDeletar = "delete from pessoa" +
                    " where id = 1";

            String sqlListar = "select  id, nome, telefone, email from pessoa";

            System.out.println("SQL:"+sqlListar);

            boolean resultado = instrucao.execute(sqlListar);

            if(resultado){
                ResultSet resultados = instrucao.getResultSet();
                while(resultados.next()){
                    System.out.println("ID:"+resultados.getInt(1));
                    System.out.println("NOME:"+resultados.getString( 2));
                    System.out.println("EMAIL:"+resultados.getString(3));
                    System.out.println("**********************************");

                }
            }
            System.out.println("Instrução realizada com sucesso!");

        }catch(SQLException excecao){

            System.out.println("Erro: "+excecao.getMessage());

        }
        System.out.println("Programa Finalizado!");
    }
}