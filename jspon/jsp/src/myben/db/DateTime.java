package myben.db;
import java.util.*;
import java.text.*;
public class DateTime {
	public String time() {
		Date now = new Date();
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy년MM월dd일 E요일 a hh:mm:ss");
		 
		 String today = sf.format(now);
		return today;
	}
}
