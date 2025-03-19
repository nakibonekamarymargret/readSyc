package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.dto.ReaderResponse;
import com.MASOWAC.readSync.exceptions.ReaderNotFoundException;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.repository.ReaderRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;
    private HashMap<String, Object> response ;

    public ReaderService(ReaderRepository readerRepository){
        this.readerRepository =readerRepository;
    }
//CRUD Part
//    Creating a reader
    public Reader createReader(Reader reader){
        return readerRepository.save(reader);
    }
//    Return all users
    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }


public Reader updateReader(Long id,  Reader readerDetails){
        try{
            return readerRepository.findById(id).map(reader ->{
//                Update fields if not null
                if(readerDetails.getFirstName()!= null){
                    reader.setFirstName(readerDetails.getFirstName());
                }
                if(readerDetails.getLastName()!= null){
                    reader.setLastName(readerDetails.getLastName());
                }
                if(readerDetails.getEmail()!=null){
                    reader.setEmail(readerDetails.getEmail());
                }
                if(readerDetails.getAddress()!=null){
                    reader.setAddress(readerDetails.getAddress());
                }
                if(readerDetails.getPhoneNumber()!=null){
                    reader.setPhoneNumber(readerDetails.getPhoneNumber());
                }
                return readerRepository.save(reader);
            }).orElseThrow(()->new RuntimeException("Reader not available"));
        }catch(Exception e){
            throw new RuntimeException("Error updating the reader details",e);
        }

}
//Deleting a reader
public Reader deleteReader(Long id) {
    Optional<Reader> reader = readerRepository.findById(id);
    if (reader.isPresent()) {
        readerRepository.deleteById(id);
        return reader.get(); // Return the deleted Reader object
    } else {
        throw new ReaderNotFoundException("Reader not found with id " + id);
    }
}

}
