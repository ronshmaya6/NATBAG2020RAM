package Flight;

import java.time.LocalDateTime;

import Flight.Flight.eStatus;

public class Arrival extends Flight {
	public Arrival(String companyFlight,String cityConnect,int year,int month,int dayOfMonth,int hour,int minute,eStatus status) {
		super(companyFlight,cityConnect,year,month,dayOfMonth,hour,minute,status);
	}
	@Override
	public Arrival clone() throws CloneNotSupportedException {
		return (Arrival)super.clone();
	}
	public String toString() {
		StringBuffer back=new StringBuffer(Arrival.class.getSimpleName()+"--->");
		back.append(super.toString());
		return back.toString();
	}
}
