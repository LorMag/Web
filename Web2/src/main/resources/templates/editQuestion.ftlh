<#import "parts/common.ftlh" as c>

<@c.page "Редактирование вопроса">
    <div class="row text-center no-pad">
        <form method="post">
            <div>
            <label for="questionText" style="font-weight: bold">Вопрос: </label>
            <input id="questionText" type="text" name="questionText" value="${question.questionText}" style="width: 400px">
            </div>
            <div style="margin-top: 10px">
            <label for="answer" style="font-weight: bold; margin-left: 13px">Ответ: </label>
            <input id="answer" type="text" name="answer" value="${question.answer}" style="width: 400px">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="margin-top: 10px">Подтвердить</button>
        </form>
    </div>
</@c.page>