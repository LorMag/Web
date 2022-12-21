<#import "parts/common.ftlh" as c>

<@c.page "Администратор">
    <div class="row no-pad">
        <div class="col-lg-6 offset-3 text-center">
            <h1 style="margin-top: 25px; margin-bottom: 25px">Пользователи</h1>
            <table class="table table-bordered table-hover align-middle">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Имя</th>
                    <th>Рейтинг</th>
                    <th>Роль</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.username}</td>
                        <td>${user.userRating}</td>
                        <td><#list user.roles as role>${role}<#sep>, </#list></td>
                        <td>
                            <#if user.isAdmin()>
                            <#else>
                                <#if user.isBanned()>
                                    <input type="button" class="deleteButton" style="background-color: green" value="Разбанить" onClick='location.href="/admin/unban/${user.userId}"'>
                                <#else>
                                    <input type="button" class="deleteButton" value="Забанить" onClick='location.href="/admin/ban/${user.userId}"'>
                                </#if>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>