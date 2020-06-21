package org.company.services;

import org.company.domain.Consumer;
import org.company.domain.Producer;
import org.company.repository.AbstractRepository;
import org.company.repository.CompanyRepository;
import org.company.repository.ConsumerRepository;

import java.util.ArrayList;
import java.util.List;

public class ConsumerService extends AbstractService<Consumer> {

    ConsumerRepository consumerRepository;

    public ConsumerService() {
        this.consumerRepository= ConsumerRepository.getInstance();
    }

    private static ConsumerService instance;

    public synchronized static ConsumerService getInstance(){
        if(instance ==null){
            instance=new ConsumerService();
        }
        return instance;
    }

    public List<Producer> getAvailableSurplusses(float consumption) throws Exception {
        List<Producer> p = consumerRepository.getThem(consumption);
        return p;
    }


    @Override
    protected AbstractRepository<Long, Consumer> getRepository() {
        return this.consumerRepository;
    }


    public void changePassword(Consumer c,String password) throws Exception {
        c.setPassword(password);
        consumerRepository.update(c);
    }

    public void changePassword(Consumer c,String newpassword,String oldpass) throws Exception {
       boolean correct= consumerRepository.checkPass(oldpass,c.getPassword());
        if(correct){
            c.setPassword(newpassword);
            consumerRepository.update(c);
        }
    }


}
