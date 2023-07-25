/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.bussiness.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author vtuan
 */
public interface Dao<T> {
    List<T> getAll();
    Optional<T> get(int id);
    int insert(T t);
    boolean update(T t);
    boolean delete(T t);
}
