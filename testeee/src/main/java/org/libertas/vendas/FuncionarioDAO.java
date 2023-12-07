package org.libertas.vendas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FuncionarioDAO {

	public void inserir(Funcionario f) {
        Conexao con = new Conexao();

        try {
            String sql = "INSERT INTO funcionario (nome, idade, cargo, departamento, horas_trabalhadas) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setString(1, f.getNome());
            prep.setInt(2, f.getIdade());
            prep.setString(3, f.getCargo());
            prep.setString(4, f.getDep());
            prep.setString(5, f.getHoras());
            prep.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar(Funcionario f) {
        Conexao con = new Conexao();

        try {
            String sql = "UPDATE funcionario SET nome = ?, idade = ?, cargo = ?, departamento = ?, horas_trabalhadas = ? WHERE id = ?";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setString(1, f.getNome());
            prep.setInt(2, f.getIdade());
            prep.setString(3, f.getCargo());
            prep.setString(4, f.getDep());
            prep.setString(5, f.getHoras());
            prep.setInt(6, f.getId());
            prep.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Funcionario f) {
        Conexao con = new Conexao();

        try {
            String sql = "DELETE FROM funcionario WHERE id = ?";
            PreparedStatement prep = con.getConnection().prepareStatement(sql);
            prep.setInt(1, f.getId());
            prep.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Funcionario consultar(int id) {
        Funcionario f = new Funcionario();
        Conexao con = new Conexao();

        try {
            String sql = "SELECT * FROM funcionario WHERE id = " + id;
            Statement sta = con.getConnection().createStatement();
            ResultSet res = sta.executeQuery(sql);
            if (res.next()) {
                f.setNome(res.getString("nome"));
                f.setIdade(res.getInt("idade"));
                f.setCargo(res.getString("cargo"));
                f.setDep(res.getString("departamento"));
                f.setHoras(res.getString("horas_trabalhadas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.desconectar();
        return f;
    }

    public List<Funcionario> listar() {
        List<Funcionario> lista = new LinkedList<>();
        Conexao con = new Conexao();

        try {
            String sql = "SELECT * FROM funcionario ORDER BY id";
            Statement sta = con.getConnection().createStatement();
            ResultSet res = sta.executeQuery(sql);
            while (res.next()) {
                Funcionario f = new Funcionario();
                f.setNome(res.getString("nome"));
                f.setIdade(res.getInt("idade"));
                f.setCargo(res.getString("cargo"));
                f.setDep(res.getString("departamento"));
                f.setHoras(res.getString("horas_trabalhadas"));
                f.setId(res.getInt("id"));
                lista.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        con.desconectar();
        return lista;
    }
}
