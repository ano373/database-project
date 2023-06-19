package com.example.jobopening_v1;

import java.sql.Date;

public class InterviewDATA {
    private int jobOpeningID;
    private String jobTitle;
    private String interviewer;
    private String interviewee;
    private String interviewType;
    private Date dated;
    private String interviewStatus;
    private int IntervieweeID;

    public InterviewDATA(int jobOpeningID, String jobTitle, String interviewer, String interviewee,
                            String interviewType, Date dated, String interviewStatus, int IntervieweeID) {
        this.jobOpeningID = jobOpeningID;
        this.jobTitle = jobTitle;
        this.interviewer = interviewer;
        this.interviewee = interviewee;
        this.interviewType = interviewType;
        this.dated = dated;
        this.interviewStatus = interviewStatus;
        this.IntervieweeID =  IntervieweeID;
    }

    public int getJobOpeningID() {
        return jobOpeningID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public String getInterviewee() {
        return interviewee;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public Date getDate() {
        return dated;
    }


    public String getInterviewStatus() {
        return interviewStatus;
    }
    public int getIntervieweeID(){
        return IntervieweeID;
    }
}

