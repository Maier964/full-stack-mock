package ro.tuc.ds2020;

import ro.tuc.ds2020.bll.ClientBLL;
import ro.tuc.ds2020.bll.BillBLL;
import ro.tuc.ds2020.bll.ProductBLL;
import ro.tuc.ds2020.model.Client;
import ro.tuc.ds2020.model.Bill;
import ro.tuc.ds2020.model.Product;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Logger;

public class Test {
    protected static final Logger LOGGER = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) throws SQLException {

        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        BillBLL orderBLL = new BillBLL();

        String result = null;

        Product testProd = new Product(1, "TestProduct", 2000, 43);

        Client test = new Client("admin", "admin", "N/A", "admin@administrator.adm"
                , 21);

        Bill testBill = new Bill( 122, 2331, 1, 20, Timestamp.from(Instant.now()), 12);

        //result = clientBLL.findByNameAndPassword("admin", "adm22in");

        result = clientBLL.delete( new Client(774915, "Asdasn", "sdamin", "Nasdsad/A", "admin@asdasdasdasdas.adm"
                , 32) );

        if ( result == null )
            System.out.println("Fail");

    }

}
