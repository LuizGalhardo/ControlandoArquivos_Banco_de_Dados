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
import models.Produto;
import models.Produto;

public class ProdutoDAO {

    private Connection connection = null;
    private PreparedStatement pstdados = null;
    private ResultSet rsdados = null;
    private static final String path = System.getProperty("user.dir");
    private static final File config_file = new File(path + "/src/main/java/bancodados/configuracaobd.properties");
    private static final String sqlconsultaprodutos = "SELECT * FROM produtos order by nome";
    private static final String sqlinserir = "INSERT INTO produtos (nome, quantidade, preco, descricao) VALUES (?, ?, ?, ?)";
    private static final String sqlalterar = "UPDATE produtos SET nome = ?, quantidade = ?, preco = ?, descricao = ? WHERE id = ?";
    private static final String sqlaexcluir = "DELETE FROM produtos WHERE id = ?";

    public ProdutoDAO() {

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

    public boolean Inserir(Produto produto) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlinserir, tipo, concorrencia);
            pstdados.setString(1, produto.getNome());
            pstdados.setInt(2, produto.getQuantidade());
            pstdados.setInt(3, produto.getPreco());
            pstdados.setString(4, produto.getDescricao());
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

    public boolean Alterar(Produto produto) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlalterar, tipo, concorrencia);
            pstdados.setString(1, produto.getNome());
            pstdados.setInt(2, produto.getQuantidade());
            pstdados.setInt(3, produto.getPreco());
            pstdados.setString(4, produto.getDescricao());
            pstdados.setInt(5, produto.getId());
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

    public boolean Excluir(Produto produto) {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlaexcluir, tipo, concorrencia);
            pstdados.setInt(1, produto.getId());
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

    public List<Produto> getAllProdutos() {
        List<Produto> listaProdutos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produtos";
            pstdados = connection.prepareStatement(sql);
            rsdados = pstdados.executeQuery();

            while (rsdados.next()) {
                int id = rsdados.getInt("id");
                String nome = rsdados.getString("nome");
                int quantidade = rsdados.getInt("quantidade");
                int preco = rsdados.getInt("preco");
                String descricao = rsdados.getString("descricao");

                Produto produto = new Produto();
                produto.setId(id);
                produto.setNome(nome);
                produto.setQuantidade(quantidade);
                produto.setPreco(preco);
                produto.setDescricao(descricao);
                listaProdutos.add(produto);
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao obter todos os produtoentes = " + erro);
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

        return listaProdutos;
    }

    public boolean ConsultarTodos() {
        try {
            int tipo = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqlconsultaprodutos, tipo, concorrencia);
            rsdados = pstdados.executeQuery();
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao executar consulta = " + erro);
        }
        return false;
    }

    public Produto getProduto() {
        Produto produto = null;
        if (rsdados != null) {
            try {
                int id = rsdados.getInt("id");
                String nome = rsdados.getString("nome");
                int quantidade = rsdados.getInt("quantidade");
                int preco = rsdados.getInt("preco");
                String descricao = rsdados.getString("descricao");
                produto = new Produto(id, nome, quantidade, preco, descricao);
            } catch (SQLException erro) {
                System.out.println(erro);
            }
        }
        return produto;
    }

    public Produto getProdutoById(String id) {
        Produto produto = null;
        try {
            String sql = "SELECT * FROM produtos WHERE id = ?";
            pstdados = connection.prepareStatement(sql);
            pstdados.setInt(1, Integer.parseInt(id));
            rsdados = pstdados.executeQuery();

            if (rsdados.next()) {
                int identificador = rsdados.getInt("id");
                String name = rsdados.getString("nome");
                int quantidade = rsdados.getInt("quantidade");
                int preco = rsdados.getInt("preco");
                String descricao = rsdados.getString("descricao");
                produto = new Produto();
                produto.setId(identificador);
                produto.setNome(name);
                produto.setQuantidade(quantidade);
                produto.setPreco(preco);
                produto.setDescricao(descricao);
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
        return produto;
    }

    /**
     * @return the rsdados
     */
    public ResultSet getRsdados() {
        return rsdados;
    }

}
