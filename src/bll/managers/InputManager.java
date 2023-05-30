package bll.managers;

import be.Project;
import be.User;
import be.enums.UserType;
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

    /*
    Gets all projects older than 48 months
     */
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

    // Search Filter inputs

    public List<Project> searchProjectName(String str, User user) throws SQLException {
        List<Project> getAllProjects;
        if(user.getType()==UserType.TECHNICIAN)
            getAllProjects = projectDAO.getUserProjects(user.getId());

            else getAllProjects = projectDAO.getAllProjects();

        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(p.getName().toLowerCase().contains(str.toLowerCase())){
                filtered.add(p);
            }
        }
        return filtered;
    }

    public List<Project> searchCustomerName(String str, User user) throws SQLException {
        List<Project> getAllProjects;
        if(user.getType()==UserType.TECHNICIAN)
            getAllProjects = projectDAO.getUserProjects(user.getId());

        else getAllProjects = projectDAO.getAllProjects();
    List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
        if(p.getCustomerName().toLowerCase().contains(str.toLowerCase())){
            filtered.add(p);
        }
    }
        return filtered;
    }

    public List<Project> searchCompanyAddress(String str, User user) throws SQLException {
        List<Project> getAllProjects;
        if(user.getType()==UserType.TECHNICIAN)
            getAllProjects = projectDAO.getUserProjects(user.getId());

        else getAllProjects = projectDAO.getAllProjects();
        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(p.getCompanyAddress().toLowerCase().contains(str.toLowerCase())){
                filtered.add(p);
            }
        }
        return filtered;
    }

    public List<Project> searchZipCode(String str, User user) throws SQLException {
        List<Project> getAllProjects;
        if(user.getType()==UserType.TECHNICIAN)
            getAllProjects = projectDAO.getUserProjects(user.getId());

        else getAllProjects = projectDAO.getAllProjects();
        List<Project> filtered = new ArrayList<>();

        for(Project p : getAllProjects){
            if(Integer.toString(p.getZipCode()).contains(str)){
                filtered.add(p);
            }
        }
        return filtered;
    }
}
