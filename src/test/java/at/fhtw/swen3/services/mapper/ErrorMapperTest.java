package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ErrorMapperTest {

    @Test
    public void dtoToEntity(){
        Error error = new Error();
        error.setErrorMessage("some error...");

        ErrorEntity errorEntity = ErrorMapper.INSTANCE.dtoToEntity(error);

        assertEquals(error.getErrorMessage(),errorEntity.getErrorMessage());
    }

    @Test
    public void entityToDto(){
        ErrorEntity errorEntity = ErrorEntity.builder().errorMessage("some error").build();

        Error error = ErrorMapper.INSTANCE.entityToDto(errorEntity);

        assertEquals(errorEntity.getErrorMessage(),error.getErrorMessage());
    }
}