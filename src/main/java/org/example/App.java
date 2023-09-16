package org.example;

import org.example.config.AppConfig;
import org.example.entity.Customer;
import org.example.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


// НУЖНА АННОТАЦИЯ SpringBootApplication
@SpringBootApplication
public class App {
    public static void main(String[] args) {

        // КОНТЕКСТ ПОЛУЧАЕМ НЕ ТАК, КАК НА ЧИСТОМ Spring
        var context = SpringApplication.run(App.class, args);

        try (context) {
            CustomerService customerService = context.getBean(CustomerService.class);

            // ПОЛУЧАЕМ ЗАПИСЬ ИЗ БАЗЫ ПО id
            Customer customer = customerService.get(1L).orElseThrow();
            System.out.println(customer);

            // ПОЛУЧАЕМ ЗАПИСЬ ИЗ БАЗЫ ПО login
            Customer aNew = customerService.findBylogin("Yura").orElseThrow();
            System.out.println(aNew);

            // МЕНЯЕМ ПАРОЛЬ В ЛЮБОЙ ЗАПИСИ БАЗЫ - В ДАННОМ СЛУЧАЕ ТОМУ customer, КОТОРОГО ПОЛУЧАЛИ ВЫШЕ ПЕРВЫМ
            customer.setPassword("new password1");
            customerService.update(customer);

            // МЕНЯЕМ ЛОГИН КАКОМУ-ТО id
            customerService.changeCustomerLoginById(1L, "PetyaComasasds");

            // ДОБАВЛЯЕМ НОВУЮ ЗАПИСЬ В БАЗУ - @transactional метод
            customerService.addNewUser(new Customer(50L, "new", "pew"));

        }

    }
}
