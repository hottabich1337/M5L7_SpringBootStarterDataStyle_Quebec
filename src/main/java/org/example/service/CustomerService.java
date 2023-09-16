package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.repository.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    public Optional<Customer> get(Long id) {
        return customerRepo.findById(id);
    }

    public void update(Customer customer) {
        customerRepo.save(customer);
    }

    public Long countByPasswordIgnoreCase(String s) {
        return customerRepo.countByPasswordIgnoreCase(s);
    }

    public Optional<Customer> getByLoginAndPassword(String login, String password) {
        return customerRepo.findByLoginAndPassword(login, password);
    }

    public Optional<Customer> findBylogin(String login) {
        return customerRepo.findCustomerByLogin(login);
    }

    public void changeCustomerLoginById(Long id, String newLogin) {
        customerRepo.updateLoginById(newLogin,id);
    }


    // МЕТОД ДОБАВЛЕНИЯ НОВОЙ ЗАПИСИ В БД НА ОСНОВЕ ПЕРЕДАННОЙ entity Customer
    // ПОПРОБУЙТЕ НАЙТИ МЕТОД customerRepo.save() В РЕПОЗИТАРИИ - ЕГО ТАМ ВИЗУАЛЬНО НЕТ
    // НО МЫ НАСЛЕДУЕМ НАШ РЕПОЗИТАРИЙ ОТ CrudRepository<?, ?> - А ТАМ ОН ПРЕДСТАВЛЕН
    @Transactional
    public void addNewUser(Customer customer) {
        customerRepo.save(customer);
    }
}
