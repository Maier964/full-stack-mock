package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.bll.BillBLL;
import ro.tuc.ds2020.bll.ClientBLL;
import ro.tuc.ds2020.bll.ProductBLL;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UtilController {

    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    BillBLL billBLL = new BillBLL();

    @CrossOrigin(origins = "http://localost:3000")
    @GetMapping(value = "/")
    public ResponseEntity<String> getStatus() {
        return new ResponseEntity<>("Backend running..", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localost:3000")
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


}
