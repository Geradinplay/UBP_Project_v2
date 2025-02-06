package cinema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private DataBaseConnection db;

    public CSVReader(DataBaseConnection db) {
        this.db = db;
    }

    public void readAndInsertCinemasFromCsv(String filePath) {//name, address, capacity
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String name = values[0].trim();
                String address = values[1].trim();
                int capacity = Integer.parseInt(values[2].trim());
                db.insertCinema(name, address, capacity); // Вставка в базу данных
            }
            System.out.println("Cinemas inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertHeadquartersFromCsv(String filePath) {// name
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String name = values[0].trim();
                db.insertHeadquarters(name); // Вставка в базу данных
            }
            System.out.println("Headquarters inserted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertStudiosFromCsv(String filePath) {//name, headquartersId
    	List<Studio> studios=new ArrayList<Studio>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	boolean found=false;
                String[] values = line.split(";");
                String name = values[0].trim();
                int headquartersId = Integer.parseInt(values[1].trim());
                List<Headquarters>headquarters=db.getAllHeadquarters();
                for (Headquarters hq : headquarters) {
                    if (hq.getHeadquartersId()==headquartersId) {
                        db.insertStudio(name, headquartersId); 
                        found=true;
                        break;
                    }
                }
                if(found!=true) {
                	Studio studio = new Studio(0, name, headquartersId);
                	studios.add(studio);
                }
            }
            if(!studios.isEmpty()) {
            	System.err.println("Attention! This list displays studios whose headquartersId was not found in the database, which led to their exclusion from the queue for additions.");
            	for(Studio studio:studios) {
            		System.out.println("Name"+studio.getName()+", HeadquartersId="+studio.getHeadquartersId());
            	}
            }
            System.out.println("Studios inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertFilmsFromCsv(String filePath) {// title, genre, duration, director, studioId
    	List<Film>films = new ArrayList<Film>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	boolean found=false;
                String[] values = line.split(";");
                String title = values[0].trim();
                String genre = values[1].trim();
                int duration = Integer.parseInt(values[2].trim());
                String director = values[3].trim();
                int studioId = Integer.parseInt(values[4].trim());
                
            
           List<Studio>studios = db.getAllStudios();
           for(Studio st:studios) {
        	   if(st.getStudioId()==studioId) {
        		   db.insertFilm(title, genre, duration, director, studioId); 
                   found=true;
                   break;
        	   }
           }
           if(found!=true) {
           	Film film = new Film(0, title, genre, duration, director, studioId);
           	films.add(film);
           }
           }
            if(!films.isEmpty()) {
            	System.err.println("Attention! This list displays films whose studioId was not found in the database, which led to their exclusion from the queue for additions.");
            	for(Film f:films) {
            		System.out.print(f.toString());
            	}
            }
            System.out.println("Studios inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertHallsFromCsv(String filePath) {// hallNumber, cinemaId, capacity)
        List<Hall> halls = new ArrayList<Hall>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	boolean found=false;
                String[] values = line.split(";");
                int hallNumber = Integer.parseInt(values[0].trim());
                int cinemaId = Integer.parseInt(values[1].trim());
                int capacity = Integer.parseInt(values[2].trim());
                List<Cinema> cinemas = db.getAllCinemas();
                for (Cinema cinema : cinemas) {
                    if (cinema.getCinemaId() == cinemaId) {
                        db.insertHall(hallNumber, cinemaId, capacity);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    Hall hall = new Hall(0, hallNumber, cinemaId, capacity);
                    halls.add(hall);
                }
            }
            if (!halls.isEmpty()) {
                System.err.println("Attention! This list displays halls whose cinemaId was not found in the database, which led to their exclusion from the queue for additions.");
                for (Hall h : halls) {
                    System.out.print(h.toString());
                }
            }
            System.out.println("Halls inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertProjectionsFromCsv(String filePath) {// filmId, hallId, date, startTime
    	List<Projection>projections=new ArrayList<Projection>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	boolean foundFilm=false;
            	boolean foundHall=false;
                String[] values = line.split(";");
                int filmId = Integer.parseInt(values[0].trim());
                int hallId = Integer.parseInt(values[1].trim());
                String date = values[2].trim();
                String startTime = values[3].trim();      
                List<Film> films = db.getAllFilms();
                List<Hall> halls = db.getAllHalls();
                for (Film f : films) {
                    if (f.getFilmId() == filmId) {
                        foundFilm = true;
                        break;
                    }
                }
                for (Hall h : halls) {
                    if (h.getHallId() == hallId) {
                        foundHall = true;
                        break;
                    }
                }
              
                if (foundFilm&&foundHall) {
                	db.insertProjection(filmId, hallId, date, startTime);
                }else {
                	Projection pr = new Projection(0, filmId, hallId, date, startTime);
                    projections.add(pr);
                }
            }
              if (!projections.isEmpty()) {
                    System.err.println("Attention! This list displays Projections whose filmId or hallId was not found in the database, which led to their exclusion from the queue for additions.");
                    for (Projection proj : projections) {
                        System.out.print(proj.toString());
                    }
                }
              System.out.println("Projections inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertCustomersFromCsv(String filePath) {// name, bonusPoints, loyaltyProgramId
    	List<Customer>customers=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	boolean found=false;
                String[] values = line.split(";");
                String name = values[0].trim();
                int rewardPoints = Integer.parseInt(values[1].trim());
                int loyaltyProgramId=0;
                if(values[2]!=null) {
                loyaltyProgramId = Integer.parseInt(values[2].trim());
                }
                List<LoyaltyProgram> loyalty = db.getAllLoyaltyPrograms();
                for (LoyaltyProgram l : loyalty) {
                    if (l.getLoyaltyProgramId() == loyaltyProgramId) {
                    	db.insertCustomer(name, rewardPoints, loyaltyProgramId);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Customer customer = new Customer(0, name, rewardPoints,loyaltyProgramId);
                    customers.add(customer);
                }
            }
            if (!customers.isEmpty()) {
                System.err.println("Attention! This list displays customer whose loyalty_program_id was not found in the database, which led to their exclusion from the queue for additions.");
                for (Customer l : customers) {
                    System.out.println(l.toString());
                }
            }
            System.out.println("");
            System.out.println("Halls inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertTicketsFromCsv(String filePath) {// projectionId, purchaseDate, typeId, customerId
    	List<Ticket> tickets = new ArrayList<Ticket>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
            	boolean foundProjection=false;
            	boolean foundType=false;
            	boolean foundCustomer=false;
                String[] values = line.split(";");
                int projectionId = Integer.parseInt(values[0].trim());
                String purchaseDate = values[1].trim();
                int type = Integer.parseInt(values[2].trim());
                int customerId = Integer.parseInt(values[3].trim());
                List<Projection> pr = db.getAllProjections();
                List<Type> tp = db.getAllTypes();
                List<Customer> cm = db.getAllCustomers();
                for (Projection p : pr) {
                    if (p.getProjectionId() == projectionId) {
                        foundProjection = true;
                        break;
                    }
                }
                for (Type t : tp) {
                    if (t.getTypeId() == type) {
                        foundType = true;
                        break;
                    }
                }
                for (Customer c : cm) {
                    if (c.getCustomerId() == customerId) {
                        foundCustomer = true;
                        break;
                    }
                }
                if (foundCustomer&&foundProjection&&foundType) {
                	db.insertTicket(projectionId, purchaseDate, type, customerId);
                }else {
                	Ticket ticket = new Ticket(0, projectionId, purchaseDate, type, customerId);
                    tickets.add(ticket);
                }
                
            }
            if (!tickets.isEmpty()) {
                System.err.println("Attention! This list displays halls whose cinemaId was not found in the database, which led to their exclusion from the queue for additions.");
                for (Ticket t : tickets) {
                    System.out.print(t.toString());
                }
            }
            System.out.println("Tickets inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertLoyaltyProgramsFromCsv(String filePath) {// Not used, just for test(trash code)
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                int bonusPoints = Integer.parseInt(values[0].trim());
                double discount = Double.parseDouble(values[1].trim());
                db.insertLoyaltyProgram(bonusPoints, discount); // Вставка в базу данных
            }
            System.out.println("Loyalty Programs inserted successfully.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void readAndInsertSpecialEventsFromCsv(String filePath) {// name, date, specialPrices
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String name = values[0].trim();
                String date = values[1].trim();
                String specialPrices = values[2].trim();
                db.insertSpecialEvent(name, date, specialPrices); // Вставка в базу данных
            }
            System.out.println("Special Events inserted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void readAndInsertEventFilmsFromCsv(String filePath) {// 
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(";");
//                int eventId = Integer.parseInt(values[0].trim());
//                int filmId = Integer.parseInt(values[1].trim());
//                db.insertEventFilm(eventId, filmId); // Вставка в базу данных
//            }
//            System.out.println("Event Films inserted successfully.");
//        } catch (IOException | NumberFormatException e) {
//            e.printStackTrace();
//        }
//    }
    public List<Cinema> readCinemasFromCsv(String filePath) {// temp
        List<Cinema> cinemas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                String name = values[0].trim();
                String address = values[1].trim();
                int capacity = Integer.parseInt(values[2].trim());

                Cinema cinema = new Cinema(0, name, address, capacity);
                cinemas.add(cinema);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return cinemas;
    }

    public List<Headquarters> readHeadquartersFromCsv(String filePath) {// temp
        List<Headquarters> headquarters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                String name = values[0].trim();

                Headquarters hq = new Headquarters(0, name);
                headquarters.add(hq);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return headquarters;
    }

    public List<Studio> readStudiosFromCsv(String filePath) {// temp
        List<Studio> studios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                String name = values[0].trim();
                int headquartersId = Integer.parseInt(values[1].trim());

                Studio studio = new Studio(0, name, headquartersId);
                studios.add(studio);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return studios;
    }

    public List<Film> readFilmsFromCsv(String filePath) {// temp
        List<Film> films = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                String title = values[0].trim();
                String genre = values[1].trim();
                int duration = Integer.parseInt(values[2].trim());
                String director = values[3].trim();
                int studioId = Integer.parseInt(values[4].trim());

                Film film = new Film(0, title, genre, duration, director, studioId);
                films.add(film);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return films;
    }

    public List<Hall> readHallsFromCsv(String filePath) {// temp
        List<Hall> halls = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                int hallNumber = Integer.parseInt(values[0].trim());
                int cinemaId = Integer.parseInt(values[1].trim());
                int capacity = Integer.parseInt(values[2].trim());

                Hall hall = new Hall(0, hallNumber, cinemaId, capacity);
                halls.add(hall);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return halls;
    }

    public List<Projection> readProjectionsFromCsv(String filePath) {// temp
        List<Projection> projections = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                int filmId = Integer.parseInt(values[0].trim());
                int hallId = Integer.parseInt(values[1].trim());
                String date = values[2].trim();
                String startTime = values[3].trim();

                Projection projection = new Projection(0, filmId, hallId, date, startTime);
                projections.add(projection);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return projections;
    }

    public List<Customer> readCustomersFromCsv(String filePath) {// temp
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                String name = values[0].trim();
                double discount = Double.parseDouble(values[0].trim());
                int bonusPoints = Integer.parseInt(values[1].trim());
                int loyaltyProgramId = Integer.parseInt(values[2].trim());

                Customer customer = new Customer(0, name, bonusPoints, loyaltyProgramId);
                customers.add(customer);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public List<Ticket> readTicketsFromCsv(String filePath) { //temp
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                int projectionId = Integer.parseInt(values[0].trim());
                String purchaseDate = values[1].trim();
                int typeId = Integer.parseInt(values[2].trim());
                int customerId = Integer.parseInt(values[3].trim());

                Ticket ticket = new Ticket(0, projectionId, purchaseDate, typeId, customerId);
                tickets.add(ticket);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public List<LoyaltyProgram> readLoyaltyProgramsFromCsv(String filePath) {// temp
        List<LoyaltyProgram> programs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                int bonusPoints = Integer.parseInt(values[0].trim());
                double discount = Double.parseDouble(values[1].trim());

                LoyaltyProgram program = new LoyaltyProgram(0, bonusPoints, discount);
                programs.add(program);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return programs;
    }

    public List<SpecialEvent> readSpecialEventsFromCsv(String filePath) {// temp
        List<SpecialEvent> events = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                String name = values[0].trim();
                String date = values[1].trim();
                double specialPrices = Double.parseDouble(values[2].trim());

                SpecialEvent event = new SpecialEvent(0, name, date, specialPrices);
                events.add(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return events;
    }

    
}













