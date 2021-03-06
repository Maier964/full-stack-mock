package ro.tuc.ds2020.bll;

import ro.tuc.ds2020.bll.validators.AgeValidator;
import ro.tuc.ds2020.bll.validators.EmailValidator;
import ro.tuc.ds2020.bll.validators.Validator;
import ro.tuc.ds2020.dao.ClientDAO;
import ro.tuc.ds2020.model.Bill;
import ro.tuc.ds2020.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientBLL {

    private List<Validator<Client>> validatorList;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validatorList = new ArrayList<>();
        validatorList.add( new EmailValidator());
        validatorList.add( new AgeValidator());

        clientDAO = new ClientDAO();
    }


    // TODO: validate inputs by iterating trough validators

    public List<Client> findAll() { return clientDAO.findAll(); }

    public Client insert( Client client ) {
        try{
            for ( Validator<Client> validatorIt : validatorList ){
                validatorIt.validate( client );
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return clientDAO.insert( client ); }

    public Client findById( int id ) { return clientDAO.findById(id); }

    public Client updateById( Client client ) { return clientDAO.update( client ); }

    public Client findByName( String name ) { return clientDAO.findByName( name ); }

    public Client findByNameAndPassword( String name, String password ) {
        return clientDAO.findByNameAndPassword( name, password ); }

    public Client findByEmailAndPassword( String email, String password ) {
        return clientDAO.findByEmailAndPassword( email, password );
    }

    public Client findByEmail( String email ){
        return clientDAO.findByEmail( email );
    }

    public String delete( Client client ) { return clientDAO.delete(client); }

}
