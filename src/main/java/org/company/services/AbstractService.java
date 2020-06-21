package org.company.services;

import org.company.domain.AbstractEntity;
import org.company.repository.AbstractRepository;

public abstract class AbstractService <T extends AbstractEntity<Long>> {


    protected abstract AbstractRepository<Long,T> getRepository();


}
