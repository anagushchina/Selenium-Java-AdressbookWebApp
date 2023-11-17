package ca.stqa.mantis.model;

public record UserData(String username, String email) {

    public UserData(){
        this("", "");
    }

    public UserData withUsername(String username){
        return new UserData(username, email);
    }

    public UserData withEmail(String email){
        return new UserData(username, email);
    }
}
