<#import "parts/common.ftlh" as c>

<@c.page "Simple Tests">
    <div class="row no-pad" style="margin-bottom: 100px">
        <div class="col-lg-6 offset-3 text-center">
            <h1 style="margin-top: 25px; margin-bottom: 25px">Тесты</h1>
            <table class="table table-bordered table-hover align-middle">
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                    <th style="width: 50px">Количество вопросов</th>
                    <th>Автор</th>
                    <th style="width: 30px">Рейтинг теста</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list tests as test>
                    <tr>
                        <td>${test.testName}</td>
                        <td>${test.description}</td>
                        <td>${test.numberOfQuestions}</td>
                        <td>${test.authorId.username}</td>
                        <td>${test.testRating}</td>
                        <td><input type="button" value="Пройти тест" onClick='location.href="/tests/test/${test.testId}"'></td>
                        <td>
                            <div><input class="like" type="button" value="+" onClick='location.href="/tests/test/${test.testId}/like"'></div>
                            <div><input class="dislike" type="button" value="&ndash;" onClick='location.href="/tests/test/${test.testId}/dislike"'></div>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>