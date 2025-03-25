//package com.MASOWAC.readSync.specifications;
//
//import com.MASOWAC.readSync.models.Staff;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.Predicate;
//import org.springframework.data.jpa.domain.Specification;
//
//public class StaffSpecification {
//    public static Specification<Staff> hasLoginId(String loginId){
//        return new Specification<Staff>{
//            public Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb) {
//                return cb.equal(root.get(Customer_.birthday), today);
//            }
//        }
//    }
//}
