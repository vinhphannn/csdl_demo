import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/csdldk"; // Thay bằng tên DB của bạn
    private static final String USERNAME = "root"; // Thay bằng username của bạn
    private static final String PASSWORD = ""; // Thay bằng password của bạn

    public static void main(String[] args) {
        // Tạo frame
        JFrame frame = new JFrame("Thông Tin Sinh Viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Tạo bảng
        String[] columns = { "Mã", "Họ Tên", "Tuổi", "Nơi Sinh", "Điểm" };
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Kết nối đến cơ sở dữ liệu và lấy dữ liệu
        Connect_mysql connector = new Connect_mysql();
        Connection connection = connector.getConnection(DB_URL, USERNAME, PASSWORD);

        if (connection != null) {
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM your_table_name"); // Thay bằng tên bảng của bạn

                while (rs.next()) {
                    // Thêm dữ liệu vào bảng
                    Object[] row = {
                            rs.getInt("ma"), // Thay bằng tên cột của bạn
                            rs.getString("ho_ten"), // Thay bằng tên cột của bạn
                            rs.getInt("tuoi"), // Thay bằng tên cột của bạn
                            rs.getString("noi_sinh"), // Thay bằng tên cột của bạn
                            rs.getDouble("diem") // Thay bằng tên cột của bạn
                    };
                    model.addRow(row);
                }

                rs.close();
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Hiển thị frame
        frame.setVisible(true);
    }
}
