package org.javacrud.js.repositorio;

import org.javacrud.js.modelo.User;
import org.javacrud.js.util.conexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepositorioImp implements UserRepositorio<User> {

    private Connection getConnection() throws SQLException {
        return conexionDB.getInstance();
    }

    @Override
    public List<User> listar() {
        List<User> users = new ArrayList<>();

        try(Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario")) {

            while (rs.next()) {
                User u = getUser(rs);
                users.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User porId(Long id) {
        User user = null;
        try (PreparedStatement stmt = getConnection()
                .prepareStatement("SELECT * FROM usuario WHERE id = ?")) {
            stmt.setLong(1, id);
            //return cursor resultset
            try (ResultSet rs = stmt.executeQuery()) {
                //move to next cursor with next
                if (rs.next()) {
                    user = getUser(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void guardar(User user) {
        String sql;
        if (user.getId() !=null && user.getId() > 0) {
            sql = "UPDATE usuario SET nombre=?, apellido=? WHERE id=?";
        }else{
            sql = "INSERT INTO usuario(nombre, apellido, fecha_registro) VALUES (?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getApellido());
            if (user.getId() !=null && user.getId() > 0) {
                //update
                stmt.setLong(3, user.getId());
            }else {
                stmt.setDate(3, new Date(user.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM usuario WHERE id =?";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //refactor/extractmethod
    private User getUser(ResultSet rs) throws SQLException{
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setFechaRegistro(rs.getDate("fecha_registro"));
        return u;
    }
}
