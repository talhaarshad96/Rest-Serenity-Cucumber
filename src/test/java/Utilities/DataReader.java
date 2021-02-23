package Utilities;

import Models.Pojo;

public class DataReader
{
    Pojo pojo = new Pojo();

    public Pojo getPojo()
    {
        return pojo;
    }

    public void setPojo(Pojo pojo) {
        this.pojo = pojo;
    }

    public Pojo addUserPayLoad(String firstName, String lastName, String email, String city, String country, String jobTitle, String company)
    {

        pojo.setFirstName(firstName);
        pojo.setLastName(lastName);
        pojo.setEmail(email);
        pojo.getLocation().setCity(city);
        pojo.getLocation().setCountry(country);
        pojo.getEmployer().setJobTitle(jobTitle);
        pojo.getEmployer().setCompany(company);

        return pojo;
    }
}
