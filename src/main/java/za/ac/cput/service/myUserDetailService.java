//package za.ac.cput.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import za.ac.cput.domain.Customer;
//import za.ac.cput.domain.UserPrinciple;
//import za.ac.cput.repository.CustomerRepository;
//@Service
//public class myUserDetailService implements UserDetailsService {
//
//    @Autowired
//    public CustomerRepository customerRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Customer customer1 = customerRepository.findByContact_Email(email);
//
//        if(customer1 == null){
//            System.out.println("userNotFound");
//            throw new UsernameNotFoundException("User not Found");
//        }
//
//        return new UserPrinciple(customer1);
//    }
//}
