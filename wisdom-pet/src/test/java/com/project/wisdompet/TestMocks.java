package com.project.wisdompet;

import com.project.wisdompet.entity.CustomerEntity;
import com.project.wisdompet.models.CustomerDTO;
import com.project.wisdompet.models.ProductDTO;

import java.util.List;

public class TestMocks {

    public static List<CustomerEntity> entityList() {
        CustomerEntity firstEntity = new CustomerEntity();
        CustomerEntity secondEntity = new CustomerEntity();

        firstEntity.setCustomerId(1);
        firstEntity.setFirstName("George");
        firstEntity.setLastName("I");
        firstEntity.setEmail("jgoegj@yahoo.com");
        firstEntity.setPhone("085940859");

        secondEntity.setCustomerId(2);
        secondEntity.setFirstName("Maria");
        secondEntity.setLastName("A");
        firstEntity.setEmail("jrojrosj@yahoo.com");
        firstEntity.setPhone("429483995");

        return List.of(firstEntity, secondEntity);
    }

    public static List<CustomerDTO> customerDTOS() {
        List<CustomerEntity> entities = entityList();
        CustomerDTO customerDTO1 = new CustomerDTO();

        CustomerDTO customerDTO2 = new CustomerDTO();

        return List.of(customerDTO1,customerDTO2);
    }


}
