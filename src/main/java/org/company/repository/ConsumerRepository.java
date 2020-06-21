package org.company.repository;

import org.company.db.HibernateUtil;
import org.company.domain.Consumer;
import org.company.domain.Producer;
import org.mindrot.jbcrypt.BCrypt;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsumerRepository extends AbstractRepository<Long,Consumer> {


    @Override
    protected Class<Consumer> getClazz() {
        return Consumer.class;
    }

    @Override
    protected String getTableName() {
        return Producer.TBL_NAME;
    }

    public ConsumerRepository() {

    }

    public boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }

    private static ConsumerRepository instance;

    public synchronized static ConsumerRepository getInstance(){
        if(instance==null){
            return new ConsumerRepository();
        }
        return instance;
    }





    public List<Producer> getThem(float num) throws Exception {
        if(num <=0.0){
            return Collections.emptyList();

        }

            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery(
                    "select * from producers s where s.powerforsell >= :stockCode")
                    .addEntity(Producer.class)
                    .setParameter("stockCode", num);
            List result = query.list();
            return result;




    }
}
