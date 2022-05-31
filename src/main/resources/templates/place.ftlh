<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/placeStyle.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <title>Place</title>
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

<div class="card mb-3" style="max-width: 540px;">
    <div class="row g-0">
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title">${place.name}</h5>
                <p class="card-text">${place.description}</p>
            </div>
        </div>
        <div class="col-md-4">
            <img src="/images/${place.photo}" class="img-fluid rounded-start" alt="">
        </div>
    </div>
</div>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <#list icons as icon>
        <div class="col">
            <div class="card gallery h-100">
                <img src="/images/icons/${icon.photo}" class="card-img-top" alt="">
            </div>
        </div>
    </#list>
</div>

<div>
    <p>Rating: ${average}</p>

</div>


<div class='closed text-center' onclick='showCloseItem(this)'>
    <p class="review">Отзывы(${count})</p>
    <div class='hiddenItem'>
        <#list reviews?sort_by("dateAdded")?reverse as review>
            <div class="container">
                <span>${review.author.email}</span>
                <p>${review.text}</p>
                <p>${review.dateAdded}</p>
            </div>
        </#list>
    </div>
</div>


<form action="/review/${place.id}" id="review_form" method="post">
    <#if dto??>
        <p style="color:red">Вы уже написали отзыв</p>
    </#if>
    <label for="message-text" class="col-form-label">Add review</label>
    <textarea rows="5" cols="10" class="form-control"
              id="message-text" name="text" required minlength="2"></textarea>
    <select name="rating" id="searchBy" required>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
    </select>
    <button type="submit" class="btn btn-success">Оставить отзыв</button>
</form>

<h3>Upload new photo</h3>
<form action="/add/${place.id}" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="formFile" class="form-label">Photo</label>
        <input class="form-control" name="file" type="file" id="formFile" required>
    </div>
    <button type="submit" class="btn btn-primary add" id="button">Upload</button>
</form>


<script>
    function showCloseItem(item) {
        $(item).toggleClass('closed', 'opened');
    };
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>