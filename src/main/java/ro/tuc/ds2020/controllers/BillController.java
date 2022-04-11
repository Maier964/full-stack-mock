package ro.tuc.ds2020.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.bll.BillBLL;
import ro.tuc.ds2020.model.Bill;


@RestController
@CrossOrigin
public class BillController {

    BillBLL billBLL = new BillBLL();

    @PostMapping("/addbill")
    public ResponseEntity<String> addClient( @RequestBody Bill bill  ){
        if ( billBLL.insert( bill ) != null )
            return new ResponseEntity<>(" <b> Success! </b> ", HttpStatus.OK);
        else
            return new ResponseEntity<>(" Failed to add user. ", HttpStatus.BAD_REQUEST);
    }


}
