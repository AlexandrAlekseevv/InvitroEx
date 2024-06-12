@ui
Feature: Invitro tests

  @first
  Scenario: Меню радиологических услуг прокликивается
    Given открыта страницу Радиология
    When прокликиваем все пункты меню радиологических услуг
    Then все пункты меню должны быть доступны

  @second
  Scenario Outline: Измените город на "<NewCity>"
    Given открываем главную страницу Invitro
    When изменяем город на "<NewCity>"
    Then город должен быть изменен на "<NewCity>"
    Examples:
      | NewCity |
      | Омск    |

  @third
  Scenario: Проверка валидности формы и ввода полей
    Given открываем главную страницу Invitro
    When нажимаем кнопку Результаты анализов
    Then название открывшейся страницы "Enter your individual order number to view the test results"
    When нажимаем на кнопку "Search result", не заполняя поля
    Then поля должны быть выделены красным цветом
    And должно появиться предупреждающее сообщение
#    When fill in the fields
#      | field          | value         |
#      | Order number   | 231231231     |
#      | Birth date     | 11.12.2000    |
#      | Last name      | тест          |
#    Then the fields should contain the following values
#      | field          | value         |
#      | Order number   | 231231231     |
#      | Birth date     | 11.12.2000    |
#      | Last name      | тест          |


#  Scenario: Compare product prices
#    Given I open the Invitro website
#    When I remember the product price on the analysis page
#    And I add the product to the cart
#    Then The price in the cart should be compared with the remembered price
#    And Print a message if the price is greater or less than 10000
#    And Fail the test if the price is equal to 10000