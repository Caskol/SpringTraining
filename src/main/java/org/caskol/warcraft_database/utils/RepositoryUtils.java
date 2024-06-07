package org.caskol.warcraft_database.utils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
public class RepositoryUtils {
    //TODO: Надо что-то сделать с i18n

    public static <T,ID> T getObjectFromRepository(JpaRepository<T, ID> repository, ID id, Class<T> clazz){
        if (id==null)
            throw new ValidationException("validation.received_invalid_data" + " id " + "validation");
        return repository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("validation.received_invalid_data"
                        + String.format(" id=%s of %s was not found",id,clazz.getSimpleName())));
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
