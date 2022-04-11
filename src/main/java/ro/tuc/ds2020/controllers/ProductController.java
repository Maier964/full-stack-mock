package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.bll.ProductBLL;
import ro.tuc.ds2020.model.Product;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    ProductBLL productBLL = new ProductBLL();

    @CrossOrigin(origins = "*")
    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct( @RequestBody Product product  ){
        if ( productBLL.insert( product ) != null )
            return new ResponseEntity<>(" <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>(" Failed to add user. ", HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/findproduct")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> findProductByName( @RequestParam String name ){
        if ( productBLL.findByName(name) != null )
            return new ResponseEntity<>( productBLL.findByName(name).toString(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Product not found", HttpStatus.BAD_REQUEST);
    }


}
