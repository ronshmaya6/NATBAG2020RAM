package Tests;

//import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

//import org.junit.jupiter.api.Test;

import Flight.Arrival;
import Flight.Flight;
import Flight.Flight.eInput;
import Flight.Flight.eStatus;
import javafx.util.converter.LocalDateTimeStringConverter;
import Flight.ControlClass;

class TestsControlClass {
/*
	@Test
	public void testShowAllArrival() {
		ControlClass cn = new ControlClass();
		StringBuffer showArrivalStringBuff = new StringBuffer();
		showArrivalStringBuff.append("Arrivals----->\n");
		showArrivalStringBuff.append("Arrival--->Date: 02/01/2022"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY20"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		showArrivalStringBuff.append("Arrival--->Date: 03/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY21"+"\t"+"City: berlin"+"\t"+"Company: el-al"+"\t"+"status: Delay\n");
		showArrivalStringBuff.append("Arrival--->Date: 02/04/2021"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY22"+"\t"+"City: singapor"+"\t"+"Company: el-al"+"\t"+"status: NotFinal\n");
		showArrivalStringBuff.append("Arrival--->Date: 02/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY23"+"\t"+"City: london"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		showArrivalStringBuff.append("Arrival--->Date: 02/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY24"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		assertEquals(showArrivalStringBuff.toString(), cn.showAllArrival());
	}
	
	@Test
	public void testShowAllDeparture() {
		ControlClass cn = new ControlClass();
		StringBuffer showDepartureStringBuff = new StringBuffer();
		showDepartureStringBuff.append("Departure----->\n");
		showDepartureStringBuff.append("Departure--->Date: 02/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Open Gates: 02:50"+"\t"+"Gate: A"+"\t"+"Flight Code: LY45"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		showDepartureStringBuff.append("Departure--->Date: 03/01/2022"+"\t"+ "Time go: 03:35"+"\t"+"Open Gates: 02:50"+"\t"+"Gate: B"+"\t"+"Flight Code: LY46"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		showDepartureStringBuff.append("Departure--->Date: 04/01/2022"+"\t"+ "Time go: 03:35"+"\t"+"Open Gates: 02:50"+"\t"+"Gate: C"+"\t"+"Flight Code: LY47"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		showDepartureStringBuff.append("Departure--->Date: 05/01/2022"+"\t"+ "Time go: 03:35"+"\t"+"Open Gates: 02:50"+"\t"+"Gate: D"+"\t"+"Flight Code: LY48"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		showDepartureStringBuff.append("Departure--->Date: 06/01/2022"+"\t"+ "Time go: 03:35"+"\t"+"Open Gates: 02:50"+"\t"+"Gate: E"+"\t"+"Flight Code: LY49"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		assertEquals(showDepartureStringBuff.toString(), cn.showAllDeparture());
	}
	
	@Test
	public void testSearchArrivalByDate() {
		ControlClass cn = new ControlClass();
		StringBuffer searchArrivalByDate = new StringBuffer();
		searchArrivalByDate.append("Arrival--->Date: 02/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY13"+"\t"+"City: london"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		searchArrivalByDate.append("Arrival--->Date: 02/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY14"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		assertEquals(searchArrivalByDate.toString(), cn.searchArriavbyDate(LocalDateTime.of(2021, 1, 2, 0, 0)));
	}
	
	@Test
	public void testSearchDepbyDate() {
		ControlClass cn = new ControlClass();
		StringBuffer searchDepByDate = new StringBuffer();
		searchDepByDate.append("Departure--->Date: 02/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Open Gates: 02:50"+"\t"+"Gate: A"+"\t"+"Flight Code: LY35"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		assertEquals(searchDepByDate.toString(), cn.searchDepbyDate(LocalDateTime.of(2021, 1, 2, 0, 0)));
	}
	
	@Test
	public void testSearchArriavbCityConnect() {
		ControlClass cn = new ControlClass();
		StringBuffer searchArrByCityConn = new StringBuffer();
		searchArrByCityConn.append("Arrival--->Date: 02/01/2022"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY0"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		searchArrByCityConn.append("Arrival--->Date: 02/01/2021"+"\t"+ "Time go: 03:35"+"\t"+"Flight Code: LY4"+"\t"+"City: amsterdam"+"\t"+"Company: el-al"+"\t"+"status: Final\n");
		assertEquals(searchArrByCityConn.toString(), cn.searchArriavbCityConnect("amsterdam"));
		
	}
	
*/
}
