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
import models.Fornecedor;
import models.Fornecedor;

/**
 *
 * @author Luiz Galhardo
 */
public class FornecedorDAO {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/main/java/bancodados/configuracaobd.properties");
    private static final String sqlconsultafornecedors = "SELECT * FROM fornecedores order by razao_social";
    private static final String sqlinserir = "INSERT INTO fornecedores (razao_social, email, telefone, cnpj) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE fornecedores SET razao_social = ?, email = ?, telefone = ?, cnpj = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM fornecedores WHERE id = ?";

    public FornecedorDAO() {

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

    public boolean Inserir(Fornecedor fornecedor) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            pstdados.setString(1, fornecedor.getRazaoSocial());
            pstdados.setString(2, fornecedor.getEmail());
            pstdados.setString(3, fornecedor.getTelefone());
            pstdados.setString(4, fornecedor.getCnpj());
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

    public boolean Alterar(Fornecedor fornecedor) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlalterar, tipo, concorrencia);
            pstdados.setString(1, fornecedor.getRazaoSocial());
            pstdados.setString(2, fornecedor.getEmail());
            pstdados.setString(3, fornecedor.getTelefone());
            pstdados.setString(4, fornecedor.getCnpj());
            pstdados.setInt(5, fornecedor.getId());
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

    public boolean Excluir(Fornecedor fornecedor) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlaexcluir, tipo, concorrencia);
            pstdados.setInt(1, fornecedor.getId());
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
            pstdados = connection.prepareStatement(sqlconsultafornecedors, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }

    public List<Fornecedor> getAllFornecedores() {
        List<Fornecedor> listaFornecedores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM fornecedores";
            pstdados = connection.prepareStatement(sql);
            rsdados = pstdados.executeQuery();

            while (rsdados.next()) {
                int id = rsdados.getInt("id");
                String razaoSocial = rsdados.getString("razao_social");
                String email = rsdados.getString("email");
                String telefone = rsdados.getString("telefone");
                String cnpj = rsdados.getString("cnpj");

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(id);
                fornecedor.setRazaoSocial(razaoSocial);
                fornecedor.setEmail(email);
                fornecedor.setTelefone(telefone);
                fornecedor.setCnpj(cnpj);
                listaFornecedores.add(fornecedor);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao obter todos os fornecedorentes = " + erro);
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

        return listaFornecedores;
    }

    public Fornecedor getFornecedor() {
        Fornecedor fornecedor = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("id");
                String razaoSocial = rsdados.getString("razao_social");
                String email = rsdados.getString("email");
                String telefone = rsdados.getString("telefone");
                String cnpj = rsdados.getString("cnpj");
                fornecedor = new Fornecedor(id, razaoSocial, email, telefone, cnpj);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return fornecedor;
    }

    public Fornecedor getFornecedorById(String id) {
        Fornecedor fornecedor = null;
        try {
            String sql = "SELECT * FROM fornecedores WHERE id = ?";
            pstdados = connection.prepareStatement(sql);
            pstdados.setInt(1, Integer.parseInt(id));
            rsdados = pstdados.executeQuery();

            if (rsdados.next()) {
                int identificador = rsdados.getInt("id");
                String razaoSocial = rsdados.getString("razao_social");
                String emailFinal = rsdados.getString("email");
                String telefone = rsdados.getString("telefone");
                String cnpj = rsdados.getString("cnpj");

                fornecedor = new Fornecedor();
                fornecedor.setId(identificador);
                fornecedor.setRazaoSocial(razaoSocial);
                fornecedor.setEmail(emailFinal);
                fornecedor.setTelefone(telefone);
                fornecedor.setCnpj(cnpj);
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
        return fornecedor;
    }

    /**
     * @return the rsdados
     */
    public ResultSet getRsdados() {
        return rsdados;
    }

}
