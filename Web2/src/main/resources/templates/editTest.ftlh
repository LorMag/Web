<#import "parts/common.ftlh" as c>

<@c.page "Редактирование теста">
    <div class="row text-center no-pad"  style="margin-bottom: 100px">
        <form method="post">
            <div style="margin-left: 2px">
                <label for="testName" style="font-weight: bold">Название: </label>
                <input id="testName" type="text" name="testName" value="${test.testName}" style="width: 400px">
            </div>
            <div>
                <label for="description" style="font-weight: bold; margin-top: 10px">Описание: </label>
                <input id="description" type="text" name="description" value="${test.description}" style="width: 400px">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="margin-top: 10px">Подтвердить</button>
        </form>
        <div class="col-lg-6 offset-3" style="margin-top: 50px">
            <table class="table table-bordered table-hover" border="1px" cellspacing="50px">
                <thead>
                <tr>
                    <th style="width: 30px">ID</th>
                    <th>Название</th>
                    <th>Описание</th>
                    <th style="width: 50px">Количество вопросов</th>
                    <th style="width: 30px">Рейтинг теста</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${test.testId}</td>
                    <td>${test.testName}</td>
                    <td>${test.description}</td>
                    <td>${test.numberOfQuestions}</td>
                    <td>${test.testRating}</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col-lg-6 offset-3" style="margin-top: 50px">
            <h1 style="margin-bottom: 25px">Вопросы</h1>
            <table class="table table-bordered table-hover" border="1px" cellspacing="50px">
                <thead>
                <tr>
                    <th style="width: 57px">#</th>
                    <th>Вопрос</th>
                    <th>Ответ</th>
                    <th style="width: 130px"></th>
                    <th style="width: 100px"></th>
                </tr>
                </thead>
                <tbody>
                <#list questions as question>
                    <tr>
                        <td>${question?index+1}</td>
                        <td>${question.questionText}</td>
                        <td>${question.answer}</td>
                        <td><input type="button" value="Редактировать" onClick='location.href="/tests/test/edit/${test.testId}/question/edit/${question.questionId}"'></td>
                        <td><input type="button" class="deleteButton" value="Удалить" onClick='location.href="/tests/test/edit/${test.testId}/question/delete/${question.questionId}"'></td>
                    </tr>
                </#list>
                </tbody>
            </table>
            <div><input type="button" value="Добавить вопрос" onClick='location.href="/tests/test/${test.testId}/createQuestion"' style="width: 200px"></div>
        </div>
    </div>
</@c.page>