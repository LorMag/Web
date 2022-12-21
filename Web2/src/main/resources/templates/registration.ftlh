<#import "parts/common.ftlh" as c>

<@c.page "Регистрация">
    <div class="row no-pad" style="margin-top: 200px">
        <div class="col-lg-4 offset-4 text-center loginWindow">
            <form action="/registration" method="post">
                <div class="row">
                    <h1>Регистрация</h1>
                    <div style="margin-top: 20px;">
                        <label style="margin-left: 25px">Имя : <input type="text" name="username" id="username"/></label>
                    </div>
                    <div style="margin-top: 20px;">
                        <label>Пароль : <input type="password" name="password" id="password"/></label>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div style="margin-top: 20px;">
                        <input type="submit" value="Зарегистрироваться"/>
                    </div>
                    <#if message??>
                        <h1 style="color: #dd0707">${message!}</h1>
                    </#if>
                </div>
            </form>
        </div>
    </div>
</@c.page>