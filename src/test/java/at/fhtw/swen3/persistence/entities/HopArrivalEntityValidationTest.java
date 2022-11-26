package at.fhtw.swen3.persistence.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.OffsetDateTime;
import java.util.Set;

@SpringBootTest
class HopArrivalEntityValidationTest {

    @Test
    public void validationTest(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        HopArrivalEntity hopArrivalEntity = HopArrivalEntity.builder()
                .id(1234L)
                .code("ABCD1")
                .description("x")
                .dateTime(OffsetDateTime.now())
                .build();

        Set<ConstraintViolation<HopArrivalEntity>> violations = validator.validate(hopArrivalEntity);

        for(ConstraintViolation<HopArrivalEntity> violation : violations){
            System.out.println(violation.getMessage());
        }
    }

}