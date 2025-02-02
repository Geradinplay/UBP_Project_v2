package cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBaseConnection {
	private String url;
    private String user;
    private String pass;//password
    private Scanner scan;

    public DataBaseConnection(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.scan=new Scanner(System.in);
    }
	// Запрос базы данных
	public Connection getNewConnection() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			System.out.println("Connection error!");
			// e.printStackTrace();
			return null;
		}
	}
	// Запрос соединения
	public boolean closeConnection(Connection c) {
		try {
			c.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error closing connection!");
			return false;
		}
	}
	
	public boolean insertHeadquarters(String name) {
	    String sql = "INSERT INTO headquarters (name) VALUES (?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, name);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Headquarters> showAllHeadquarters() {
	    String sql = "SELECT * FROM headquarters";
	    List<Headquarters> headquartersList = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Headquarters hq = new Headquarters(
	                rs.getInt("headquarters_id"),
	                rs.getString("name")
	            );
	            headquartersList.add(hq);
	        }
	     // Вывод содержимого списка Headquarters в консоль
	        for (Headquarters h : headquartersList) {
	            System.out.println("id="+h.getHeadquartersId()+", name="+h.getName());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return headquartersList;
	}
	public List<Headquarters> getAllHeadquarters() {
	    String sql = "SELECT * FROM headquarters";
	    List<Headquarters> headquartersList = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Headquarters hq = new Headquarters(
	                rs.getInt("headquarters_id"),
	                rs.getString("name")
	            );
	            headquartersList.add(hq);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return headquartersList;
	}
	public boolean updateHeadquarters(int headquartersId, String newName) {
	    String sql = "UPDATE headquarters SET name = ? WHERE headquarters_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, newName);
	        stmt.setInt(2, headquartersId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Headquarters> findHeadquartersByName(String name){
		  List<Headquarters> headquarters = new ArrayList<>();
		    String sql = "SELECT * FROM headquarters WHERE name = ?";

		    try (Connection conn = getNewConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, name);
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		            Headquarters headquarter = new Headquarters(
		                rs.getInt("headquarters_id"),
		                rs.getString("name")
		            );
		            headquarters.add(headquarter);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return headquarters;
	}
	public boolean deleteHeadquarters(int headquartersId) {
	    String sql = "DELETE FROM headquarters WHERE headquarters_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, headquartersId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertCinema(String name, String address, int capacity) {
	    String sql = "INSERT INTO cinema (name, address, capacity) VALUES (?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, name);
	        stmt.setString(2, address);
	        stmt.setInt(3, capacity);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Cinema> getAllCinemas() {
	    String sql = "SELECT * FROM cinema";
	    List<Cinema> cinemas = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Cinema cinema = new Cinema(
	                rs.getInt("cinema_id"),
	                rs.getString("name"),
	                rs.getString("address"),
	                rs.getInt("capacity")
	            );
	            cinemas.add(cinema);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cinemas;
	}
	public List<Cinema> showAllCinemas() {
	    String sql = "SELECT * FROM cinema";
	    List<Cinema> cinemas = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Cinema cinema = new Cinema(
	                rs.getInt("cinema_id"),
	                rs.getString("name"),
	                rs.getString("address"),
	                rs.getInt("capacity")
	            );
	            cinemas.add(cinema);
	        }
	        // Вывод содержимого списка cinemas в консоль
	        for (Cinema cinema : cinemas) {
	            System.out.println(cinema);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cinemas;
	}
	public boolean updateCinema(int cinemaId, String newName, String newAddress, int newCapacity) {
	    String sql = "UPDATE cinema SET name = ?, address = ?, capacity = ? WHERE cinema_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, newName);
	        stmt.setString(2, newAddress);
	        stmt.setInt(3, newCapacity);
	        stmt.setInt(4, cinemaId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean updateCinemaByName(String name,String newName, String newAddress, int newCapacity) {
	    String sql = "UPDATE cinema SET name = ?, address = ?, capacity = ? WHERE cinema_id = ?";
	    List<Cinema>cinemas= findCinemaByName(name);
	    int idSc=0;
	    if (cinemas.isEmpty()) {
	        System.out.println("Error: cinema not found");
	        return false;
	    } 
	    switch (cinemas.size()) {
	     case 1:
	             idSc=cinemas.get(0).getCinemaId();   
	         break;
	     default:
	      for (Cinema c : cinemas) {
	        System.out.println(c);
	      }
	      System.out.println("Select your cinema: ");
	        idSc = scan.nextInt();
	        break;
	    }
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, newName);
	        stmt.setString(2, newAddress);
	        stmt.setInt(3, newCapacity);
	        stmt.setInt(4, idSc);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Cinema> findCinemaByName(String name) {
	    List<Cinema> cinemas = new ArrayList<>();
	    String sql = "SELECT * FROM cinema WHERE name = ?";

	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, name);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Cinema cinema = new Cinema(
	                rs.getInt("cinema_id"),
	                rs.getString("name"),
	                rs.getString("address"),
	                rs.getInt("capacity")
	            );
	            cinemas.add(cinema);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cinemas;
	}

	public boolean deleteCinema(int id) {
	    String sql = "DELETE FROM cinema WHERE cinema_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertFilm(String title, String genre, int duration, String director, int studioId) {
	    String sql = "INSERT INTO film (title, genre, duration, director, studio_id) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, title);
	        stmt.setString(2, genre);
	        stmt.setInt(3, duration);
	        stmt.setString(4, director);
	        stmt.setInt(5, studioId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Film> getAllFilms() {
	    String sql = "SELECT * FROM film";
	    List<Film> films = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Film film = new Film(
	                rs.getInt("film_id"),
	                rs.getString("title"),
	                rs.getString("genre"),
	                rs.getInt("duration"),
	                rs.getString("director"),
	                rs.getInt("studio_id")
	            );
	            films.add(film);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return films;
	}
	public List<Film> showAllFilms() {
	    String sql = "SELECT * FROM film";
	    List<Film> films = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Film film = new Film(
	                rs.getInt("film_id"),
	                rs.getString("title"),
	                rs.getString("genre"),
	                rs.getInt("duration"),
	                rs.getString("director"),
	                rs.getInt("studio_id")
	            );
	            films.add(film);
	        }
	     // Вывод содержимого списка films в консоль
	        for (Film film: films) {
	            System.out.print(film);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return films;
	}
	public boolean updateFilm(int filmId, String newTitle, String newGenre, int newDuration, String newDirector, int newStudioId) {
	    String sql = "UPDATE film SET title = ?, genre = ?, duration = ?, director = ?, studio_id = ? WHERE film_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, newTitle);
	        stmt.setString(2, newGenre);
	        stmt.setInt(3, newDuration);
	        stmt.setString(4, newDirector);
	        stmt.setInt(5, newStudioId);
	        stmt.setInt(6, filmId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean deleteFilm(int filmId) {
	    String sql = "DELETE FROM film WHERE film_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, filmId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertHall(int hallNumber, int cinemaId, int capacity) {
	    String sql = "INSERT INTO hall (hall_number, cinema_id, capacity) VALUES (?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, hallNumber);
	        stmt.setInt(2, cinemaId);
	        stmt.setInt(3, capacity);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Hall> getAllHalls() {
	    String sql = "SELECT * FROM hall";
	    List<Hall> halls = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Hall hall = new Hall(
	                rs.getInt("hall_id"),
	                rs.getInt("hall_number"),
	                rs.getInt("cinema_id"),
	                rs.getInt("capacity")
	            );
	            halls.add(hall);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return halls;
	}
	public List<Hall> showAllHalls() {
	    String sql = "SELECT * FROM hall";
	    List<Hall> halls = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Hall hall = new Hall(
	                rs.getInt("hall_id"),
	                rs.getInt("hall_number"),
	                rs.getInt("cinema_id"),
	                rs.getInt("capacity")
	            );
	            halls.add(hall);
	        }
	     // Вывод содержимого списка halls в консоль
	        for (Hall hall: halls) {
	            System.out.println(hall);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return halls;
	}

	public List<Hall> showAllHallsWithCinemas() {
	    String sql = "SELECT h.hall_id, h.hall_number, h.capacity, c.name AS cinema_name, c.cinema_id " +
	                 "FROM hall h " +
	                 "JOIN cinema c ON h.cinema_id = c.cinema_id";
	    
	    List<Hall> halls = new ArrayList<>();

	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        
	        while (rs.next()) {
	            int hallId = rs.getInt("hall_id");
	            int hallNumber = rs.getInt("hall_number");
	            int hallCapacity = rs.getInt("capacity");
	            String cinemaName = rs.getString("cinema_name"); 
	            int cinemaId=rs.getInt("cinema_id");
	            System.out.println(
	            		"Hall ID=" + hallId +
                        ", Number=" + hallNumber +
                        ", Capacity=" + hallCapacity +
                        ", Cinema ID=" + cinemaId +
                        ", Cinema Name=" + cinemaName
                        );

	            Hall hall = new Hall(hallId, hallNumber, cinemaId,hallCapacity);
	            halls.add(hall);
	        }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return halls;
	}

	
	public boolean updateHall(int hallId, int newHallNumber, int newCinemaId, int newCapacity) {
	    String sql = "UPDATE hall SET hall_number = ?, cinema_id = ?, capacity = ? WHERE hall_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, newHallNumber);
	        stmt.setInt(2, newCinemaId);
	        stmt.setInt(3, newCapacity);
	        stmt.setInt(4, hallId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean deleteHall(int hallId) {
	    String sql = "DELETE FROM hall WHERE hall_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, hallId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertProjection(int filmId, int hallId, String date, String startTime) {
	    String sql = "INSERT INTO projection (film_id, hall_id, date, start_time) VALUES (?, ?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, filmId);
	        stmt.setInt(2, hallId);
	        stmt.setString(3, date);
	        stmt.setString(4, startTime);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Projection> getAllProjections() {
	    String sql = "SELECT * FROM projection";
	    List<Projection> projections = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Projection projection = new Projection(
	                rs.getInt("projection_id"),
	                rs.getInt("film_id"),
	                rs.getInt("hall_id"),
	                rs.getString("date"),
	                rs.getString("start_time")
	            );
	            projections.add(projection);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return projections;
	}
	public List<Projection> showAllProjections() {
	    String sql = "SELECT * FROM projection";
	    List<Projection> projections = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Projection projection = new Projection(
	                rs.getInt("projection_id"),
	                rs.getInt("film_id"),
	                rs.getInt("hall_id"),
	                rs.getString("date"),
	                rs.getString("start_time")
	            );
	            projections.add(projection);
	        }
	     // Вывод содержимого списка projections в консоль
	        for (Projection projection : projections) {
	            System.out.print(projection);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return projections;
	}
	public List<Projection> showAllProjectionsWithFilmName() {
	    String sql = "SELECT p.projection_id, f.title, f.genre, p.date, p.start_time, h.hall_id, f.film_id\r\n"
	    		+ "FROM projection p, film f, hall h\r\n"
	    		+ "WHERE p.film_id=f.film_id\r\n"
	    		+ "AND h.hall_id=p.hall_id;";
	    List<Projection> projections = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Projection projection = new Projection(
	                rs.getInt("projection_id"),
	                rs.getInt("film_id"),
	                rs.getInt("hall_id"),
	                rs.getString("date"),
	                rs.getString("start_time")
	            );
	            System.out.println("id:"+rs.getInt("projection_id")
	            +", title="+rs.getString("title")
	            +", genre="+rs.getString("genre")
	            +", date="+rs.getDate("date")
	            +", time="+rs.getTime("start_time"));
	            projections.add(projection);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return projections;
	}
	public boolean updateProjection(int projectionId, int newFilmId, int newHallId, String newDate, String newStartTime) {
	    String sql = "UPDATE projection SET film_id = ?, hall_id = ?, date = ?, start_time = ? WHERE projection_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, newFilmId);
	        stmt.setInt(2, newHallId);
	        stmt.setString(3, newDate);
	        stmt.setString(4, newStartTime);
	        stmt.setInt(5, projectionId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean deleteProjection(int projectionId) {
	    String sql = "DELETE FROM projection WHERE projection_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, projectionId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertTicket( int projectionId, String purchaseDate, int type, int customerId) {
	    String sql = "INSERT INTO ticket (projection_id, purchase_date, type_id, customer_id) VALUES (?, ?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, projectionId);
	        stmt.setString(2, purchaseDate);
	        stmt.setInt(3, type);
	        stmt.setInt(4, customerId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Ticket> getAllTickets() {
	    String sql = "SELECT * FROM ticket";
	    List<Ticket> tickets = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Ticket ticket = new Ticket(
	                rs.getInt("ticket_id"),
	                rs.getInt("projection_id"),
	                rs.getString("purchase_date"),
	                rs.getInt("type_id"),
	                rs.getInt("customer_id")
	            );
	            tickets.add(ticket);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tickets;
	}	
	public List<Ticket> showAllTickets() {
	    String sql = "SELECT * FROM ticket";
	    List<Ticket> tickets = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Ticket ticket = new Ticket(
	                rs.getInt("ticket_id"),
	                rs.getInt("projection_id"),
	                rs.getString("purchase_date"),
	                rs.getInt("type_id"),
	                rs.getInt("customer_id")
	            );
	            tickets.add(ticket);
	        }
	     // Вывод содержимого списка tickets в консоль
	        for (Ticket ticket: tickets) {
	            System.out.println(ticket);
	        }
	        System.out.println();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tickets;
	}	
	public boolean updateTicket(int ticketId, int newProjectionId, String newPurchaseDate, int newType, int newCustomerId) {
	    String sql = "UPDATE ticket SET projection_id = ?, purchase_date = ?, type = ?, customer_id = ? WHERE ticket_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, newProjectionId);
	        stmt.setString(2, newPurchaseDate);
	        stmt.setInt(3, newType);
	        stmt.setInt(4, newCustomerId);
	        stmt.setInt(5, ticketId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean deleteTicket(int ticketId) {
	    String sql = "DELETE FROM ticket WHERE ticket_id = ?";
	    try (Connection conn = getNewConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, ticketId);

	        int rowsDeleted = stmt.executeUpdate();
	        return rowsDeleted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertCustomer(String name, int bonus_points, int loyaltyProgramId) {
	    String sql = "INSERT INTO customer (name, bonus_points, loyalty_program_id) VALUES (?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, name);
	        stmt.setInt(2, bonus_points);
	        stmt.setInt(3, loyaltyProgramId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Customer> showAllCustomers() {
	    String sql = "SELECT * FROM customer";
	    List<Customer> customers = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Customer customer = new Customer(
	                rs.getInt("customer_id"),
	                rs.getString("name"),
	                rs.getInt("bonus_points"),
	                rs.getInt("loyalty_program_id")
	            );
	            customers.add(customer);
	        }
	     // Вывод содержимого списка custoners в консоль
	        for (Customer customer: customers) {
	            System.out.println(customer);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return customers;
	}
	public List<Customer> getAllCustomers() {
	    String sql = "SELECT * FROM customer";
	    List<Customer> customers = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Customer customer = new Customer(
	                rs.getInt("customer_id"),
	                rs.getString("name"),
	                rs.getInt("bonus_points"),
	                rs.getInt("loyalty_program_id")
	            );
	            customers.add(customer);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return customers;
	}
	public boolean updateCustomer(int customerId, String newName,int newRewardPoints, int newLoyaltyProgramId) {
	    String sql = "UPDATE customer SET name = ?, bonus_points = ?, loyalty_program_id = ? WHERE customer_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, newName);
	        stmt.setInt(2, newRewardPoints);
	        stmt.setInt(3, newLoyaltyProgramId);
	        stmt.setInt(4, customerId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean deleteCustomer(int customerId) {
	    String sql = "DELETE FROM customer WHERE customer_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, customerId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertLoyaltyProgram(int bonusPoints, double discount) {
	    String sql = "INSERT INTO loyaltyprogram (bonus_points, discount) VALUES (?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, bonusPoints);
	        stmt.setDouble(2, discount);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<LoyaltyProgram> getAllLoyaltyPrograms() {
	    String sql = "SELECT * FROM loyaltyprogram";
	    List<LoyaltyProgram> programs = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            LoyaltyProgram program = new LoyaltyProgram(
	                rs.getInt("loyalty_program_id"),
	                rs.getInt("bonus_points"),
	                rs.getDouble("discount")
	            );
	            programs.add(program);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return programs;
	}
	public List<LoyaltyProgram> showAllLoyaltyPrograms() {
	    String sql = "SELECT * FROM loyaltyprogram";
	    List<LoyaltyProgram> programs = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            LoyaltyProgram program = new LoyaltyProgram(
	                rs.getInt("loyalty_program_id"),
	                rs.getInt("bonus_points"),
	                rs.getDouble("discount")
	            );
	            programs.add(program);
	        }
	     // Вывод содержимого списка loyaltyProgram в консоль
	        for (LoyaltyProgram loyaltyProgram: programs) {
	            System.out.println(loyaltyProgram);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return programs;
	}
	public boolean updateLoyaltyProgram(int programId, int newBonusPoints, double newDiscount) {
	    String sql = "UPDATE loyaltyprogram SET bonus_points = ?, discount = ? WHERE loyalty_program_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, newBonusPoints);
	        stmt.setDouble(2, newDiscount);
	        stmt.setInt(3, programId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean deleteLoyaltyProgram(int programId) {
	    String sql = "DELETE FROM loyaltyprogram WHERE loyalty_program_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, programId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertSpecialEvent(String name, String date, String specialPrices) {
	    String sql = "INSERT INTO specialevent (name, date, special_prices) VALUES (?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, name);
	        stmt.setString(2, date);
	        stmt.setString(3, specialPrices);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	        return false;
	    }
	}
	public List<SpecialEvent> getAllSpecialEvents() {
	    String sql = "SELECT * FROM specialevent";
	    List<SpecialEvent> events = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            SpecialEvent event = new SpecialEvent(
	                rs.getInt("event_id"),
	                rs.getString("name"),
	                rs.getString("date"),
	                rs.getDouble("special_prices")
	            );
	            events.add(event);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return events;
	}
	public List<SpecialEvent> showAllSpecialEvents() {
	    String sql = "SELECT * FROM specialevent";
	    List<SpecialEvent> events = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            SpecialEvent event = new SpecialEvent(
	                rs.getInt("event_id"),
	                rs.getString("name"),
	                rs.getString("date"),
	                rs.getDouble("special_prices")
	            );
	            events.add(event);
	        }
	     // Вывод содержимого списка SpecialEvent в консоль
	        for (SpecialEvent sp: events) {
	            System.out.println(sp);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return events;
	}
	public boolean updateSpecialEvent(int eventId, String newName, String newDate, double newSpecialPrices) {
	    String sql = "UPDATE specialevent SET name = ?, date = ?, special_prices = ? WHERE event_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, newName);
	        stmt.setString(2, newDate);
	        stmt.setDouble(3, newSpecialPrices);
	        stmt.setInt(4, eventId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<SpecialEvent>findSpecialEventByName(String name){
		String sql = "SELECT * FROM specialevent WHERE name = ?"; 
	    List<SpecialEvent> events = new ArrayList<>();
	    
	    try (Connection conn = getNewConnection(); 
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, name);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            SpecialEvent event = new SpecialEvent(
	                rs.getInt("event_id"),
	                rs.getString("name"),
	                String.valueOf(rs.getDate("date")),
	                rs.getDouble("special_prices")
	            );
	            events.add(event);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return events;
	}
	public boolean deleteSpecialEvent(int eventId) {
	    String sql = "DELETE FROM specialevent WHERE event_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, eventId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean insertEventFilm(int eventId, int filmId) {
	    String sql = "INSERT INTO eventfilms (event_id, film_id) VALUES (?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, eventId);
	        stmt.setInt(2, filmId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<EventFilms> getAllEventFilms() {
	    String sql = "SELECT * FROM eventfilms";
	    List<EventFilms> eventFilmsList = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            EventFilms eventFilm = new EventFilms(
	                rs.getInt("event_id"),
	                rs.getInt("film_id")
	            );
	            eventFilmsList.add(eventFilm);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return eventFilmsList;
	}
    public boolean updateEventFilm(int oldEventId, int oldFilmId, int newEventId, int newFilmId) {
	    String sql = "UPDATE event_films SET event_id = ?, film_id = ? WHERE event_id = ? AND film_id = ?";
	    try (Connection conn = getNewConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, newEventId); // Новый event_id
	        stmt.setInt(2, newFilmId);  // Новый film_id
	        stmt.setInt(3, oldEventId); // Старый event_id
	        stmt.setInt(4, oldFilmId);  // Старый film_id

	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
    public boolean deleteEventFilm(int eventId, int filmId) {
    String sql = "DELETE FROM eventfilms WHERE event_id = ? AND film_id = ?";
    try (Connection conn = getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, eventId);
        stmt.setInt(2, filmId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
	public String showAllFilmsWithEvent() {
	    String sql = "SELECT f.film_id, s.event_id, f.title, s.name, s.date, s.special_prices\r\n"
	    		+ "FROM eventfilms e, specialevent s, film f\r\n"
	    		+ "WHERE e.event_id=s.event_id\r\n"
	    		+ "AND e.film_id=f.film_id";
	    String temp ="";
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            EventFilms eventFilm = new EventFilms(
	                rs.getInt("event_id"),
	                rs.getInt("film_id")
	            );
	            temp+="film_id="+rs.getInt("film_id")+", event_id="+rs.getInt("event_id")+", "
	            		+ "title="+rs.getString("title")+", name="+rs.getString("name")
	            		+", date="+ rs.getDate("date")+", special_prices="+rs.getString("special_prices")+"\n";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return temp;
	}

	public boolean insertStudio(String name, int headquartersId) {
	    String sql = "INSERT INTO studio (name, headquarters_id) VALUES (?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, name);
	        stmt.setInt(2, headquartersId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Studio> getAllStudios() {
	    String sql = "SELECT * FROM studio";
	    List<Studio> studios = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Studio studio = new Studio(
	                rs.getInt("studio_id"),
	                rs.getString("name"),
	                rs.getInt("headquarters_id")
	            );
	            studios.add(studio);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return studios;
	}
	public List<Studio> showAllStudiosDemo() {
	    String sql = "SELECT * FROM studio";
	    List<Studio> studios = new ArrayList<>();
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            Studio studio = new Studio(
	                rs.getInt("studio_id"),
	                rs.getString("name"),
	                rs.getInt("headquarters_id")
	            );
	            studios.add(studio);
	        }
	     // Вывод содержимого списка Headquarters в консоль
	        for (Studio s : studios) {
	            System.out.println(s);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return studios;
	}
	public boolean updateStudio(int studioId, String newName, int newHeadquartersId) {
	    String sql = "UPDATE studio SET name = ?, headquarters_id = ? WHERE studio_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, newName);
	        stmt.setInt(2, newHeadquartersId);
	        stmt.setInt(3, studioId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public List<Studio> findStudioByName(String name){
		 List<Studio> studios = new ArrayList<>();
		    String sql = "SELECT * FROM studio WHERE name = ?";

		    try (Connection conn = getNewConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, name);
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		            Studio studio = new Studio(
		                rs.getInt("studio_id"),
		                rs.getString("name"),
		                rs.getInt("headquarters_id")
		            );
		            studios.add(studio);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return studios;
	}

	public boolean deleteStudio(int studioId) {
	    String sql = "DELETE FROM studio WHERE studio_id = ?";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, studioId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public List<List<String>> showTable(String tableName) {
		List<List<String>> rows = new ArrayList<>();

		String sql = "SELECT * FROM " + tableName;
		
		try {
			Connection conn = getNewConnection(); 
			Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql); 
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount(); 

			for (int i = 1; i <= columnCount; i++) {
			    String columnName = metaData.getColumnName(i); 
			    System.out.print("-("+columnName+")-");
			}
			System.out.println();
			while (rs.next()) {
				List<String> row = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++) {
					String value = rs.getString(i);
					row.add(value);
				}
				rows.add(row);
				for (String list: row) {
		            System.out.print('"'+list+'"'+" ");
		        }
				System.out.println();
			}
			 
		} catch (SQLException e) {
			e.printStackTrace(); 
		}

		return rows;
	}
	public List<String> showAllTableNames() {
        List<String> tableNames = new ArrayList<>();
        
        try (Connection conn = getNewConnection();
        	 Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES;")) {
        	System.out.println("-----List tabla-----");
            while (rs.next()) {
                tableNames.add(rs.getString(1));
                System.out.println("-"+rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableNames;
    }
	public List<String> getAllTableNames() {
        List<String> tableNames = new ArrayList<>();
        
        try (Connection conn = getNewConnection();
        	 Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES;")) {
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableNames;
    }

	public Customer findCustomerById(int id) {
		String sql = "SELECT *\r\n"
				+ "FROM customer;";
		try {
			Connection conn = getNewConnection(); 
			Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(sql); 
			rs.next();
			Customer customer = new Customer(
					rs.getInt("customer_id"), 
					rs.getString("name"), 
					rs.getInt("loyalty_program_id"), 
					rs.getInt("bonus_points")
					);
			 return customer;
		} catch (SQLException e) {
			e.printStackTrace(); 
			return null;
		}
	}
	public int insertCustomerReturnId(String name, int bonus_points, int loyaltyProgramId) {
	    String sql = "INSERT INTO customer (name, bonus_points, loyalty_program_id) VALUES (?, ?, ?)";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, name);
	        stmt.setInt(2, bonus_points);
	        stmt.setInt(3, loyaltyProgramId);
	         int updated=stmt.executeUpdate();
	         if(updated>0) {
	        	 ResultSet keys = stmt.getGeneratedKeys();
	        	 if (keys.next()) {
	                    return keys.getInt(1); // Cгенерированный ключ 
	                }
	         }
	         return -1;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }
	}

       
	public List<String> showFilmsGrouped() {
		  String sql = "SELECT title\r\n"
		  		+ "FROM film\r\n"
		  		+ "group by film.title;";
		     List<String> list = new ArrayList<>();
		    try (Connection conn = getNewConnection();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {
		        while (rs.next()) {   
		           list.add(rs.getString("title"));
		        }
		        // Вывод содержимого списка films сгруппировано в консоль
		        int count=0;
		        for (String st : list) {
		        	count++;
		            System.out.println(count+") "+st);
		        }
		        return list;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return list;
	}
	public List<Projection> findProjectionsWithSelectedFilm(String film) {
		String sql="SELECT projection_id, p.film_id, hall_id, date, start_time\r\n"
				+ "FROM projection p, film f\r\n"
				+ "WHERE p.film_id=f.film_id\r\n"
				+ "AND f.title="+"'"+film+"'";
		List<Projection> list = new ArrayList<>();
	    try (Connection conn = getNewConnection();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {
		        while (rs.next()) {   	
		         Projection projection = new Projection(
		        		 rs.getInt("projection_id"), 
		        		 rs.getInt("film_id"), 
		        		 rs.getInt("hall_id"), 
		        		 String.valueOf(rs.getDate("date")), 
		        		 String.valueOf(rs.getTime("start_time")));
		         list.add(projection);
		        }
		        // Вывод содержимого списка Projections сгруппировано в консоль
		        int count=0;
		        for (Projection l: list) {
		        	count++;
		        	System.out.print(count+") "+l.getDate()+" "+l.getStartTime()+"\n");
		        }
		        return list;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return list;
	}
	public List<Type> getAllTypes(){
		String sql ="SELECT *\r\n"
				+ "FROM type";
		List<Type> list = new ArrayList<Type>();
		 try (Connection conn = getNewConnection();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {
		        while (rs.next()) {   	
		         Type type = new Type(
		        		 rs.getInt("type_id"), 
		        		 rs.getString("type_name"));
		         list.add(type);
		        }
		        return list;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return list;
	}
	public List<Type> showAllTypes(){
		String sql ="SELECT *\r\n"
				+ "FROM type";
		List<Type> list = new ArrayList<Type>();
		 try (Connection conn = getNewConnection();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(sql)) {
		        while (rs.next()) {   	
		         Type type = new Type(
		        		 rs.getInt("type_id"), 
		        		 rs.getString("type_name"));
		         list.add(type);
		        }
		        // Вывод содержимого списка films сгруппировано в консоль
		        for (Type l: list) {
		        	System.out.print(l.getTypeId()+") "+l.getTypeName()+"\n");
		        }
		        return list;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return list;
	}
	public int getLoyaltyProgramIdForCustomer(int points) {
	    String sql = "SELECT loyalty_program_id, bonus_points FROM loyaltyprogram WHERE bonus_points < ? ORDER BY bonus_points DESC limit 1";
	    try (Connection conn = getNewConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, points);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("loyalty_program_id");
	                int maxPoints = rs.getInt("bonus_points");
	                return id;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
  return -1;
	}
public String showFullInfoTicket() {
	String sql ="SELECT t.ticket_id, f.title, c.name, p.date, p.start_time, ty.type_name\r\n"
			+ "FROM ticket t, customer c, projection p, film f, type ty\r\n"
			+ "Where t.customer_id=c.customer_id\r\n"
			+ "AND t.projection_id=p.projection_id\r\n"
			+ "AND t.type_id=ty.type_id\r\n"
			+ "AND f.film_id=p.film_id;";
    String temp="\n";
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {   
	           temp+="id: "+rs.getString("ticket_id")+"\n"
	           +"title: "+rs.getString("title")+"\n"
	           +"name: "+rs.getString("name")+"\n"
	           +"date: "+String.valueOf(rs.getString("date"))+"\n"
	           +"time: "+String.valueOf(rs.getString("start_time"))+"\n"
	           +"type: "+rs.getString("type_name")+"\n\n";
	        }
	        return temp;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return temp;
}
public String showFullInfoFilms() {
	String sql ="SELECT f.title, f.genre, p.date, p.start_time\r\n"
			+ "FROM film f, projection p\r\n"
			+ "WHERE p.film_id=f.film_id";
    String temp="\n";
	    try (Connection conn = getNewConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {   
	           temp+="title: "+rs.getString("title")+"\n"
	           +"genre: "+rs.getString("genre")+"\n"
	           +"date: "+String.valueOf(rs.getString("date"))+"\n"
	           +"time: "+String.valueOf(rs.getString("start_time"))+"\n\n";
	        }
	        return temp;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return temp;
}

public List<Studio> showAllStudios() {
    String sql = "SELECT s.studio_id, s.name, h.headquarters_id, h.name as hName\r\n"
    		+ "FROM studio s, headquarters h\r\n"
    		+ "WHERE s.headquarters_id=h.headquarters_id;";
    List<Studio> studios = new ArrayList<>();
    try (Connection conn = getNewConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Studio studio = new Studio(
                rs.getInt("studio_id"),
                rs.getString("name"),
                rs.getInt("headquarters_id")
            ); 
            System.out.println("studio_id="+rs.getInt("studio_id")+", StudioName="+rs.getString("name")+", headquarters_id="+rs.getInt("headquarters_id")+", headquartersName="+rs.getString("hName"));
            studios.add(studio);
        }
//     // Вывод содержимого списка Headquarters в консоль
//        for (Studio s : studios) {
//            System.out.println(s);
//        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return studios;
}

public boolean insertEventToFilm(int eventId, int filmId) {
    String sql = "INSERT INTO eventfilms (event_id, film_id) VALUES (?, ?)";
    try (Connection conn = getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, eventId);
        stmt.setInt(2, filmId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
public boolean deleteEventFromFilm(int eventId, int filmId) {
    String sql = "DELETE FROM eventfilms WHERE event_id = ? AND film_id = ?";
    try (Connection conn = getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, eventId);
        stmt.setInt(2, filmId);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public List<Film> findFilmByTitle(String title) {
    String sql = "SELECT * FROM film WHERE title = ?"; 
    List<Film> films = new ArrayList<>();
    
    try (Connection conn = getNewConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, title);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Film film = new Film(
                rs.getInt("film_id"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getInt("duration"),
                rs.getString("director"),
                rs.getInt("studio_id") 
            );
            films.add(film);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return films;
}

public List<Film> showAllFilmsWithStudioName() {
    String sql = "SELECT f.film_id, f.title, f.genre, f.duration, f.director, s.studio_id, s.name AS studio_name " +
            "FROM film f " +
            "JOIN studio s ON f.studio_id = s.studio_id";
    
    List<Film> films = new ArrayList<>();
    
    try (Connection conn = getNewConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        
        while (rs.next()) {
            int filmId = rs.getInt("film_id");
            String title = rs.getString("title");
            String genre = rs.getString("genre");
            int duration = rs.getInt("duration");
            String director = rs.getString("director");
            String studioName = rs.getString("studio_name");
            int studioId=rs.getInt("studio_id");
            System.out.println(
            		"ID=" + filmId +
                    ", Title=" + title +
                    ", Genre=" + genre +
                    ", Duration=" + duration +
                    ", Director=" + director +
                    ", Studio Name=" + studioName
                    );
            Film film = new Film(filmId, title, genre, duration, director, studioId);
            films.add(film);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return films;
}


public List<Hall> findHallByCapacity( int capacity) {
    String sql = "SELECT * FROM hall WHERE capacity >= ?";
    List<Hall> halls = new ArrayList<>();

    try (Connection conn = getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, capacity);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int hallId = rs.getInt("hall_id");
            int hallCapacity = rs.getInt("capacity");
            int cinemaId = rs.getInt("cinema_id");
            int hallNumber =rs.getInt("hall_number");

            Hall hall = new Hall(hallId, hallNumber, cinemaId, hallCapacity);
            halls.add(hall);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return halls;
}


}
