/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.bussiness.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import qlsv.connect.MyConnection;
import qlsv.entity.SinhVien;

/**
 *
 * @author vtuan
 */
public class SinhVienDao implements Dao<SinhVien>{
    MyConnection myConnection = new MyConnection();
    Connection conn = myConnection.getInstance();
    @Override
    public List<SinhVien> getAll() {
        List<SinhVien> list = new ArrayList<>();
        String sql = "select * from sinhvien";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {                
                list.add(new SinhVien(rs.getInt("masv"), rs.getString("hoten"), rs.getInt("tuoi")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Optional<SinhVien> get(int id) {
        SinhVien sv = new SinhVien();
        String sql = "select * from sinhvien where  masv = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {  
                sv.setMaSV(rs.getInt("masv"));
                sv.setHoTen(rs.getString("hoten"));
                sv.setTuoi(rs.getInt("tuoi"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(sv);
    }

    @Override
    public int insert(SinhVien t) {
        String sql = "insert into sinhvien values(?, ?, ?)";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setInt(1, t.getMaSV());//tự động tăng
            preStmt.setString(2, t.getHoTen());
            preStmt.setInt(3, t.getTuoi());
            if (preStmt.executeUpdate() > 0) {
                ResultSet rs = preStmt.getGeneratedKeys();
                while (rs.next()) {                    
                    return rs.getInt(1);
                }
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean update(SinhVien t) {
        String sql = "update sinhvien set hoten = ?, tuoi = ? where masv = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, t.getHoTen());//tự động tăng
            preStmt.setInt(2, t.getTuoi());
            preStmt.setInt(3, t.getMaSV());
            if (preStmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(SinhVien t) {
        String sql = "delete from sinhvien where masv = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, t.getMaSV());
            if (preStmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(int masv) {
        String sql = "delete from sinhvien where masv = ?";
        try {
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setInt(1, masv);
            if (preStmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        SinhVienDao sinhVienDao = new SinhVienDao();
        for (SinhVien sv : sinhVienDao.getAll()) {
            System.out.println(sv);
        }
        System.out.println("---------------------");
        SinhVien sv = sinhVienDao.get(21).get();
        System.out.println(sv);
        System.out.println("---------------------------");
        SinhVien svNew = new SinhVien(0, "Ân Tố Tố", 20);
//        System.out.println(sinhVienDao.insert(svNew));
        SinhVien svUpdate = new SinhVien(21, "Dương Tiêu", 20);
//        System.out.println(sinhVienDao.update(svUpdate));
        System.out.println("---------Xóa-------------");
//        System.out.println(sinhVienDao.delete(svUpdate));
        System.out.println(sinhVienDao.delete(18));
    }
}

