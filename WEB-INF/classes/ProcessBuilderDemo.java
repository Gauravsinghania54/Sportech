import java.lang.*; 
import java.io.*; 
import java.util.*; 



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
class ProcessBuilderDemo 
{ 
    public static void main(String[] arg) throws IOException 
    { 
        // creating list of process 
        
  
        // create the process 
        String command =
  "curl,-X,GET,--header,Authorization: Bearer UGE6FGLedRgWsGhyRSlj21goTGxD,--header,Accept: application/json,https://api.stubhub.com/sellers/search/events/v3?city=Chicago&state=IL&rows=500,";
ProcessBuilder processBuilder = new ProcessBuilder(command.split(","));


processBuilder.directory(new File("C:\\Illinois tech\\EWA\\Project"));
Process process = processBuilder.start();
try
{
InputStream inputStream = process.getInputStream();
         
          
        // checking the command in list 
        String result = convertInputStreamToString(inputStream);
            System.out.println(result);
 }    
 catch(Exception e)
 {

 }     
    } 
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8.name());

    }
} 