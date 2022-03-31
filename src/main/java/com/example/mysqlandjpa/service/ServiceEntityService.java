package com.example.mysqlandjpa.service;

import com.example.mysqlandjpa.entity.ServiceEntity;
import com.example.mysqlandjpa.repository.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEntityService {
    @Autowired
    ServiceEntityRepository serviceEntityRepository;
    public String getByUrl(String url){
        String serviceInfo=serviceEntityRepository.findByUrl(url);
        return serviceInfo;
    }

    public String save(List<ServiceEntity> test){
         List<ServiceEntity> s=serviceEntityRepository.saveAll(test);
        return s.toString();
    }
     public List<ServiceEntity> findAll(){
        List<ServiceEntity> serviceEntities=serviceEntityRepository.findAll();
        return  serviceEntities;
     }

     public void saveOne(ServiceEntity serviceEntity){
        serviceEntityRepository.save(serviceEntity);
     }

     public void delById(String serviceId){
        serviceEntityRepository.deleteById(serviceId);
     }
     public ServiceEntity findOne(String serviceId)
     {
         Optional<ServiceEntity> serviceEntity=serviceEntityRepository.findById(serviceId);
         return  serviceEntity.get();
     }
     public List<ServiceEntity> findByUrlAndBody(String url,String body)
     {
         List<ServiceEntity> all =serviceEntityRepository.findByUrlOrBody(url,body);
         return all;
     }
     public Page<ServiceEntity> getServiceListWithPage(int pageNum, int pageSize)
     {
         Sort sort= Sort.by(Sort.Direction.DESC,"createDate");
         Pageable pageable= PageRequest.of(pageNum,pageSize,sort);
         Page<ServiceEntity> serviceEntities=serviceEntityRepository.findAll(pageable);
         return  serviceEntities;
     }

}
