/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.entity;

/**
 *
 * @author vtuan
 */
public class SinhVien {
    private int maSV;
    private String hoTen;
    private int Tuoi;

    public SinhVien() {
    }

    public SinhVien(int maSV, String hoTen, int Tuoi) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.Tuoi = Tuoi;
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int Tuoi) {
        this.Tuoi = Tuoi;
    }

    @Override
    public String toString() {
        return hoTen;
    }
    
}
