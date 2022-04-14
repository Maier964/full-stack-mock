package ro.tuc.ds2020.bll;

import ro.tuc.ds2020.bll.validators.InvalidPriceValidator;
import ro.tuc.ds2020.bll.validators.InvalidQuantityValidator;
import ro.tuc.ds2020.bll.validators.Validator;
import ro.tuc.ds2020.dao.ProductDAO;
import ro.tuc.ds2020.model.Bill;
import ro.tuc.ds2020.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {

    private List<Validator<Product>> validatorList;
    private ProductDAO productDAO;

    public ProductBLL(){
        validatorList = new ArrayList<>();
        validatorList.add( new InvalidQuantityValidator() );
        validatorList.add( new InvalidPriceValidator() );

        productDAO = new ProductDAO();
    }

    public List<Product> findAll() { return  productDAO.findAll(); }


    /**
     * @param product
     * @return product if operation succeeded
     * @return null if operation failed
     */
    public Product insert( Product product ) {
        try{
            for ( Validator<Product> validatorIt : validatorList ){
                validatorIt.validate( product );
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return productDAO.insert( product ); }

    /**
     *
     * @param product
     * @return product if operation succeeded
     * @return null if operation failed
     */
    public Product updateById( Product product ) { return productDAO.update( product ); }

    /**
     *
     * @param name
     * @return product if operation succeeded
     * @return null if operation failed
     */
    public Product findByName( String name ) { return productDAO.findByName( name ); }

    /**
     *
     * @param product
     * @return product if operation succeeded
     * @return null if operation failed
     */
    public String delete( Product product ) { return productDAO.delete( product ); }


}
