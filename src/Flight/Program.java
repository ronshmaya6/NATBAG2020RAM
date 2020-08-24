package Flight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

import Flight.Flight.eInput;
import Flight.Flight.eStatus;

public class Program {

	public static void main(String[] args) throws CloneNotSupportedException, FileNotFoundException {
		boolean isHtml= args.length > 0 && args[0].equalsIgnoreCase("html");
		Scanner src=new Scanner(System.in);
		ControlClass control=new ControlClass();
		int choice;
		if (isHtml) {
			boolean isDepartures= args.length > 1 && args[1].equalsIgnoreCase("departures");
			String ans;
			if (isDepartures) {
				ans=control.getFlightsHtml(args, Departure.class.getSimpleName());	
			}
			else {
				ans=control.getFlightsHtml(args, Arrival.class.getSimpleName());
			}
			System.out.println(ans);
		}
		else {
			do {
				menu();
				choice=src.nextInt();
				switch (choice) {
				case ControlClass.TO_ADD_ARRIVAL:
					addFlight(control, src,Arrival.class.getSimpleName());
					break;
				case ControlClass.TO_ADD_DEPARTURE:
					addFlight(control, src,Departure.class.getSimpleName());
					break;
				case ControlClass.TO_SHOW_ALL_ARRIVAL:
					System.out.println(control.showAllArrival());
					break;
				case ControlClass.TO_SHOW_ALL_DEPARTURE:
					System.out.println(control.showAllDeparture());
					break;
				case ControlClass.TO_SEARCH_ARRIVAL_BY_DATE:
					LocalDateTime dateToCheckArrival=getDateFromUser(control, src);
					System.out.println(control.searchArriavbyDate(dateToCheckArrival));
					break;
				case ControlClass.TO_SEARCH_DEPARTURE_BY_DATE:
					LocalDateTime dateToCheckDep=getDateFromUser(control, src);
					System.out.println(control.searchArriavbyDate(dateToCheckDep));
					break;
				case ControlClass.TO_SEARCH_ARRIVAL_BY_CITY:
					System.out.println("enter city");
					src.nextLine();
					String city=src.nextLine();
					System.out.println(control.searchArriavbCityConnect(city));
					break;
				case ControlClass.TO_SEARCH_DEPARTURE_BY_CITY:
					System.out.println("enter city");
					src.nextLine();
					String city2=src.nextLine();
					System.out.println(control.searchDepCityConnect(city2));
					break;
				case ControlClass.TO_SEARCH_ARRIVAL_DATE_TO_DATE:
					System.out.println(control.searchArriavbyDateToDate(getDateFromUser(control, src), getDateFromUser(control, src)));
					break;
				case ControlClass.TO_SEARCH_DEPARTURE_DATE_TO_DATE:
					System.out.println(control.searchDepByToDate(getDateFromUser(control, src), getDateFromUser(control, src)));
					break;
				case ControlClass.TO_SEARCH_ARRIVAL_DATE_TO_DATE_AND_CITY:
					System.out.println("enter city");
					src.nextLine();
					String city3=src.nextLine();
					System.out.println(control.searchArriavbyDateToDateAndCity(getDateFromUser(control, src), getDateFromUser(control, src), city3));
					break;
				case ControlClass.TO_SEARCH_DEPARTURE_DATE_TO_DATE_AND_CITY:
					System.out.println("enter city");
					src.nextLine();
					String city4=src.nextLine();
					System.out.println(control.searchDepByToDateAndCity(getDateFromUser(control, src), getDateFromUser(control, src), city4));
					break;
				case ControlClass.TO_WRITE_TO_FILE:
					writeToFile(control);
					break;
				case ControlClass.TO_READ_FROM_FILE:
					readFromFile(src,control);
					break;
				case ControlClass.TO_EXIT_MENU:
					break;
				default:
					System.out.println("input uncorrect");
					break;
				}
			} while (choice!=ControlClass.TO_EXIT_MENU);
		}
		src.close();
	}
	public static void menu() {
		System.out.println("menu--->");
		System.out.println("to add Arrival--->"+ControlClass.TO_ADD_ARRIVAL);
		System.out.println("to add Departure--->"+ControlClass.TO_ADD_DEPARTURE);
		System.out.println("to show all Arrival--->"+ControlClass.TO_SHOW_ALL_ARRIVAL);
		System.out.println("to show all Departure--->"+ControlClass.TO_SHOW_ALL_DEPARTURE);
		System.out.println("to search arrival by date--->"+ControlClass.TO_SEARCH_ARRIVAL_BY_DATE);
		System.out.println("to search departure by date--->"+ControlClass.TO_SEARCH_DEPARTURE_BY_DATE);
		System.out.println("to search arrival by city--->"+ControlClass.TO_SEARCH_ARRIVAL_BY_CITY);
		System.out.println("to search departure by city--->"+ControlClass.TO_SEARCH_DEPARTURE_BY_CITY);
		System.out.println("to search arrival by date to date--->"+ControlClass.TO_SEARCH_ARRIVAL_DATE_TO_DATE);
		System.out.println("to search departure by date to date--->"+ControlClass.TO_SEARCH_DEPARTURE_DATE_TO_DATE);
		System.out.println("to search arrival date to date and city--->"+ControlClass.TO_SEARCH_ARRIVAL_DATE_TO_DATE_AND_CITY);
		System.out.println("to search departure date to date and city--->"+ControlClass.TO_SEARCH_DEPARTURE_DATE_TO_DATE_AND_CITY);
		System.out.println("to write to file--->"+ControlClass.TO_WRITE_TO_FILE);
		System.out.println("to read from file--->"+ControlClass.TO_READ_FROM_FILE);
		System.out.println("to Exit menu--->"+ControlClass.TO_EXIT_MENU);
	}
	public static void addFlight(ControlClass control,Scanner src,String className) throws CloneNotSupportedException {
		String city,company;
		eStatus status=eStatus.OnTime;
		int year,month,day,hour,minute,statusNum;
		boolean inputOk=false;
		src.nextLine();
		System.out.println("please enter date of flight--->  day mount year, year between "+Flight.MIN_YEAR+"--->"+Flight.MAX_YEAR);
		day=src.nextInt();
		month=src.nextInt();
		year=src.nextInt();
		System.out.println("please enter time arrival of flight--->  hour minute");
		hour=src.nextInt();
		minute=src.nextInt();
		src.nextLine();
		System.out.println("please enter city of flight");
		city=src.nextLine();
		System.out.println("please enter company of the flight");
		company=src.nextLine();
		while (!inputOk) {
			System.out.println("please enter status of the flight---> 0 for-> OnTime, 1 for-> Final,2 for-> NotFinal,3 for->Canceld,4 for->Delay");
			statusNum=src.nextInt();
			src.nextLine();
			if (statusNum!=0 && statusNum!=1 && statusNum!=2 && statusNum!=3 && statusNum!=4) {
				System.out.println("uncorrect input");
			}
			else {
				if (statusNum==0) {
					status=eStatus.OnTime;
				}
				else if (statusNum==1) {
					status=eStatus.Final;
				}
				else if (statusNum==2) {
					status=eStatus.NotFinal;
				}
				else if (statusNum==3) {
					status=eStatus.Canceld;
				}
				else if (statusNum==4) {
					status=eStatus.Delay;
				}
				inputOk=true;
			}
		}
		if (className.equals(Arrival.class.getSimpleName())) {
			Arrival a1=new Arrival(company, city, year, month, day, hour, minute, status);
			eInput ans=control.addFlight(a1);
			System.out.println(ans);
			if (ans!=eInput.succeded) {
				System.out.println("please try again");
			}
		}
		else {
			System.out.println("please enter gate --->"+Arrays.toString(Departure.GATES));
			char get;
			get=src.nextLine().charAt(0);
			Departure b1=new Departure(company, city, year, month, day, hour, minute, get, status);
			eInput ans=control.addFlight(b1);
			System.out.println(ans);
			if (ans!=eInput.succeded) {
				System.out.println("please try again");
			}	
		}
	}
	public static LocalDateTime getDateFromUser(ControlClass controlClass,Scanner src) {
		int year,mounth,day;
		System.out.println("please enter date---> day mounth year");
		day=src.nextInt();
		mounth=src.nextInt();
		year=src.nextInt();
		src.nextLine();
		return LocalDateTime.of(year, mounth, day, 0,0);
	}
	public static void writeToFile(ControlClass control) throws FileNotFoundException {
		PrintWriter pw=new PrintWriter(new File("Flight1.xslx"));
		pw.println("Type,Date,Time go,Flight Code,City,Company,Status,Open Gates,Gate");
		control.save(pw);
		pw.close();
	}
	public static void readFromFile(Scanner src, ControlClass control) throws FileNotFoundException {
		src=new Scanner(new File("Flight1.xslx"));
		control=new ControlClass(src);
	}
}
