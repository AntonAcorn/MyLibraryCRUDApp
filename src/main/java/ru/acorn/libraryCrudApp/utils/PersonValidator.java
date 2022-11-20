package ru.acorn.libraryCrudApp.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.acorn.libraryCrudApp.dao.PersonDAO;
import ru.acorn.libraryCrudApp.models.Person;

@Component
public class PersonValidator implements Validator {
    PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDAO.getPersonByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName", "","This name is already exists");
        }
    }
}
