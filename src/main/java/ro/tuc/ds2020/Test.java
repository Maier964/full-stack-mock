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

        Client result = null;

        Product testProd = new Product(1, "TestProduct", 2000, 43);

        Client test = new Client("admin", "admin", "N/A", "admin@administrator.adm"
                , 21);

        Bill testBill = new Bill( 122, 2331, 1, 20, Timestamp.from(Instant.now()), 12);

        for( int i = 0; i < 10; i++ )
            result = clientBLL.insert(test);

        if ( result == null )
            System.out.println("Fail");

    }

}
