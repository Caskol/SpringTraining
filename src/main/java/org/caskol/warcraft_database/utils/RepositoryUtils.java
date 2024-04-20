package org.caskol.warcraft_database.utils;

import jakarta.validation.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class RepositoryUtils {

    public static <T,U> T getFromRepository(JpaRepository<T, U> repository, U id, Class<T> clazz){
        if (id==null)
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+ " id объекта " + clazz.getSimpleName() + " является недопустимым.");
        Optional<T> objectFromRepo = repository.findById(id);
        if (!objectFromRepo.isPresent())
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+ " id="
                    + id+" объекта " + clazz.getSimpleName() + " не был найден.");
        return objectFromRepo.get();
    }
}
