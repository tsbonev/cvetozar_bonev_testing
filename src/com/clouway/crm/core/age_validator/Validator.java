package com.clouway.crm.core.age_validator;

public class Validator {

    public boolean validate(String age){

        try {

            int ageInInt = Integer.parseInt(age);

            if(ageInInt >= 10 && ageInInt <= 100) return true;

        }
        catch (NumberFormatException e){
            throw e;
        }

        return false;

    }

}
