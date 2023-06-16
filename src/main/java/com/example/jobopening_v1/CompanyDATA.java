package com.example.jobopening_v1;

public class CompanyDATA {
    private int companyID;
    private String companyName;
    private String url;
    private String location;
    private String industry;
    private byte[] logo;

    public CompanyDATA(int companyID, String companyName, String url, String location, String industry, byte[] logo) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.url = url;
        this.location = location;
        this.industry = industry;
        this.logo = logo;
    }
    // no logo
    public CompanyDATA(int companyID, String companyName, String url, String location, String industry) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.url = url;
        this.location = location;
        this.industry = industry;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
}
