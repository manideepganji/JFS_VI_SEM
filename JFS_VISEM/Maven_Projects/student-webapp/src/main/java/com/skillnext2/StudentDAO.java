package com.skillnext2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentDAO {

    public static void save(Student st) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/skillnext2_db",
                    "root",
                    "manideep"
            );

            String sql = "INSERT INTO student(name, branch, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, st.getName());
            ps.setString(2, st.getBranch());
            ps.setString(3, st.getEmail());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace(); // DO NOT REMOVE
        }
    }
}
