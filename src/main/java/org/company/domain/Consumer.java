package org.company.domain;


import javax.persistence.Entity;

import java.util.List;

@Entity(name = Consumer.TBL_NAME)
public class Consumer extends AbstractClient {

    public static final String TBL_NAME="consumers";

    public Consumer() {
    }

    public Consumer(String clientId, String name, List<String> location, float pwConsumption, String email, String password) {
        super(clientId, name, location, pwConsumption, email, password);
    }

}
