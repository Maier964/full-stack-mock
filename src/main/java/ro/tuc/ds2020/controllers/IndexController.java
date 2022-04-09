package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.bll.BillBLL;
import ro.tuc.ds2020.bll.ClientBLL;
import ro.tuc.ds2020.bll.ProductBLL;
import ro.tuc.ds2020.model.Bill;
import ro.tuc.ds2020.model.Client;
import ro.tuc.ds2020.model.Product;


@RestController
@CrossOrigin
public class IndexController {

    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    BillBLL billBLL = new BillBLL();


    @GetMapping(value = "/")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("Backend running..", HttpStatus.FORBIDDEN);
    }


    @GetMapping("/list")
    public ResponseEntity<String> listAll( @RequestParam String table ) {

        switch (table){
            case "client":
                return new ResponseEntity<>( clientBLL.findAll().toString(), HttpStatus.OK );
            case "product":
                return new ResponseEntity<>( productBLL.findAll().toString(), HttpStatus.OK );
            case "bill":
                return new ResponseEntity<>( billBLL.findAll().toString(), HttpStatus.OK);
            default:
                return new ResponseEntity<>( "Invalid request", HttpStatus.BAD_REQUEST );
        }
    }

    // TODO: test these

    @PostMapping("/addclient")
    public ResponseEntity<String> addClient( @RequestBody Client client  ){
        if ( clientBLL.insert( client ) != null )
            return new ResponseEntity<>(" <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>(" Failed to add user. ", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct( @RequestBody Product product  ){
        if ( productBLL.insert( product ) != null )
            return new ResponseEntity<>(" <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>(" Failed to add user. ", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/addbill")
    public ResponseEntity<String> addClient( @RequestBody Bill bill  ){
        if ( billBLL.insert( bill ) != null )
            return new ResponseEntity<>(" <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>(" Failed to add user. ", HttpStatus.BAD_REQUEST);
    }


}
