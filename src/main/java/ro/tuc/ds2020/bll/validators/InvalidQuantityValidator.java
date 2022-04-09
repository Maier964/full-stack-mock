package ro.tuc.ds2020.bll.validators;

import ro.tuc.ds2020.model.Product;

public class InvalidQuantityValidator implements Validator<Product> {

    @Override
    public void validate(Product product) {
        if ( product.getQuantity() < 0 )
            throw new IllegalArgumentException(" Product quantity cannot be negative.");
    }
}
