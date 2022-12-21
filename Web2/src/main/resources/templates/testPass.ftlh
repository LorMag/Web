<#import "parts/common.ftlh" as c>

<@c.page "Тест">
    <div class="row no-pad" style="margin-bottom: 100px">
        <div class="text-center">
            <h1>${test.getTestName()}</h1>
            <div style="font-size: large">${test.getDescription()}</div>
            <form method="post">
            <div class="cards col-lg-4 offset-4" style="margin-top: 25px">
                <#list test.getQuestions() as question>
                    <div class="card" style="margin-top: 25px">
                        <div class="card-body">
                            <p><b>Вопрос ${question?index+1}</b></p>
                            <p>${question.getQuestionText()}</p>
                        </div>
                        <div class="card-footer text-muted text-right">
                            <label>Ответ : <input type="text" name="answer" style="width: 300px"/></label>
                        </div>
                    </div>
                </#list>
            </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" class="passTestButton" value="Закончить">
            </form>
        </div>
    </div>

</@c.page>