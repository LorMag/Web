<#import "parts/common.ftlh" as c>

<@c.page "Создание теста">
    <div class="row text-center no-pad">
        <form method="post">
            <div style="margin-left: 2px">
                <label for="testName" style="font-weight: bold">Название: </label>
                <input id="testName" type="text" name="testName" minlength="1" style="width: 400px">
            </div>
            <div>
                <label for="description" style="font-weight: bold; margin-top: 10px">Описание: </label>
                <input id="description" type="text" name="description" style="width: 400px">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="margin-top: 10px">Создать</button>
            <input type="button" class="deleteButton" value="Отмена" onClick='location.href="/profile"'>
        </form>
    </div>
</@c.page>