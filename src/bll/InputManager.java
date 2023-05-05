package bll;

import be.Project;
import dal.ProjectDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InputManager {

    ProjectDAO projectDAO = new ProjectDAO();


    // Date input
    public String getDateToday(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    // Filter input

    public List<Project> searchProjectName(String str) throws SQLException {
        List<Project> getAllProjects = projectDAO.getAllProjects();
        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(p.getName().toLowerCase().equals(str.toLowerCase())){
                filtered.add(p);
            }
        }
        return filtered;
    }

    public List<Project> searchCustomerName(String str) throws SQLException {
    List<Project> getAllProjects = projectDAO.getAllProjects();
    List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
        if(p.getCustomerName().toLowerCase().equals(str.toLowerCase())){
            filtered.add(p);
        }
    }
        return filtered;
    }

    public List<Project> searchCompanyAddress(String str) throws SQLException {
        List<Project> getAllProjects = projectDAO.getAllProjects();
        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(p.getCompanyAddress().toLowerCase().equals(str.toLowerCase())){
                filtered.add(p);
            }
        }
        return filtered;
    }
}
