package sg.edu.nus.iss.day22_RSVP.Repositories;

public interface Queries {

    public static final String SQL_SELECT_ALL_RSVPS = "select * from rsvp";

    public static final String SQL_SELECT_RSVP_BY_NAME = "select * from rsvp where name like ? ";

    public static final String SQL_INSERT_RSVP = "insert into rsvp (name,email,phone,confirmation_date,comments) values (?,?,?,?,?)";

    public static final String SQL_SELECT_BY_EMAIL = "select * from rsvp where email = ?";

    public static final String SQL_UPDATE_RSVP = "update rsvp set name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ? where email = ?";

    public static final String SQL_COUNT_RSVP = "select count(*) from rsvp";
    
}
