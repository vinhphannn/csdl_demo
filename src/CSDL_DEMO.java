import java.sql.Connection;
import java.sql.SQLException;

public class CSDL_DEMO {
    public static void main(String[] args) {
        // Thông tin kết nối
        String dbURL = "jdbc:mysql://localhost:3306/csdldk"; // Thay thế bằng tên cơ sở dữ liệu của bạn
        String userName = "root"; // Tên đăng nhập MySQL
        String password = ""; // Mật khẩu MySQL

        // Tạo đối tượng Connect_mysql
        Connect_mysql connector = new Connect_mysql();

        // Kết nối đến cơ sở dữ liệu
        Connection connection = connector.getConnection(dbURL, userName, password);

        // Thực hiện các thao tác với cơ sở dữ liệu ở đây...

        // Đóng kết nối khi không cần thiết nữa
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
