package org.caskol.warcraft_database.utils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public class RepositoryUtils {

    public static <T,ID> T getOneFromRepository(JpaRepository<T, ID> repository, ID id, Class<T> clazz){
        if (id==null)
            throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+ " id объекта " + clazz.getSimpleName() + " является недопустимым.");
        Optional<T> objectFromRepo = repository.findById(id);
        if (!objectFromRepo.isPresent())
            throw new EntityNotFoundException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+ " id="
                    + id+" объекта " + clazz.getSimpleName() + " не был найден.");
        return objectFromRepo.get();
    }

    public static <T,U> boolean isClientIdsValid(Collection<T> idsFromDatabase, Collection<T> idsFromClient, Class<U> clazz){
        idsFromClient.removeAll(idsFromDatabase);
        if (idsFromClient.isEmpty()){
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (T i : idsFromClient){
            sb.append("id="+i+" не был найден."+"\n");
        }
        throw new ValidationException(RestExceptionHandler.VALIDATION_EXCEPTION_MSG+"Ошибки для объекта "+clazz.getSimpleName()+": "+ sb);
    }
}
