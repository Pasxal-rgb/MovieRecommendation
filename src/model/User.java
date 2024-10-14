package model;

public class User {

    private int u_id;
    private String name;

    public User(int u_id, String name) {
        this.u_id = u_id;
        this.name = name;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
