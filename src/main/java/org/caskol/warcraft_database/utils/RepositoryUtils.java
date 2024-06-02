package org.caskol.warcraft_database.utils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
public class RepositoryUtils {

    public static <T,ID> T getOneFromRepository(JpaRepository<T, ID> repository, ID id, Class<T> clazz){
        if (id==null)
            throw new ValidationException("validation.received_invalid_data" + " id " + "validation");
        Optional<T> objectFromRepo = repository.findById(id);
        if (!objectFromRepo.isPresent())
            throw new EntityNotFoundException("validation.received_invalid_data" + String.format(" id=%s of %s was not found",id,clazz.getSimpleName()));
        return objectFromRepo.get();
    }

    public static <T,U> boolean isClientIdsValid(Collection<T> idsFromDatabase, Collection<T> idsFromClient, Class<U> clazz){
        idsFromClient.removeAll(idsFromDatabase);
        if (idsFromClient.isEmpty()){
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (T i : idsFromClient){
            sb.append("id=").append(i).append(" was not found.").append("\n");
        }
        throw new ValidationException("validation.received_invalid_data"+"Errors for object "+clazz.getSimpleName()+": "+ sb);
    }
}
