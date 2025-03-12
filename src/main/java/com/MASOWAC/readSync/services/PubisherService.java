package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.models.Publisher;
import com.MASOWAC.readSync.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PubisherService {
    private final PublisherRepository publisherRepository;

    public PubisherService(PublisherRepository publisherRepository){
        this . publisherRepository = publisherRepository;
    }

//    Creating a publisher
    public Publisher createPublisher(Publisher publisher){
        return publisherRepository.save(publisher);
    }

//    Returning all publishers
    public List<Publisher>getAllPublishers(){
        return publisherRepository.findAll();
    }

//    Returning publishers by id
    public Optional<Publisher>getPublisherById(Long id){
        return publisherRepository.findById(id);
    }

//    Updating a publisher
    public Publisher updatePublisher(Long id, Publisher publisherDetails){
        try{
            return publisherRepository.findById(id).map(publisher ->{
                if (publisherDetails.getPublisherName() != null) {
                    publisher.setPublisherName(publisherDetails.getPublisherName());
                }
                return publisherRepository.save(publisher);

            }).orElseThrow(()-> new RuntimeException("Publisher not available"));
        }catch(Exception e){
            throw new RuntimeException("Error editing publisher details", e);
        }
    }
//    Deleting a publisher
    public void deletePublisher(Long id){
        publisherRepository.deleteById(id);
    }
}
