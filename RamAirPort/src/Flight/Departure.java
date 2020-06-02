package Flight;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Departure extends Flight {
	public static final char[] GATES= {'A','B','C','D','E','F'};

	private char gate;
	private LocalDateTime openGatesTime ;

	public Departure(String companyFlight,String cityConnect,int year,int month,int dayOfMonth,int hourFlight,int minuteFlight,char gate,eStatus status) {
		super(companyFlight, cityConnect, year, month, dayOfMonth, hourFlight, minuteFlight, status);
		setTimeGateOpen();
		setGate(gate);
	}
	public void setGate(char gate) {//exseption
		for (int i = 0; i < GATES.length; i++) {
			if (GATES[i]==gate) {
				this.gate=gate;
				return;
			}
		}
		setcheckInput(eInput.gateNotExsist);
		return;
	}
	public void setTimeGateOpen() {
		if (input==eInput.succeded) {
			this.openGatesTime=this.dateArrivOrLend.minusMinutes(45);
			return;
		}
	}
	public String toString() {
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatTime=DateTimeFormatter.ofPattern("hh:mm");
		StringBuffer back=new StringBuffer(Departure.class.getSimpleName()+"---> Date: "+dateArrivOrLend.format(format)+"\t"+
		"Time go: "+dateArrivOrLend.format(formatTime)+"\t"+"Open Gates: "+openGatesTime.format(formatTime)+
		"\t"+"Gate: "+this.gate+"\t"+"Flight Code: LY"+this.numFlight+"\t"+"City: "+this.cityConnect+"\t"+"Company: "
		+this.companyFlight+"\t"+"status: "+this.status);
		
		return back.toString();
	}
	@Override
	public Departure clone() throws CloneNotSupportedException {
		return (Departure)super.clone();
	}
	public void save(PrintWriter pw) {
		DateTimeFormatter formatTime=DateTimeFormatter.ofPattern("hh:mm");
		pw.print(this.getClass().getSimpleName()+",");
		super.save(pw);
		pw.println(","+this.openGatesTime.format(formatTime)+","+this.gate);
	}
}
