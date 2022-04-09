package ro.tuc.ds2020.bll.validators;

import ro.tuc.ds2020.dao.ClientDAO;
import ro.tuc.ds2020.dao.ProductDAO;
import ro.tuc.ds2020.model.Bill;

public class OrderValidator implements Validator<Bill>{

    @Override
    public void validate(Bill bill) {
        ClientDAO clientDAO = new ClientDAO();
        ProductDAO productDAO = new ProductDAO();

        if ( productDAO.findById( bill.getProductid() ) == null ||
        clientDAO.findById( bill.getClientid()  ) == null ) {
            throw new IllegalArgumentException(" Client and/or product doesn't exist ");
        }

        if ( productDAO.findById( bill.getProductid() ).getQuantity() < bill.getQuantity() )
            throw new UnsupportedOperationException( " Product quantity < order quantity. " );
    }
}
