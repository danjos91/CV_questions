# Оценка Mock-интервью (Mock Interview Evaluation)

## Общая оценка
Вы показываете хороший уровень знаний для Middle+/Senior уровня. У вас есть понимание архитектурных паттернов (Saga, Outbox), вы знаете алгоритмы System Design (Token Bucket) и понимаете концепции отказоустойчивости (Graceful Degradation). Однако в вопросах глубокого знания Core Java (JVM/Memory Leaks) и "магии" Spring Framework (AOP/Proxies) были допущены серьезные ошибки.

---

### Анализ ответов:

#### 1. Утечка памяти (Memory Leak)
**Вопрос:** *Imagine we have a high-throughput application and we are experiencing frequent `OutOfMemoryError: Java heap space`. As a Senior Developer, how would you approach diagnosing and fixing this memory leak in production? Walk me through your step-by-step process and the tools you would use.*
*   **Ваш ответ:** Проверка коммитов, логов, добавление оперативной памяти (RAM).
*   **Фидбек:** `Оценка 4/10`. Добавление RAM не лечит утечку памяти, а лишь откладывает падение (OOM). 
*   **Как отвечать Senior-у:** Упомянуть JVM флаг `-XX:+HeapDumpOnOutOfMemoryError`, инструменты анализа дампа памяти (Eclipse MAT, VisualVM, JProfiler), анализ Dominator Tree и GC логов (Garbage Collection). 

#### 2. Concurrency: `synchronizedMap` vs `ConcurrentHashMap`
**Вопрос:** *In a highly concurrent application, we need to share a map across multiple threads. Can you explain the difference between using `Collections.synchronizedMap(new HashMap<>())` and `ConcurrentHashMap`? How does each one achieve thread safety under the hood, and why is one generally preferred over the other in modern Java?*
*   **Ваш ответ:** `synchronizedMap` блокирует всю мапу (один поток), нет атомарных операций. `ConcurrentHashMap` блокирует часть мапы, есть атомарные операции.
*   **Фидбек:** `Оценка 7/10`. Хороший ответ. 
*   **Как улучшить:** Добавить детали про эволюцию Java (в Java 8 `ConcurrentHashMap` перешел от Segment Locking к CAS-операциям и блокировке только первого узла корзины). Упомянуть про weakly consistent итераторы и запрет на использование `null` ключей/значений в `ConcurrentHashMap`.

#### 3. Spring AOP и Транзакции (Self-invocation)
**Вопрос:** *В Spring Boot приложении есть класс `UserService` с методом `processUser()`, который вызывает внутри себя метод `updateUser()` (помеченный `@Transactional`). Сработает ли транзакция при вызове `updateUser()` внутри `processUser()`? Если нет, то почему (опишите механизм под капотом Spring), и как минимум 2-3 способа это исправить?*
*   **Ваш ответ:** Транзакция сработает при вызове `@Transactional` метода из обычного метода того же класса.
*   **Фидбек:** `Оценка 1/10`. Критическая ошибка. Транзакция **не сработает** из-за проблемы самовызова (Self-invocation).
*   **Как отвечать Senior-у:** Объяснить механизм Spring AOP (прокси-объекты). Вызов `this.updateUser()` идет в обход прокси, поэтому Spring не может начать транзакцию. Решение: вынести метод в другой бин, повесить аннотацию на вызывающий метод, использовать само-инжекцию (self-injection) или AspectJ.

#### 4. Проблема N+1 (JPA/Hibernate)
**Вопрос:** *В Hibernate / JPA одной из самых частых проблем производительности является проблема N+1. 1. Объясните, что такое проблема N+1 (на примере). 2. Как эта проблема возникает? 3. Назовите как минимум два способа, как Senior-разработчик решил бы эту проблему в Spring Data JPA.*
*   **Ваш ответ:** Запрос получает данные, а затем в цикле делает еще N запросов. Решение: получить все нужные данные одним запросом.
*   **Фидбек:** `Оценка 5/10`. Правильная суть, но не хватает терминологии фреймворка.
*   **Как отвечать Senior-у:** Упомянуть `FetchType.LAZY`. Назвать конкретные решения: `JOIN FETCH` в JPQL/HQL, `EntityGraph` (в JPA 2.1+) для декларативного указания загрузки, и настройку `@BatchSize` (или `default_batch_fetch_size`) для смягчения проблемы.

#### 5. Распределенные транзакции (Микросервисы)
**Вопрос:** *Вы проектируете систему микросервисов. Процесс оформления заказа затрагивает Order Service, Inventory Service и Payment Service (у каждого своя БД). Если на этапе Payment происходит ошибка, нам нужно откатить товар в Inventory и перевести заказ в FAILED в Order Service. Какой паттерн вы бы применили для обеспечения согласованности данных?*
*   **Ваш ответ:** Saga (Orchestration с методами отмены), Transactional Outbox (с брокером сообщений и статусами в таблице).
*   **Фидбек:** `Оценка 8.5/10`. Отличный ответ, названы правильные паттерны и механизмы (компенсирующие транзакции).
*   **Как улучшить:** Чуть развернуть мысль. Упомянуть Eventual Consistency (итоговую согласованность) и важность Идемпотентности (Idempotency) консьюмеров при чтении из брокера сообщений.

#### 6. Rate Limiter (System Design)
**Вопрос:** *Нам нужно реализовать Rate Limiter, который позволяет каждому пользователю делать не более 100 запросов в минуту к API. 1. Какие алгоритмы вы знаете? 2. Где бы вы хранили данные счетчиков в распределенной системе? 3. Что произойдет, если хранилище счетчиков временно станет недоступным?*
*   **Ваш ответ:** Token bucket (корзина токенов), Redis для распределенного хранения, Graceful Degradation (локальные счетчики при падении Redis).
*   **Фидбек:** `Оценка 9/10`. Блестящий ответ.
*   **Разбор:** Вы точно назвали алгоритм (Token Bucket), правильное in-memory хранилище (Redis) и предложили идеальный архитектурный выход из нештатной ситуации (Graceful Degradation с fallback на локальные лимиты).

---

### Рекомендации по подготовке на завтра:
1.  **Повторите Spring AOP и Proxy механизм.** Это "база" для Senior разработчика. Почитайте про проблемы Self-invocation для `@Transactional`, `@Async` и `@Cacheable`.
2.  **Повторите инструменты профилирования JVM.** Посмотрите видео или почитайте статьи о том, как читать Heap Dump (Eclipse MAT) и Thread Dump.
3.  **Повторите тонкости Hibernate.** `JOIN FETCH`, `EntityGraph`, кеш первого и второго уровня (L1/L2 Cache), жизненный цикл сущности (Transient, Managed, Detached).
4.  Ваши знания архитектуры (Saga, Outbox, System Design) на очень хорошем уровне, чувствуйте себя уверенно в этих вопросах! Удачи на собеседовании!