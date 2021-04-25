package app.model.Users;

public interface UserInterface {
    public abstract String getUsername();
    public abstract String getSalt();
    public abstract String getHashedPassword();
    public abstract String getFirstname();
    public abstract String getLastname();
    public abstract String getEmail();
    public abstract String getGender();
    public abstract String getCountry();
}
