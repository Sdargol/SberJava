## Задания по Java :computer:

### Первая работа. 
:books: Класс ArrayMethod содержит два статических метода - для сортировки пузырьком и бинарного поиска.

:triangular_ruler: Абстрактный класс Figure описывает сущность фигуры, от него наследуются классы
Circle, Rect, Square, Triangle - в их конструкторах мы вызываем конструктор Figure и настраиваем фигуру.
Класс Painter может отобразить фигуру исходя из объекта, который мы ему передали.

:thermometer: IConverter - функциональный интерфейс, необходим для определения лямбда выражений, которые отвечают за конвертацию температуры. Класс Celsius позволяет производить различные действия с температурой, например установить новое значение, произвести конвертацию, добавить конвертер.

### Вторая работа.

:car: Класс Car - автомобиль, класс StorageCar - хранилище автомобилей, в конструкторе принимает
список Car. Затем мы заполняем HashMap, где ключ - это тип автомобиля, а значение - это список,
где хранятся автомобили определенного типа (sedan, hatchback, crossover и т.д). Метод `getCarByType`
позволят вывести автомобили с определенным типом ( т.е получить по ключу список с автомобилями определенного типа).

:page_with_curl: TextWork - класс, где реализованы задания для работы с текстом, полученным из файла.
Содержит следующие методы: `numDifferentWords` - выводит самое встречаемое слово и сколько раз
мы его встретили, `allUniqueWord` - выводит число уникальных слов, `printSortWord` - выводит 
слова по возрастанию их длины и указывает, сколько раз встречалось каждое слово, `printLine` -
позволяет вывести определенные строки, `printReverse` - выводит строки файла в обратном порядке,
для этого используется кастомный итератор ListIteratorReverse.

### Третья работа.

:book: ICountMap - интерфейс, в котором объявлены следующие методы: `add` - добавляет элемент, 
`getCount` - возращает количество добавлений данного элемента, `remove ` - удаляет элемент и возвращает число его добавлений , 
`size` - кол-во разных элементов, `addAll` - добавить все элементы из одной коллекции в другую, в случае одинаковых суммировать значения, 
`toMap` - вернет ссылку на карту, где ключ - элемент, а значение - число его добавлений, 
`toMap(Map<T, Integer> destination)` - как и toMap, но в аргументе принимаем источник.

:hammer: CollectionUtils - класс, для работы с коллекциями. Содержит следующие статические методы:
`addAll` - добавляет элементы из одной коллекции в другую, `newArrayList` - возвращает ссылку на новый
экземпляр ArrayList, `indexOf` - возвращает индекс элемента, `limit` - возвращает ссылку на
усеченный лист, `add` - добавляет элемент в коллекцию, `removeAll` - удаляет из коллекции элементы
другой коллекции, `containsAll` - проверяет, содержит ли коллекция все элементы другой коллекции,
`containsAny` - проверяет, содержит ли коллекция хоть один элемент другой коллекции,
`range` - вернет отсортированный список с элементами в диапазоне от min до max.

### Четвертая работа.
Работа по теме Maven (Module, Build tools, Testing) находится в репозитории SocialNetwork-SberSchool-L4.

### Пятая работа.
Разработка терминала. Класс Terminal содержит логику ввода данных пользователя. Каждый символ PIN кода считывается
отдельно и проходит проверку валидатором. Класс Server отвечает за авторизацию, в случае
если пользователь неверно ввел PIN 3 раза, то аккаунт блокируется.

### Шестая работа
Задания по рефлексии.

### Седьмая работа
Задания по теме ClassLoader. Класс PluginManager с помощью метода `load` может загрузить класс плагина,
`loadUnique` - загрузит уникальный класс, т.е сможет загрузить в систему уникальный класс при условии, что класс с таким 
именем уже был загружен, `unsafeLoad` - загрузка класса без делегирования предку, в таком случае нельзя привести к типу IPlugin, загруженного системным загрузчиком,
плюс надо с плагином хранить скомпилированный IPlugin ( в данном случае в папке main ), `unsafeInvoke` - вызов метода doUseful у "небезопасного" плагина.
CryptoLoader `encryptClass` - читаем класс, шифруем и записываем новый файл - класс, `loadClass` - дешифрует класс (в нашем случае
шифрование это увеличение байта на 1).

### Восьмая работа
В рамках данного задания был реализован кэш, основанный на динамическом прокси. Данный кэш умеет сохранять данные ( сериализация и десериализация ) в файл на компьютере.
Аннотацией @Cache мы помечаем метод, результат работы которого необходимо кэшировать. Данная аннотация имеет следующие свойства: `cacheType` - хранить результат в памяти или на
файле, `cacheFileName` - имя файла кэша, `identityBy` - массив типа Class<?>, куда можно указать типы аргументов, которые будут определять уникальность результата, `listSize` - для
List размер, который необходимо хранить, `toZip` - сохранять файл дополнительно в zip архив или нет. В случае `cacheType` CacheType.IN_MEMORY используется ConcurrentHashMap, что позволяет нескольким потокам работать с кэшем параллельно и не блокировать друг друга, сериализация синхронизирована и проработана ситуация, когда два потока одновременно захотят 
сохранить кэш, в таком случае один не затрет результат другого.

### Девятая работа
В данной работе реализованы несколько вариантов Stream: `CStream` - ленивый, т.е выполняет вычисления после вызова терминального метода и `XStream` более простая реализация, но без ленивых вычислений.

### Одиннадцатая работа
Реализован свой класс ThredPool.

### Двенадцатая работа 
Задачи по многопоточности.