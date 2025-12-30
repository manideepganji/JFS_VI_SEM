package com.skillnext2;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean choice = true;

        while (choice) {

            System.out.println("\n===== STUDENT CRUD MENU =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Retrieve Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();
            sc.nextLine(); // clear buffer

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            switch (ch) {

                // INSERT
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter branch: ");
                    String branch = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    Student s = new Student(name, branch, email);
                    session.persist(s);
                    tx.commit();

                    System.out.println("Student inserted successfully!");
                    break;

                // RETRIEVE
                case 2:
                    System.out.print("Enter student ID: ");
                    int rid = sc.nextInt();

                    Student rs = session.get(Student.class, rid);
                    if (rs != null) {
                        System.out.println("Student Details:");
                        System.out.println(rs);
                    } else {
                        System.out.println("Student not found!");
                    }
                    tx.commit();
                    break;

                // UPDATE
                case 3:
                    System.out.print("Enter student ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    Student us = session.get(Student.class, uid);
                    if (us != null) {
                        System.out.print("Enter new branch: ");
                        us.setBranch(sc.nextLine());

                        System.out.print("Enter new email: ");
                        us.setEmail(sc.nextLine());

                        session.update(us);
                        System.out.println("Student updated successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    tx.commit();
                    break;

                // DELETE
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int did = sc.nextInt();

                    Student ds = session.get(Student.class, did);
                    if (ds != null) {
                        session.delete(ds);
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    tx.commit();
                    break;

                // EXIT
                case 5:
                    choice = false;
                    tx.commit();
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    tx.commit();
            }

            session.close();
        }

        HibernateUtil.getSessionFactory().close();
        sc.close();
    }
}
