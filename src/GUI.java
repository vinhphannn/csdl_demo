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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Kết nối CSDL
                    Connection cnn = new ConnectMySql().getConnection(DB_URL, USER_NAME, PASSWORD);
                    String query = "INSERT INTO bang (id, name, age, address, gpa) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = cnn.prepareStatement(query);
                    pstmt.setInt(1, Integer.parseInt(txtMa.getText()));
                    pstmt.setString(2, txtTen.getText());
                    pstmt.setString(3, txtTuoi.getText());
                    pstmt.setString(4, txtNoisinh.getText());
                    pstmt.setDouble(5, Double.parseDouble(txtDiem.getText()));
                    pstmt.executeUpdate();

                    // Thêm dữ liệu vào bảng giao diện
                    model.addRow(new Object[] { txtMa.getText(), txtTen.getText(), txtTuoi.getText(),
                            txtNoisinh.getText(), txtDiem.getText() });
                    cnn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra nếu có dòng được chọn
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Kích hoạt các nút Xóa và Sửa khi có dòng được chọn
                        btnXoa.setEnabled(true);
                        btnSua.setEnabled(true);

                        // Hiển thị dữ liệu của hàng được chọn vào các ô nhập liệu
                        txtMa.setText(model.getValueAt(selectedRow, 0).toString());
                        txtTen.setText(model.getValueAt(selectedRow, 1).toString());
                        txtTuoi.setText(model.getValueAt(selectedRow, 2).toString());
                        txtNoisinh.setText(model.getValueAt(selectedRow, 3).toString());
                        txtDiem.setText(model.getValueAt(selectedRow, 4).toString());
                    } else {
                        // Vô hiệu hóa các nút Xóa và Sửa khi không có hàng nào được chọn
                        btnXoa.setEnabled(false);
                        btnSua.setEnabled(false);
                    }
                }
            }
        });
        // Thêm sự kiện cho nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        // Lấy mã của hàng cần xóa
                        String ma = model.getValueAt(selectedRow, 0).toString();
                        System.out.println(ma);

                        // Kết nối CSDL và xóa
                        Connection cnn = new ConnectMySql().getConnection(DB_URL, USER_NAME, PASSWORD);
                        String query = "DELETE FROM bang WHERE id = ?";
                        PreparedStatement pstmt = cnn.prepareStatement(query);
                        pstmt.setString(1, ma);
                        pstmt.executeUpdate();

                        // Xóa hàng trong giao diện
                        model.removeRow(selectedRow);
                        cnn.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Thêm sự kiện cho nút Sửa (nếu có)
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        // Lấy mã của hàng cần sửa
                        String ma = model.getValueAt(selectedRow, 0).toString();

                        // Kết nối CSDL và cập nhật
                        Connection cnn = new ConnectMySql().getConnection(DB_URL, USER_NAME, PASSWORD);
                        String query = "UPDATE bang SET ten = ?, tuoi = ?, noisinh = ?, diem = ? WHERE id = ?";
                        PreparedStatement pstmt = cnn.prepareStatement(query);
                        pstmt.setString(1, txtTen.getText());
                        pstmt.setString(2, txtTuoi.getText());
                        pstmt.setString(3, txtNoisinh.getText());
                        pstmt.setDouble(4, Double.parseDouble(txtDiem.getText()));
                        pstmt.setString(5, ma);
                        pstmt.executeUpdate();

                        // Cập nhật bảng giao diện
                        model.setValueAt(txtTen.getText(), selectedRow, 1);
                        model.setValueAt(txtTuoi.getText(), selectedRow, 2);
                        model.setValueAt(txtNoisinh.getText(), selectedRow, 3);
                        model.setValueAt(txtDiem.getText(), selectedRow, 4);
                        cnn.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Thêm sự kiện cho nút Thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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
