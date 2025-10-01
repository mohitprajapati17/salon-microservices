package com.mohitmac.catergory.Service.service.serviceImpl;

import java.util.Set;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;

import com.mohitmac.catergory.Service.model.Catergory;
import com.mohitmac.catergory.Service.payload_DTO.SalonDTO;
import com.mohitmac.catergory.Service.repository.CategoryRepository;
import com.mohitmac.catergory.Service.service.CatergoryService;

public class CategoryServiceImpl implements CatergoryService{


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Catergory saveCategory(Catergory catergory, SalonDTO salonDTO) {
        Catergory newCategory = new Catergory();
        newCategory.setName(catergory.getName());;
        newCategory.setImage(catergory.getImage());
        newCategory.setSalonId(salonDTO.getId());
        return categoryRepository.save(newCategory);


    }

    @Override
    public Set<Catergory> getAllbySalon(Long id) {
        return categoryRepository
        .getAllbySalonId(id);
    }

    @Override
    public Catergory getById(Long id) {
        Catergory catergory=categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("no category present"+id));
       return catergory;
    }

    @Override
    public void deleteById(Long id,Long salonId) throws Exception  {
        Catergory catergory=getById(id);
        if(catergory.getId()!=salonId){
            throw new RuntimeException("not permission to delete"+id);
        }
        categoryRepository.deleteById(id);

    }
    
}
