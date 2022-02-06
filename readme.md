[Test task - S7](https://github.com/boomzin/s7SpringBoot)

Сервис для социальной сети.

Основные функции сервиса:

- регистрация нового пользователя;
- авторизация существующего пользователя;
- пользователь может искать других пользователей;
- пользователь может добавлять других пользователей в «друзья»;
- пользователь может удалять других пользователей из «друзей»;
- пользователь может получить список своих друзей;


#### База данных H2 в памяти с таблицами:
- **USERS** - содержит сущности отправителей
    - соответствует классу `User.class`
- **FRIENDSHIP, USER_ROLES** - вспомогательные таблицы не связанные с сущностями

Порядок запуска из репозитория:
<pre>git clone https://github.com/boomzin/s7springboot
cd s7springboot/
mvn package
java -jar target/s7testtask-0.0.1-SNAPSHOT.jar
</pre>

Порт приложения по умолчанию - 8085

После запуска будут доступны два endpoint:
<pre>http://localhost:8085/api/account</pre>
<pre>http://localhost:8085/api/users</pre>

И документация к API:
<pre>http://localhost:8085/swagger-ui/index.html</pre>

Для тестирования доступны учетные данные пользователей:

    user1@gmail.com user1pass
    user2@gmail.com user2pass
    user3@gmail.com user3pass


