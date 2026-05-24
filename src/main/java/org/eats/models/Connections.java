package org.eats.models;

import org.eats.errors.ApiExceptions;

import java.sql.*;

public class Connections {
    private String username;
    private String password;
    private String host;

    public Connections() {

        this.username = System.getenv("USER_MYSQL");
        this.password = System.getenv("PASS_MYSQL");
        this.host = System.getenv("HOST_MYSQL");
        setup();
    }

    private void setup() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (
                    java.sql.Connection conn =
                            DriverManager.getConnection(host, username, password)
            ) {
                System.out.println("database connected");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver tidak ditemukan", e);

        } catch (SQLException e) {
            System.out.println("disini errornya");
            throw new RuntimeException(e);
        }
    }

    public int count(String table, String field, String uniqueId){
        String sql = """
        SELECT COUNT(*)
        FROM {{table}}
        WHERE {{field}} = ?
        """.replace("{{table}}", table).replace("{{field}}", field);
        try(
                java.sql.Connection conn = DriverManager.getConnection(host, username, password);
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, uniqueId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return  rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new ApiExceptions(400, e.getMessage());
        }

    }

    public void createUsers(String username, String password, String email, String name){
        String sql = """
        INSERT INTO users (email, username, password, name)
        VALUES (?, ?, ?, ?)
        """;
        try(
                Connection conn = DriverManager.getConnection(host, username, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ) {
            ps.setString(1, email);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, name);

            int rows = ps.executeUpdate();

        } catch (SQLException e) {
            throw new ApiExceptions(400, e.getMessage());
        }
    }

    public ResultSet getUnique(String table, String fields, String query){
        String sql = """
        SELECT *
                FROM {{table}}
        WHERE {{fields}} = ?
        """.replace("{{table}}", table).replace("{{fields}}", fields);

        try(
                Connection conn = DriverManager.getConnection(host, username, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ) {
            ps.setString(1, query);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs;
            }

            return rs;
        } catch (SQLException e) {
            throw new ApiExceptions(400,e.getMessage());
        }
    }

}
