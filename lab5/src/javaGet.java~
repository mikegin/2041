import java.util.*; 
import java.io.*;

class javaGet
{

  public static void main( String args[] )
  {

      //
      //  Here is a minimalistic CGI program that uses cgi_lib
      //

      //
      //  Print the required CGI header.
      //
      System.out.println(cgi_lib.Header());

      //
      //  Parse the form data into a Hashtable.
      //
      Hashtable form_data = cgi_lib.ReadParse(System.in);

      //
      // Create the Top of the returned HTML page
      //
      String first_name = (String)form_data.get("first_name");
	  String last_name = (String)form_data.get("last_name");

      System.out.println(cgi_lib.HtmlTop("Using Get with Java " ));

      System.out.println("<h3> Hello  " + first_name +"  " + last_name+ " !</h3>");
	  System.out.println("<h3> Nice to see you  from Java !</h3>");
	  System.out.println("<hr />");

      //System.out.println("Here are the name/value pairs from the form:");

      //
      //  Print the name/value pairs sent from the browser.
      //
     // System.out.println(cgi_lib.Variables(form_data));

      //
      //  Print the Environment variables sent in from the Unix script.
      //
      System.out.println("Here are the CGI environment variables/value pairs" +
                         "passed in from the UNIX script:");

      System.out.println(cgi_lib.Environment());

      //
      // Create the Bottom of the returned HTML page to close it cleanly.
      //
      System.out.println(cgi_lib.HtmlBot());

  }

}

