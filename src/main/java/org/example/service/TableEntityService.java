package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.TableEntity;
import org.example.repository.TableEntityRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@AllArgsConstructor
public class TableEntityService {

    private final TableEntityRepo tableEntityRepo;

    public Optional<TableEntity> get(Long id) {
        return tableEntityRepo.findById(id);
    }

    public void update(TableEntity TableEntity) {
        tableEntityRepo.save(TableEntity);
    }


    public Optional<TableEntity> findByName(String name) {
        return tableEntityRepo.findAllByName(name);
    }

    public void changeTableEntityLoginById(Long id, String newName) {
        tableEntityRepo.updateNameById(newName,id);
    }


    // МЕТОД ДОБАВЛЕНИЯ НОВОЙ ЗАПИСИ В БД НА ОСНОВЕ ПЕРЕДАННОЙ entity TableEntity
    // ПОПРОБУЙТЕ НАЙТИ МЕТОД tableEntityRepo.save() В РЕПОЗИТАРИИ - ЕГО ТАМ ВИЗУАЛЬНО НЕТ
    // НО МЫ НАСЛЕДУЕМ НАШ РЕПОЗИТАРИЙ ОТ CrudRepository<?, ?> - А ТАМ ОН ПРЕДСТАВЛЕН
    @Transactional
    public void addNewUser(TableEntity TableEntity) {
        tableEntityRepo.save(TableEntity);
    }
}
