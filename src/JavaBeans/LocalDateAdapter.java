package JavaBeans;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
 
//    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        
	@Override
    public String marshal(LocalDate v) throws Exception {
		 return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(v);
    
    }
	
	@Override
	public LocalDate unmarshal(String v) throws Exception {
		
		return LocalDate.parse(v, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    
	


}
		
		
		
		
		
		
		
 
    
