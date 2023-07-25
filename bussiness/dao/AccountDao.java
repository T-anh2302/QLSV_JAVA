/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.bussiness.dao;

import java.util.List;
import java.util.Optional;
import qlsv.entity.Account;

/**
 *
 * @author vtuan
 */
public class AccountDao implements Dao<Account>{

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Optional<Account> get(int id) {
        return null;
    }

//    @Override
//    public boolean insert(Account t) {
//        return false;
//    }
    @Override
    public int insert(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Account t) {
        return false;
    }

    @Override
    public boolean delete(Account t) {
        return false;
    }

    
    
}
