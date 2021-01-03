package com.mihneacristian.report_it.domain.entity;

public class IssuesEntity {

    private String issueTitle;
    private String issueDescription;
    private String issueType;
    private String issueSeverity;
    private String dateAdded;
    private double lat;
    private double lng;
    private String userName;
    private String userEmailAddress;
    private String photoURL;

    public IssuesEntity(String issueTitle, String issueDescription, String issueType, String issueSeverity, String dateAdded, double lat, double lng, String userName, String userEmailAddress, String photoURL) {
        this.issueTitle = issueTitle;
        this.issueDescription = issueDescription;
        this.issueType = issueType;
        this.issueSeverity = issueSeverity;
        this.dateAdded = dateAdded;
        this.lat = lat;
        this.lng = lng;
        this.userName = userName;
        this.userEmailAddress = userEmailAddress;
        this.photoURL = photoURL;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueSeverity() {
        return issueSeverity;
    }

    public void setIssueSeverity(String issueSeverity) {
        this.issueSeverity = issueSeverity;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public String toString() {
        return "IssuesEntity{" +
                "issueTitle='" + issueTitle + '\'' +
                ", issueDescription='" + issueDescription + '\'' +
                ", issueType='" + issueType + '\'' +
                ", issueSeverity='" + issueSeverity + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", userName='" + userName + '\'' +
                ", userEmailAddress='" + userEmailAddress + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
