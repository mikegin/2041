import java.util.*;
import java.io.*;
import java.lang.*;
public class MG_capital{
	public static void main(String [] args){
		
		System.out.println(cgi_lib.Header());		
		
		Hashtable form_data = cgi_lib.ReadParse(System.in);
		
		System.out.println(cgi_lib.HtmlTop("Capital Accumulation" ));
		
		String fn = (String)form_data.get("MG_first_name");

		String ln = (String)form_data.get("MG_last_name");
		
		String cp = (String)form_data.get("MG_capital");
		double cap = Double.parseDouble(cp);
		
 		String rt = (String)form_data.get("MG_rate");
		double rat = Double.parseDouble(rt);
		
		String yr = (String)form_data.get("MG_years");
		double yer = Double.parseDouble(yr);
		
		

		System.out.println("<div align=\"center\" style=\"background-color:yellow; font-family:arial;\"><p style=\"font-weight:bold;\">First name: " + fn + 
				"<br /><br />Last name: " + ln + 
				"</p><p style=\"font-size:90%;font-weight:bold;\">Your initial capital: " + cap + 
				"<br /><br />Your interest rate: " + rat +"</p></div><br />");
		
		double amount = 0;
		System.out.println("<div align=\"center\" style=\"background-color:gray;\"><p style=\"font-size:85%;font-weight:bold;font-family:arial\">");
		for(int i = 1; i <= yer; i++){
			amount = cap*rat;
			cap+=amount;
			System.out.println("Interest amount after " + i + " year is " +
				amount);
			if(yer>i){
				System.out.println("<br /><br />");
			}
		}
		System.out.println("</div><br />");
		

		System.out.println("<div align=\"center\" style=\"font-family:arial;font-weight:bold;\">Your capital after " + yer + " year(s) is: " + cap + "</div>");  

		System.out.println(cgi_lib.HtmlBot());
	}
}