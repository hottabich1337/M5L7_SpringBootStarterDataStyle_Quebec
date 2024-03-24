package org.example;

import org.example.entity.TableEntity;
import org.example.service.TableEntityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;


// НУЖНА АННОТАЦИЯ SpringBootApplication
@SpringBootApplication
public class App {
    public static void main(String[] args) {

        // КОНТЕКСТ ПОЛУЧАЕМ НЕ ТАК, КАК НА ЧИСТОМ Spring
        var context = SpringApplication.run(App.class, args);

        try (context) {

            TableEntityService tableEntityService = context.getBean(TableEntityService.class);
//
//            // ПОЛУЧАЕМ ЗАПИСЬ ИЗ БАЗЫ ПО id
//            Customer customer = customerService.get(1L).orElseThrow();
//            System.out.println(customer);
            TableEntity tableEntity = tableEntityService.get(5L).orElseThrow();
//            // ПОЛУЧАЕМ ЗАПИСЬ ИЗ БАЗЫ ПО login
//            Customer aNew = customerService.findBylogin("Yura").orElseThrow();
            System.out.println("Name moego chuvachka ravna : "+tableEntity.getName());
            tableEntity = tableEntityService.get(8L).orElseThrow();
            System.out.println("Name moego chuvachka ravna : "+tableEntity.getName());
            tableEntityService.changeTableEntityLoginById(1L,"Sanek");
            tableEntity = tableEntityService.get(1L).orElseThrow();
            System.out.println("1-st id name : "+tableEntity.getName());
//            // МЕНЯЕМ ПАРОЛЬ В ЛЮБОЙ ЗАПИСИ БАЗЫ - В ДАННОМ СЛУЧАЕ ТОМУ customer, КОТОРОГО ПОЛУЧАЛИ ВЫШЕ ПЕРВЫМ
//            customer.setPassword("new password1");
//            customerService.update(customer);
//
//            // МЕНЯЕМ ЛОГИН КАКОМУ-ТО id
//            customerService.changeCustomerLoginById(1L, "PetyaComasasds");
//
//            // ДОБАВЛЯЕМ НОВУЮ ЗАПИСЬ В БАЗУ - @transactional метод
//            customerService.addNewUser(new Customer(50L, "new", "pew"));

        }

    }
}
