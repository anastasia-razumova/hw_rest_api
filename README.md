#  Проект по API тестированию для [reqres.in](https://reqres.in/)

##	Содержание

- [Технологии и инструменты](#technologist-технологии-и-инструменты)
- [Реализованные проверки](#bookmark_tabs-реализованные-проверки)
- [Запуск тестов из терминала](#computer-запуск-тестов-из-терминала)
- [Запуск тестов в Jenkins](#-запуск-тестов-в-jenkins)
- [Отчет о результатах тестирования в Allure Report](#-отчет-о-результатах-тестирования-в-Allure-report)



## :rocket: Технологии и инструменты:

<p  align="center">

<code><img width="5%" title="Java" src="images/Java.svg"></code>
<code><img width="5%" title="GitHub" src="images/GitHub.svg"></code>
<code><img width="5%" title="IntelliJ IDEA" src="images/Idea.svg"></code>
<code><img width="5%" title="Gradle" src="images/Gradle.svg"></code>
<code><img width="5%" title="Junit5" src="images/Junit5.svg"></code>
<code><img width="5%" title="Allure Report" src="images/Allure.svg"></code>
<code><img width="5%" title="Jenkins" src="images/Jenkins_logo.svg"></code>
</p>

## :heavy_check_mark: Реализованные проверки:
### API Tests

- Создание нового пользователя
- Запрос данных о конкретном пользователе
- Запрос данных о списке пользователей
- Удаление пользователя
- Негативный тест на поиск несуществующего пользователя



## :computer: Запуск тестов из терминала: 


### Локальный запуск тестов:

```bash
gradle clean test
```

## <img width="4%" title="Jenkins" src="images/Jenkins_logo.svg"> Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/hw_rest_api/):
Для запуска сборки необходимо нажать кнопку <code><strong>*Собрать*</strong></code>.

<p align="center">
  <img src="images/jenk2.png" alt="Jenkins" width="800">
</p>

После выполнения сборки, в блоке <code><strong>*История сборок*</strong></code> напротив номера сборки появится
значок *Allure Report*, кликнув по которому, откроется страница с сформированным html-отчетом.

<p align="center">
  <img src="images/all2.png" alt="allure" width="800">
</p>



## <img width="4%" title="Allure Report" src="images/Allure.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/hw_rest_api/2/allure/):

<p align="center">
  <img src="images/all3.png" alt="jenk" width="900">
</p>
