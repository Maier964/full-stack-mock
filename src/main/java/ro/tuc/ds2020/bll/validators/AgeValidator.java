package ro.tuc.ds2020.bll.validators;


import ro.tuc.ds2020.model.Client;

public class AgeValidator implements Validator<Client>{
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 60;

    public void validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }

    }

}

