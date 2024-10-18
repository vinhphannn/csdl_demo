/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kduon
 */
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class GUI {
    JTextField txtMa = new JTextField();
    JTextField txtTen = new JTextField();
    JTextField txtNoisinh = new JTextField();
    JTextField txtTuoi = new JTextField();
    JTextField txtDiem = new JTextField();
    JButton btnThem = new JButton("Them");
    JButton btnXoa = new JButton("Xoa");
    JButton btnSua = new JButton("Sua");
    JButton btnThoat = new JButton("Thoat");
    // bang
    public String[] col = { "Ma", "Ho ten", "Tuoi", "Noi sinh", "Diem" };
    public String[][] row = {};
    public DefaultTableModel model = new DefaultTableModel(row, col);
    public JTable table = new JTable(model);
    //
    String DB_URL = "jdbc:mysql://localhost:3306/csdldk";
    String USER_NAME = "root";
    String PASSWORD = "";

    public void initUI() {
        JFrame a = new JFrame(" Form đăng ký ");
        a.setSize(830, 420);
        a.setLayout(null);
        JLabel lbA = new JLabel(" Ma: ");
        lbA.setBounds(20, 20, 80, 25);
        a.add(lbA);
        txtMa.setBounds(100, 20, 80, 25);
        a.add(txtMa);
        JLabel lbB = new JLabel(" Ten: ");
        lbB.setBounds(20, 60, 80, 25);
        a.add(lbB);
        txtTen.setBounds(100, 60, 220, 25);
        a.add(txtTen);
        JLabel lbc = new JLabel(" Tuoi: ");
        lbc.setBounds(20, 100, 80, 25);
        a.add(lbc);
        txtTuoi.setBounds(100, 100, 80, 25);
        a.add(txtTuoi);
        JLabel lbD = new JLabel(" Noi Sinh: ");
        lbD.setBounds(20, 180, 80, 25);
        a.add(lbD);
        txtNoisinh.setBounds(100, 150, 220, 75);
        a.add(txtNoisinh);
        JLabel lbH = new JLabel(" Diem: ");
        lbH.setBounds(20, 260, 80, 25);
        a.add(lbH);
        txtDiem.setBounds(100, 260, 80, 25);
        a.add(txtDiem);
        JScrollPane pane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(pane);
        panel.setBounds(350, 20, 450, 300);
        a.add(panel);
        a.setVisible(true);
        a.setLayout(null);
        btnThem.setBounds(100, 300, 90, 25);
        a.add(btnThem);
        btnSua.setBounds(230, 300, 90, 25);
        a.add(btnSua);
        btnXoa.setBounds(100, 340, 90, 25);
        a.add(btnXoa);
        btnThoat.setBounds(230, 340, 90, 25);
        a.add(btnThoat);
        a.setVisible(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
    }

    public void Loaddulieu() {
        try {
            Connection cnn = null;
            ConnectMySql conn = new ConnectMySql();
            cnn = conn.getConnection(DB_URL, USER_NAME, PASSWORD);
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bang");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String age = rs.getString(3);
                String address = rs.getString(4);
                int gpa = rs.getInt(5);
                Object[] row = { id, name, age, address, gpa };
                model = (DefaultTableModel) table.getModel();
                model.addRow(row);
            }
            cnn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}