package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ErrorRepositoryTest {

    @Autowired
    private ErrorRepository errorRepository;

    @Test
    public void repoTest(){
        ErrorEntity error = ErrorEntity.builder()
                .errorMessage("error")
                .build();

        errorRepository.save(error);

        System.out.println(errorRepository.findAll());
    }

}