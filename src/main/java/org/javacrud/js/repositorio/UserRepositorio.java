package org.javacrud.js.repositorio;

import java.util.List;

public interface UserRepositorio<T> {
    List<T> listar();
    //search by id
    T porId(Long id);
    //save and update
    void guardar(T t);
    //delete
    void eliminar(Long id);
}
