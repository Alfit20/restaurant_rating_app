<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/productsStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Products</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand">Products</a>
        <div class="test">
            <div class="child2">
                <a class="buttons-link" href="/registration">
                    <button class="buttons">Зарегистрироваться</button>
                </a>
                <a class="buttons-link" href="/login">
                    <button class="buttons">Войти</button>
                </a>
                <#if email??>
                <a class="buttons-link" href="/registration">
                    <button class="buttons">Зарегистрироваться</button>
                </a>
                <span class="buttons-link">Hello ${email.email}</span>
                <a class="buttons-link" href="/add">
                    <button class="buttons">Add new Place</button>
                </a>
            </div>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="buttons">Выйти</button>
                </#if>
            </form>
        </div>
    </div>
</nav>
<h1>Найти ${message} оказалось сложнее, чем мы думали</h1>

<p> - Сократите запрос или задайте его в единственном числе</p>

<p> - Убедитесь, что наименование, имя или описание написаны правильно</p>

<a href="http://localhost:8085/">Нажмите сюда чтобы вернуться к местам</a>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>