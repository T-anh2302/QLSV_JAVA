/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.entity;

/**
 *
 * @author vtuan
 */
public class Account {
    private int id;
    private String userName;
    private String password;
    private String des;

    public Account() {
    }

    public Account(int id, String userName, String password, String des) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", des=" + des + '}';
    }
    
}
