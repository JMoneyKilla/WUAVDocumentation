package bll;

import be.Project;
import dal.ProjectDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InputManager {

    ProjectDAO projectDAO = new ProjectDAO();


    // Date input
    public String getDateToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    public boolean isDate48MonthsOld(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDate dateToCheck = localDate;
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateToCheck, now);
        int months = (int) period.toTotalMonths();
        if (months >= 48) {
            return true;
        } else return false;

    }

    public List getOldProjects() throws SQLException {
        List<Project> getAllProjects = projectDAO.getAllProjects();
        List<Project> oldProjects = new ArrayList<>();

        for(Project p : getAllProjects){
            if(isDate48MonthsOld(p.getDateLastVisited())){
                oldProjects.add(p);
            }
        }
        return oldProjects;
    }

        // Filter input

    public List<Project> searchProjectName(String str) throws SQLException {
        List<Project> getAllProjects = projectDAO.getAllProjects();
        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(p.getName().toLowerCase().contains(str.toLowerCase())){
                filtered.add(p);
            }
        }
        return filtered;
    }

    public List<Project> searchCustomerName(String str) throws SQLException {
    List<Project> getAllProjects = projectDAO.getAllProjects();
    List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
        if(p.getCustomerName().toLowerCase().contains(str.toLowerCase())){
            filtered.add(p);
        }
    }
        return filtered;
    }

    public List<Project> searchCompanyAddress(String str) throws SQLException {
        List<Project> getAllProjects = projectDAO.getAllProjects();
        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(p.getCompanyAddress().toLowerCase().contains(str.toLowerCase())){
                filtered.add(p);
            }
        }
        return filtered;
    }

    public List<Project> searchZipCode(String str) throws SQLException {
        List<Project> getAllProjects = projectDAO.getAllProjects();
        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(Integer.toString(p.getZipCode()).contains(str)){
                filtered.add(p);
            }
        }
        return filtered;
    }
}
