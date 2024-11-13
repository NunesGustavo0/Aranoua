import java.sql.*;

public class TestaConexaoBD {

    public static void main(String[] args){

       // jdbc:mysql://localhost:3306/aranoua_java_web

        String url = "jdbc:mysql://localhost:3306/aranoua_java_web";
        String usuario =  "root";
        String senha = "root";

        try {

            Pessoa pessoa = new Pessoa("Pessoa 82",  "emailpessoa82@ifam.edu");

            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexâo realizada!");

            Statement instrucao = conexao.createStatement();

            String sqlInserir = "insert into pessoa" +
                         " (nome,email)" +
                         " values" +
                         " ('"+pessoa.getNome()+"','"+pessoa.getEmail()+"')";

            String sqlAlterar = "update pessoa" +
                                " set nome = 'João'" +
                                " where id = 1";


            String sqlDeletar = "delete from pessoa" +
                                " where id = 1";

            String sqlListar = "select  id, nome, email from pessoa";

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
