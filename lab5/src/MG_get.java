import java.util.*; 
import java.io.*;

public class MG_get
{

  public static void main(String[] args)
  {

      //
      //  Print the required CGI header.
      //
      System.out.println(cgi_lib.Header());

      //
      //  Parse the form data into a Hashtable.
      //
      Hashtable form_data = cgi_lib.ReadParse(System.in);

      // store in java variables the data sent by the browser
    
      	String id = (String)form_data.get("MG_cust_ID_old");
	String password = (String)form_data.get("MG_password_old");
	  //new customer
	  Boolean IsCustomer = true; 
	  if(id.equals(""))
	  {
	        id = (String)form_data.get("MG_cust_ID_new");
			password = (String)form_data.get("MG_password_new");
			IsCustomer = false;
	  }
	  
	  System.out.println(cgi_lib.HtmlTop("Client Message" ));
	  File dataFile =  new  File("passwords.txt");
	  Boolean found = false;
	  //old customer
	  try
	  {
		 Scanner fileScanner = new Scanner(dataFile);
		 while (fileScanner.hasNextLine()) 
		 {
                String line = fileScanner.nextLine();
                StringTokenizer st = new StringTokenizer(line, ",");
				 if (st.hasMoreTokens()) 
				 {
					if(st.nextToken().equals(id))
					{
						if(st.hasMoreTokens())
						{
							if(st.nextToken().equals(password))
							{
								found = true;
								break;
							}
						}
					}
				 }
				 
         }
         fileScanner.close();
	  }
	  catch(FileNotFoundException e)
	  {
		 found = false;
	  }
	  
	  if(found)
	  {
			if(IsCustomer)
			{
				System.out.println("<h2 style=\"background: yellow\">WELCOME BACK,<br />DEAR CUSTOMER!</h2>");
			}
			else
			{
				System.out.println("<h2 style=\"background: grey\">You are already in our file.<br />Go back!<br />Your customer ID is: " + id +"<br />and password: " + password + "<br /></h2>");
			}
	  }
	  else
	  {
			if(IsCustomer)
			{
				System.out.println("<h2 style=\"background: grey\">SORRY,<br />you are not in our file!</h2>");
			}
			else
			{
				try 
				{
					BufferedWriter output = new BufferedWriter(new FileWriter(dataFile,true));
					output.write(id + "," + password + "\n");
					output.close();
				} 
				catch ( IOException e ) 
				{
					e.printStackTrace();
				}
				System.out.println("<h2 style=\"background: yellow\">CONGRATULATIONS!!<br />DEAR NEW CUSTOMER<br />YOUR CUSTOMER ID IS: " + id + "<br />AND PASSWORD: " + password + "<br /></h2>");
			}
			
	  }


      
      // Create the Bottom of the returned HTML page.
      
      System.out.println(cgi_lib.HtmlBot());
  }
}
