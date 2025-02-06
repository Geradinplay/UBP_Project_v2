package cinema;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
	            System.out.println("13. CSV readers"); 
	            System.out.println("14. Report");  
	            System.out.println("15. Exit"); 
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
	                case 10:
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
	                	report();
	                	break;
	                case 15:
	                    System.out.println("Exiting...");
	                    return;
	                default:
	                    System.out.println("Invalid choice, try again.");
	            }
	        }
	    }
	    public void report() {
	    	 while (true) {
		            System.out.println("\n--- Report Menu ---");
		            System.out.println("1. All films by date(list)"); 
		            System.out.println("2. Hall with the right size"); 
		            System.out.println("3. Customer count by loyaltyProgram");
		            System.out.println("4. Ticket count by type");
		            System.out.println("5. Films by date(manual entry)");
		            System.out.println("6. Halls by Cinema");
		            System.out.println("7. Studio by headquarter name");
		            System.out.println("8. Exit"); 
		            System.out.print("Select an option: ");
		            
		            int choice = scan.nextInt();
		            scan.nextLine(); 
		            switch (choice) {
		                case 1:
		                	db.showAllFilmsByGroupedDate();
		                    break;
		                case 2:
		                    System.out.print("\n🏟Enter hall capacity: ");
		                    int capacity = scan.nextInt();
		                    
		                    List<Hall> halls = db.findHallByCapacityWithCinema(capacity);
		                    
		                    if (halls.isEmpty()) {
		                        System.out.println("\n❌ No halls found with capacity: " + capacity);
		                    }
		                    break;
		                case 3:
		                	db.showCustomerCountByLoyaltyProgram();
		                    break;
		                case 4:
		                	db.showTicketCountByType();
		                    break;
		                case 5:
		                	System.out.print("🔍Enter the date:");
		                	String value=scan.nextLine();
		                	db.showFilmsByDate(value);
		                    break;
		                case 6:
		                	System.out.print("Enter the cinema name: ");
		                	String cinemaName =scan.nextLine();
		                	db.showHallsByCinema(cinemaName);
		                    break;
		                case 7:
		                	System.out.print("Enter the headquarter name: ");
		                	String headquarterName =scan.nextLine();
		                	db.showStudiosByHeadquarters(headquarterName);
		                    break;
		                case 8:
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
	                	buyTicketModed();
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
 	    private void buyTicket() {//old version just dead 
	    	Ticket ticket = new Ticket(0, 0, null, 0, 0);
	    	while(true) {
	    		
	    	System.out.println("Are you here for the first time?(YES/NO)");
	    	String answer=scan.next();
	    	
	    	if(answer.equalsIgnoreCase("YES")) {
	    		scan.nextLine();
	    		System.out.print("Enter your name!:");
	    		String name = scan.nextLine();
	    		int id=db.insertCustomerReturnId(name, 0,  0);
	    		System.out.println(id);
	    		ticket.setCustomerId(id);
	    		
	    	}else if(answer.equalsIgnoreCase("NO")) {
	    	while(true) {
	    		System.out.print("Enter user name: ");
	    		scan.nextLine();
	    		String name = scan.nextLine();
	    		int customersId=0;
	    		List<Customer> customers =db.findCustomerByName(name);
	    		if (customers.isEmpty()) {
            	    System.out.println("Error: Customers name not found!");
            	    return;
            	}

            	if (customers.size() == 1) {
            	    customersId = customers.get(0).getCustomerId();
            	} else {
            	    for (int i = 0; i < customers.size(); i++) {
            	        System.out.println(customers.get(i));
            	    }
            	    System.out.print("Select studio ID: ");
            	    customersId = scan.nextInt();
            	    scan.nextLine();
            	}

	    		
	    		int userID = customersId;//todo
	    		if(db.findCustomerById(userID)!=null) {
	    			ticket.setCustomerId(userID);
	    			break;
	    		}else {
	    			System.out.println("Incorrect input, try again!");
	    		}
	    		}
	    	}
	    	
	    			List<String>films=db.showFilmsGrouped();
	    			System.out.println("Enter film film: ");
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
 	    
 	   private void buyTicketModed() {
 		    Ticket ticket = new Ticket(0, 0, null, 0, 0);
 		    while (true) {
 		        System.out.println("Are you here for the first time? (YES/NO)");
 		        String answer = scan.next();

 		        if (answer.equalsIgnoreCase("YES")) {
 		            System.out.print("Enter your name: ");
 		            String name = scan.nextLine();
 		            
 		            int id = db.insertCustomerReturnId(name, 0, 0);
 		            ticket.setCustomerId(id);
 		        } else if (answer.equalsIgnoreCase("NO")) {
 		            while (true) {
 		                System.out.print("Enter user name: ");
 		                scan.nextLine();
 		                String name = scan.nextLine().trim();

 		                List<Customer> customers = db.findCustomerByName(name);
 		                if (customers.isEmpty()) {
 		                    System.out.println("Error: Customer name not found!");
 		                    return;
 		                }

 		                int customerId;
 		                if (customers.size() == 1) {
 		                    customerId = customers.get(0).getCustomerId();
 		                } else {
 		                    System.out.println("Customers found:");
 		                    for (Customer c : customers) {
 		                        System.out.println(c);
 		                    }
 		                    System.out.print("Select customer ID: ");
 		                    customerId = scan.nextInt();
 		                    scan.nextLine();
 		                }

 		                ticket.setCustomerId(customerId);
 		                break;
 		            }
 		        }

 		        // Ввод фильма по названию
 		        db.showAllFilmTitles();
 		        System.out.print("Enter film name: ");
 		        String filmName = scan.nextLine();

 		        List<Film> films = db.findFilmByTitle(filmName);
 		        if (films.isEmpty()) {
 		            System.out.println("Error: Film not found!");
 		            return;
 		        }

 		        int filmId;
 		        if (films.size() == 1) {
 		            filmId = films.get(0).getFilmId();
 		        } else {
 		            System.out.println("Films found with this title:");
 		            for (Film f : films) {
 		                System.out.println(f);
 		            }
 		            System.out.print("Select film ID: ");
 		            filmId = scan.nextInt();
 		            scan.nextLine();
 		        }

 		        // Выбор проекции по фильму
 		        List<Projection> projections = db.findProjectionsDate(filmName);
 		        if (projections.isEmpty()) {
 		            System.out.println("Error: No projections found for this film.");
 		            return;
 		        }

 		        System.out.print("Enter projection date (YYYY-MM-DD): ");
 		        String projectionDate = scan.next();

 		        List<Projection> filteredProjections = new ArrayList<>();
 		        for (Projection p : projections) {
 		            if (p.getDate().equals(projectionDate)) {
 		                filteredProjections.add(p);
 		            }
 		        }

 		        if (filteredProjections.isEmpty()) {
 		            System.out.println("Error: No projections found on this date.");
 		            return;
 		        }

 		        int projectionId;
 		        if (filteredProjections.size() == 1) {
 		            projectionId = filteredProjections.get(0).getProjectionId();
 		        } else {
 		            System.out.println("Available times for " + filmName + " on " + projectionDate + ":");
 		            for (Projection p : filteredProjections) {
 		                System.out.println(p.getStartTime());
 		            }
 		            System.out.print("Enter projection start time (HH:MM:SS): ");
 		            String startTime = scan.next();

 		            List<Projection> finalProjections = new ArrayList<>();
 		            for (Projection p : filteredProjections) {
 		                if (p.getStartTime().equals(startTime)) {
 		                    finalProjections.add(p);
 		                }
 		            }

 		            if (finalProjections.isEmpty()) {
 		                System.out.println("Error: No projections found at this time.");
 		                return;
 		            } else if (finalProjections.size() == 1) {
 		                projectionId = finalProjections.get(0).getProjectionId();
 		            } else {
 		                System.out.println("Projections found with this time:");
 		                for (Projection p : finalProjections) {
 		                    System.out.print(p);
 		                }
 		                System.out.print("Select projection ID: ");
 		                projectionId = scan.nextInt();
 		                scan.nextLine();
 		            }
 		        }

 		        ticket.setProjectionId(projectionId);
 		        ticket.setPurchaseDate(String.valueOf(LocalDate.now()));

 		        // Выбор типа билета
 		        while (true) {
 		            System.out.println("What type of ticket do you prefer?");
 		            List<Type> types = db.showAllTypes();


 		           System.out.print("Enter ticket type (regular/VIP): ");
                   String ticketTypeString=scan.next();
                   int ticketType=0;
                   if(ticketTypeString.equalsIgnoreCase("vip")) {
                   	 ticketType = 2;
                   } else if(ticketTypeString.equalsIgnoreCase("regular")) {
                   	ticketType=1;
                   }
                  if(ticketType==0) {
               	   System.out.println("Error: type not found!");
               	   break;
                  }
 		            int typeId = ticketType;

 		            boolean validType = false;
 		            for (Type type : types) {
 		                if (type.getTypeId() == typeId) {
 		                    validType = true;
 		                    ticket.setTypeId(typeId);
 		                    db.insertTicket(ticket.getProjectionId(), ticket.getPurchaseDate(), ticket.getTypeId(), ticket.getCustomerId());
 		                    System.out.println("Successfully purchased ticket!");
 		                    return;
 		                }
 		            }

 		            if (!validType) {
 		                System.out.println("Invalid ticket type, try again.");
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
	                    boolean isDeleted = db.deleteCinema(idCmDel);
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
	                	List<Headquarters> headquarters=db.findHeadquartersByName(newName);
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
	                	    tHallId = mHalls.get(0).getHallId();
	                	} else {
	                	    for (int i = 0; i < mHalls.size(); i++) {
	                	        System.out.println(mHalls.get(i));
	                	    }
	                	    System.out.print("Select hall id: ");
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
	                	    System.out.print("Enter film title: ");
	                	    String filmTitleIn = scan.nextLine(); 
	                	    List<Film> filmsIn = db.findFilmByTitle(filmTitleIn);

	                	    int filmIdIn = 0; 
	                	    if (filmsIn.isEmpty()) {
	                	        System.out.println("Error: Film title not found!");
	                	        break;
	                	    }

	                	    if (filmsIn.size() == 1) {
	                	        filmIdIn = filmsIn.get(0).getFilmId();
	                	    } else {
	                	        for (Film film : filmsIn) {
	                	            System.out.println(film);
	                	        }
	                	        System.out.print("Select film ID: ");
	                	        filmIdIn = scan.nextInt();
	                	        scan.nextLine();
	                	    }

//	                	    db.showAllHalls();
	                	    System.out.print("Enter hall capacity: ");
	                	    int hallCapacityIn = scan.nextInt();
	                	    scan.nextLine();

	                	    List<Hall> hallsIn = db.findHallByCapacity(hallCapacityIn);

	                	    int hallIdIn = 0; 
	                	    if (hallsIn.isEmpty()) {
	                	        System.out.println("Error: No halls found with this capacity!");
	                	        break;
	                	    }

	                	    if (hallsIn.size() == 1) {
	                	        hallIdIn = hallsIn.get(0).getHallId();
	                	    } else {
	                	        for (Hall hall : hallsIn) {
	                	            System.out.println(hall);
	                	        }
	                	        System.out.print("Select hall ID: ");
	                	        hallIdIn = scan.nextInt();
	                	        scan.nextLine();
	                	    }

	                	    System.out.print("Enter projection date (YYYY-MM-DD): ");
	                	    String dateIn = scan.next(); 
	                	    System.out.print("Enter projection start time (HH:MM:SS): ");
	                	    String startTimeIn = scan.next(); 

	                	    boolean isAdded = db.insertProjection(filmIdIn, hallIdIn, dateIn, startTimeIn);
	                	    if (isAdded) {
	                	        System.out.println("Projection inserted successfully!");
	                	    } else {
	                	        System.out.println("Failed to insert projection.");
	                	    }
	                	    break;

	                case 2:
	                    
	                	db.showAllProjections();
	                	System.out.print("Enter projection date (YYYY-MM-DD): ");
	                	String dateUpdate = scan.next();

	                	List<Projection> projectionsByDate = db.findProjectionByDate(dateUpdate);

	                	if (projectionsByDate.isEmpty()) {
	                	    System.out.println("No projections found with this date.");
	                	    break;
	                	}

	                	int projectionIdUpdate = -1;

	                	if (projectionsByDate.size() == 1) {
	                	    System.out.print("Only one projection found. Do you want to update it? (YES/NO): ");
	                	    String confirm = scan.next();
	                	    if (!confirm.equalsIgnoreCase("YES")) {
	                	        System.out.println("Update canceled.");
	                	        break;
	                	    }
	                	    projectionIdUpdate = projectionsByDate.get(0).getProjectionId();
	                	} else { 
	                	    System.out.print("Enter projection start time (HH:MM:SS): ");
	                	    String startTimeUpdate = scan.next();
	                	    
	                	   

	                	    List<Projection> mProjections = new ArrayList<>();

	                	    for (Projection p1 : projectionsByDate) {
	                	        if (p1.getStartTime().equals(startTimeUpdate)) {
	                	            mProjections.add(p1);
	                	        }
	                	    }

	                	    if (mProjections.isEmpty()) {
	                	        System.out.println("No projections found with this date and time.");
	                	        break;
	                	    }

	                	    if (mProjections.size() == 1) {
	                	        projectionIdUpdate = mProjections.get(0).getProjectionId();
	                	    } else {
	                	        System.out.println("Projections found with this date and time:");
	                	        for (Projection p : mProjections) {
	                	            System.out.print(p);
	                	        }
	                	        System.out.print("Select projection ID to update: ");
	                	        projectionIdUpdate = scan.nextInt();
	                	        scan.nextLine();
	                	    }
	                	}

	                	if (projectionIdUpdate == -1) {
	                	    System.out.println("Error: No projection selected.");
	                	    break;
	                	}

	                	db.showAllFilms();
	                	System.out.print("Enter new film title: ");
	                	scan.nextLine();
	                	String newFilmTitle = scan.nextLine();
	                	List<Film> newFilms = db.findFilmByTitle(newFilmTitle);

	                	int newFilmId = 0;
	                	if (newFilms.isEmpty()) {
	                	    System.out.println("Error: Film title not found!");
	                	    break;
	                	}

	                	if (newFilms.size() == 1) {
	                	    newFilmId = newFilms.get(0).getFilmId();
	                	} else {
	                	    for (Film film : newFilms) {
	                	        System.out.println(film);
	                	    }
	                	    System.out.print("Select new film ID: ");
	                	    newFilmId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	db.showAllHalls();
	                	System.out.print("Enter new hall capacity: ");
	                	int newHallCapacity = scan.nextInt();
	                	scan.nextLine();

	                	List<Hall> newHalls = db.findHallByCapacity(newHallCapacity);

	                	int newHallId = 0;
	                	if (newHalls.isEmpty()) {
	                	    System.out.println("Error: No halls found with this capacity!");
	                	    break;
	                	}

	                	if (newHalls.size() == 1) {
	                	    newHallId = newHalls.get(0).getHallId();
	                	} else {
	                	    for (Hall hall : newHalls) {
	                	        System.out.println(hall);
	                	    }
	                	    System.out.print("Select new hall ID: ");
	                	    newHallId = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	System.out.print("Enter new projection date (YYYY-MM-DD): ");
	                	String newDate = scan.next();
	                	System.out.print("Enter new start time (HH:MM:SS): ");
	                	String newStartTime = scan.next();

	                	boolean isUpdated = db.updateProjection(projectionIdUpdate, newFilmId, newHallId, newDate, newStartTime);
	                	if (isUpdated) {
	                	    System.out.println("Projection updated successfully!");
	                	} else {
	                	    System.out.println("Failed to update projection.");
	                	}
	                	break;


	                case 3:
	                    db.showAllProjectionsWithFilmAndHallCapacity();
	                    break;

	                case 4:
	                   
	                	db.showAllProjections();
	                	System.out.print("Enter projection date (YYYY-MM-DD): ");
	                	String projectionDate = scan.next();

	                	List<Projection> projectionsByDateDel = db.findProjectionByDate(projectionDate);
	                	if (projectionsByDateDel.isEmpty()) {
	                	    System.out.println("Error: No projections found for this date.");
	                	    break;
	                	}

	                	int projectionIdToDelete;
	                	if (projectionsByDateDel.size() == 1) {
	                	    projectionIdToDelete = projectionsByDateDel.get(0).getProjectionId();
	                	} else {
	                	    System.out.println("Available projections on " + projectionDate + ":");
	                	    for (Projection p : projectionsByDateDel) {
	                	        System.out.println("Projection id=" + p.getProjectionId() + 
	                	                           ", Film=" + db.getFilmTitleById(p.getFilmId()) + 
	                	                           ", Time=" + p.getStartTime());
	                	    }
	                	    System.out.print("Enter projection start time (HH:MM:SS): ");
	                	    String projectionTime = scan.next();

	                	    List<Projection> projectionsByTime = new ArrayList<>();
	                	    for (Projection p : projectionsByDateDel) {
	                	        if (p.getStartTime().equals(projectionTime)) {
	                	            projectionsByTime.add(p);
	                	        }
	                	    }

	                	    if (projectionsByTime.isEmpty()) {
	                	        System.out.println("Error: No projections found for this date and time.");
	                	        break;
	                	    } else if (projectionsByTime.size() == 1) {
	                	        projectionIdToDelete = projectionsByTime.get(0).getProjectionId();
	                	    } else {
	                	        System.out.println("Projections found with this date and time:");
	                	        for (Projection p : projectionsByTime) {
	                	            System.out.println("Projection id+" + p.getProjectionId() +
	                	                               ", Film: " + db.getFilmTitleById(p.getFilmId()));
	                	        }
	                	        System.out.print("Enter projection id to delete: ");
	                	        projectionIdToDelete = scan.nextInt();
	                	        scan.nextLine();
	                	    }
	                	}


	                	boolean isDeleted = db.deleteProjection(projectionIdToDelete);
	                	System.out.println(isDeleted ? "Projection deleted successfully!" : "Failed to delete projection.");
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
	                    String name = scan.nextLine();
	                    System.out.print("Enter reward points: ");
	                    int rewardPoints = scan.nextInt();

	                    int loyaltyProgramId = db.getLoyaltyProgramIdForCustomer(rewardPoints);
	               
//	                    loyaltyProgramId = ;
	                    boolean isAdded = db.insertCustomer(name, rewardPoints, loyaltyProgramId);
	                    if (isAdded) {
	                        System.out.println("Customer inserted successfully!");
	                    } else {
	                        System.out.println("Failed to insert customer.");
	                    }
	                    break;

	                case 2:
	                  
	                	 db.showAllCustomers();
	                	    System.out.print("Enter customer name: ");
	                	    String customerName = scan.nextLine(); 

	                	    List<Customer> customers = db.findCustomerByName(customerName);

	                	    if (customers.isEmpty()) {
	                	        System.out.println("Error: Customer name not found!");
	                	        break;
	                	    }

	                	    int customerId;
	                	    if (customers.size() == 1) {
	                	        customerId = customers.get(0).getCustomerId();
	                	    } else {
	                	        System.out.println("Сustomers found with this name:");
	                	        for (Customer c : customers) {
	                	            System.out.println(c);
	                	        }
	                	        System.out.print("Select customer ID: ");
	                	        customerId = scan.nextInt();
	                	        scan.nextLine();
	                	    }
               
	                	    System.out.print("Enter new customer name: ");
	                	    String newName = scan.nextLine();


	                	    System.out.print("Enter new reward points: ");
	                	    int newRewardPoints = scan.nextInt();
	                	    scan.nextLine();


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
	                    db.showAllCustomersWithDiscount();
	                    break;

	                case 4:
	                	db.showAllCustomers();
	                	System.out.print("Enter customer name: ");
	                	String customerNameDel = scan.nextLine();

	                	List<Customer> customersDel = db.findCustomerByName(customerNameDel);
	                	if (customersDel.isEmpty()) {
	                	    System.out.println("Error: Customer name not found!");
	                	    break;
	                	}

	                	int customerIdDel;
	                	if (customersDel.size() == 1) {
	                	    customerIdDel = customersDel.get(0).getCustomerId();
	                	} else {
	                	    System.out.println("Customers found with this name:");
	                	    for (Customer c : customersDel) {
	                	        System.out.println("Customer ID: " + c.getCustomerId() + " | Name: " + c.getName());
	                	    }
	                	    System.out.print("Select customer ID to delete: ");
	                	    customerIdDel = scan.nextInt();
	                	    scan.nextLine();
	                	}

	                	// Удаление выбранного клиента
	                	boolean isDeleted = db.deleteCustomer(customerIdDel);
	                	System.out.println(isDeleted ? "Customer deleted successfully!" : "Failed to delete customer.");

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
	                    
	                    // Выбор фильма
	                    db.showAllFilms();
	                    System.out.print("Enter film name: ");
	                    String filmName = scan.nextLine();

	                    List<Film> films = db.findFilmByTitle(filmName);
	                    if (films.isEmpty()) {
	                        System.out.println("Error: Film name not found!");
	                        break;
	                    }

	                    int filmId;
	                    if (films.size() == 1) {
	                        filmId = films.get(0).getFilmId();
	                    } else {
	                        System.out.println("Films found with this title:");
	                        for (Film f : films) {
	                            System.out.println(f);
	                        }
	                        System.out.print("Select film ID: ");
	                        filmId = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    // Выбор даты
	                    db.showAllDates(filmId);
	                    System.out.print("Enter projection date (YYYY-MM-DD): ");
	                    String date = scan.next();

	                    List<Projection> projectionsByDate = db.findProjectionByFilmAndDate(filmId, date);
	                    if (projectionsByDate.isEmpty()) {
	                        System.out.println("Error: No projections found for this film on this date.");
	                        break;
	                    }

	                    int projectionId = -1;
	                    if (projectionsByDate.size() == 1) {
	                        projectionId = projectionsByDate.get(0).getProjectionId();
	                    } else {
	                        // Выбор времени
	                        System.out.println("Available times for " + filmName + " on " + date + ":");
	                        for (Projection p : projectionsByDate) {
	                            System.out.println("Time: " + p.getStartTime());
	                        }
	                        System.out.print("Enter projection start time (HH:MM:SS): ");
	                        String startTime = scan.next();

	                        List<Projection> projectionsByTime = new ArrayList<>();
	                        for (Projection p : projectionsByDate) {
	                            if (p.getStartTime().equals(startTime)) {
	                                projectionsByTime.add(p);
	                            }
	                        }

	                        if (projectionsByTime.isEmpty()) {
	                            System.out.println("Error: No projections found for this film, date, and time.");
	                            break;
	                        } else if (projectionsByTime.size() == 1) {
	                            projectionId = projectionsByTime.get(0).getProjectionId();
	                        } else {
	                            System.out.println("Projections found with this date and time:");
	                            for (Projection p : projectionsByTime) {
	                                System.out.print(p);
	                            }
	                            System.out.print("Select projection id: ");
	                            projectionId = scan.nextInt();
	                            scan.nextLine();
	                        }
	                    }

	                    // Выбор клиента по имени
	                    db.showAllCustomers();
	                    System.out.print("Enter customer name: ");
	                    String customerName = scan.nextLine();

	                    List<Customer> customers = db.findCustomerByName(customerName);
	                    if (customers.isEmpty()) {
	                        System.out.println("Error: Customer name not found!");
	                        break;
	                    }

	                    int customerId;
	                    if (customers.size() == 1) {
	                        customerId = customers.get(0).getCustomerId();
	                    } else {
	                        System.out.println("Customers found with this name:");
	                        for (Customer c : customers) {
	                            System.out.println(c);
	                        }
	                        System.out.print("Select customer id: ");
	                        customerId = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    // Выбор типа билета
	                    List<Type> types = db.showAllTypes();
	                    System.out.print("Enter ticket type (regular/VIP): ");
	                    String ticketTypeString=scan.next();
	                    int ticketType=0;
	                    if(ticketTypeString.equalsIgnoreCase("vip")) {
	                    	 ticketType = 2;
	                    } else if(ticketTypeString.equalsIgnoreCase("regular")) {
	                    	ticketType=1;
	                    }
	                   if(ticketType==0) {
	                	   System.out.println("Error: type not found!");
	                	   break;
	                   }

	                    boolean validType = false;
	                    for (Type type : types) {
	                        if (type.getTypeId() == ticketType) {
	                            validType = true;
	                            break;
	                        }
	                    }

	                    if (!validType) {
	                        System.out.println("Invalid ticket type!");
	                        break;
	                    }

	                    // Вставка билета
	                    boolean isInserted = db.insertTicket(projectionId, date, ticketType, customerId);
	                    System.out.println(isInserted ? "Ticket added successfully!" : "Failed to add ticket.");
	                    break;

	                	
	                case 2:

	                    // Выбор фильма
	                    db.showAllFilmTitles();
	                    System.out.print("Enter film name: ");

	                    String filmNameUpd = scan.nextLine();

	                    List<Film> filmsUpd = db.findFilmByTitle(filmNameUpd);
	                    if (filmsUpd.isEmpty()) {
	                        System.out.println("Error: Film name not found!");
	                        break;
	                    }

	                    int filmIdUpd;
	                    if (filmsUpd.size() == 1) {
	                        filmIdUpd = filmsUpd.get(0).getFilmId();
	                    } else {
	                        System.out.println("Films found with this title:");
	                        for (Film f : filmsUpd) {
	                            System.out.println(f);
	                        }
	                        System.out.print("Select film ID: ");
	                        filmIdUpd = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    // Выбор клиента по имени
	                    db.showAllCustomers();
	                    System.out.print("Enter customer name: ");
	                    String customerNameUpd = scan.nextLine();

	                    List<Customer> customersUpd = db.findCustomerByName(customerNameUpd);
	                    if (customersUpd.isEmpty()) {
	                        System.out.println("Error: Customer name not found!");
	                        break;
	                    }

	                    int customerIdUpd;
	                    if (customersUpd.size() == 1) {
	                        customerIdUpd = customersUpd.get(0).getCustomerId();
	                    } else {
	                        System.out.println("Customers found with this name:");
	                        for (Customer c : customersUpd) {
	                            System.out.println(c);
	                        }
	                        System.out.print("Select customer ID: ");
	                        customerIdUpd = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    // Поиск билетов по клиенту и фильму
	                    List<Ticket> ticketsByCustomerFilm = db.findTicketsByCustomerAndFilm(customerIdUpd, filmIdUpd);
	                    if (ticketsByCustomerFilm.isEmpty()) {
	                        System.out.println("Error: No tickets found for this customer and film.");
	                        break;
	                    }

	                    int ticketIdUpd;
	                    if (ticketsByCustomerFilm.size() == 1) {
	                        ticketIdUpd = ticketsByCustomerFilm.get(0).getTicketId();
	                    } else {
	                        System.out.println("Tickets found for customer and film:");
	                        db.showAllTicketsWithFilmProjectionCustomer();
	                        System.out.print("Select ticket ID to update: ");
	                        ticketIdUpd = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    // Выбор даты проекции
	                    db.showAllDatesForFilm(filmIdUpd);
	                    System.out.print("Enter new projection date (YYYY-MM-DD): ");
	                    String newDateUpd = scan.next();

	                    List<Projection> projByDateUpd = db.findProjectionByFilmAndDate(filmIdUpd, newDateUpd);
	                    if (projByDateUpd.isEmpty()) {
	                        System.out.println("Error: No projections found for this film on this date.");
	                        break;
	                    }

	                    int newProjectionIdUpd;
	                    if (projByDateUpd.size() == 1) {
	                        newProjectionIdUpd = projByDateUpd.get(0).getProjectionId();
	                    } else {
	                        System.out.println("Available times for " + filmNameUpd + " on " + newDateUpd + ":");
	                        for (Projection p : projByDateUpd) {
	                            System.out.println("Time: " + p.getStartTime());
	                        }
	                        System.out.print("Enter new projection start time (HH:MM:SS): ");
	                        String newStartTimeUpd = scan.next();

	                        List<Projection> projByTimeUpd = new ArrayList<>();
	                        for (Projection p : projByDateUpd) {
	                            if (p.getStartTime().equals(newStartTimeUpd)) {
	                                projByTimeUpd.add(p);
	                            }
	                        }

	                        if (projByTimeUpd.isEmpty()) {
	                            System.out.println("Error: No projections found for this film, date, and time.");
	                            break;
	                        } else if (projByTimeUpd.size() == 1) {
	                            newProjectionIdUpd = projByTimeUpd.get(0).getProjectionId();
	                        } else {
	                            System.out.println("Projections found with this date and time:");
	                            for (Projection p : projByTimeUpd) {
	                                System.out.print(p);
	                            }
	                            System.out.print("Select new projection ID: ");
	                            newProjectionIdUpd = scan.nextInt();
	                            scan.nextLine();
	                        }
	                    }

	                    // Выбор нового типа билета
	                    List<Type> typesUpd = db.showAllTypes();
	                    System.out.print("Enter new ticket type (regular/VIP): ");
	                    String ticketTypeStringUpd = scan.next();
	                    int newTicketTypeUpd = 0;

	                    if (ticketTypeStringUpd.equalsIgnoreCase("vip")) {
	                        newTicketTypeUpd = 2;
	                    } else if (ticketTypeStringUpd.equalsIgnoreCase("regular")) {
	                        newTicketTypeUpd = 1;
	                    }

	                    if (newTicketTypeUpd == 0) {
	                        System.out.println("Error: type not found!");
	                        break;
	                    }

	                    // Обновление билета
	                    boolean isUpdated = db.updateTicket(ticketIdUpd, newProjectionIdUpd, newDateUpd, newTicketTypeUpd, customerIdUpd);
	                    System.out.println(isUpdated ? "Ticket updated successfully!" : "Failed to update ticket.");
	                    break;



	                case 3:
	                    db.showAllTicketsWithFilmProjectionCustomer();
	                    break;
	                    
	                case 4:
	                    System.out.println(db.showFullInfoTicket());
	                    break;

	                case 5:
	                	   // Выбор фильма
	                    db.showAllFilmTitles();
	                    System.out.print("Enter film name: ");

	                    String filmNameDel = scan.nextLine();

	                    List<Film> filmsDel = db.findFilmByTitle(filmNameDel);
	                    if (filmsDel.isEmpty()) {
	                        System.out.println("Error: Film name not found!");
	                        break;
	                    }

	                    int filmIdDel;
	                    if (filmsDel.size() == 1) {
	                        filmIdDel = filmsDel.get(0).getFilmId();
	                    } else {
	                        System.out.println("Films found with this title:");
	                        for (Film f : filmsDel) {
	                            System.out.println(f);
	                        }
	                        System.out.print("Select film ID: ");
	                        filmIdDel = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    // Выбор клиента по имени
	                    db.showAllCustomers();
	                    System.out.print("Enter customer name: ");
	                    String customerNameDel = scan.nextLine();

	                    List<Customer> customersDel = db.findCustomerByName(customerNameDel);
	                    if (customersDel.isEmpty()) {
	                        System.out.println("Error: Customer name not found!");
	                        break;
	                    }

	                    int customerIdDel;
	                    if (customersDel.size() == 1) {
	                        customerIdDel = customersDel.get(0).getCustomerId();
	                    } else {
	                        System.out.println("Customers found with this name:");
	                        for (Customer c : customersDel) {
	                            System.out.println(c);
	                        }
	                        System.out.print("Select customer ID: ");
	                        customerIdDel = scan.nextInt();
	                        scan.nextLine();
	                    }

	                    // Поиск билетов по клиенту и фильму
	                    List<Ticket> ticketsByCustomerFilmDel = db.findTicketsByCustomerAndFilm(customerIdDel, filmIdDel);
	                    if (ticketsByCustomerFilmDel.isEmpty()) {
	                        System.out.println("Error: No tickets found for this customer and film.");
	                        break;
	                    }

	                    int ticketIdDel;
	                    if (ticketsByCustomerFilmDel.size() == 1) {
	                        ticketIdDel = ticketsByCustomerFilmDel.get(0).getTicketId();
	                    } else {
	                        System.out.println("Tickets found for customer and film:");
	                        db.showAllTicketsWithFilmProjectionCustomer();
	                        System.out.print("Select ticket ID to update: ");
	                        ticketIdDel = scan.nextInt();
	                        scan.nextLine();
	                    }
	                    
	                    
	                    int deleteTicketId = ticketIdDel;//todo

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
