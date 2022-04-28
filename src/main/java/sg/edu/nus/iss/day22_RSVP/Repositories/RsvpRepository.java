package sg.edu.nus.iss.day22_RSVP.Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day22_RSVP.Models.Rsvp;
import static sg.edu.nus.iss.day22_RSVP.Repositories.Queries.*;
import static sg.edu.nus.iss.day22_RSVP.Models.Rsvp.*;

@Repository
public class RsvpRepository {

    @Autowired
    JdbcTemplate jTemplate;

    public List<Rsvp> getAllRsvps(){
        List<Rsvp> rsvpList = new ArrayList<>();

        jTemplate.query(SQL_SELECT_ALL_RSVPS,
            (ResultSet rs) -> {
                Rsvp rsvp = Rsvp.createModel(rs);
                rsvpList.add(rsvp);
            });
        
        return rsvpList;
    }

    public List<Rsvp> getRsvpByName(String name){

        List<Rsvp> rsvpList = new ArrayList<>();

        jTemplate.query(SQL_SELECT_RSVP_BY_NAME,
            (ResultSet rs) -> { 
                if(!rs.next())
                    return rsvpList;
                do{
                Rsvp rsvp = Rsvp.createModel(rs);
                rsvpList.add(rsvp);
                } while(rs.next());
                    return rsvpList;
            },name);

        return rsvpList;

    }

    public Boolean saveRsvp(Rsvp rsvp){

        if(checkIfEmailExists(rsvp)){
            try{
            jTemplate.update(SQL_UPDATE_RSVP, rsvp.getName(),rsvp.getEmail(),rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getEmail());
            } catch (Exception ex){
                return false;
            }
            return true;
        }

        try {    
        jTemplate.update(SQL_INSERT_RSVP, rsvp.getName(),rsvp.getEmail(),rsvp.getPhone(),rsvp.getConfirmationDate(),rsvp.getComments());
        } catch (Exception ex) {
            return false;
        }
        return true;

    }

    public Boolean checkIfEmailExists(Rsvp rsvp){

        return jTemplate.query(SQL_SELECT_BY_EMAIL,
            (ResultSet rs) ->{
                if(rs.next())
                    return true;
                return false;
            },rsvp.getEmail()
        );
    }

    public Integer getRsvpCount(){

        return
        jTemplate.queryForObject(SQL_COUNT_RSVP, Integer.class);
    }
    
}
