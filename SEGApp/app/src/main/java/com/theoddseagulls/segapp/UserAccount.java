package com.theoddseagulls.segapp;

public class UserAccount extends Account{

    private static final String type = "Utilisateur";
    private String appointment;

    public UserAccount () {
        super();

        super.setType(type);
    }

    public UserAccount(String e, String p) {
        super(e,p);

        super.setType(type);
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }
}
