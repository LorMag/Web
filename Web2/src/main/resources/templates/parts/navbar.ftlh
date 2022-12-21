<#include "security.ftlh">
<div class="navbar border-bottom fixed-top navbar-expand-lg navbar-light bg-light text-center">
    <div class="col-lg-6">
        <h1><a href="/" style="text-decoration: none; color: black">Simple Tests</a></h1>
    </div>
    <#if name = "unknown">
        <div class="col-lg-2 offset-lg-4">
            <input type="button" value="Войти" onClick='location.href="/login"' style="width: 100px">
        </div>
    <#else>
        <#if isAdmin>
            <div class="col-lg-1">
                <a class="nav-item nav-link my-1" href="/admin">Администратор</a>
            </div>
        </#if>
        <div class="col-lg-1">
            <a class="nav-item nav-link my-1" href="/main">Тесты</a>
        </div>
        <div class="col-lg-1">
            <a class="nav-item nav-link my-1" href="/profile">Профиль</a>
        </div>
        <div class="col-lg-1">
            <b>${name}</b>
        </div>
        <div class="col-lg-1 mr-3">
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="submit" value="Выйти" style="width: 100px"/>
            </form>
        </div>
    </#if>
</div>