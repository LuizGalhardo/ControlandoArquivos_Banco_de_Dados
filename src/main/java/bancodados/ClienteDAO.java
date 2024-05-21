/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancodados;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;

/**
 *
 * @author Luiz Galhardo
 */
public class ClienteDAO {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/main/java/bancodados/configuracaobd.properties");
    private static final String sqlconsultaclientes = "SELECT * FROM clientes order by nome";
    private static final String sqlinserir = "INSERT INTO clientes (nome, email, telefone, cpf) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE clientes SET nome = ?, email = ?, telefone = ?, cpf = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM clientes WHERE id = ?";

    public ClienteDAO() {

    }

    public boolean CriaConexao() {
        try {
            JDBCUtil.init(config_file);
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.

            DatabaseMetaData dbmt = connection.getMetaData();
            System.out.println("Nome do BD: " + dbmt.getDatabaseProductName());
            System.out.println("Versao do BD: " + dbmt.getDatabaseProductVersion());
            System.out.println("URL: " + dbmt.getURL());
            System.out.println("Driver: " + dbmt.getDriverName());
            System.out.println("Versao Driver: " + dbmt.getDriverVersion());
            System.out.println("Usuario: " + dbmt.getUserName());

            return true;
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC." + erro);
        } catch (IOException erro) {
            System.out.println("Falha ao carregar o arquivo de configuração." + erro);
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
        }
        return false;
    }

    public boolean FechaConexao() {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException erro) {
                System.err.println("Erro ao fechar a conexão = " + erro);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean Inserir(Cliente cli) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            pstdados.setString(1, cli.getNome());
            pstdados.setString(2, cli.getEmail());
            pstdados.setString(3, cli.getTelefone());
            pstdados.setString(4, cli.getCpf());

            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da inserção = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da inserção = " + erro);
        }
        return false;
    }

    public boolean Alterar(Cliente cli) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlalterar, tipo, concorrencia);
            pstdados.setString(1, cli.getNome());
            pstdados.setString(2, cli.getEmail());
            pstdados.setString(3, cli.getTelefone());
            pstdados.setString(4, cli.getCpf());
            pstdados.setInt(5, cli.getId());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da atualização = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da atualização = " + erro);
        }
        return false;
    }

    public boolean Excluir(Cliente cli) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlaexcluir, tipo, concorrencia);
            pstdados.setInt(1, cli.getId());
            int resposta = pstdados.executeUpdate();
            pstdados.close();
            //DEBUG
            System.out.println("Resposta da exclusão = " + resposta);
            //FIM-DEBUG
            if (resposta == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException erro) {
            System.out.println("Erro na execução da exclusão = " + erro);
        }
        return false;
    }

    public boolean ConsultarTodos() {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaclientes, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> listaClientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM clientes";
            pstdados = connection.prepareStatement(sql);
            rsdados = pstdados.executeQuery();

            while (rsdados.next()) {
                int id = rsdados.getInt("id");
                String nome = rsdados.getString("nome");
                String email = rsdados.getString("email");
                String telefone = rsdados.getString("telefone");
                String cpf = rsdados.getString("cpf");

                Cliente cli = new Cliente();
                cli.setId(id);
                cli.setNome(nome);
                cli.setEmail(email);
                cli.setTelefone(telefone);
                cli.setCpf(cpf);
                listaClientes.add(cli);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao obter todos os clientes = " + erro);
        } finally {
            try {
                if (rsdados != null) {
                    rsdados.close();
                }
                if (pstdados != null) {
                    pstdados.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos = " + e);
            }
        }

        return listaClientes;
    }

    public Cliente getCliente() {
        Cliente cli = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("id");
                String nome = rsdados.getString("nome");
                String email = rsdados.getString("email");
                String telefone = rsdados.getString("telefone");
                String cpf = rsdados.getString("cpf");
                cli = new Cliente(id, nome, email, telefone, cpf);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return cli;
    }

    public Cliente getClienteById(String id) {
        Cliente cli = null;
        try {
            String sql = "SELECT * FROM clientes WHERE id = ?";
            pstdados = connection.prepareStatement(sql);
            pstdados.setInt(1, Integer.parseInt(id));
            rsdados = pstdados.executeQuery();

            if (rsdados.next()) {
                int identificador = rsdados.getInt("id");
                String nome = rsdados.getString("nome");
                String telefone = rsdados.getString("telefone");
                String email = rsdados.getString("email");
                String cpf = rsdados.getString("cpf");

                cli = new Cliente();
                cli.setId(identificador);
                cli.setNome(nome);
                cli.setTelefone(telefone);
                cli.setEmail(email);
                cli.setCpf(cpf);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta por email = " + erro);
        } finally {
            try {
                if (rsdados != null) {
                    rsdados.close();
                }
                if (pstdados != null) {
                    pstdados.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos = " + e);
            }
        }
        return cli;
    }

    /**
     * @return the rsdados
     */
    public ResultSet getRsdados() {
        return rsdados;
    }

}
