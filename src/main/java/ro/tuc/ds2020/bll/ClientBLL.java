package ro.tuc.ds2020.bll;

import ro.tuc.ds2020.bll.validators.AgeValidator;
import ro.tuc.ds2020.bll.validators.EmailValidator;
import ro.tuc.ds2020.bll.validators.Validator;
import ro.tuc.ds2020.dao.ClientDAO;
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

    public List<Client> findAll() { return clientDAO.findAll(); }

    public Client insert( Client client ) { return clientDAO.insert( client ); }

    public Client updateById( Client client ) { return clientDAO.update( client ); }

    public Client findByName( String name ) { return clientDAO.findByName( name ); }

}
