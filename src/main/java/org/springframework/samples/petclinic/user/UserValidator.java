package org.springframework.samples.petclinic.user;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{
    

    @Override
    public void validate(Object obj, Errors errors){
        User user=(User) obj;

        //Password validation
        String psswd= user.getPassword();
        Boolean tieneMinus=false;
        Boolean tieneMayus=false;
        Boolean tieneDigit=false;
        
        Integer i=0;
        while(i<psswd.length()){
            Character c=psswd.charAt(i);
            if(Character.isLowerCase(c)){
                tieneMinus=true;
            }
            if(Character.isUpperCase(c)){
                tieneMayus=true;
            }
            if(Character.isDigit(c)){
                tieneDigit=true;
            }
            i++;
        }
        if(psswd==null|| (psswd.length()<6 || psswd.length()>18) || !(tieneDigit && tieneMayus && tieneMinus)){
            errors.rejectValue("password", "Requirido & entre 6 y 18 characters, tambien necesita al menos 1 digito, 1 Mayus y 1 Minus", "Requirido y entre 6 y 18 characters, tambien necesita al menos 1 digito, 1 Mayus y una Minus");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return User.class.isAssignableFrom(clazz);
    }
    
}
