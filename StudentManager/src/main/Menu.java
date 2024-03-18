package main;

import controls.StudentManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Teacher;

public class Menu {

    public void menu() {
        StudentManager sm = new StudentManager();
        sm.setConn(connectToSQLServer.Connect.getConnection());
        try {
            Statement st = sm.getConn().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Menu;");
            ArrayList<String> list = new ArrayList<>();
            while (rs.next()) {
                String s = rs.getInt("ID") +". "+ rs.getString("Infor");
                list.add(s);
            }
            int choice;
            Teacher t = sm.login();
            ResultSet rs1 = st.executeQuery("SELECT isADmin FROM Teacher WHERE giaoVienID = " + t.getGiaoVienID() + ";");
            rs1.next();
            if (rs1.getString("isADmin").equalsIgnoreCase("yes")) {
                do {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(list.get(i));
                    }
                    System.out.println("Nhấn số khác để thoát");
                    choice = Inputter.Inputter.GetInt("Nhập lựa chọn của bạn : ");
                    switch (choice) {
                        case 1:
                            System.out.println(list.get(0));
                            sm.createStudent();
                            break;
                        case 2:
                            System.out.println(list.get(1));
                            sm.editStudent();
                            break;
                        case 3:
                            System.out.println(list.get(2));
                            sm.deleteStudent();
                            break;
                        case 4:
                            System.out.println(list.get(3));
                            sm.search();
                            break;
                        case 5:
                            System.out.println(list.get(4));
                            sm.createTeacher();
                            
                            break;
                        case 6:
                            System.out.println(list.get(5));
                            sm.editTeacher();
                            
                            break;
                        case 7:
                            System.out.println(list.get(6));
                            sm.deleteTeacher();
                            
                            break;
                        case 8:
                            System.out.println(list.get(7));
                            sm.logout(t);
                            
                            break;
                        case 9:
                            System.out.println(list.get(8));
                            sm.createAdmin();
                            break;
                        
                    }
                } while (choice > 0 && choice <= list.size());
            } else {
                do {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i++ + ". " + list.get(i));
                    }
                    System.out.println("Nhấn khác để thoát");
                    choice = Inputter.Inputter.GetInt("Nhập lựa chọn của bạn : ");
                    switch (choice) {
                        case 1:
                            System.out.println(list.get(0));
                            sm.createStudent();
                            break;
                        case 2:
                            System.out.println(list.get(1));
                            sm.editStudent();
                            break;
                        case 3:
                            System.out.println(list.get(2));
                            sm.deleteStudent();
                            break;
                        case 4 :
                            System.out.println(list.get(3));
                            sm.search();
                            break;
                        
                    }
                } while (choice > 0 && choice < 3);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        Menu m = new Menu();
        m.menu();
    }
}
