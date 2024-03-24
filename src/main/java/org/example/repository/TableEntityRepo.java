package org.example.repository;

import org.example.entity.TableEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// ТЕПЕРЬ НАМ НЕ НУЖНА РЕАЛИЗАЦИЯ ИНТЕРФЕЙСА, НУЖЕН ЛИШЬ САМ ИНТЕРФЕЙС
// МЫ ЛИШЬ ОПИСЫВАЕМ МЕТОДЫ, А Spring ВОЗЬМЕТ СОЗДАНИЕ РЕАЛИЗАЦИЙ НА СЕБЯ
// ОСНОВЫВАЯСЬ НА НАЗВАНИЯХ МЕТОДОВ И АННОТАЦИЯХ НАД НИМИ

@Repository
public interface TableEntityRepo extends CrudRepository<TableEntity, Long> {

    // РАЗНЫЕ ВИДЫ Find (АНАЛОГ Get) - ЭТО БУКВА R из cRud (https://ru.wikipedia.org/wiki/CRUD)
    TableEntity findByNameIgnoreCase(String login);
    Optional<TableEntity> findById(Long id);

    Optional<TableEntity> findAllByName(String name);


    // ПАРОЧКА Update (ТЕХ, КОТОРЫЕ ЧТО-ТО МЕНЯЮТ В БАЗЕ)

    @Transactional
    @Modifying
    @Query("update TableEntity c set c.name = ?1 where c.id = ?2")
    void updateNameById(String login, Long id);

    // А ТАКЖЕ ПРИМЕР МЕТОДА, В КОТОРОМ БУДЕТ ПРОИЗВОДИТЬСЯ ПОДСЧЕТ ЧИСЛА ПОЛЕЙ В БАЗЕ
    // У КОТОРЫХ ПАРОЛЬ СОВПАДЕТ С ПЕРЕДАННЫМ В МЕТОД - ЗАМЕТЬТЕ, ОПЯТЬ Spring РЕАЛИЗУЕТ ВСЕ САМ


}
