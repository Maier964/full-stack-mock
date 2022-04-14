package ro.tuc.ds2020.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType3Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.bll.BillBLL;
import ro.tuc.ds2020.bll.ClientBLL;
import ro.tuc.ds2020.bll.ProductBLL;
import ro.tuc.ds2020.model.Bill;
import ro.tuc.ds2020.model.Client;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


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
    public ResponseEntity<String> listAll(@RequestParam String table) {

        switch (table) {
            case "client":
                return new ResponseEntity<>(clientBLL.findAll().toString(), HttpStatus.OK);
            case "product":
                return new ResponseEntity<>(productBLL.findAll().toString(), HttpStatus.OK);
            case "bill":
                return new ResponseEntity<>(billBLL.findAll().toString(), HttpStatus.OK);
            default:
                return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/createTXT")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createTXT(@RequestParam Integer id) {
        // check if id corresponds to valid user
        Client found = clientBLL.findById(id);

        if (found == null)
            return new ResponseEntity<>("Invalid user.", HttpStatus.BAD_REQUEST);

        List<Bill> result = billBLL.findByClientID(id);

        if (result == null)
            return new ResponseEntity<>("No orders for user " + found.getName(), HttpStatus.OK);

        // Generate txt
        StringBuilder stringBuilder = new StringBuilder("Order information:");

        for( Bill billIterator : result )
            stringBuilder.append(billIterator.toString()).append("\n");

        try{
            File file = new File(found.getName() + "_orders.txt");
            Writer fileWriter = new FileWriter(found.getName() + "_orders.txt", false);
            fileWriter.write( stringBuilder.toString() );
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>("Fail!", HttpStatus.OK);

        }



        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }




}
