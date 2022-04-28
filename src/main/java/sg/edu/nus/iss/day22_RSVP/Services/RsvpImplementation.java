package sg.edu.nus.iss.day22_RSVP.Services;

import java.util.List;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.edu.nus.iss.day22_RSVP.Models.Rsvp;
import sg.edu.nus.iss.day22_RSVP.Repositories.RsvpRepository;

@Service
public class RsvpImplementation implements RsvpInterface {

    @Autowired
    RsvpRepository rsvpRepo;

    @Override
    public List<Rsvp> getAllRsvps() {
        
        return rsvpRepo.getAllRsvps();
    }

    @Override
    public JsonArray createJsonArray(List<Rsvp> rsvpList) {
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        rsvpList.stream()
            .map(rsvp -> Rsvp.createJsonObj(rsvp))
            .forEach(rsvpJsonObj -> arrBuilder.add(rsvpJsonObj));
        
        JsonArray rsvpJsonArr = arrBuilder.build();
        return rsvpJsonArr;
    }

    @Override
    public List<Rsvp> getRsvpByName(String name) {

        return rsvpRepo.getRsvpByName(name);
    }

    @Override
    public Boolean saveRsvp(Rsvp rsvp) {
        
        return rsvpRepo.saveRsvp(rsvp);
        
    }

    @Override
    public Integer getRsvpCount() {
        return rsvpRepo.getRsvpCount();
        
    }


}
