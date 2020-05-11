package tech.berjis.lynn;

public class Models {
    private String first_name, last_name, user_description, user_email, user_id, user_image, user_name, user_phone, user_type;

    public Models(String first_name, String last_name, String user_description, String user_email, String user_id, String user_image, String user_name, String user_phone, String user_type) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_description = user_description;
        this.user_email = user_email;
        this.user_id = user_id;
        this.user_image = user_image;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_type = user_type;
    }

    public Models() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_description() {
        return user_description;
    }

    public void setUser_description(String user_description) {
        this.user_description = user_description;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
