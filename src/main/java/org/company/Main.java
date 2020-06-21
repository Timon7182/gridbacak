package org.company;


import org.company.domain.Consumer;

import org.company.domain.Producer;
import org.company.repository.CompanyRepository;
import org.company.repository.ConsumerRepository;
import org.company.repository.ProducerRepository;
import org.company.services.ConsumerService;
import org.company.services.ProducerService;

import java.util.List;

public class Main {

    public static void main(String[] main) throws Exception {


        CompanyRepository companyRepository = CompanyRepository.getInstance();
        ProducerRepository producerRepository = ProducerRepository.getInstance();
        ConsumerRepository consumerRepository = ConsumerRepository.getInstance();
        ProducerService producerService= ProducerService.getInstance();
        ConsumerService consumerService = ConsumerService.getInstance();
        System.out.println("sdadsds");


}
    }
