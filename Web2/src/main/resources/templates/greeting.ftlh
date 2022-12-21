<#import "parts/common.ftlh" as c>

<@c.page "Greeting">
    <div class="text-center">
        <h1 style="margin-top: 200px">Добро пожаловать на сайт Simple Tests</h1>
        <input type="button" class="startButton" value="Перейти к тестам" onClick='location.href="/main"'>
    </div>
</@c.page>