package bean;

public class ContactInfo {
    private long photo;
    private String name;
    private String phone;

    public ContactInfo(String name, String phone) {
        this.name=name;
        this.phone=phone;
    }

    public long getPhoto() {
        return photo;
    }

    public void setPhoto(long photo) {
        this.photo = photo;
    }

    public ContactInfo() {
    }

    public ContactInfo(Long photo, String name, String phone) {
        this.photo=photo;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

