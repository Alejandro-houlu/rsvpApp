package sg.edu.nus.iss.day22_RSVP.Models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Rsvp {

    private String name;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String Comments;

    @Override
    public String toString() {
        return "Rsvp [Comments=" + Comments + ", confirmationDate=" + confirmationDate + ", email=" + email + ", name="
                + name + ", phone=" + phone + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public static Rsvp createModel(ResultSet rs) throws SQLException{
        Rsvp rsvp = new Rsvp();
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setConfirmationDate(rs.getDate("confirmation_date"));
        rsvp.setComments(rs.getString("comments"));

        return rsvp;
    }

    
    public static Rsvp createModel(String body) {
        DocumentContext jContext = JsonPath.parse(body);

        String tempDate = jContext.read("$.confirmation_date");
        Date date = Date.valueOf(tempDate);
        
        Rsvp rsvp = new Rsvp();
        rsvp.setName(jContext.read("$.name"));
        rsvp.setEmail(jContext.read("$.email"));
        rsvp.setPhone(jContext.read("$.phone"));
        rsvp.setConfirmationDate(date);
        rsvp.setComments(jContext.read("$.comments"));

        return rsvp;
    }

    public static JsonObject createJsonObj(Rsvp rsvp){
        JsonObject jsonObj = Json.createObjectBuilder()
            .add("name", rsvp.getName())
            .add("email", rsvp.getEmail())
            .add("phone", rsvp.getPhone())
            .add("confirmation_date", rsvp.getConfirmationDate().toString())
            .add("comments", rsvp.getComments())
            .build();
        
        return jsonObj;  
    }

    
    
}
