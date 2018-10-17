package ducban.deptrai.comot.khonghai.sampleproject.model;

public class User {
    private String username;
    private String password;
    private String name;
    private int photouser;
    private String phonenumber;

    public User(String username, String password, String name, int photouser, String phonenumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.photouser = photouser;
        this.phonenumber = phonenumber;
    }

    public User(String username, String password, String name, String phonenumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phonenumber = phonenumber;
    }

    public User() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotouser() {
        return photouser;
    }

    public void setPhotouser(int photouser) {
        this.photouser = photouser;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
