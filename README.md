# Diplom_3#

Проект Stellar Burgers.

Написаны тесты:

- Регистрации
- Вход в систему
- Перехода в личный кабинет
- Перехода из личного кабинета в конструктор
- Выхода из аккаунта
- Функционал раздела «Конструктор»
    - Вкладки ингридиентов

Для запуска тестов в Яндекс браузере необходимо запустить тесты в терминале, указав путь к драйверу в переменную
driver_path
Команда: mvn clean test -Ddriver_path="src/test/driver/yandexdriver.exe"

Использованы библиотеки:

- selenium-java
- junit
- slf4j-nop
- allure-junit4
- rest-assured
- gson
- javafaker

Использованы плагины

- maven-surefire-plugin 
