package Flight;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Flight.Flight.eInput;
import Flight.Flight.eStatus;

public class ControlClass {
	public static final int TO_ADD_ARRIVAL=1; 
	public static final int TO_ADD_DEPARTURE=2;
	public static final int TO_SHOW_ALL_ARRIVAL=3;
	public static final int TO_SHOW_ALL_DEPARTURE=4;
	public static final int TO_SEARCH_ARRIVAL_BY_DATE=5;
	public static final int TO_SEARCH_DEPARTURE_BY_DATE=6;
	public static final int TO_SEARCH_ARRIVAL_BY_CITY=7;
	public static final int TO_SEARCH_DEPARTURE_BY_CITY=8;
	public static final int TO_SEARCH_ARRIVAL_DATE_TO_DATE=9;
	public static final int TO_SEARCH_DEPARTURE_DATE_TO_DATE=10;
	public static final int TO_SEARCH_ARRIVAL_DATE_TO_DATE_AND_CITY=11;
	public static final int TO_SEARCH_DEPARTURE_DATE_TO_DATE_AND_CITY=12;
	public static final int TO_WRITE_TO_FILE=13;
	public static final int TO_READ_FROM_FILE=14;
	public static final int TO_EXIT_MENU=15;


	private List<Flight> flight;
	private boolean isNeedToSort;

