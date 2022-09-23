package org.polytech.covidapi.model;

public class patient {

    String nomPatient;
    String prenomPatient;
    String mail;
    long numeroTelephone;
    boolean vaccine;

    public void estVaccine(boolean vaccine){
        vaccine = vaccine;
    }



}
