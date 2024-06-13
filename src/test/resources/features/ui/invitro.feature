@ui
Feature: Invitro tests

  @first
  Scenario: Меню радиологических услуг прокликивается
    Given открыта страница Радиология
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
  Scenario Outline: Проверка валидности формы и ввода полей
    Given открываем главную страницу Invitro
    When нажимаем кнопку Результаты анализов
    Then название открывшейся страницы "Введите индивидуальный номер заказа, чтобы посмотреть результаты анализов"
    When нажимаем на кнопку "Найти результаты", не заполняя поля
    Then поля должны быть выделены красным цветом
    And должно появиться предупреждающее сообщение
    When вводим данные индивидуального заказа для "<userId>"
    Then поля должны содержать введённые значения "<userId>"
    Examples:
      | userId |
      | user1  |
      | user2  |

  @fourth
    @fifth
  Scenario Outline: сравниваем сумму в корзине с запомненной суммой
    Given пользователь находится на странице Анализы
    When ищем анализ по коду в поисковой строке "<AnalyzeCode>"
    When пользователь сохраняет цену выбраного анализа "<AnalyzeCode>"
    And пользователь добавляет выбранный анализ "<AnalyzeCode>" в корзину
    And кликаем по иконке корзины
    Then цена в корзине должна совпадать с сохраненной ценой
    Examples:
      | AnalyzeCode |
      | 16          |
      | 1515        |

#  Scenario: Compare product prices
#    Given I open the Invitro website
#    When I remember the product price on the analysis page
#    And I add the product to the cart
#    Then The price in the cart should be compared with the remembered price
#    And Print a message if the price is greater or less than 10000
#    And Fail the test if the price is equal to 10000