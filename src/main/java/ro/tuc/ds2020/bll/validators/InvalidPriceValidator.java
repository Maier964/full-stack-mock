package ro.tuc.ds2020.bll.validators;

import ro.tuc.ds2020.model.Product;

public class InvalidPriceValidator implements Validator<Product> {

    @Override
    public void validate(Product product) {
        if ( product.getPrice() <= 0 )
            throw new IllegalArgumentException(" Price of a product cannot be negative.");
    }
}
