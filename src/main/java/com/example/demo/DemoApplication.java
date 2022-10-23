package com.example.demo;

import static com.example.demo.Count.calcDouble;
import static com.example.demo.CheckSign.parsingS;
import static com.example.demo.CheckBrackets.parsingB;
import static com.example.demo.Count.calcInt;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Double or Int");
			String or = keyboard.nextLine();
			if(!or.equals("Double") && !or.equals("Int")){
				System.out.println("Unknown type");
				System.exit(0);
			}
		System.out.println("Enter your expression");
		String str = keyboard.nextLine();
		if(!parsingB(str)){
			System.out.println("Error, brackets are not placed correctly");
			System.exit(0);
		}
		if(!parsingS(str)){
			System.out.println("Error, signs are not placed correctly");
			System.exit(0);
		}

		try{
			String url = "jdbc:mysql://localhost/Demo";
			String username = "root";
			String password = "root";
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

			try (Connection conn = DriverManager.getConnection(url, username, password)) {

				Statement statement = conn.createStatement();
				if(or.equals("Double")){
					String Add = "INSERT INTO Demo(Expression,Answer) VALUES ('"+str+"','"+ calcDouble(str) +"')";
					statement.executeUpdate(Add);
				}
				if(or.equals("Int")){
					String Add = "INSERT INTO Demo(Expression,Answer) VALUES ('"+str+"','"+ calcInt(str) +"')";
					statement.executeUpdate(Add);
				}

				System.out.println("Addition to the database has been completed");
				System.out.println("Change expression? y/n");
					String k = keyboard.nextLine();
					if (k.equals("y")) {
						System.out.println("Enter your expression");
						String newStr = keyboard.nextLine();
						if (!parsingB(newStr)) {
							System.out.println("Error, brackets are not placed correctly");
							System.exit(0);
						}
						if (!parsingS(newStr)) {
							System.out.println("Error, signs are not placed correctly");
							System.exit(0);
						}
						System.out.println("Which id do you want to change?");
						int newId = keyboard.nextInt();
						if (or.equals("Double")) {
							String Update = "UPDATE Demo" + " SET Expression = ('" + newStr + "'),Answer = ('" + calcDouble(newStr) + "') where Id = ('" + newId + "') ";
							statement.executeUpdate(Update);
						}
						if (or.equals("Int")) {
							String Update = "UPDATE Demo" + " SET Expression = ('" + newStr + "'),Answer = ('" + calcInt(newStr) + "') where Id = ('" + newId + "') ";
							statement.executeUpdate(Update);
						}
						System.out.println("The database has been updated");
					}
						System.out.println("Show db? y/n");
						String j = keyboard.nextLine();
						if (j.equals("y")) {
							ResultSet resultSet = statement.executeQuery("SELECT * FROM Demo");
							while (resultSet.next()) {

								int id = resultSet.getInt(1);
								String Expression = resultSet.getString(2);
								String Answer = resultSet.getString(3);
								System.out.println("  ---------------------------------------------");
								System.out.println(" | " + id + " | " + Expression + " | " + Answer + " | ");
							}
						}
			}
		}
		catch(Exception ex){
			System.out.println("Connection failed...");
			System.out.println(ex);
		}
	}

}
