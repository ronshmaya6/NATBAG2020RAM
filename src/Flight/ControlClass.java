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
	private static final int NUMBER_OF_INPUTS_NEEDED = 14;


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
		while (compartToWithOutTime(firstDate,secondDate)!=1) {
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
		Arrival a3=new Arrival("el-al", "paris", 2022, 1, 2, 15, 35, eStatus.NotFinal);
		Arrival a4=new Arrival("el-al", "london", 2021, 1, 2, 15, 35, eStatus.Final);
		Arrival a5=new Arrival("el-al", "amsterdam", 2021, 1, 9, 15, 35, eStatus.Final);
		Arrival a6=new Arrival("el-al", "amsterdam", 2022, 1, 16, 1, 35, eStatus.Final);
		Arrival a7=new Arrival("el-al", "amsterdam", 2022, 1, 23, 5, 35, eStatus.Final);
		Arrival a8=new Arrival("el-al", "paris", 2022, 1, 9, 11, 35, eStatus.Final);
		Arrival a9=new Arrival("el-al", "paris", 2022, 1, 16, 14, 35, eStatus.Final);
		Arrival a10=new Arrival("el-al", "amsterdam", 2022, 1, 2, 15, 35, eStatus.Final);
		Departure d1=new Departure("el-al", "amsterdam", 2022, 1, 2, 15, 35,'A', eStatus.Final);
		Departure d2=new Departure("el-al", "amsterdam", 2022, 1, 9, 1, 35,'B', eStatus.Final);
		Departure d3=new Departure("el-al", "amsterdam", 2022, 1, 16, 11, 35,'C', eStatus.Final);
		Departure d4=new Departure("el-al", "amsterdam", 2022, 1, 23, 13, 35,'D', eStatus.Final);
		Departure d5=new Departure("el-al", "amsterdam", 2022, 1, 30, 5, 35,'E', eStatus.Final);
		Departure d6=new Departure("el-al", "paris", 2022, 1, 2, 15, 35,'D', eStatus.Final);
		Departure d7=new Departure("el-al", "paris", 2022, 1, 9, 1, 45,'A', eStatus.Final);
		Departure d8=new Departure("el-al", "paris", 2022, 1, 16, 3, 35,'B', eStatus.Final);
		Departure d9=new Departure("el-al", "paris", 2022, 1, 23, 7, 35,'C', eStatus.Final);
		Departure d10=new Departure("el-al", "paris", 2022, 1, 30, 9, 30,'A', eStatus.Final);
		flight.add(a1);
		flight.add(a2);
		flight.add(a3);
		flight.add(a4);
		flight.add(a5);
		flight.add(a6);
		flight.add(a7);
		flight.add(a8);
		flight.add(a9);
		flight.add(a10);
		flight.add(d1);
		flight.add(d2);
		flight.add(d3);
		flight.add(d4);
		flight.add(d5);
		flight.add(d6);
		flight.add(d7);
		flight.add(d8);
		flight.add(d9);
		flight.add(d10);

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
	public String getFlightsHtml(String[] args,String className) {
		StringBuffer back=new StringBuffer();
		if (args.length != NUMBER_OF_INPUTS_NEEDED) {
			back.append("the inputs not ok you have -> "+args.length+" inputs, you need-> "+NUMBER_OF_INPUTS_NEEDED);
		}
		else {
			html(back,className);
			int counterArgs;
			int counterFlightFound=0;
			for (int i = 0; i < flight.size(); i++) {
				counterArgs=3;
				if (className.equals(flight.get(i).getClass().getSimpleName())) {
					if (flight.get(i).getCityConnect().equals(args[counterArgs++])) {
						counterArgs++;
						if (flight.get(i).getCompanyFlight().equalsIgnoreCase(args[counterArgs++])) {
							LocalDateTime firstDate=LocalDateTime.of(Integer.parseInt(args[(counterArgs+2)]),  Integer.parseInt(args[(counterArgs+1)])
									, Integer.parseInt(args[counterArgs]), 0,0);
							counterArgs=counterArgs+3;
							LocalDateTime secondDate=LocalDateTime.of(Integer.parseInt(args[(counterArgs+2)]),  Integer.parseInt(args[(counterArgs+1)])
									, Integer.parseInt(args[counterArgs]), 0,0);
							counterArgs=counterArgs+3;
							if (isdatesOkHtml(firstDate, secondDate)) {
								if (firstDate.compareTo(flight.get(i).getDate()) <= 0 && secondDate.compareTo(flight.get(i).getDate()) >= 0) {
									if (args[counterArgs++].equalsIgnoreCase(String.valueOf(flight.get(i).getDate().getDayOfWeek())) ||
											args[counterArgs].equalsIgnoreCase(String.valueOf(flight.get(i).getDate().getDayOfWeek()))) {
										addFlightHtmlView(back,i,className);
										counterFlightFound++;
									}
								}

							}
						}
						

					}
				}
			}
			endViewHtmal(back,counterFlightFound);
		}
		return back.toString();
	}

	private boolean isdatesOkHtml(LocalDateTime firstDate,LocalDateTime secondDate) {
		if (compartToWithOutTime(firstDate,secondDate)==1) {
			return false;
		}
		return true;
	}
	private void html(StringBuffer back,String className) {
		back.append("<h1 style=\"text-align: center;\"><span style=\"background-color: #ff00ff;\">"+className+"</span></h1>"
				+"<table style=\"border-color: black; background-color: #ffff00;\" border=\"3\">"
				+"<tbody>"
				+"<tr>"
				+"<td style=\"width: 40.8px;\"><strong>Date</strong></td>"
				+"<td style=\"width: 42.4px;\"><strong>Time go</strong></td>"
				+"<td style=\"width: 49.6px;\"><strong>Flight Code</strong></td>"
				+"<td style=\"width: 49.6px;\"><strong>city</strong></td>"
				+"<td style=\"width: 80.8px;\"><strong>Company</strong></td>"
				+"<td style=\"width: 56.8px;\"><strong>Status</strong></td>");
		if (className.equals(Departure.class.getSimpleName())) {
			back.append("<td style=\"width: 49.6px;\"><strong>Gate</strong></td>"
					+"<td style=\"width: 44px;\"><strong>Open Gates</strong></td>");
		}
		back.append("</tr>");

	}
	private void addFlightHtmlView(StringBuffer back, int i,String className) {
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatTime=DateTimeFormatter.ofPattern("hh:mm");
		back.append("<tr>"
				+"<td style=\"width: 40.8px;\">"+flight.get(i).getDate().format(format)+";</td>"
				+"<td style=\"width: 42.4px;\">"+flight.get(i).getDate().format(formatTime)+"</td>"
				+"<td style=\"width: 49.6px;\">"+"LY"+flight.get(i).getNumFlight()+"</td>"
				+"<td style=\"width: 49.6px;\">"+flight.get(i).getCityConnect()+"</td>"
				+"<td style=\"width: 80.8px;\">"+flight.get(i).getCompanyFlight()+"</td>"
				+"<td style=\"width: 56.8px;\">"+flight.get(i).getStatus()+"</td>");

		if (className.equals(Departure.class.getSimpleName())) {
			back.append("<td style=\"width: 49.6px;\">"+((Departure)flight.get(i)).getGate()+"</td>"
					+"<td style=\"width: 44px;\">"+((Departure)flight.get(i)).getOpenGetTime().format(formatTime)+"</td>");
		}
		back.append("</tr>");
	}
	private void endViewHtmal(StringBuffer back,int counterFlightFound) {
		back.append("</tbody>"
				+"</table>"
				+"<p>found-> "+counterFlightFound+" Flights</p>");
	}
}
