# API ENDPOINTS

### Авторизация

POST: http://95.214.11.83:8080/api/auth/signin

##### Тело запроса:
```json
{
    "username":string,
    "password":string
}
```

##### Ответ:
* Успешная авторизация (Статус ответа: 200):
```json
{
    "token": string,
    "id": integer,
    "username": string
}
```

* Неверный логин/пароль, пользователь не существует и т.п:
Статус 401 без тела ответа


### Регистрация

POST: http://95.214.11.83:8080/api/auth/signup

##### Тело запроса:
```json
{
    "username":string,
    "password":string
}
```

##### Ответ:
* Успешная регистрация (Статус ответа: 200):
```json
{
    "message": "user registered successfully"
}
```
* Имя пользователя занято (Статус ответа: 400):

```json
{
    "error": "username is already taken!"
}
```

## Все следующие эндпоинты требуют наличия bearer токена в заголовке запроса
Если токена нет либо он истёк или неверный, эти эндпоинты отвечают статусом 401 без тела ответа

Такой же ответ будет, если какое-то обязательное значение в jsonе запроса отсутствует или json кривой

Токен живёт 12 часов

### Создание нового персонажа

POST: http://95.214.11.83:8080/api/character/create 

##### Тело запроса:
form-data со следующими параметрами:

* name - Text

* class - Text

* race - Text

* image - File

Здесь поле image является не обязательным. Это картинка - аватарка персонажа, которую пользователь может загрузить со своего устройства

Допустимые значения поля race: "Бард", "Жрец", "Друид", "Монах", "Плут", "Колдун", "Варвар", "Воин", "Следопыт", "Паладин", "Чародей", "Волшебник"

В зависимости от значения поля race будет рассчитан показатель здоровья созданного персонажа

Остальные параметры ставятся по умолчанию

##### Ответ:
* Персонаж создан (Статус ответа: 200):
```json
{
    "id": integer,
    "userId": integer,
    "name": string,
    "characterClass": string,
    "race": string,
    "imageLink": string,
    "lvl": integer,
    "experience": integer,
    "health": integer,
    "strength": integer,
    "physique": integer,
    "dexterity": integer,
    "wisdom": integer,
    "intelligence": integer,
    "charisma": integer,
    "notes": string,
    "modifiers": {
        "id": integer,
        "athletics": integer,
        "acrobatics": integer,
        "sleightOfHand": integer,
        "stealth": integer,
        "perception": integer,
        "survival": integer,
        "medicine": integer,
        "insight": integer,
        "animalCare": integer,
        "analysis": integer,
        "history": integer,
        "magic": integer,
        "nature": integer,
        "religion": integer,
        "performance": integer,
        "intimidation": integer,
        "fraud": integer,
        "conviction": integer
    }
}
```

### Получение списка всех персонажей пользователя

GET: http://95.214.11.83:8080/api/character/get

##### Ответ:
* Список персонажей (Статус ответа: 200)
```json
{
    "characters": [
        {
            "characterClass": string,
            "race": string,
            "name": string,
            "imageLink": string,
            "characterLink": string
        },
        ...
      ]
}
```

### Получение полной информации по конкретному персонажу

GET: http://95.214.11.83:8080/api/character/{id}

##### Ответ: Точно такой же json, как при создании персонажа

### Изменение параметров персонажа

PATCH: http://95.214.11.83:8080/api/character/update

##### Тело запроса:

```json
{
  "characterId": integer,
  "newValues": {
    string:value(integer or string)
  }
}
```
В newValues перечислить все характеристики, которые нужно изменить и их новые значения

Это распространяется на модификаторы и все остальные характеристики

Для примера:

```json
{
    "characterId":4,
    "newValues":{
        "acrobatics":-4
    }
}
```

```json
{
    "characterId":4,
    "newValues":{
        "acrobatics":-4,
        "health": 17
    }
}
```

```json
{
    "characterId":4,
    "newValues":{
        "acrobatics":-4,
        "health": 17,
        "name":"aboba"
    }
}
```

Все приведённые выше jsonы одинаково валидны. Можно поменять хоть всё разом, но чаще всего, вероятно, будет использоваться подход, когда при одном запросе меняется один показатель

ВАЖНО: здесь не нужно выделять модификаторы в отдельный объект

##### Ответ: Точно такой же json, как при создании персонажа, но с новыми значениями

### Получение картинки персонажа

GET: http://95.214.11.83:8080/api/image/{imageName}

Это ссылка из поля персонажа imageLink

##### Ответ:

Загруженное пользователем при создании персонажа изображение