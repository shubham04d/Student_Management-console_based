package database_related;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import student_related.Student;

public class CRUD {

	private static Scanner sc;

	/////////////// Show Method ////////////////
	public static void show() {
		Connection connection = DatabaseConnection.connectToDb();
		ResultSet result = null;
		// fetch data from table
		String query = "select * from student;";
		try {
			Statement s = connection.createStatement();
			result = s.executeQuery(query);
			while (result.next()) {
				int id = result.getInt(1);
				String stud_name = result.getString(2);
				String classroom = result.getString(3);
				int age = result.getInt(4);
				System.out.println(id + "--" + stud_name + "--" + classroom + "--" + age);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/////////////// Insert Method ////////////////

	public static boolean insert(Student s) {
		boolean is_inserted = false;
		Connection connection = DatabaseConnection.connectToDb();
		String query = "insert into student(name,classroom,age) values(?,?,?);";
		try {
			PreparedStatement ps1 = connection.prepareStatement(query);
			ps1.setString(1, s.stud_name());
			ps1.setString(2, s.classroom());
			ps1.setInt(3, s.age());

			ps1.executeUpdate();
			is_inserted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is_inserted;
	}

	////////////// Update Method ////////////////

	public static boolean update(int id) {
		boolean is_updated = false;
		Connection connection = DatabaseConnection.connectToDb();
		String query = "select * from student where id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				System.out.println("ID exist");
				System.out.println("---- Enter New Data --");
				sc = new Scanner(System.in);
				System.out.print("Enter Student name -->");
				String stud_name = sc.next();

				System.out.print("Enter Student classroom -->");
				String classroom = sc.next();

				System.out.print("Enter Student age -->");
				int age = sc.nextInt();

				String update_query = "update student set name=?,classroom=?,age=? where id=?;";
				PreparedStatement ps2 = connection.prepareStatement(update_query);
				ps2.setString(1, stud_name);
				ps2.setString(2, classroom);
				ps2.setInt(3, age);
				ps2.setInt(4, id);
				ps2.executeUpdate();
				is_updated = true;
			} else {
				System.out.println("ID does not exist");
			}

			is_updated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is_updated;

	}

	////////////// Delete Method ////////////////

	public static boolean delete(int id) {
		boolean is_deleted = false;
		Connection connection = DatabaseConnection.connectToDb();
		String query = "select * from student where id=?;";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				System.out.println("ID exist");
				String delete_query = "delete from student where id=?;";
				PreparedStatement ps3 = connection.prepareStatement(delete_query);
				ps3.setInt(1, id);
				ps3.executeUpdate();
				is_deleted = true;
			} else {
				System.out.println("ID does not exist");
			}

			is_deleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is_deleted;
	}
}
