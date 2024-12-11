package servlet;

import dao.RecursosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Recursos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "recursoServlet", value = "/recurso")
public class RecursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String acao = request.getParameter("acao");
            switch (acao != null ? acao : "") {
                case "cadastrar":
                    formularioRecurso(request, response);
                    break;
                case "consultar":
                    consultarRecurso(request, response);
                    break;
                case "alterar":
                    formularioRecurso(request, response);
                    break;
                case "excluir":
                    excluirRecurso(request, response);
                    break;
                default:
                    listarRecurso(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tituloCadastrar = "Cadastro de Equipamento";
        String tituloAlterar = "Alteração de Equipamento";
        String titulo;

        String mensagemCadastrar = "Equipamento cadastrada com sucesso!";
        String mensagemAlterar = "Equipamento alterada com sucesso!";
        String mensagem;

        try {

            String salvar = request.getParameter("salvar");

            if (salvar != null) {

                Recursos recursos = new Recursos();

                recursos.setEquipamento(request.getParameter("equipamento"));
                recursos.setMarca(request.getParameter("marca"));
                recursos.setModelo(request.getParameter("modelo"));

                RecursosDAO recursosDAO = new RecursosDAO();

                String numeroTombo = request.getParameter("numeroTombo");

                if (numeroTombo != null && !numeroTombo.equals("0")) {
                    //Alterar
                    titulo = tituloAlterar;
                    mensagem = mensagemAlterar;
                    recursos.setNumeroTombo(Integer.parseInt(numeroTombo));
                    recursosDAO.alterar(recursos);
                } else {
                    titulo = tituloCadastrar;
                    mensagem = mensagemCadastrar;
                    recursosDAO.inserir(recursos);
                }

                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>" + titulo + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + titulo + "</h1>");

                out.println("<p>" + mensagem + "</p>");

                out.println("<a href='recurso'>Voltar</a>");

                out.println("</body>");
                out.println("</html>");

            }

        } catch (Exception e) {
            throw new ServletException(e);
        }


    }

    private void formularioRecurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String numeroTombo = request.getParameter("numeroTombo");

            Recursos recursos;

            if (numeroTombo != null) {
                RecursosDAO recursosDAO = new RecursosDAO();
                recursos = recursosDAO.consultar(Long.parseLong(numeroTombo));
            } else {
                recursos = new Recursos();
                recursos.setEquipamento("");
                recursos.setMarca("");
                recursos.setModelo("");
            }

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");

            if (numeroTombo != null) {
                out.println("<title>Alterar Recursos</title>");
            } else {
                out.println("<title>Cadastrar Recursos</title>");
            }


            out.println("</head>");
            out.println("<body>");

            if (numeroTombo != null) {
                out.println("<h1>Alterar Recursos</h1>");
            } else {
                out.println("<h1>Cadastrar Recursos</h1>");
            }


            out.println("<form action='recurso' method='post'>");

            out.println("<input type='hidden' name='numeroTombo' value='" + recursos.getNumeroTombo() + "'>");

            out.println("<label for='recursos_equipamento'>Equipamento:</label>");

            out.println("<input type='text' name='equipamento' value='" + recursos.getEquipamento() + "' required>");

            out.println("<br><br>");
            out.println("<label for='recursos_marca'>Marca:</label>");

            out.println("<input type='text' name='marca' value='" + recursos.getMarca() + "' required>");

            out.println("<br><br>");
            out.println("<label for='recursos_modelo'>Modelo:</label>");

            out.println("<input type='text' name='modelo' value='" + recursos.getModelo() + "' required>");

            out.println("<br><br>");
            out.println("<input type='reset' name='limpar' value='Limpar'>");
            out.println("<input type='submit' name='salvar' value='Salvar'>");

            out.println("</form>");

            out.println("<a href='recurso'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    private void listarRecurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            RecursosDAO recursosDAO = new RecursosDAO();

            List<Recursos> recursos = recursosDAO.listar();

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Lista de Recursos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Recursos</h1>");

            out.println("<table border='1'>");

            out.println("<th>Numero Tombo</th><th>Equipamento</th><th>Marca</th><th>Modelo</th>");
            for (Recursos recursos1 : recursos) {
                out.println("<tr><td><a href='recurso?acao=consultar&numeroTombo=" + recursos1.getNumeroTombo() + "'>" +
                        recursos1.getNumeroTombo() + "</a></td><td>" +
                        recursos1.getEquipamento() + "</td><td>" +
                        recursos1.getMarca() + "</td><td>" +
                        recursos1.getModelo() + "</td></tr>");
            }

            out.println("</table>");
            out.println("<br><br>");
            out.println("<a href='recurso?acao=cadastrar'>Cadastrar Recursos</a>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void consultarRecurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            RecursosDAO recursosDAO = new RecursosDAO();

            String numeroTombo = request.getParameter("numeroTombo");

            Recursos recursos = recursosDAO.consultar(Long.parseLong(numeroTombo));

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Consulta Detalhes dos Recursos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Consulta Detalhes dos Recursos</h1>");

            out.println("<p>Numero Tombo: " + recursos.getNumeroTombo() + "</p>");
            out.println("<p>Equipamento: " + recursos.getEquipamento() + "</p>");
            out.println("<p>Marca: " + recursos.getMarca() + "</p>");
            out.println("<p>Modelo: " + recursos.getModelo() + "</p>");

            out.println("<a href='recurso?acao=alterar&numeroTombo=" + recursos.getNumeroTombo() + "'>Alterar</a>");
            out.println("<a href='recurso?acao=excluir&numeroTombo=" + recursos.getNumeroTombo() + "'>Excluir</a>");
            out.println("<a href='recurso'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void excluirRecurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            RecursosDAO recursosDAO = new RecursosDAO();

            String numeroTombo = request.getParameter("numeroTombo");

            recursosDAO.excluir(Long.parseLong(numeroTombo));

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Exclusão dos Recursos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Exclusão dos Recursos</h1>");

            out.println("<p> Recurso excluída com sucesso</p>");

            out.println("<a href='recurso'>Voltar</a>");

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
