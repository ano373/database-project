package com.example.jobopening_v1;

public class ApplicationsViewDATA {
        private int memberId;
        private String jobTitle;
        private String name;
        private String email;
        private String city;
        private String phoneNumber;
        private String currentPosition;
        private String currentCompany;
        private String applicationStatus;

        public ApplicationsViewDATA(int memberId, String jobTitle, String name, String email, String city, String phoneNumber, String currentPosition, String currentCompany, String applicationStatus) {
            this.memberId = memberId;
            this.jobTitle = jobTitle;
            this.name = name;
            this.email = email;
            this.city = city;
            this.phoneNumber = phoneNumber;
            this.currentPosition = currentPosition;
            this.currentCompany = currentCompany;
            this.applicationStatus = applicationStatus;
        }

        public int getMemberId() {
            return memberId;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getCity() {
            return city;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getCurrentPosition() {
            return currentPosition;
        }

        public String getCurrentCompany() {
            return currentCompany;
        }

        public String getApplicationStatus() {
            return applicationStatus;
        }


}

