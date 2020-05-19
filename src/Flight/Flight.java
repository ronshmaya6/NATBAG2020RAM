package Flight;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public abstract class Flight implements Comparable<Flight>,Cloneable  {
	public static enum eInput {succeded,dateInputNotOk,flightOnList,timeInputNotOk,cityInputNotOk, companyInputNotOk, gateNotExsist};
	public static enum eStatus {OnTime,Final,NotFinal,Canceld,Delay};
	public static int numberOfFlight=0;
	public static final int MAX_YEAR=2025;
	public static final int MIN_YEAR=2021;
	protected int numFlight;
	protected eStatus status;
	protected String companyFlight;
	protected String cityConnect;
	protected LocalDateTime dateArrivOrLend;
	protected eInput input;

	public Flight(String companyFlight,String cityConnect,int year,int month,int dayOfMonth,int hour,int minute,eStatus status) {
		input=eInput.succeded;
		setDate(year,month,dayOfMonth,hour,minute);
		setCompanyFlight(companyFlight);
		setCityConnect(cityConnect);
		this.status=status;
		if (input==eInput.succeded) {
			numFlight=numberOfFlight++;
		}
	}
	public  void setDate(int year, int month, int dayOfMonth, int hour, int minute) {
		if (year>=MIN_YEAR && year<=MAX_YEAR && month>0 && month<=12) {
			YearMonth yearMonth=YearMonth.of(year, month);
			if (yearMonth.lengthOfMonth()>=dayOfMonth && dayOfMonth>0) {
				if (hour>=0 && hour<24 && minute>=0 && minute<60) {
					this.dateArrivOrLend=LocalDateTime.of(year, month, dayOfMonth, hour, minute);
					return;
				}
				else {
					setcheckInput(eInput.timeInputNotOk);
					return;
				}
			}
		}
		setcheckInput(eInput.dateInputNotOk);
	}
	public void setCityConnect(String cityConnect) {
		if (cityConnect!="" && cityConnect!=null) {
			this.cityConnect=cityConnect;
			return;
		}
		setcheckInput(eInput.cityInputNotOk);
	}
	public void setCompanyFlight(String companyFlight) {//exseption
		if (companyFlight!="" && companyFlight!=null) {
			this.companyFlight=companyFlight;
			return;
		}
		setcheckInput(eInput.companyInputNotOk);
		return;
	}
	public LocalDateTime getDate() {
		return this.dateArrivOrLend;
	}
	public String getCityConnect() {
		return this.cityConnect;
	}
	public String toString() {
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatTime=DateTimeFormatter.ofPattern("hh:mm");
		StringBuffer back=new StringBuffer("Date: "+dateArrivOrLend.format(format)+"\t"+"Time go: "+dateArrivOrLend.format(formatTime)+"\t"+"Flight Code: LY"+
				this.numFlight+"\t"+"City: "+this.cityConnect+"\t"+"Company: "+this.companyFlight+"\t"+"status: "+this.status);
		return back.toString();
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof Flight)) {
			return false;
		}
		if (this.numFlight!=((Flight)obj).numFlight) {
			return false;
		}
		return true;
	}
	protected void setcheckInput(eInput e1) {
		if (e1!=eInput.succeded) {
			input=e1;
		}
	}
	public eInput getcheckInput() {
		return input;
	}
	@Override
	public int compareTo(Flight other) {
		return this.dateArrivOrLend.compareTo(other.dateArrivOrLend);
	}
	@Override
	public Flight clone() throws CloneNotSupportedException {
		return (Flight)super.clone();
	}
	public boolean isSameDate(LocalDateTime other) {
		if (this.dateArrivOrLend.compareTo(other)==0) {
			return true;
		}
		else {
			return false;
		}
				
	}
}
