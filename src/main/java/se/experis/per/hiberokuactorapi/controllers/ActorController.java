package se.experis.per.hiberokuactorapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.per.hiberokuactorapi.models.Actor;
import se.experis.per.hiberokuactorapi.repositories.ActorRepository;
import se.experis.per.hiberokuactorapi.repositories.CommonResponse;
import se.experis.per.hiberokuactorapi.utils.Command;
import se.experis.per.hiberokuactorapi.utils.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository repository;

    @GetMapping("/actor")
    public ResponseEntity<CommonResponse> actorRoot(HttpServletRequest request){
        Command command = new Command(request);

        //process
        CommonResponse commandResponse = new CommonResponse();
        commandResponse.data = null;
        commandResponse.message = "Not implemented";

        commandResponse.message = "Sup!";

        HttpStatus resp = HttpStatus.NOT_IMPLEMENTED;

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

    @GetMapping("/actor/all")
    public ResponseEntity<CommonResponse> getAllActors(HttpServletRequest request){
        Command command = new Command(request);

        //process
        CommonResponse commandResponse = new CommonResponse();
        commandResponse.data = repository.findAll();
        commandResponse.message = "All actors";

        HttpStatus resp = HttpStatus.OK;

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

    @GetMapping("/actor/{id}")
    public ResponseEntity<CommonResponse> getActorById(HttpServletRequest request, @PathVariable("id") Integer id){
        Command command = new Command(request);

        //process
        CommonResponse commandResponse = new CommonResponse();
        HttpStatus resp;

        if(repository.existsById(id)) {
            commandResponse.data = repository.findById(id);
            commandResponse.message = "Actor with id: " + id;
            resp = HttpStatus.OK;
        } else {
            commandResponse.data = null;
            commandResponse.message = "Actor not found";
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

    @GetMapping("/actor/{id}/movies")
    public ResponseEntity<CommonResponse> getMoviesbyActor(HttpServletRequest request, @PathVariable("id") Integer id){
        Command command = new Command(request);

        //process
        CommonResponse commandResponse = new CommonResponse();
        HttpStatus resp;

        if(repository.existsById(id)) {
            Optional<Actor> actorRepo = repository.findById(id);
            Actor actor = actorRepo.get();
            commandResponse.data = actor.movies;
            commandResponse.message = "Movies by actor with id: " + id;
            resp = HttpStatus.OK;
        } else {
            commandResponse.data = null;
            commandResponse.message = "Actor not found";
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

    @GetMapping("/actor/search/{word}")
    public ResponseEntity<CommonResponse> getActorSearch(HttpServletRequest request, @PathVariable String word){
        Command command = new Command(request);


        String searchWord = word.toUpperCase();
        //process
        CommonResponse commandResponse = new CommonResponse();
        List<Actor> allActors = repository.findAll();
        ArrayList<Actor> results = new ArrayList<Actor>();

        for(Actor actor : allActors){
            if((actor.first_name != null && actor.first_name.toUpperCase().contains(searchWord))
                    || (actor.last_name != null && actor.last_name.toUpperCase().contains(searchWord) )
                    || (actor.dob != null && actor.dob.toUpperCase().contains(searchWord))
                    || (actor.imdb_url != null && actor.imdb_url.toUpperCase().contains(searchWord))){

                results.add(actor);
            }
        }

        commandResponse.data = results;
        commandResponse.message = "Results of actor search for: " + word;

        HttpStatus resp = HttpStatus.OK;

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

    @PostMapping("/actor")
    public ResponseEntity<CommonResponse> addActor(HttpServletRequest request, HttpServletResponse response, @RequestBody Actor actor){
        Command command = new Command(request);

        //process
        actor = repository.save(actor);

        CommonResponse commandResponse = new CommonResponse();
        commandResponse.data = actor;
        commandResponse.message = "New actor with id: " + actor.id;

        HttpStatus resp = HttpStatus.CREATED;

        response.addHeader("Location", "/actor/" + actor.id);

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }


    @PatchMapping("/actor/{id}")
    public ResponseEntity<CommonResponse> updateActor(HttpServletRequest request, @RequestBody Actor newActor, @PathVariable Integer id) {
        Command command = new Command(request);

        //process
        CommonResponse commandResponse = new CommonResponse();
        HttpStatus resp;

        if(repository.existsById(id)) {
            Optional<Actor> actorRepo = repository.findById(id);
            Actor actor = actorRepo.get();

            if(newActor.first_name != null) {
                actor.first_name = newActor.first_name;
            }
            if(newActor.last_name != null) {
                actor.last_name = newActor.last_name;
            }
            if(newActor.dob != null) {
                actor.dob = newActor.dob;
            }
            if(newActor.imdb_url != null) {
                actor.imdb_url = newActor.imdb_url;
            }

            repository.save(actor);

            commandResponse.data = actor;
            commandResponse.message = "Updated actor with id: " + actor.id;
            resp = HttpStatus.OK;
        } else {
            commandResponse.message = "Actor not found with id: " + id;
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<CommonResponse> replaceActor(HttpServletRequest request, @RequestBody Actor newActor, @PathVariable Integer id) {
        Command command = new Command(request);

        //process
        CommonResponse commandResponse = new CommonResponse();
        HttpStatus resp;

        if(repository.existsById(id)) {
            Optional<Actor> actorRepo = repository.findById(id);
            Actor actor = actorRepo.get();

            actor.first_name = newActor.first_name;
            actor.last_name = newActor.last_name;
            actor.dob = newActor.dob;
            actor.imdb_url = newActor.imdb_url;

            repository.save(actor);

            commandResponse.data = actor;
            commandResponse.message = "Replaced actor with id: " + actor.id;
            resp = HttpStatus.OK;
        } else {
            commandResponse.message = "Actor not found with id: " + id;
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<CommonResponse> deleteActor(HttpServletRequest request, @PathVariable Integer id) {
        Command command = new Command(request);

        //process
        CommonResponse commandResponse = new CommonResponse();
        HttpStatus resp;

        if(repository.existsById(id)) {
            repository.deleteById(id);
            commandResponse.message = "Deleted actor with id: " + id;
            resp = HttpStatus.OK;
        } else {
            commandResponse.message = "Actor not found with id: " + id;
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        command.setResult(resp);
        Logger.getInstance().logCommand(command);
        return new ResponseEntity<>(commandResponse, resp);
    }

}

