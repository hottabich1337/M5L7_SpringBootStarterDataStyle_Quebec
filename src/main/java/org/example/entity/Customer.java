package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

// ТЕПЕРЬ Customer СНАБЖЕН АННОТАЦИЯМИ ИЗ МИРА JPA - Entity И Table
// ОСТАЛЬНЫЕ - КОТОРЫЕ ИЗ Lombok - ЛИШЬ ПОМОГАЮТ НЕ ПИСАТЬ РУКАИ ГЕТТЕРЫ, СЕТТЕРИ И ПРОЧЕЕ

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String login;
    String password;

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }
}