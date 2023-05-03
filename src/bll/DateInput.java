package bll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateInput {

    public String getDateToday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }
}
