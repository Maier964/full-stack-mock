package ro.tuc.ds2020.model;

public class Client {
    private int id;
    private String name;
    private String password;
    private String address;
    private String email;
    private int age;

    public Client() {}

    public Client(int id, String name, String password, String address, String email, int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public Client(String name, String password, String address, String email, int age) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"address\":\"" + address + '\"' +
                ", \"email\":\"" + email + '\"' +
                ", \"age\":" + age +
                '}';
    }

}
