/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import qlsv.bussiness.dao.SinhVienDao;
import qlsv.entity.SinhVien;

/**
 *
 * @author vtuan
 */
public class FrmQLSV extends JFrame implements ActionListener, MouseListener {

    private JLabel lblTitle;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnRemove;
    private JButton btnClear;
    private JTree treeDanhSach;
    private JLabel lblMaSV;
    private JLabel lblHoTen;
    private JLabel lblTuoi;
    private JTextField txtMaSV;
    private JTextField txtHoTen;
    private JTextField txtTuoi;
    private DefaultMutableTreeNode dmtn;
    private SinhVienDao sinhVienDao = new SinhVienDao();

    public FrmQLSV(String title) {
        super(title);
        initUI();
        addAction();
    }

    public void addAction() {
        btnClear.addActionListener(this);
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnUpdate.addActionListener(this);
        treeDanhSach.addMouseListener(this);
    }

    public void initUI() {
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        //Tạo layout tổng
        JPanel pnBorder = new JPanel(new BorderLayout(10, 10));
        //Bắt đầu tạo vùng north
        JPanel pnNorth = new JPanel();
        lblTitle = new JLabel("THÔNG TIN SINH VIÊN");
        lblTitle.setForeground(Color.red);
        Font flblTitle = new Font("Tahoma", Font.BOLD, 30);
        lblTitle.setFont(flblTitle);
        pnNorth.add(lblTitle);
        pnBorder.add(pnNorth, BorderLayout.NORTH);
        //kết thúc vùng North

        //Bắt đầu tạo vùng South
        JPanel pnSouth = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnRemove = new JButton("Remove");
        btnClear = new JButton("Clear");
        pnSouth.add(btnAdd);
        pnSouth.add(btnUpdate);
        pnSouth.add(btnRemove);
        pnSouth.add(btnClear);
        pnBorder.add(pnSouth, BorderLayout.SOUTH);
        //Kết thúc vùng South

        //Bắt đầu tạo vùng West
        JPanel pnWest = new JPanel();
        dmtn = new DefaultMutableTreeNode("Danh sách sinh viên");
//        DefaultMutableTreeNode sv1 = new DefaultMutableTreeNode("Dương Thị Nến");
//        DefaultMutableTreeNode sv2 = new DefaultMutableTreeNode("Lê Thị Lửa");
//        dmtn.add(sv1);
//        dmtn.add(sv2);
        DefaultMutableTreeNode treeNode = null;
        for (SinhVien sv : sinhVienDao.getAll()) {
            treeNode = new DefaultMutableTreeNode(sv);
            dmtn.add(treeNode);
        }

        treeDanhSach = new JTree(dmtn);
        pnWest.add(treeDanhSach);
        pnBorder.add(new JScrollPane(pnWest), BorderLayout.WEST);
        //Kết thúc vùng West

        //Bắt đầu tạo vùng Center
        JPanel pnCenter = new JPanel();
        //Box left
        JPanel boxleft = new JPanel();
        boxleft.setLayout(new BoxLayout(boxleft, BoxLayout.Y_AXIS));
        lblMaSV = new JLabel("Mã sinh viên: ");
        lblHoTen = new JLabel("Họ và Tên: ");
        lblTuoi = new JLabel("Tuổi: ");
        boxleft.add(lblMaSV);
        boxleft.add(Box.createVerticalStrut(30));
        boxleft.add(lblHoTen);
        boxleft.add(Box.createVerticalStrut(30));
        boxleft.add(lblTuoi);
        pnCenter.add(boxleft);
        //Box right
        JPanel boxRight = new JPanel();
        boxRight.setLayout(new BoxLayout(boxRight, BoxLayout.Y_AXIS));
        txtMaSV = new JTextField(25);
        txtHoTen = new JTextField(25);
        txtTuoi = new JTextField(25);
        boxRight.add(txtMaSV);
        boxRight.add(Box.createVerticalStrut(30));
        boxRight.add(txtHoTen);
        boxRight.add(Box.createVerticalStrut(30));
        boxRight.add(txtTuoi);

        pnCenter.add(boxRight);
        pnBorder.add(pnCenter, BorderLayout.CENTER);
        //Kết thúc vùng Center

        this.add(pnBorder);
    }

    public static void main(String[] args) {
        FrmQLSV fqlsv = new FrmQLSV("Quản lý sinh viên");
        fqlsv.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear")) {
            txtMaSV.setText("");
            txtHoTen.setText("");
            txtTuoi.setText("");
        } else if (e.getActionCommand().equals("Add")) {
            String hoten = txtHoTen.getText().trim();
            int tuoi = Integer.parseInt(txtTuoi.getText().trim());
            SinhVien svNew = new SinhVien(0, hoten, tuoi);//masv tự động tăng
            int LastId = sinhVienDao.insert(svNew);
            if (LastId >= 0) {
                JOptionPane.showMessageDialog(rootPane, "Thêm mới sinh viên " + hoten + " thành công!");
                DefaultMutableTreeNode sv = new DefaultMutableTreeNode(new SinhVien(LastId, hoten, tuoi));
                DefaultTreeModel treeModel = (DefaultTreeModel) treeDanhSach.getModel();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
                root.add(sv);
                treeModel.reload();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra, xem lại lớp DAO!");
            }

        } else if (e.getActionCommand().equals("Remove")) {
            DefaultTreeModel treeModel = (DefaultTreeModel) treeDanhSach.getModel();
            TreePath[] paths = treeDanhSach.getSelectionPaths();
            if (paths != null) {
                for (TreePath path : paths) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    if (node.getParent() != null) {
                        SinhVien svDelete = (SinhVien) node.getUserObject();
                        if (sinhVienDao.delete(svDelete)) {
                            JOptionPane.showMessageDialog(rootPane, "Xóa thành công!!");
                            treeModel.removeNodeFromParent(node);

                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Có lỗi, xem lại lớp DAO");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Không tồn tại đối tượng cần xóa!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Bạn phải chọn ít nhất 1 người để xóa!!");
            }
        } else if (e.getActionCommand().equals("Update")) {
            int masv = Integer.parseInt(txtMaSV.getText().trim());
            String hoten = txtHoTen.getText().trim();
            int tuoi = Integer.parseInt(txtTuoi.getText().trim());
            SinhVien svUpdate = new SinhVien(masv, hoten, tuoi);
            DefaultTreeModel treeModel = (DefaultTreeModel) treeDanhSach.getModel();
            TreePath[] paths = treeDanhSach.getSelectionPaths();
            if (paths != null) {
                for (TreePath path : paths) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                    if (node.isLeaf()) {
                        if (sinhVienDao.update(svUpdate)) {
                            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công sinh viên " + hoten);
                            node.setUserObject(svUpdate);
                            treeModel.reload();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Lỗi, xem lại cập nhật trong lớp DAO!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Đối tượng " + node + " không là đối tượng cần sửa!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Bạn phải chọn 1 người để sửa!!");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DefaultTreeModel treeModel = (DefaultTreeModel) treeDanhSach.getModel();
        TreePath[] paths = treeDanhSach.getSelectionPaths();
        for (TreePath path : paths) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (node.isLeaf()) {
                SinhVien sv = (SinhVien) node.getUserObject();
                txtMaSV.setText(String.valueOf(sv.getMaSV()));
                txtMaSV.setEditable(false);
                txtHoTen.setText(sv.getHoTen());
                txtTuoi.setText(String.valueOf(sv.getTuoi()));
            } else {
                JOptionPane.showMessageDialog(rootPane, "Đối tượng " + node + " không là đối tượng cần sửa!");
            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
