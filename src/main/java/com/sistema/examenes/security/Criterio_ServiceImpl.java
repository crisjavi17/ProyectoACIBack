package com.sistema.examenes.security;

import com.sistema.examenes.entity.Criterio;
import com.sistema.examenes.repository.Criterio_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class Criterio_ServiceImpl extends GenericServiceImpl<Criterio, Long> implements Criterio_Service {
    @Autowired
    private Criterio_repository repository;
    @Override
    public CrudRepository<Criterio, Long > getDao() {

        return repository;
    }


}
