package org.company.services;

import org.company.domain.Consumer;
import org.company.domain.Producer;
import org.company.repository.AbstractRepository;
import org.company.repository.ConsumerRepository;
import org.company.repository.ProducerRepository;

import java.util.List;

public class ProducerService extends AbstractService<Producer> {



    ProducerRepository producerRepository;
    ConsumerRepository consumerRepository;

    public ProducerService() {
        this.producerRepository= ProducerRepository.getInstance();
        this.consumerRepository = ConsumerRepository.getInstance();
    }

    private static ProducerService instance;
    public synchronized static ProducerService getInstance(){
        if(instance ==null){
            instance=new ProducerService();
        }
        return instance;
    }

    @Override
    protected AbstractRepository<Long, Producer> getRepository() {
        return this.producerRepository;
    }

    public void switchCategori(Producer p) throws Exception {

        String clientId = p.getClientId();
        String name = p.getName();
        List<String> location = p.getLocation();
        float pwConsumption = p.getPwConsumption();
        String email =p.getEmail();
        String password=p.getPassword();
        Consumer consumer = new Consumer(clientId,name,location,pwConsumption,email,password);
        consumer.setHashedPass(password);
        consumerRepository.save(consumer);
        producerRepository.delete(p.getId());

    }
}
