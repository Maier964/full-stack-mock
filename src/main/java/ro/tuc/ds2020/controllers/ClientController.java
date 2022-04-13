package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.bll.ClientBLL;
import ro.tuc.ds2020.model.Client;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    ClientBLL clientBLL = new ClientBLL();


    @PostMapping("/addclient")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> addClient( @RequestBody Client client  ){
        if ( clientBLL.insert( client ) != null )
            return new ResponseEntity<>(" <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>(" Failed to add user. ", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/find")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> findByNameAndPassword( @RequestParam String name, @RequestParam String password ){
        if ( clientBLL.findByNameAndPassword( name, password ) != null )
            return new ResponseEntity<>(" <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>("No user matches these credentials.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByEmailAndPassword")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> findByEmailAndPassword( @RequestParam String email, @RequestParam String password ){
        if ( clientBLL.findByEmailAndPassword( email, password ) != null )
            return new ResponseEntity<>(clientBLL.findByEmailAndPassword( email, password).toString(), HttpStatus.OK);
        else
            return new ResponseEntity<>("No user matches these credentials.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByEmail")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> findByEmail( @RequestParam String email ){
        if ( clientBLL.findByEmail( email ) != null )
            return new ResponseEntity<>(clientBLL.findByEmail(email).toString(), HttpStatus.OK);
        else
            return new ResponseEntity<>("No user matches these credentials.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/deleteclient")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteClient( @RequestParam Integer id ){
        if ( clientBLL.delete( new Client( id, "anyName", "anyPass", "anyAddr", "anyEmail", 12 ) ) != null )
            return new ResponseEntity<>( " <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>("No user matches these credentials.", HttpStatus.NOT_FOUND);
    }

}
