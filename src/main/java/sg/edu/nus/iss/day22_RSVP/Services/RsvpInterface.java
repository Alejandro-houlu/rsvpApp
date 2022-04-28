package sg.edu.nus.iss.day22_RSVP.Services;

import java.util.List;

import jakarta.json.JsonArray;
import sg.edu.nus.iss.day22_RSVP.Models.Rsvp;

public interface RsvpInterface {

    public List<Rsvp> getAllRsvps();

    public List<Rsvp> getRsvpByName(String name);
    
    public JsonArray createJsonArray(List<Rsvp> rsvpList);

    public Boolean saveRsvp(Rsvp rsvp);

    public Integer getRsvpCount();

    
}