	public ControlClass() {
		this.flight=new ArrayList<>();
		isNeedToSort=false;
		hardCoded();
	}
	public ControlClass(Scanner src) {
		this.flight=new ArrayList<>();
		isNeedToSort=false;
		read(src);
	}
	public eInput addFlight(Flight flight) throws CloneNotSupportedException {
		if (flight.getcheckInput()!=eInput.succeded) {
			return flight.getcheckInput();
		}
		if (checkIfEqulas(this.flight,flight)) {
			return eInput.flightOnList;
		}
		this.flight.add(flight.clone());
		isNeedToSort=true;
		return eInput.succeded;
	}
	private <T extends Flight> boolean checkIfEqulas(List<T> arr,T otherFlight) {
		return arr.contains(otherFlight);
	}
	private void sortFlight() {
		Collections.sort(flight);
	}
	public String showAllArrival() {
		if (isNeedToSort) {
			sortFlight();
			this.isNeedToSort=false;
		}
		StringBuffer back=new StringBuffer("Arrivals----->\n");
		for (int i = 0; i < flight.size(); i++) {
			if (flight.get(i) instanceof Arrival) {
				back.append(flight.get(i).toString()+"\n");
			}
		}
		return back.toString();
	}
	public String showAllDeparture() {
		if (isNeedToSort) {
			sortFlight();
			this.isNeedToSort=false;
		}
		StringBuffer back=new StringBuffer("Departure----->\n");
		for (int i = 0; i < flight.size(); i++) {
			if (flight.get(i) instanceof Departure) {
				back.append(flight.get(i).toString()+"\n");
			}
		}
		return back.toString();
	}
	public String searchArriavbyDate(LocalDateTime date) {
		return searchByDate(date,Arrival.class.getSimpleName(),true);
	}
	public String searchDepbyDate(LocalDateTime date) {
		return searchByDate(date,Departure.class.getSimpleName(),true);
	}
	private String searchByDate(LocalDateTime date,String className,boolean needToPrintEmpty) {
		StringBuffer back=new StringBuffer();
		for (int i = 0; i < flight.size(); i++) {
			if (className.equals(flight.get(i).getClass().getSimpleName())) {
				if (flight.get(i).getDate().getDayOfMonth()==date.getDayOfMonth() && flight.get(i).getDate().getMonthValue()==date.getMonthValue() && 
						flight.get(i).getDate().getYear()==date.getYear()) {
					back.append(flight.get(i));
					back.append("\n");
				}
			}
		}
		if (back.toString().isEmpty() && needToPrintEmpty) {
			return "sorry flight not found";
		}
		return back.toString();
	}
	public String searchArriavbCityConnect(String city) {
		return searchByCityConnect(city,Arrival.class.getSimpleName());
	}
	public String searchDepCityConnect(String city) {
		return searchByCityConnect(city,Departure.class.getSimpleName());
	}
	private String searchByCityConnect(String city,String className) {
		StringBuffer back=new StringBuffer();
		for (int i = 0; i < flight.size(); i++) {
			if (className.equals(flight.get(i).getClass().getSimpleName())) {
				if (flight.get(i).getCityConnect().equals(city)) {
					back.append(flight.get(i));
					back.append("\n");
				}
			}
		}
		if (back.toString().isEmpty()) {
			return "sorry flight not found";
		}
		return back.toString();
	}
	public String searchArriavbyDateToDate(LocalDateTime firstDate,LocalDateTime secondDate) {
		return searchByDateToDate(firstDate,secondDate,Arrival.class.getSimpleName());
	}
	public String searchDepByToDate(LocalDateTime firstDate,LocalDateTime secondDate) {
		return searchByDateToDate(firstDate,secondDate,Departure.class.getSimpleName());
	}
	private String searchByDateToDate(LocalDateTime firstDate,LocalDateTime secondDate,String className) {
		StringBuffer back=new StringBuffer();
		if (compartToWithOutTime(firstDate,secondDate)==1) {
			return "The days input not ok";
		}
		int counter=0;
		while (compartToWithOutTime(firstDate,secondDate)!=1 && flight.size()>counter) {
			back.append(searchByDate(firstDate, className,false));
			firstDate=firstDate.plusDays(1);
			counter++;
		}
		if (back.length()==0) {
			return "Sorry Not Found";
		}
		return back.toString();
	}
	public String searchArriavbyDateToDateAndCity(LocalDateTime firstDate,LocalDateTime secondDate,String city) {
		return searchByDateToDateAndCity(firstDate,secondDate,Arrival.class.getSimpleName(),city);
	}
	public String searchDepByToDateAndCity(LocalDateTime firstDate,LocalDateTime secondDate,String city) {
		return searchByDateToDateAndCity(firstDate,secondDate,Departure.class.getSimpleName(),city);
	}
	private String searchByDateToDateAndCity(LocalDateTime firstDate,LocalDateTime secondDate,String className,String city) {
		StringBuffer back=new StringBuffer();
		if (compartToWithOutTime(firstDate,secondDate)==1) {
			return "The days input not ok";
		}
		int counter=0;
		while (compartToWithOutTime(firstDate,secondDate)!=1 && flight.size()>counter) {
			if (className.equals(flight.get(counter).getClass().getSimpleName())) {
				if (flight.get(counter).isSameDate(firstDate) && flight.get(counter).getCityConnect().equals(city)) {
					back.append(flight.get(counter));
					back.append("\n");
				}
			}
			firstDate=firstDate.plusDays(1);
			counter++;
		}
		if (back.length()==0) {
			return "sorry not found";
		}
		return back.toString();
	}
	private int compartToWithOutTime(LocalDateTime day1,LocalDateTime day2) {
		if (day1.getDayOfMonth()==day2.getDayOfMonth() && day1.getMonthValue()==day2.getMonthValue() && day1.getYear()==day2.getYear()) {
			return 0;
		}
		else {
			return day1.compareTo(day2);
		}

	}
	private void hardCoded() {
		Arrival a1=new Arrival("el-al", "amsterdam", 2022, 1, 2, 15, 35, eStatus.Final);
		Arrival a2=new Arrival("el-al", "berlin", 2021, 1, 3, 15, 35, eStatus.Delay);
		Arrival a3=new Arrival("el-al", "singapor", 2021, 4, 2, 15, 35, eStatus.NotFinal);
		Arrival a4=new Arrival("el-al", "london", 2021, 1, 2, 15, 35, eStatus.Final);
		Arrival a5=new Arrival("el-al", "amsterdam", 2021, 1, 2, 15, 35, eStatus.Final);
		Departure d1=new Departure("el-al", "amsterdam", 2021, 1, 2, 15, 35,'A', eStatus.Final);
		Departure d2=new Departure("el-al", "amsterdam", 2022, 1, 3, 15, 35,'B', eStatus.Final);
		Departure d3=new Departure("el-al", "amsterdam", 2022, 1, 4, 15, 35,'C', eStatus.Final);
		Departure d4=new Departure("el-al", "amsterdam", 2022, 1, 5, 15, 35,'D', eStatus.Final);
		Departure d5=new Departure("el-al", "amsterdam", 2022, 1, 6, 15, 35,'E', eStatus.Final);
		flight.add(a1);
		flight.add(a2);
		flight.add(a3);
		flight.add(a4);
		flight.add(a5);
		flight.add(d1);
		flight.add(d2);
		flight.add(d3);
		flight.add(d4);
		flight.add(d5);

	}
	public void save(PrintWriter pw) {
		for (int i = 0; i < flight.size(); i++) {
			flight.get(i).save(pw);
		}
	}
	public void read(Scanner src) {
		while (src.hasNextLine()) {
			String line=src.nextLine();
			String[] lineFull=line.split(",");
			String[] time=lineFull[2].split(":");
			String[] date=lineFull[1].split("/");
			if (lineFull[0].equals(Arrival.class.getSimpleName())) {
				flight.add(new Arrival(lineFull[5], lineFull[4], Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]),
						Integer.parseInt(time[0]), Integer.parseInt(time[1]), eStatus.valueOf(lineFull[6])));
			}
			else {
				flight.add(new Departure(lineFull[5], lineFull[4], Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]),
						Integer.parseInt(time[0]), Integer.parseInt(time[1]),lineFull[7].charAt(0), eStatus.valueOf(lineFull[6])));
			}
			
		}
	}
}
