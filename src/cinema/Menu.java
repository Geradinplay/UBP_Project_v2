package cinema;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {
	    private final DataBaseConnection db;
	    private final Scanner scan;
	    private final CSVReader rd; 

	    public Menu(DataBaseConnection db) {
	        this.db = db;
	        this.scan = new Scanner(System.in); 
	        this.rd=new CSVReader(db);
	    }

	    public void start() throws SQLException { 
	        while (true) {
	            System.out.println("\n--- Main Menu ---");
	            System.out.println("1. Cinemas");  // Кинотеатры
	            System.out.println("2. Studios");  // Студии
	            System.out.println("3. Films");  // Фильмы
	            System.out.println("4. Halls");  // Залы
	            System.out.println("5. Projections");  // Проекции
	            System.out.println("6. Customers");  // Клиенты
	            System.out.println("7. Tickets");  // Билеты
	            System.out.println("8. Loyalty Programs");  // Программы лояльности
	            System.out.println("9. Special Events");  // Специальные события
	            System.out.println("10. Headquarters");  // Специальные события
	            System.out.println("11. Show table"); //Метод для вывода таблиц по названию(список высвечивается)
	            System.out.println("12. User Interaction"); // Использование системы со стороны пользователя
	            System.out.println("13. CSV readers");  // Выход
	            System.out.println("14. Exit");  // Выход
	            System.out.print("Select an option: ");
	            
	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                    cinemaMenu();  // Меню для кинотеатров
	                    break;
	                case 2:
	                    studioMenu();  // Меню для студий
	                    break;
	                case 3:
	                    filmMenu();  // Меню для фильмов
	                    break;
	                case 4:
	                    hallMenu();  // Меню для залов
	                    break;
	                case 5:
	                    projectionMenu();  // Меню для проекций
	                    break;
	                case 6:
	                    customerMenu();  // Меню для клиентов
	                    break;
	                case 7:
	                    ticketMenu();  // Меню для билетов
	                    break;
	                case 8:
	                    loyaltyProgramMenu();  // Меню для программ лояльности
	                    break;
	                case 9:
	                    specialEventMenu();  // Меню для специальных событий
	                    break;
	                case 10://todo
	                	headquartersMenu();
	                    break;
	                case 11:
	                	db.showAllTableNames();
	                	System.out.println("Select you tabla:");
		                 String name=scan.next();
		                 db.showTable(name);
		                 break;
	                case 12:
	                	userInteraction();
		                 break;
	                case 13:
	                	CSVreadersMenu();
	                	break;
	                case 14:
	                    System.out.println("Exiting...");
	                    return;
	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void headquartersMenu(){
	    	while (true) {
	            System.out.println("--- Headquarters Menu ---");
	            System.out.println("1. Insert headquarter");   
	            System.out.println("2. Update headquarter"); 
	            System.out.println("3. Show headquarter");   
	            System.out.println("4. Delete headquarter");   
	            System.out.println("5. Back to Main Menu"); 
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1: 
	                    System.out.print("Enter headquarters name: ");
	                    String name = scan.nextLine();
	                    boolean isAdded = db.insertHeadquarters(name);
	                    if (isAdded) {
	                        System.out.println("Headquarters added successfully!");
	                    } else {
	                        System.out.println("Failed to add Headquarters.");
	                    }
	                    break;

	                case 2: 
	                    System.out.println("\n--- Update Cinema ---");
	                    int idHd=0;
	                    System.out.print("Enter cinema name: ");
	                    String nameSc = scan.nextLine();
	                    List<Headquarters>headquarters=db.findHeadquartersByName(nameSc);
	                    if(headquarters.isEmpty()) {
	                    	System.out.println("Error: name not found!");
	                    	break;
	                    }
	                    switch (headquarters.size()) {
	           	     case 1:
	           	             idHd=headquarters.get(0).getHeadquartersId();
	           	             break;
	           	     default:
	           	      for (Headquarters c : headquarters) {
	           	        System.out.println(c);
	           	      }
	           	      System.out.print("Select your cinema(id): ");
	           	        idHd = scan.nextInt();
	           	        scan.nextLine();
	           	        break;
	           	    }
	                    System.out.println("Enter new name: ");
	                    String hdName=scan.nextLine();
	                    boolean isUpdated = db.updateHeadquarters(idHd, hdName);
	                    if (isUpdated) {
	                        System.out.println("Headquarters updated successfully!");
	                    } else {
	                        System.out.println("Failed to update headquarter.");
	                    }
	                    break;

	                case 3: 
	                    System.out.println("\n--- List of Headquarters ---");
	                    db.showAllHeadquarters();
	                    break;

	                case 4: 
	                    System.out.println("\n--- Delete Headquarters ---");
	                    System.out.print("Enter the cinema name: ");
	                    String deleteName = scan.nextLine();
	                    List<Headquarters>headquartersDel=db.findHeadquartersByName(deleteName);
	                    int idHdDel=0;
	                    if(headquartersDel.isEmpty()) {
	                    	System.out.println("Error: name not found!");
	                    	break;
	                    }
	                    switch (headquartersDel.size()) {
	           	     case 1:
	           	    	idHdDel=headquartersDel.get(0).getHeadquartersId();
	           	             break;
	           	     default:
	           	      for (Headquarters c : headquartersDel) {
	           	        System.out.println(c);
	           	      }
	           	      System.out.print("Select your cinema(id): ");
	           	   idHdDel = scan.nextInt();
	           	        scan.nextLine();
	           	        break;
	           	    }
	                    boolean isDeleted = db.deleteHeadquarters(idHdDel);
	                    if (isDeleted) {
	                        System.out.println("Headquarters deleted successfully!");
	                    } else {
	                        System.out.println("Failed to delete headquarters.");
	                        }
	                    break;
	                case 5: 
	                    return;

	                default: 
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void CSVreadersMenu() {
	    	System.out.println("");
	    	System.out.println("---------Select table---------");
	            System.out.println("1. Cinemas");
	            System.out.println("2. Studios");
	            System.out.println("3. Films");
	            System.out.println("4. Halls");
	            System.out.println("5. Projections"); 
	            System.out.println("6. Customers"); 
	            System.out.println("7. Tickets"); 
	            System.out.println("8. Special Events"); 
	            System.out.println("9. Headquarters");
	            System.out.println("10. Exit");
	            System.out.print("Select an option: ");
		    	 int choice = scan.nextInt();
		    	 String path="";
		    	 if(choice!=11) {
		    	 System.out.print("Enter the path: ");
		         path = scan.next();
		    	 }
		         scan.nextLine(); 
		            switch (choice) {
		                case 1:
		                	rd.readAndInsertCinemasFromCsv(path);// порядок в файле: name, address, capacity
		                   break;
		                case 2:
		                	rd.readAndInsertStudiosFromCsv(path);// name, headquartersId
		                    break;
		                case 3:
		                	rd.readAndInsertFilmsFromCsv(path);// title, genre, duration, director, studioId
		                    break;
		                case 4:
		                	rd.readAndInsertHallsFromCsv(path);// hallNumber, cinemaId, capacity
		                    break;
		                case 5:
		                	rd.readAndInsertProjectionsFromCsv(path);// filmId, hallId, date, startTime
		                    break;
		                case 6:
		                	rd.readAndInsertCustomersFromCsv(path);// name, rewardPoints, loyaltyProgramId
		                    break;
		                case 7:
		                	rd.readAndInsertTicketsFromCsv(path);// projectionId, purchaseDate, type, customerId
		                    break;
		                case 8:
		                	rd.readAndInsertSpecialEventsFromCsv(path);// name, date, specialPrices
		                    break;
		                case 9:
		                	rd.readAndInsertHeadquartersFromCsv(path);// name
		                   return;
		                case 10:
		                	System.out.println("Exiting...");
		                   return;
		                default:
		                    System.out.println("Invalid choice, try again.");
		            }
	    }
	    private void userInteraction() {
	    	while(true) {
	    	 System.out.println("\n--- Main Menu ---");
	            System.out.println("1. Buy ticket");  // Кинотеатры
	            System.out.println("2. Show films");  // Студии 
	            System.out.println("3. Exit");  // Выход
	            System.out.print("Select an option: ");
	            
	    	 int choice = scan.nextInt();
	    	 scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                	buyTicket();
	                   break;
	                case 2:
	                	System.out.println( db.showFullInfoFilms());
	                    break;
	                case 3:
	                   return;
	               
	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	    	
	    	}
	    }
 	    private void buyTicket() {
	    	Ticket ticket = new Ticket(0, 0, null, 0, 0);
	    	while(true) {
	    		
	    	System.out.println("Are you here for the first time?(YES/NO)");
	    	String answer=scan.next();
	    	
	    	if(answer.equalsIgnoreCase("YES")) {
	    		scan.nextLine();
	    		System.out.print("Enter your name!:");
	    		String name = scan.nextLine();
	    		int id=db.insertCustomerReturnId(name, 0, 30);
	    		System.out.println(id);
	    		ticket.setCustomerId(id);
	    		
	    	}else if(answer.equalsIgnoreCase("NO")) {
	    	while(true) {
	    		db.showAllCustomers();
	    		System.out.println("Select user: ");
	    		int userID = scan.nextInt();
	    		if(db.findCustomerById(userID)!=null) {
	    			ticket.setCustomerId(userID);
	    			break;
	    		}else {
	    			System.out.println("Incorrect input, try again!");
	    		}
	    		}
	    	}
	    	
	    			List<String>films=db.showFilmsGrouped();
	    			System.out.println("Select film: ");
	    			int film=scan.nextInt();
	    			List<Projection> projection=db.findProjectionsWithSelectedFilm(films.get(film-1));
	    			System.out.println("Selectc projection(date and time): ");
	    			int dateTime=scan.nextInt();
	    			ticket.setProjectionId(projection.get(dateTime-1).getProjectionId());
	    		    ticket.setPurchaseDate(String.valueOf(LocalDate.now()));
	    		    while(true) {
	    		    System.out.println("What type of tickets do you prefer?");
	    		   List<Type> types= db.showAllTypes();
	    		   System.out.print("Enter: ");
	    		    int status=scan.nextInt();
	    		    for(Type type:types) {
	    		    	if(type.getTypeId()==status) {
	    		    		ticket.setTypeId(status);
	    		    		db.insertTicket(ticket.getProjectionId(), ticket.getPurchaseDate(), ticket.getTypeId(), ticket.getCustomerId());
	    		    		System.out.println("Successfully");
	    		    		return;
	    		    	}
	    		    }
	    		    }
	    		   
	    	}
	
	    }
	    private void cinemaMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Cinema Menu ---");
	            System.out.println("1. Insert Cinema");      // Добавить кинотеатр
	            System.out.println("2. Update Cinema");   // Обновить информацию о кинотеатре
	            System.out.println("3. Show Cinemas");    // Отобразить все кинотеатры
	            System.out.println("4. Delete Cinema");   // Удалить кинотеатр
	            System.out.println("5. Back to Main Menu"); // Вернуться в главное меню
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1: 
	                    System.out.print("Enter cinema name: ");
	                    String name = scan.nextLine();
	                    System.out.print("Enter cinema address: ");
	                    String address = scan.nextLine(); 
	   
	                    System.out.print("Enter cinema capacity: ");
	                    int capacity = scan.nextInt();

	                    boolean isAdded = db.insertCinema(name, address, capacity);
	                    if (isAdded) {
	                        System.out.println("Cinema added successfully!");
	                    } else {
	                        System.out.println("Failed to add cinema.");
	                    }
	                    break;

	                case 2: 
	                    System.out.println("\n--- Update Cinema ---");

	                   int idCm=0;
	                    System.out.print("Enter cinema name: ");
	                    String nameSc = scan.nextLine();
	                    List<Cinema>cinemas=db.findCinemaByName(nameSc);
	                    if(cinemas.isEmpty()) {
	                    	System.out.println("Error: name not found!");
	                    	break;
	                    }
	                    switch (cinemas.size()) {
	           	     case 1:
	           	             idCm=cinemas.get(0).getCinemaId();
	           	             break;
	           	     default:
	           	      for (Cinema c : cinemas) {
	           	        System.out.println(c);
	           	      }
	           	      System.out.print("Select your cinema(id): ");
	           	        idCm = scan.nextInt();
	           	        scan.nextLine();
	           	        break;
	           	    }
	                    
	                    
	                    System.out.print("Enter new cinema name: ");
	                    String newName =scan.nextLine();
	                    System.out.print("Enter new cinema address: ");
	                    String newAddress = scan.nextLine();
	                    System.out.print("Enter new cinema capacity: ");
	                    int newCapacity = scan.nextInt();
	                    scan.nextLine(); 

	                    boolean isUpdated = db.updateCinema(idCm, newName, newAddress, newCapacity);
	                    if (isUpdated) {
	                        System.out.println("Cinema updated successfully!");
	                    } else {
	                        System.out.println("Failed to update cinema.");
	                    }
	                    break;

	                case 3: 
	                    System.out.println("\n--- List of Cinemas ---");
	                    db.showAllCinemas();
	                    break;

	                case 4: 
	                    System.out.println("\n--- Delete Cinema ---");
//	                    db.showAllCinemas(); 
	                    System.out.print("Enter the cinema name: ");
	                    String deleteName = scan.nextLine();
	                    List<Cinema>cinemasDel=db.findCinemaByName(deleteName);
	                    int idCmDel=0;
	                    if(cinemasDel.isEmpty()) {
	                    	System.out.println("Error: name not found!");
	                    	break;
	                    }
	                    switch (cinemasDel.size()) {
	           	     case 1:
	           	    	idCmDel=cinemasDel.get(0).getCinemaId();
	           	             break;
	           	     default:
	           	      for (Cinema c : cinemasDel) {
	           	        System.out.println(c);
	           	      }
	           	      System.out.print("Select your cinema(id): ");
	           	   idCmDel = scan.nextInt();
	           	        scan.nextLine();
	           	        break;
	           	    }
	                    boolean isDeleted = db.deleteCinema(idCmDel);//todo
	                    if (isDeleted) {
	                        System.out.println("Cinema deleted successfully!");
	                    } else {
	                        System.out.println("Failed to delete cinema.");
	                        }
	                    break;
	                    

	                case 5: 
	                    return;

	                default: 
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void studioMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Studio Menu ---");
	            System.out.println("1. Insert Studio");      // Добавить студию
	            System.out.println("2. Update Studio");      // Обновить информацию о студии
	            System.out.println("3. Show Studios");       // Отобразить все студии
	            System.out.println("4. Delete Studio");      // Удалить студию
	            System.out.println("5. Back to Main Menu");  // Вернуться в главное меню
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1: 
	                    System.out.print("Enter studio name: ");
	                    String newStudioIn = scan.nextLine();
	                    db.showAllHeadquarters();
	                    System.out.print("Enter headquarters name: ");
	                    String newheadquartersIn = scan.nextLine();
	                    List<Headquarters>headquartersForeignIn=db.findHeadquartersByName(newheadquartersIn);
	                    int idStIn=0;
	                    if(headquartersForeignIn.isEmpty()) {
	                    	System.out.println("Error: name not found!");
	                    	break;
	                    }
	                    switch (headquartersForeignIn.size()) {
	           	     case 1:
	           	    	idStIn=headquartersForeignIn.get(0).getHeadquartersId();
	           	             break;
	           	     default:
	           	      for (Headquarters c : headquartersForeignIn) {
	           	        System.out.println(c);
	           	      }
	           	      System.out.print("Select your studio(id): ");
	           	        idStIn = scan.nextInt();
	           	        scan.nextLine();
	           	        break;
	           	    }

	                    boolean isAdded = db.insertStudio(newStudioIn, idStIn);
	                    if (isAdded) {
	                        System.out.println("Studio added successfully!");
	                    } else {
	                        System.out.println("Failed to add studio.");
	                    }
	                    break;

	                case 2:
	                	 System.out.println("\n--- Update Studio ---");
	                	  int idStUp=0;
		                    System.out.print("Enter studio name: ");
		                    String nameSt = scan.nextLine();
		                    List<Studio>studios=db.findStudioByName(nameSt);
		                    if(studios.isEmpty()) {
		                    	System.out.println("Error: name not found!");
		                    	break;
		                    }
		                    switch (studios.size()) {
		           	     case 1:
		           	             idStUp=studios.get(0).getStudioId();
		           	             break;
		           	     default:
		           	      for (Studio c : studios) {
		           	        System.out.println(c);
		           	      }
		           	      System.out.print("Select your studio(id): ");
		           	        idStUp=scan.nextInt();
		           	        scan.nextLine();
		           	        break;
		           	    }
		                    
	                	
	                	System.out.print("Enter new studio name: ");
	                    String newName = scan.nextLine();
	                	List<Headquarters> headquarters=db.findHeadquartersByName(newName);//todo
	                    System.out.println("\n--- Available Headquarters ---");
	                    db.showAllHeadquarters(); 
	                    
	                    System.out.print("Enter new headquarters name: ");
	                    int idStF=0;
	                    String newHeadquarters = scan.nextLine();
	                    List<Headquarters>headquartersForeign=db.findHeadquartersByName(newHeadquarters);
	                    if(headquartersForeign.isEmpty()) {
	                    	System.out.println("Error: name not found!");
	                    	break;
	                    }
	                    switch (headquartersForeign.size()) {
	           	     case 1:
	           	             idStF=headquartersForeign.get(0).getHeadquartersId();
	           	             break;
	           	     default:
	           	      for (Headquarters c : headquartersForeign) {
	           	        System.out.println(c);
	           	      }
	           	      System.out.print("Select your cinema(id): ");
	           	        idStF = scan.nextInt();
	           	        scan.nextLine();
	           	        break;
	           	    }

	                    boolean isUpdated = db.updateStudio(idStUp, newName, idStF);
	                    if (isUpdated) {
	                        System.out.println("Studio updated successfully!");
	                    } else {
	                        System.out.println("Failed to update studio.");
	                    }
	                    break;

	                case 3: 
	                    System.out.println("\n--- List of Studios ---");
	                    db.showAllStudios(); 
	                    break;

	                case 4: 
	                    System.out.println("\n--- Delete Studio ---");
//	                    db.showAllStudios(); 
	                    int idStDel=0;
	                    System.out.print("Enter studio name: ");
	                    String nameStDel = scan.nextLine();
	                    List<Studio>studiosDel=db.findStudioByName(nameStDel);
	                    if(studiosDel.isEmpty()) {
	                    	System.out.println("Error: name not found!");
	                    	break;
	                    }
	                    switch (studiosDel.size()) {
	           	     case 1:
	           	             idStDel=studiosDel.get(0).getStudioId();
	           	             break;
	           	     default:
	           	      for (Studio c : studiosDel) {
	           	        System.out.println(c);
	           	      }
	           	      System.out.print("Select your studio(id): ");
	           	        idStDel=scan.nextInt();
	           	        scan.nextLine();
	           	        break;
	           	    }

	                    boolean isDeleted = db.deleteStudio(idStDel);
	                    if (isDeleted) {
	                        System.out.println("Studio deleted successfully!");
	                    } else {
	                        System.out.println("Failed to delete studio.");
	                    }
	                    break;

	                case 5: 
	                    return;

	                default: 
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void filmMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Film Menu ---");
	            System.out.println("1. Insert Film");
	            System.out.println("2. Update Film");
	            System.out.println("3. Show Films");
	            System.out.println("4. Delete Film");
	            System.out.println("5. add event");
	            System.out.println("6. remove event");	            
	            System.out.println("7. Show all films with special events");
	            System.out.println("8. Back to Main Menu");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                	System.out.print("Enter film title: ");
	                	String title = scan.nextLine();

	                	System.out.print("Enter film genre: ");
	                	String genre = scan.nextLine();

	                	System.out.print("Enter film duration (in minutes): ");
	                	int duration = scan.nextInt();
	                	scan.nextLine(); // Очищаем буфер

	                	System.out.print("Enter film director: ");
	                	String director = scan.nextLine();
	                	db.showAllStudios();
	                	System.out.print("Enter studio name: ");
	                	String studioName = scan.nextLine();
	                	List<Studio> studios = db.findStudioByName(studioName);

	                	int studioId = 0;
	                	if (studios.isEmpty()) {
	                	    System.out.println("Error: Studio name not found!");
	                	    break;
	                	}

	                	if (studios.size() == 1) {
	                	    studioId = studios.get(0).getStudioId();
	                	} else {
	                	    for (int i = 0; i < studios.size(); i++) {
	                	        System.out.println(studios.get(i));
	                	    }
	                	    System.out.print("Select studio ID: ");
	                	    studioId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	boolean isAdded = db.insertFilm(title, genre, duration, director, studioId);
	                	if (isAdded) {
	                	    System.out.println("Film inserted successfully!");
	                	} else {
	                	    System.out.println("Failed to insert film.");
	                	}
	                	break;


	                case 2:
	                	System.out.println("\n--- Update Film ---");
	                	db.showAllFilms();

	                	System.out.print("Enter the current film title: ");
	                	String currentTitle = scan.nextLine();
	                	List<Film> films = db.findFilmByTitle(currentTitle);

	                	int filmId = 0;
	                	if (films.isEmpty()) {
	                	    System.out.println("Error: Film title not found!");
	                	    break;
	                	}

	                	if (films.size() == 1) {
	                	    filmId = films.get(0).getFilmId();
	                	} else {
	                	    for (int i = 0; i < films.size(); i++) {
	                	        System.out.println(films.get(i));
	                	    }
	                	    System.out.print("Select film ID: ");
	                	    filmId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	System.out.print("Enter new film title: ");
	                	String newTitle = scan.nextLine();

	                	System.out.print("Enter new film genre: ");
	                	String newGenre = scan.nextLine();

	                	System.out.print("Enter new film duration (in minutes): ");
	                	int newDuration = scan.nextInt();
	                	scan.nextLine();

	                	System.out.print("Enter new film director: ");
	                	String newDirector = scan.nextLine();
	                	
	                	db.showAllStudios();
	                	System.out.print("Enter new studio name: ");
	                	String newStudioName = scan.nextLine();
	                	List<Studio> studiosFilm = db.findStudioByName(newStudioName);

	                	int newStudioId = 0;
	                	if (studiosFilm.isEmpty()) {
	                	    System.out.println("Error: Studio name not found!");
	                	    break;
	                	}

	                	if (studiosFilm.size() == 1) {
	                	    newStudioId = studiosFilm.get(0).getStudioId();
	                	} else {
	                	    for (int i = 0; i < studiosFilm.size(); i++) {
	                	        System.out.println(studiosFilm.get(i));
	                	    }
	                	    System.out.print("Select studio ID: ");
	                	    newStudioId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	boolean isUpdated = db.updateFilm(filmId, newTitle, newGenre, newDuration, newDirector, newStudioId);
	                	if (isUpdated) {
	                	    System.out.println("Film updated successfully!");
	                	} else {
	                	    System.out.println("Failed to update film.");
	                	}
	                	break;


	                case 3:
	                    System.out.println("\n--- List of Films ---");
	                    db.showAllFilmsWithStudioName();
	                    break;

	                case 4:
	                	System.out.println("\n--- Delete Film ---");
	                	db.showAllFilms();

	                	System.out.print("Enter the title of the film to delete: ");
	                	String filmTitleToDelete = scan.nextLine();
	                	List<Film> filmsDel = db.findFilmByTitle(filmTitleToDelete);

	                	int deleteFilmId = 0;
	                	if (filmsDel.isEmpty()) {
	                	    System.out.println("Error: Film title not found!");
	                	    break;
	                	}

	                	if (filmsDel.size() == 1) {
	                	    deleteFilmId = filmsDel.get(0).getFilmId();
	                	} else {
	                	    for (int i = 0; i < filmsDel.size(); i++) {
	                	        System.out.println(filmsDel.get(i));
	                	    }
	                	    System.out.print("Select film ID to delete: ");
	                	    deleteFilmId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	boolean deletionSuccess = db.deleteFilm(deleteFilmId);
	                	if (deletionSuccess) {
	                	    System.out.println("Film deleted successfully!");
	                	} else {
	                	    System.out.println("Failed to delete film.");
	                	}
	                	break;


	                case 5: 
	                    System.out.println("\n--- Add Event to Film ---");

	                    db.showAllSpecialEvents();
	                    System.out.print("Enter special event name: ");
	                    String eventName = scan.nextLine();
	                    List<SpecialEvent> eventList = db.findSpecialEventByName(eventName);

	                    int eventId = 0;
	                    if (eventList.isEmpty()) {
	                        System.out.println("Error: Event name not found!");
	                        break;
	                    }
	                    
	                    if (eventList.size() == 1) {
	                        eventId = eventList.get(0).getEventId();
	                    } else {
	                        for (int i = 0; i < eventList.size(); i++) {
	                            System.out.println(eventList.get(i));
	                        }
	                        System.out.print("Select event ID: ");
	                        eventId = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    db.showAllFilms();
	                    System.out.print("Enter film title: ");
	                    String filmTitle = scan.nextLine();
	                    List<Film> filmList = db.findFilmByTitle(filmTitle);

	                    int filmIdEF = 0;
	                    if (filmList.isEmpty()) {
	                        System.out.println("Error: Film title not found!");
	                        break;
	                    }
	                    
	                    if (filmList.size() == 1) {
	                        filmIdEF = filmList.get(0).getFilmId();
	                    } else {
	                        for (int i = 0; i < filmList.size(); i++) {
	                            System.out.println(filmList.get(i));
	                        }
	                        System.out.print("Select film ID: ");
	                        filmIdEF = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    boolean insertionSuccess = db.insertEventToFilm(eventId, filmIdEF);
	                    if (insertionSuccess) {
	                        System.out.println("Event linked to film successfully!");
	                    } else {
	                        System.out.println("Failed to link event to film.");
	                    }
	                    break;

	                case 6: 
	                    System.out.println("\n--- Remove Event from Film ---");

	                    db.showAllSpecialEvents();
	                    System.out.print("Enter special event name: ");
	                    String eventNameToDelete = scan.nextLine();
	                    List<SpecialEvent> eventListToDelete = db.findSpecialEventByName(eventNameToDelete);

	                    int eventIdToDelete = 0;
	                    if (eventListToDelete.isEmpty()) {
	                        System.out.println("Error: Event name not found!");
	                        break;
	                    }
	                    
	                    if (eventListToDelete.size() == 1) {
	                        eventIdToDelete = eventListToDelete.get(0).getEventId();
	                    } else {
	                        for (int i = 0; i < eventListToDelete.size(); i++) {
	                            System.out.println(eventListToDelete.get(i));
	                        }
	                        System.out.print("Select event ID: ");
	                        eventIdToDelete = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    db.showAllFilms();
	                    System.out.print("Enter film title: ");
	                    String filmTitleToDel = scan.nextLine();
	                    List<Film> filmListToDelete = db.findFilmByTitle(filmTitleToDel);

	                    int filmIdEFToDel = 0;
	                    if (filmListToDelete.isEmpty()) {
	                        System.out.println("Error: Film title not found!");
	                        break;
	                    }
	                    
	                    if (filmListToDelete.size() == 1) {
	                        filmIdEFToDel = filmListToDelete.get(0).getFilmId();
	                    } else {
	                        for (int i = 0; i < filmListToDelete.size(); i++) {
	                            System.out.println(filmListToDelete.get(i));
	                        }
	                        System.out.print("Select film ID: ");
	                        filmIdEFToDel = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    boolean deletionSc = db.deleteEventFromFilm(eventIdToDelete, filmIdEFToDel);
	                    if (deletionSc) {
	                        System.out.println("Event unlinked from film successfully!");
	                    } else {
	                        System.out.println("Failed to unlink event from film.");
	                    }
	                    break;

	                    
	                    
	                case 7:
	                    System.out.println(db.showAllFilmsWithEvent());
	                    break;
	                    
	                case 8:
	                    return;

	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void hallMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Hall Menu ---");
	            System.out.println("1. Insert Hall");
	            System.out.println("2. Update Hall");
	            System.out.println("3. Show Halls");
	            System.out.println("4. Delete Hall");
	            System.out.println("5. Back to Main Menu");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                	db.showAllCinemas();
	                	System.out.print("Enter cinema name: ");
	                	String cinemaName = scan.nextLine();
	                	List<Cinema> cinemas = db.findCinemaByName(cinemaName);
	                	int cinemaId = 0;
	                	if (cinemas.isEmpty()) {
	                	    System.out.println("Error: Cinema name not found!");
	                	    break;
	                	}
	                	if (cinemas.size() == 1) {
	                	    cinemaId = cinemas.get(0).getCinemaId();
	                	} else {
	                	    for (int i = 0; i < cinemas.size(); i++) {
	                	        System.out.println(cinemas.get(i));
	                	    }
	                	    System.out.print("Select cinema ID: ");
	                	    cinemaId = scan.nextInt();
	                	    scan.nextLine();
	                	}
	                    System.out.print("Enter hall number: ");
	                    int hallNumber = scan.nextInt();
	                    System.out.print("Enter hall capacity: ");
	                    int capacity = scan.nextInt();

	                    boolean isAdded = db.insertHall(hallNumber, cinemaId, capacity);
	                    if (isAdded) {
	                        System.out.println("Hall inserted successfully!");
	                    } else {
	                        System.out.println("Failed to insert hall.");
	                    }
	                    break;

	                case 2:
	                	
	                	System.out.println("\n--- Update Hall ---");

	                	System.out.print("Enter the current hall capacity: ");
	                	int currentCapacity = scan.nextInt();
	                	scan.nextLine(); 

	                	List<Hall> halls = db.findHallByCapacity(currentCapacity);

	                	int hallId = 0;
	                	if (halls.isEmpty()) {
	                	    System.out.println("Error: Hall not found with this number and capacity!");
	                	    break;
	                	}

	                	if (halls.size() == 1) {
	                	    hallId = halls.get(0).getHallId();
	                	} else {
	                	    for (int i = 0; i < halls.size(); i++) {
	                	        System.out.println(halls.get(i));
	                	    }
	                	    System.out.print("Select hall ID: ");
	                	    hallId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	System.out.print("Enter new hall number: ");
	                	int newHallNumber = scan.nextInt();
	                	scan.nextLine();

	                	System.out.print("Enter new hall capacity: ");
	                	int newCapacity = scan.nextInt();
	                	scan.nextLine();
	                	db.showAllCinemas();
	                	System.out.print("Enter new cinema name: ");
	                	String newCinemaName = scan.nextLine();
	                	List<Cinema> newCinemas = db.findCinemaByName(newCinemaName);

	                	int newCinemaId = 0;
	                	if (newCinemas.isEmpty()) {
	                	    System.out.println("Error: New cinema name not found!");
	                	    break;
	                	}

	                	if (newCinemas.size() == 1) {
	                	    newCinemaId = newCinemas.get(0).getCinemaId();
	                	} else {
	                	    for (int i = 0; i < newCinemas.size(); i++) {
	                	        System.out.println(newCinemas.get(i));
	                	    }
	                	    System.out.print("Select new cinema ID: ");
	                	    newCinemaId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	boolean isUpdated = db.updateHall(hallId, newHallNumber, newCinemaId, newCapacity);
	                	if (isUpdated) {
	                	    System.out.println("Hall updated successfully!");
	                	} else {
	                	    System.out.println("Failed to update hall.");
	                	}
	                	break;


	                case 3:
	                    db.showAllHallsWithCinemas();
	                    break;

	                case 4:
	                    
	                	System.out.println("\n--- Delete Hall ---");

	                	System.out.print("Enter the hall capacity: ");
	                	int tHall = scan.nextInt();
	                	List<Hall> mHalls = db.findHallByCapacity(tHall);

	                	int tHallId = 0;
	                	if (mHalls.isEmpty()) {
	                	    System.out.println("Error: hall name not found!");
	                	    break;
	                	}

	                	if (mHalls.size() == 1) {
	                	    tHallId = mHalls.get(0).getCinemaId();
	                	} else {
	                	    for (int i = 0; i < mHalls.size(); i++) {
	                	        System.out.println(mHalls.get(i));
	                	    }
	                	    System.out.print("Select hall ID: ");
	                	    tHallId = scan.nextInt();
	                	    scan.nextLine(); 
	                	}

	                	

	                	boolean isDeleted = db.deleteHall(tHallId);
	                	if (isDeleted) {
	                	    System.out.println("Hall deleted successfully!");
	                	} else {
	                	    System.out.println("Failed to delete hall.");
	                	}
	                	break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void projectionMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Projection Menu ---");
	            System.out.println("1. Insert Projection");
	            System.out.println("2. Update Projection");
	            System.out.println("3. Show Projections");
	            System.out.println("4. Delete Projection");
	            System.out.println("5. Back to Main Menu");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                    db.showAllFilms();
	                    System.out.print("Enter film ID: ");
	                    int filmId = scan.nextInt();
	                    db.showAllHalls();
	                    System.out.print("Enter hall ID: ");
	                    int hallId = scan.nextInt();
	                    System.out.print("Enter projection date (YYYY-MM-DD): ");
	                    String date = scan.next();
	                    System.out.print("Enter projection start time (HH:MM:SS): ");
	                    String startTime = scan.next();

	                    boolean isAdded = db.insertProjection(filmId, hallId, date, startTime);
	                    if (isAdded) {
	                        System.out.println("Projection inserted successfully!");
	                    } else {
	                        System.out.println("Failed to insert projection.");
	                    }
	                    break;

	                case 2:
	                    db.showAllProjections();
	                    System.out.print("Enter the ID of the projection to update: ");
	                    int projectionId = scan.nextInt();
	                    db.showAllFilms();
	                    System.out.print("Enter new film ID: ");
	                    int newFilmId = scan.nextInt();
	                    db.showAllHalls();
	                    System.out.print("Enter new hall ID: ");
	                    int newHallId = scan.nextInt();
	                    System.out.print("Enter new projection date (YYYY-MM-DD): ");
	                    String newDate = scan.next();
	                    System.out.print("Enter new start time (HH:MM:SS): ");
	                    String newStartTime = scan.next();

	                    boolean isUpdated = db.updateProjection(projectionId, newFilmId, newHallId, newDate, newStartTime);
	                    if (isUpdated) {
	                        System.out.println("Projection updated successfully!");
	                    } else {
	                        System.out.println("Failed to update projection.");
	                    }
	                    break;

	                case 3:
	                    db.showAllProjections();
	                    break;

	                case 4:
	                    db.showAllProjections();
	                    System.out.print("Enter the ID of the projection to delete: ");
	                    int deleteProjectionId = scan.nextInt();

	                    boolean isDeleted = db.deleteProjection(deleteProjectionId);
	                    if (isDeleted) {
	                        System.out.println("Projection deleted successfully!");
	                    } else {
	                        System.out.println("Failed to delete projection.");
	                    }
	                    break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void customerMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Customer Menu ---");
	            System.out.println("1. Insert Customer");
	            System.out.println("2. Update Customer");
	            System.out.println("3. Show Customers");
	            System.out.println("4. Delete Customer");
	            System.out.println("5. Back to Main Menu");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                    System.out.print("Enter customer name: ");
	                    String name = scan.next();
	                    System.out.print("Enter reward points: ");
	                    int rewardPoints = scan.nextInt();

	                    int loyaltyProgramId = db.getLoyaltyProgramIdForCustomer(rewardPoints);

	                    loyaltyProgramId = (loyaltyProgramId == 0) ? null : loyaltyProgramId;

	                    boolean isAdded = db.insertCustomer(name, rewardPoints, loyaltyProgramId);
	                    if (isAdded) {
	                        System.out.println("Customer inserted successfully!");
	                    } else {
	                        System.out.println("Failed to insert customer.");
	                    }
	                    break;

	                case 2:
	                    db.showAllCustomers();
	                    System.out.print("Enter the ID of the customer to update: ");
	                    int customerId = scan.nextInt();

	                    System.out.print("Enter new customer name: ");
	                    String newName = scan.next();
	                    
	                    System.out.print("Enter new reward points: ");
	                    int newRewardPoints = scan.nextInt();
	                    db.showAllLoyaltyPrograms();
	                    
	                    int newLoyaltyProgramId = db.getLoyaltyProgramIdForCustomer(newRewardPoints);

	                    boolean isUpdated = db.updateCustomer(customerId, newName, newRewardPoints, newLoyaltyProgramId);
	                    if (isUpdated) {
	                        System.out.println("Customer updated successfully!");
	                    } else {
	                        System.out.println("Failed to update customer.");
	                    }
	                    break;

	                case 3:
	                    db.showAllCustomers();
	                    break;

	                case 4:
	                    db.showAllCustomers();
	                    System.out.print("Enter the ID of the customer to delete: ");
	                    int deleteCustomerId = scan.nextInt();

	                    boolean isDeleted = db.deleteCustomer(deleteCustomerId);
	                    if (isDeleted) {
	                        System.out.println("Customer deleted successfully!");
	                    } else {
	                        System.out.println("Failed to delete customer.");
	                    }
	                    break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void ticketMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Ticket Menu ---");
	            System.out.println("1. Insert Ticket");
	            System.out.println("2. Update Ticket");
	            System.out.println("3. Show Tickets");
	            System.out.println("4. Show Tickets extended");
	            System.out.println("5. Delete Ticket");
	            System.out.println("6. Back to Main Menu");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                    db.showAllProjectionsWithFilmName();
	                    System.out.print("Enter projection ID: ");
	                    int projectionId = scan.nextInt();

	                    System.out.print("Enter purchase date (YYYY-MM-DD): ");
	                    String purchaseDate = scan.next();
	                    

	                    List<Type>types=db.showAllTypes();
	                    System.out.print("Enter ticket type (regular/VIP): ");
	                    int ticketType = scan.nextInt();
	                    for(Type type:types) {
	                    	if(ticketType==type.getTypeId()) {
	                    		db.showAllCustomers();
	                    System.out.print("Enter customer ID: ");
	                    int customerId = scan.nextInt();

	                    boolean isInserted = db.insertTicket(projectionId, purchaseDate, ticketType, customerId);
	                    System.out.println(isInserted ? "Ticket added successfully!" : "Failed to add ticket.");
	                    break;
	                    	}
	                  }
	                    break;
	                case 2:
	                    db.showAllTickets();
	                    System.out.print("Enter ticket ID to update: ");
	                    int ticketId = scan.nextInt();

	                    db.showAllProjections();
	                    System.out.print("Enter new projection ID: ");
	                    int newProjectionId = scan.nextInt();

	                    System.out.print("Enter new purchase date (YYYY-MM-DD): ");
	                    String newPurchaseDate = scan.next();

	                    System.out.print("Enter new ticket type (regular/VIP): ");
	                    int newTicketType = scan.nextInt();

	                    db.showAllCustomers();
	                    System.out.print("Enter new customer ID (or 0 if no customer): ");
	                    int newCustomerId = scan.nextInt();

	                    boolean isUpdated = db.updateTicket(ticketId, newProjectionId, newPurchaseDate, newTicketType, newCustomerId);
	                    System.out.println(isUpdated ? "Ticket updated successfully!" : "Failed to update ticket.");
	                    break;

	                case 3:
	                    db.showAllTickets();
	                    break;
	                    
	                case 4:
	                    System.out.println(db.showFullInfoTicket());
	                    break;

	                case 5:
	                    db.showAllTickets();
	                    System.out.print("Enter ticket ID to delete: ");
	                    int deleteTicketId = scan.nextInt();

	                    boolean isDeleted = db.deleteTicket(deleteTicketId);
	                    System.out.println(isDeleted ? "Ticket deleted successfully!" : "Failed to delete ticket.");
	                    break;

	                case 6:
	                    return;

	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void loyaltyProgramMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Loyalty Program Menu ---");
	            System.out.println("1. Insert Loyalty Program");
	            System.out.println("2. Update Loyalty Program");
	            System.out.println("3. Show Loyalty Programs");
	            System.out.println("4. Delete Loyalty Program");
	            System.out.println("5. Back to Main Menu");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                    System.out.print("Enter bonus points: ");
	                    int bonusPoints = scan.nextInt();

	                    System.out.print("Enter discount percentage: ");
	                    double discount = scan.nextDouble();

	                    boolean isInserted = db.insertLoyaltyProgram(bonusPoints, discount);
	                    System.out.println(isInserted ? "Loyalty program added successfully!" : "Failed to add loyalty program.");
	                    break;

	                case 2:
	                    db.showAllLoyaltyPrograms();
	                    System.out.print("Enter loyalty program ID to update: ");
	                    int loyaltyProgramId = scan.nextInt();

	                    System.out.print("Enter new bonus points: ");
	                    int newBonusPoints = scan.nextInt();

	                    System.out.print("Enter new discount percentage: ");
	                    double newDiscount = scan.nextDouble();

	                    boolean isUpdated = db.updateLoyaltyProgram(loyaltyProgramId, newBonusPoints, newDiscount);
	                    System.out.println(isUpdated ? "Loyalty program updated successfully!" : "Failed to update loyalty program.");
	                    break;

	                case 3:
	                    db.showAllLoyaltyPrograms();
	                    break;

	                case 4:
	                    db.showAllLoyaltyPrograms();
	                    System.out.print("Enter loyalty program ID to delete: ");
	                    int deleteLoyaltyProgramId = scan.nextInt();

	                    boolean isDeleted = db.deleteLoyaltyProgram(deleteLoyaltyProgramId);
	                    System.out.println(isDeleted ? "Loyalty program deleted successfully!" : "Failed to delete loyalty program.");
	                    break;

	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    private void specialEventMenu() throws SQLException {
	        while (true) {
	            System.out.println("\n--- Special Event Menu ---");
	            System.out.println("1. Insert Special Event");
	            System.out.println("2. Update Special Event");
	            System.out.println("3. Show Special Events");
	            System.out.println("4. Delete Special Event");
	            System.out.println("5. Back to Main Menu");
	            System.out.print("Enter your choice: ");

	            int choice = scan.nextInt();
	            scan.nextLine(); 
	            switch (choice) {
	                case 1:
	                    System.out.print("Enter event name: ");
	                    String eventName = scan.next();

	                    System.out.print("Enter event date (YYYY-MM-DD): ");
	                    String eventDate = scan.next();

	                    System.out.print("Enter special prices: ");
	                    String specialPrices = scan.next();

	                    boolean isInserted = db.insertSpecialEvent(eventName, eventDate, specialPrices);
	                    System.out.println(isInserted ? "Special event added successfully!" : "Failed to add special event.");
	                    break;

	                case 2: 
	                    System.out.println("\n--- Update Special Event ---");
//	                    db.showAllSpecialEvents();

	                    System.out.print("Enter special event name: ");
	                    String nameEvent = scan.nextLine();
	                    List<SpecialEvent> events = db.findSpecialEventByName(nameEvent);

	                    int eventIdToUpdate = 0;
	                    if (events.isEmpty()) {
	                        System.out.println("Error: Event name not found!");
	                        break;
	                    }
	                    
	                    if (events.size() == 1) {
	                        eventIdToUpdate = events.get(0).getEventId();
	                    } else {
	                        for (int i = 0; i < events.size(); i++) {
	                            System.out.println(events.get(i));
	                        }
	                        System.out.print("Select the event (ID): ");
	                        eventIdToUpdate = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    System.out.print("Enter new event name: ");
	                    String newEventName = scan.nextLine();

	                    System.out.print("Enter new event date (YYYY-MM-DD): ");
	                    String newEventDate = scan.nextLine();

	                    System.out.print("Enter new special prices: ");
	                    double newSpecialPrices = Double.parseDouble(scan.next());
	                    boolean isUpdated = db.updateSpecialEvent(eventIdToUpdate, newEventName, newEventDate, newSpecialPrices);
	                    if (isUpdated) {
	                        System.out.println("Special event updated successfully!");
	                    } else {
	                        System.out.println("Failed to update special event.");
	                    }
	                    break;

	                case 3:
	                    db.showAllSpecialEvents();
	                    break;

	                case 4:
	                   
	                	System.out.println("\n--- Delete Special Event ---");
	                	db.showAllSpecialEvents();

	                	int eventIdToDelete = 0;
	                	System.out.print("Enter special event name: ");
	                	String nameEventDel = scan.nextLine();
	                	List<SpecialEvent> eventsToDelete = db.findSpecialEventByName(nameEventDel);

	                	if (eventsToDelete.isEmpty()) {
	                	    System.out.println("Error: Event name not found!");
	                	    break;
	                	}

	                	if (eventsToDelete.size() == 1) {
	                	    eventIdToDelete = eventsToDelete.get(0).getEventId();
	                	} else {
	                	    for (SpecialEvent e : eventsToDelete) {
	                	        System.out.println(e);
	                	    }
	                	    System.out.print("Select the event (ID): ");
	                	    eventIdToDelete = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	boolean isDeleted = db.deleteSpecialEvent(eventIdToDelete);
	                	if (isDeleted) {
	                	    System.out.println("Special event deleted successfully!");
	                	} else {
	                	    System.out.println("Failed to delete special event.");
	                	}
	                	break;


	                case 5:
	                    return;

	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }

	    
}
