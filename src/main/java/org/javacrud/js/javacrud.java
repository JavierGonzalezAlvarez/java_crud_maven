package org.javacrud.js;

import org.javacrud.js.modelo.User;
import org.javacrud.js.repositorio.UserRepositorio;
import org.javacrud.js.repositorio.UserRepositorioImp;
import org.javacrud.js.util.conexionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class javacrud {

    public static void main(String[] args) {
        try (Connection conn = conexionDB.getInstance()) {
            UserRepositorio<User> repositorio = new UserRepositorioImp();
            System.out.println("===================== Listar =====================");
            repositorio.listar().forEach(System.out::println);

            System.out.println("===================== Listar por Id =====================");
            System.out.println("por id: " + repositorio.porId(2L));

            System.out.println("===================== Insertar =====================");
            User user = new User();
            user.setNombre("Ana");
            user.setApellido("Cabal");
            //java.util
            user.setFechaRegistro(new Date());
            repositorio.guardar(user);
            System.out.println("usuario guardado");
            repositorio.listar().forEach(System.out::println);

            System.out.println("===================== Update =====================");
            user.setId(1L);
            user.setNombre("Maria");
            user.setApellido("Salvador");
            repositorio.guardar(user);
            System.out.println("usuario actualizado");
            repositorio.listar().forEach(System.out::println);

            System.out.println("===================== Delete =====================");
            repositorio.eliminar(3L);
            System.out.println("usuario eliminado");
            repositorio.listar().forEach(System.out::println);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
