package files.model;

import java.time.Instant;

public class Admin implements Comparable<Admin> {

    private static long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Role role;
    private Instant creatDate;
    private Instant updateDate;


    public Admin() {

    }

    public Admin(long id, String username, String password, String name, String phone, String email, String address, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public static Admin parseAdmin(String rar) {
        Admin admin = new Admin();
        String[] strings = rar.split(",");
        admin.id = Long.parseLong(strings[0]);
        admin.username = strings[1];
        admin.password = strings[2];
        admin.name = strings[3];
        admin.phone = strings[4];
        admin.email = strings[5];
        admin.address = strings[6];
        admin.role = Role.fromValue(strings[7]);
        String temp = strings[8];
        admin.creatDate = Instant.parse(temp);
        temp = strings[9];
        if (temp != null && !temp.equals("null"))
            admin.updateDate = Instant.parse(temp);
        return admin;

    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public  String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return this.role;
    }


    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Instant getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Instant creatDate) {
        this.creatDate = creatDate;
    }

    public static long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return id + "," +
                username + "," +
                password + "," +
                name + "," +
                phone + "," +
                email + "," +
                address + "," +
                role + "," +
                creatDate + ","
                + updateDate;
    }

    @Override
    public int compareTo(Admin o) {
        if (id - o.id == 0) {
            return name.compareTo(o.name);
        } else {
            return (int) (id - o.id);
        }
    }
}
