package controls;

import Inputter.Inputter;
import java.sql.*;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import models.Student;
import models.Teacher;

public class StudentManager {

    Connection conn;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    // Hàm login
    public Teacher login() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs;
            while (true) {
                String userName = Inputter.GetString("Enter thr username : ");
                String passWord = Inputter.GetString("Enter the password");
                String sql = "SELECT * FROM Teacher WHERE giaoVienID = '" + userName + "' AND matKhau = '" + passWord + "';";
                rs = st.executeQuery(sql);
                if (rs.next() == true) {
                    break;
                }
                System.out.println("Invalid Username Or Invalid Password!");
            }
            //
            Teacher t = new Teacher(rs.getInt("giaoVienId"),
                    rs.getString("hoTen"), rs.getString("diaChi"),
                    rs.getString("matKhau"), rs.getString("soDienThoai"),
                    rs.getString("monHoc"));
            return t;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Hàm logout
    public void logout(Teacher t) {
        System.out.println("Bạn đã đăng xuất !");
        t = login();
    }

    // Hàm tạo Account Admin
    public void createAdmin() {
        try {
            Statement st = conn.createStatement();
            int id = searchTeacher();
            if (id == -1) {
                System.out.println("ID không hợp lệ.");
                return;
            }
            String password = Inputter.GetString("Nhập password : ");
            String name = Inputter.GetString("Nhập họ và tên : ");
            String address = Inputter.GetString("Nhập địa chỉ : ");
            String phoneNumber = Inputter.getPhoneNumber("Nhập số điện thoại : ");
            String sql = "INSERT INTO Teacher "
                    + "(giaoVienID, hoTen, diaChi, matKhau, soDienThoai, monHoc, isADmin)"
                    + "VALUES "
                    + "(" + id + ", " + name + ", " + address + ", " + password + ", " + phoneNumber + ", NULL, " + 1 + ");";
            st.executeUpdate(sql);
            System.out.println("Tạo thành công.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createTeacher() {
        Statement st;
        try {
            st = conn.createStatement();
            int id = searchTeacher();
            if (id == -1) {
                System.out.println("ID không hợp lệ.");
                return;
            }
            String hoTen = Inputter.GetString("Nhập họ và tên giáo viên : ");
            String diaChi = Inputter.GetString("Nhập địa chỉ của giáo viên : ");
            String matKhau = Inputter.GetString("Nhập mật khẩu : ");
            String soDienThoai = Inputter.getPhoneNumber("Nhập số điện thoại : ");
            String monHoc = Inputter.GetString("Nhập môn học mà giáo viên dạy : ");
            int isADmin = Inputter.getBit("Có phải là admin không : ");
            String newSql = "INSERT INTO Teacher"
                    + " (giaoVienID, hoTen, diaChi, matKhau, soDienThoai, monHoc, isADmin) "
                    + "VALUES (" + id + ", '" + hoTen + "', '" + diaChi + "', '" + matKhau + "', '" + soDienThoai + "', '" + monHoc + "', " + isADmin + ");";
            st.executeUpdate(newSql);
            System.out.println("Tạo thành công.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void createStudent() {
        try {
            Statement st = conn.createStatement();
            int id = findStudent();
            if (id == -1) {
                System.out.println("Id đã tồn tại !");
                return;
            };
            String hoTen = Inputter.GetString("Nhập họ và tên sinh viên : ");
            String diaChi = Inputter.GetString("Nhập địa chỉ của sinh viên : ");
            String soDienThoai = Inputter.getPhoneNumber("Nhập số điện thoại : ");
            int giaoVienID = Inputter.GetInt("Nhập id của giáo viên : ");
            String newSql = "INSERT INTO Student"
                    + " (ID, hoVaTen, diaChi, soDienThoai, giaoVienID) "
                    + "VALUES (" + id + ", '" + hoTen + "', '" + diaChi + "', '" + soDienThoai + "', " + giaoVienID + ");";
            st.executeUpdate(newSql);
            System.out.println("Tạo thành công.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteStudent() {
        try {
            Statement st = conn.createStatement();
            Student s = search();
            if (s == null) {
                System.out.println("ID không hợp lệ.");
                return;
            }
            int id = s.getID();
            String sql = "DELETE Student WHERE ID = " + id + ";";
            st.executeUpdate(sql);
            System.out.println("Xóa thành công .");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteTeacher() {
        try {
            Statement st = conn.createStatement();
            Teacher t = findTeacher();
            if (t == null) {
                System.out.println("ID không hợp lệ.");
                return;
            }
            int id = t.getGiaoVienID();
            String sql = "DELETE Teacher WHERE giaoVienID = " + id + ";";
            st.executeUpdate(sql);
            System.out.println("Xóa thành công .");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editTeacher() {
        try {
            Statement st = conn.createStatement();
            Teacher t = findTeacher();
            if (t == null) {
                System.out.println("ID không hợp lệ.");
                return;
            }
            int id = t.getGiaoVienID();
            String hoTen = Inputter.GetString("Nhập họ và tên giáo viên : ");
            String diaChi = Inputter.GetString("Nhập địa chỉ của giáo viên : ");
            String matKhau = Inputter.GetString("Nhập mật khẩu : ");
            String soDienThoai = Inputter.getPhoneNumber("Nhập số điện thoại : ");
            String monHoc = Inputter.GetString("Nhập môn học mà giáo viên dạy : ");
            String isADmin = Inputter.GetString("Có phải là admin không : ");
            String sql = "UPDATE Teacher "
                    + "SET hoTen = '" + hoTen + "', "
                    + "diaChi = '" + diaChi + "', "
                    + "matKhau = '" + matKhau + "', "
                    + "soDienThoai = '" + soDienThoai + "', "
                    + "monHoc = '" + monHoc + "', "
                    + "isADmin = '" + isADmin + "';";
            st.executeUpdate(sql);
            System.out.println("Sửa thành công.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editStudent() {
        try {
            Statement st = conn.createStatement();
            Student s = search();

            if (s == null) {
                System.out.println("ID không hợp lệ.");
                return;
            }
            int id = s.getID();
            String hoTen = Inputter.GetString("Nhập họ và tên sinh viên : ");
            String diaChi = Inputter.GetString("Nhập địa chỉ của sinh viên : ");
            String soDienThoai = Inputter.getPhoneNumber("Nhập số điện thoại : ");
            int giaoVienID = Inputter.GetInt("Nhập id của giáo viên : ");
            String sql = "UPDATE Student "
                    + "SET hoVaTen = '" + hoTen + "', "
                    + "diaChi = '" + diaChi + "', "
                    + "soDienThoai = '" + soDienThoai + "', "
                    + "giaoVienID = " + giaoVienID + " WHERE ID = " + id + ";";
            st.executeUpdate(sql);
            System.out.println("Sửa thành công.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Student search() {
        System.out.println("1. tìm kiếm theo id sinh viên.");
        System.out.println("2. tìm kiếm theo họ và tên sinh viên.");
        System.out.println("3. tìm kiếm theo id giáo viên.");
        System.out.println("Nhấn số khác để thoát.");
        try {
            Statement st = conn.createStatement();
            int choice = Inputter.GetInt("Nhập lựa chọn : ");
            switch (choice) {
                case 1:
                    ResultSet rs1 = st.executeQuery("SELECT * FROM Student WHERE ID = " + Inputter.GetInt("Nhập ID sinh viên : ") + ";");
                    if (rs1.next() == false) {
                        return null;
                    }
                    System.out.println(rs1.getInt("ID") + " " + rs1.getString("hoVaTen") + " " + rs1.getString("diaChi") + " " + rs1.getString("soDienThoai") + " " + rs1.getInt("giaoVienID"));
                    Student st1 = new Student(rs1.getInt("ID"), rs1.getString("hoVaTen"), rs1.getString("diaChi"), rs1.getString("soDienThoai"), rs1.getInt("giaoVienID"));
                    return st1;
                case 2:
                    ResultSet rs2 = st.executeQuery("SELECT * FROM Student WHERE hoVaTen = '" + Inputter.GetString("Nhập họ và tên sinh viên : ") + "';");
                    Student st2 = null;
                    while (rs2.next()) {
                        System.out.println(rs2.getInt("ID") + " " + rs2.getString("hoVaTen") + " " + rs2.getString("diaChi") + " " + rs2.getString("soDienThoai") + " " + rs2.getInt("giaoVienID"));
                        st2 = new Student(rs2.getInt("ID"), rs2.getString("hoVaTen"), rs2.getString("diaChi"), rs2.getString("soDienThoai"), rs2.getInt("giaoVienID"));
                        return st2;
                    }

                case 3:
                    ResultSet rs3 = st.executeQuery("SELECT * FROM Student WHERE ID = " + Inputter.GetInt("Nhập id giáo viên : ") + ";");
                    Student st3 = null;
                    while (rs3.next()) {
                        System.out.println(rs3.getInt("ID") + " " + rs3.getString("hoVaTen") + " " + rs3.getString("diaChi") + " " + rs3.getString("soDienThoai") + " " + rs3.getInt("giaoVienID"));
                        st3 = new Student(rs3.getInt("ID"), rs3.getString("hoVaTen"), rs3.getString("diaChi"), rs3.getString("soDienThoai"), rs3.getInt("giaoVienID"));
                    }
                    return st3;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Teacher findTeacher() {
        int id = Inputter.GetInt("Nhập id : ");
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Teacher WHERE giaoVienID = " + id + ";");
            boolean b = rs.next();
            if (b == false) {
                return null;
            }
            Teacher t = new Teacher(rs.getInt("giaoVienID"), rs.getString("hoTen"), rs.getString("diaChi"), rs.getString("matKhau"), rs.getString("soDienThoai"), rs.getString("monHoc"));
            return t;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int findStudent() {
        try {
            Statement st = conn.createStatement();
            int id = Inputter.GetInt("Nhập ID sinh viên : ");
            ResultSet rs1 = st.executeQuery("SELECT * FROM Student WHERE ID = " + id + ";");
            if (rs1.next() == false) {
                return id;
            }
            System.out.println(rs1.getInt("ID") + " " + rs1.getString("hoVaTen") + " " + rs1.getString("diaChi") + " " + rs1.getString("soDienThoai") + " " + rs1.getInt("giaoVienID"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int searchTeacher() {
        int id = Inputter.GetInt("Nhập id : ");
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Teacher WHERE giaoVienID = " + id + ";");
            if (rs.next() == false) {
                return id;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException {
        StudentManager sm = new StudentManager();
        sm.conn = connectToSQLServer.Connect.getConnection();
        Statement st = sm.conn.createStatement();
        sm.createAdmin();
    }
}
