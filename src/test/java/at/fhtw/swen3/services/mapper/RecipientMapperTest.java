package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipientMapperTest {

    @Test
    public void dtoToEntity(){
        Recipient recipient = new Recipient();
        recipient.setCity("A");
        recipient.setCountry("A");
        recipient.setName("A");
        recipient.setStreet("A");
        recipient.setPostalCode("A");

        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(recipient);

        assertEquals(recipient.getCity(),recipientEntity.getCity());
        assertEquals(recipient.getCountry(),recipientEntity.getCountry());
        assertEquals(recipient.getName(),recipientEntity.getName());
        assertEquals(recipient.getStreet(),recipientEntity.getStreet());
        assertEquals(recipient.getPostalCode(),recipientEntity.getPostalCode());
    }

    @Test
    public void entityToDto(){
        RecipientEntity recipientEntity = RecipientEntity.builder()
                .city("A")
                .country("A")
                .name("A")
                .postalCode("A")
                .street("A").build();

        Recipient recipient = RecipientMapper.INSTANCE.entityToDto(recipientEntity);

        assertEquals(recipient.getCity(),recipientEntity.getCity());
        assertEquals(recipient.getCountry(),recipientEntity.getCountry());
        assertEquals(recipient.getName(),recipientEntity.getName());
        assertEquals(recipient.getStreet(),recipientEntity.getStreet());
        assertEquals(recipient.getPostalCode(),recipientEntity.getPostalCode());
    }
}