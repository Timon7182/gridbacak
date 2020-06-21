package org.company.repository;

import org.company.domain.AbstractEntity;
import org.company.domain.Company;

public class CompanyRepository extends AbstractRepository<Long,Company> {


    @Override
    protected Class getClazz() {
        return Company.class;
    }

    @Override
    protected String getTableName() {
        return Company.TBL_NAME;
    }

    public CompanyRepository() {
    }

    private static CompanyRepository instance;

    public synchronized static CompanyRepository getInstance(){
        if(instance==null){
            return new CompanyRepository();
        }
        return instance;
    }


}
