# M-N-K
Реализация игры m-n-k на java

(Задание взято с [этого](https://www.kgeorgiy.info/courses/prog-intro/homeworks.html) сайта)

(Все тесты стоит запускать на 17 версии java)

* Реализация [игры m,n,k](https://en.wikipedia.org/wiki/M,n,k-game) (k в ряд на доске m×n).
* Добавлена обработка ошибок ввода пользователя. В случае ошибочного хода пользователь должен иметь возможность сделать другой ход.
* Добавлена обработка ошибок игроков. В случае ошибки игрок автоматически проигрывает.
* Доска производит обработку хода (проверку корректности, изменение состояния и определение результата) за O(k).
* Предотвращение жульничества: у игрока не должно быть возможности достать Board из Position.
* Добавлена поддержка кругового турнира для нескольких участников.
    * В рамках кругового турнира каждый с каждым должен сыграть две партии, по одной каждым цветом.
    * Выведена таблица очков по схеме:
        * 3 очка за победу;
        * 1 очко за ничью;
        * 0 очков за поражение.
* Добавлена поддержка препятствий — клеток, в которые запрещено делать ходы.
    * В качестве примера, сделана доска размером 11×11, у которой клетки на диагоналях запрещены.
* Добавлена поддержка мультиплеера:
    * Добавлена поддержка значков - и |;
    * Добавлена возможность игры для 3 и 4 игроков.
