package ro.tuc.ds2020.bll;

import ro.tuc.ds2020.bll.validators.OrderValidator;
import ro.tuc.ds2020.bll.validators.Validator;
import ro.tuc.ds2020.dao.BillDAO;
import ro.tuc.ds2020.model.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillBLL {

    private List<Validator<Bill>> validatorList;
    private BillDAO orderDAO;

    public BillBLL() {
        validatorList = new ArrayList<>();
        validatorList.add( new OrderValidator());
        orderDAO = new BillDAO();
    }

    public List<Bill> findAll() { return orderDAO.findAll(); }

    public Bill insert(Bill bill) { return orderDAO.insert(bill); }

    public Bill updateById(Bill bill) { return orderDAO.update(bill); }

    public Bill findByName(String name ) { return orderDAO.findByName( name ); }
}
