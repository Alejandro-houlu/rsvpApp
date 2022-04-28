package sg.edu.nus.iss.day22_RSVP.RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.day22_RSVP.Models.Rsvp;
import sg.edu.nus.iss.day22_RSVP.Services.RsvpInterface;

@RestController
@RequestMapping("/api")
public class RsvpRestController {

    @Autowired
    RsvpInterface rsvpSvc;

    @GetMapping("/rsvps")
    public ResponseEntity<String> getAllRsvps(){

        List<Rsvp> rsvpList = rsvpSvc.getAllRsvps();

        return ResponseEntity
            .ok()
            .body(rsvpSvc.createJsonArray(rsvpList).toString());

    }

    @GetMapping("/rsvp")
    public ResponseEntity<String> getRsvpByName(@RequestParam String q){
        List<Rsvp> rsvpList = rsvpSvc.getRsvpByName(q+"%");

        if(rsvpList.size() < 1)
            return ResponseEntity
                .notFound().build();

        return ResponseEntity
            .ok()
            .body(rsvpSvc.createJsonArray(rsvpList).toString());
    }

    @PostMapping("/rsvp")
    public ResponseEntity<String> createRsvp(@RequestBody String body){

        Rsvp rsvp = Rsvp.createModel(body);
        Boolean flag = rsvpSvc.saveRsvp(rsvp);

        return (flag? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build()); 
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<String> getRsvpCount(){
        Integer count = rsvpSvc.getRsvpCount();
        JsonObject jsonObj = Json.createObjectBuilder()
            .add("Count", count)
            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(jsonObj.toString());
    }
    
}
