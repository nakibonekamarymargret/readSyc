package com.MASOWAC.readSync.services;

import com.MASOWAC.readSync.dto.ReaderResponse;
import com.MASOWAC.readSync.models.Reader;
import com.MASOWAC.readSync.repository.ReaderRepository;
import com.MASOWAC.readSync.specifications.ReaderSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository){
        this.readerRepository =readerRepository;
    }
//CRUD Part
//    Creating a reader
    public Reader createReader(Reader reader){
        return readerRepository.save(reader);
    }
//    Return all users
//    public List<Reader> getAllReaders(){
//        return readerRepository.findAll();
//    }
//    public Optional<Reader> getReaderById(Long id){
//        return readerRepository.findById(id);
//    }
//    public Optional<Reader>getReaderByEmail(){
//        return readerRepository.findReaderByEmail();
//    }
//    public Optional<Reader> getReaderByFirstName(String firstName){
//        return readerRepository.findReaderByFirstName();
//    }
//    public Optional<Reader> getReaderByLastName(String firstName){
//        return readerRepository.findReaderByLasttName();
//    }
////
public List<Reader> searchReaderByField(String field, String value) {
 Specification<Reader> specification = ReaderSpecification.byField(field, value);
   return specification != null ? readerRepository.findAll(specification) : List.of();
}
public List<ReaderResponse> getAllReaders() {
    List<Reader> readers = readerRepository.findAll();
    return readers.stream()
            .map(reader -> new ReaderResponse(
                    reader.getId(),
                    reader.getFirstName(),
                    reader.getLastName(),
                    reader.getEmail(),
                    reader.getPhoneNumber(),
                    reader.getAddress()))
            .collect(Collectors.toList());
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
    public void deleteReader(Long id){
       readerRepository.deleteById(id);
    }
}
