package Models;

public class Pojo
{
    String firstName;
    String lastName;
    String email;

    PojoLocation location;
    PojoEmployer employer;

    public Pojo()
    {
        this.location =new PojoLocation();
        this.employer = new PojoEmployer();
    }

    public PojoLocation getLocation()
    {
        return location;
    }

    public void setLocation(PojoLocation location)
    {
        this.location = location;
    }

    public PojoEmployer getEmployer() {
        return employer;
    }

    public void setEmployer(PojoEmployer employer) {
        this.employer = employer;
    }


    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }




}
