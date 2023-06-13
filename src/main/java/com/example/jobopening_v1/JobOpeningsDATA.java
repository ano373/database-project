package com.example.jobopening_v1;
import java.sql.Date;
public class JobOpeningsDATA {
    private int jobOpeningID;
    private int companyID;
    private String jobTitle;
    private String jobDescription;
    private String jobType;
    private int salary;
    private Date deadline;

    public JobOpeningsDATA(int companyID, String jobTitle, String jobDescription,
                      String jobType, int salary, Date deadline) {
        this.companyID = companyID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobType = jobType;
        this.salary = salary;
        this.deadline = deadline;
    }
    public JobOpeningsDATA(int jobOpeningID ,int companyID, String jobTitle, String jobDescription,
                           String jobType, int salary, Date deadline) {
        this.jobOpeningID = jobOpeningID;
        this.companyID = companyID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobType = jobType;
        this.salary = salary;
        this.deadline = deadline;
    }

    public int getJobOpeningID() {
        return jobOpeningID;
    }

    public void setJobOpeningID(int jobOpeningID) {
        this.jobOpeningID = jobOpeningID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
