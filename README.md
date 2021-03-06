# ATM-emulator

 Консольный эмулятор работы банкомата c функциями: 
 - аутентификации по номеру карты и пин-коду, 
 - просмотра баланса, 
 - внесения и снятия средств. 
 - мультивалютности.
 Пользователь сможет оперировать только теми купюрами, которые поместит в банкомат.
 Если для снятия требуемой суммы будет недостаточно банкнот, то банкомат сообщит об этом пользователю.

 ConsoleHelper выводит в консоль сообщения, считывает их с консоли и возвращает нужную операцию.

 Enum Operation содержит возможные для работы банкомата операции LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT.

 Пакет exception содержит два checked исключения:
 InterruptOperationException, когда нужно прервать текущую операцию и выйти из приложения.
 NotEnoughMoneyException, когда банкомат не сможет выдать запрашиваемую сумму.

 CurrencyManipulator хранит всю информацию о выбранной валюте.
 String currencyCode - код валюты, например, USD. Состоит из трех букв.
 Map<Integer, Integer> denominations - это Map<номинал, количество>.

 CurrencyManipulatorFactory - фабрика, которая создает и хранит манипуляторы.
 getManipulatorByCurrencyCode создает нужный манипулятор, если он еще не существует, либо возвращать ранее созданный.
 Без учета регистра искомой валюты.

 Пакет Command реализует одноименный паттерн. В нем каждой команде из enum Operation соответствует определенный
 класс-команда. Класс CommandExecutor содержит статическую карту Map<Operation, Command> allKnownCommandsMap,
 проинициализированную всеми известными операциями и командами, и позволяет взаимодействовать со всеми командами.
 Метод execute(Operation operation) вызывает соответствующий метод execute у нужной команды.

 Пакет resources содержит property файлы с данными, которые используются в методе execute() каждой командой
 из пакета Command.
