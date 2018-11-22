package files;

public class UserAction {
    //private String action;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String rule;

    @Override
    public String toString() {
        return "UserAction{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }

    public UserAction() {
    }

    public UserAction(String login, String password, String firstname, String lastname, String rule) {
        //if(action.equals("CREATE")) {
            //this.action = action;
            this.login = login;
            this.password = password;
            this.firstname = firstname;
            this.lastname = lastname;
            this.rule = rule;
       // }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
