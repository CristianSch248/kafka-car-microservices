package com.store.car.service;

import com.store.car.dto.CarPostDTO;
import com.store.car.entity.CarPost;
import com.store.car.repository.CarPostRepository;
import com.store.car.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarPostService
    implements
        CarPostServiceInterface
{
    @Autowired
    private CarPostRepository carPostRepository;

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    @Override
    public void newPostDetails( CarPostDTO carPostDTO )
    {
        CarPost carPost = mapCarDtoToEntity( carPostDTO );
        carPostRepository.save( carPost );
    }

    @Override
    public List<CarPostDTO> getCarSales()
    {
        List<CarPostDTO> listCarSales = new ArrayList<>();

        carPostRepository.findAll()
                         .forEach( carPost ->
        {
            listCarSales.add( mapCarEntityToDTO( carPost ) );
        } );

        return listCarSales;
    }

    @Override
    public void changeCarSale( CarPostDTO carPostDTO, Long id )
    {
        carPostRepository.findById( id )
                         .ifPresentOrElse( carPost ->
        {
            carPost.setDescription( carPostDTO.getDescription() );
            carPost.setContact( carPostDTO.getContact() );
            carPost.setPrice( carPostDTO.getPrice() );
            carPost.setBrand( carPostDTO.getBrand() );
            carPost.setEngineVersion( carPostDTO.getEngineVersion() );
            carPost.setModel( carPostDTO.getModel() );

            carPostRepository.save( carPost );

        },
        () ->
        {
            throw new NoSuchElementException();
        } );
    }

    @Override
    public void removeCarSale( Long id )
    {
        carPostRepository.deleteById( id );
    }

    private CarPostDTO mapCarEntityToDTO( CarPost carPost )
    {
        return CarPostDTO.builder()
                         .brand( carPost.getBrand() )
                         .city( carPost.getCity() )
                         .model( carPost.getModel() )
                         .description( carPost.getDescription() )
                         .engineVersion( carPost.getEngineVersion() )
                         .createdDate( carPost.getCreatedDate() )
                         .ownerName( carPost.getOwnerPost().getName() )
                         .price( carPost.getPrice() )
                         .build();
    }

    private CarPost mapCarDtoToEntity( CarPostDTO carPostDTO )
    {
        CarPost carPost = new CarPost();

        ownerPostRepository.findById( carPostDTO.getOwnerId() )
                           .ifPresentOrElse( item ->
        {
            carPost.setOwnerPost( item );
            carPost.setContact( item.getContactNumber() );
        },

        () ->
        {
            throw new RuntimeException();
        } );

        carPost.setModel( carPostDTO.getModel() );
        carPost.setBrand( carPostDTO.getBrand() );
        carPost.setPrice( carPostDTO.getPrice() );
        carPost.setCity( carPostDTO.getCity() );
        carPost.setDescription( carPostDTO.getDescription() );
        carPost.setEngineVersion( carPostDTO.getEngineVersion() );
        carPost.setCreatedDate( String.valueOf( new Date() ) );

        return carPost;
    }
}
