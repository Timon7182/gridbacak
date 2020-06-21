package org.company.repository;

import org.company.domain.Producer;

public class ProducerRepository extends AbstractRepository<Long, Producer> {
    @Override
    protected Class<Producer> getClazz() {
        return Producer.class;
    }

    @Override
    protected String getTableName() {
        return Producer.TBL_NAME;
    }

    private static ProducerRepository instance;

    public synchronized static ProducerRepository getInstance(){
        if(instance==null){
            return new ProducerRepository();
        }
        return instance;
    }
}
