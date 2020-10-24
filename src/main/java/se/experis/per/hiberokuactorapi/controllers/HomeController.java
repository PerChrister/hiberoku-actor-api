package se.experis.per.hiberokuactorapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.experis.per.hiberokuactorapi.repositories.CommonResponse;
import se.experis.per.hiberokuactorapi.utils.Command;
import se.experis.per.hiberokuactorapi.utils.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<CommonResponse> landing(HttpServletRequest request){
        Command command = new Command(request);

        // For the Process
        CommonResponse commandResponse = new CommonResponse();
        commandResponse.message = "index page coming soon...";

        // For logging and returns results
        command.setResult(HttpStatus.OK);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, HttpStatus.OK);
    }

    @GetMapping("/setup")
    public ResponseEntity<CommonResponse> setup(HttpServletRequest request){
        Command command = new Command(request);

        // For the Process
        CommonResponse commandResponse = new CommonResponse();
        commandResponse.message = "Setup on-going...";

        // For logging and returns results
        command.setResult(HttpStatus.OK);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, HttpStatus.OK);
    }

    @GetMapping("/log")
    public ResponseEntity<ArrayList<Command>> log(HttpServletRequest request){
        Command command = new Command(request);

        // For logging and returns results
        command.setResult(HttpStatus.OK);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<ArrayList<Command>>(Logger.getInstance().getLog(), HttpStatus.OK);
    }
}

