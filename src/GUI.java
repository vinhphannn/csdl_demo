import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    // Khai báo các thành phần giao diện
    private JTextField txtMa, txtTen, txtTuoi, txtNoiSinh, txtDiem;
    private JButton btnThem, btnXoa, btnSua, btnThoat;
    private JTable table;
    private DefaultTableModel model;

    public GUI() {
        // Thiết lập giao diện form
        setTitle("Form đăng ký");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa màn hình
        setLayout(null);

        // Tạo các label và textfield
        JLabel lblMa = new JLabel("Ma :");
        lblMa.setBounds(20, 20, 100, 20);
        add(lblMa);
        txtMa = new JTextField();
        txtMa.setBounds(120, 20, 150, 20);
        add(txtMa);

        JLabel lblTen = new JLabel("Ten :");
        lblTen.setBounds(20, 50, 100, 20);
        add(lblTen);
        txtTen = new JTextField();
        txtTen.setBounds(120, 50, 150, 20);
        add(txtTen);

        JLabel lblTuoi = new JLabel("Tuoi :");
        lblTuoi.setBounds(20, 80, 100, 20);
        add(lblTuoi);
        txtTuoi = new JTextField();
        txtTuoi.setBounds(120, 80, 150, 20);
        add(txtTuoi);

        JLabel lblNoiSinh = new JLabel("Noi Sinh :");
        lblNoiSinh.setBounds(20, 110, 100, 20);
        add(lblNoiSinh);
        txtNoiSinh = new JTextField();
        txtNoiSinh.setBounds(120, 110, 150, 50);
        add(txtNoiSinh);

        JLabel lblDiem = new JLabel("Diem :");
        lblDiem.setBounds(20, 170, 100, 20);
        add(lblDiem);
        txtDiem = new JTextField();
        txtDiem.setBounds(120, 170, 150, 20);
        add(txtDiem);

        // Tạo các nút bấm
        btnThem = new JButton("THEM");
        btnThem.setBounds(50, 220, 100, 30);
        add(btnThem);

        btnSua = new JButton("SUA");
        btnSua.setBounds(160, 220, 100, 30);
        add(btnSua);

        btnXoa = new JButton("XOA");
        btnXoa.setBounds(50, 260, 100, 30);
        add(btnXoa);

        btnThoat = new JButton("THOAT");
        btnThoat.setBounds(160, 260, 100, 30);
        add(btnThoat);

        // Tạo bảng hiển thị dữ liệu
        String[] col = { "Mã", "Họ tên", "Tuổi", "Nơi sinh", "Điểm" };
        model = new DefaultTableModel(col, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(300, 20, 280, 270);
        add(sp);

        // ActionListener cho nút Thêm
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ma = txtMa.getText();
                String ten = txtTen.getText();
                String tuoi = txtTuoi.getText();
                String noiSinh = txtNoiSinh.getText();
                String diem = txtDiem.getText();
                model.addRow(new Object[] { ma, ten, tuoi, noiSinh, diem });
            }
        });

        // ActionListener cho nút Xóa
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                }
            }
        });

        // ActionListener cho nút Thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Hiển thị form
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
