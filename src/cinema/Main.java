package cinema;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
	 static Scanner scan = new Scanner(System.in);
     static DataBaseConnection db = new DataBaseConnection("jdbc:mysql://localhost:3306/cinema", "root", "");
     static CSVReader reader = new CSVReader(db);
     
public static void main(String[] args) throws SQLException {
	Menu menu = new Menu(db);
	menu.start();
//db.insertCinema("DownTown", "Zabugoskoje 1", 500);
//db.updateCinema(32, "DownTown", "Zabugoskoje 1", 500);

//db.getAllCinemas();
//reader.readCinemasFromCsv("C:\\Users\\Geradine\\eclipse-workspace\\CinemaUBP\\src\\testCSV\\cinema.csv");
//	
//db.showTable("cinema");
//db.showTable("cinema");
}
}
