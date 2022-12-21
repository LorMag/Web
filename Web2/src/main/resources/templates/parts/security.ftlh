<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    curUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = curUser.getUsername()
    id = curUser.getUserId()
    isAdmin = curUser.isAdmin()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    >
</#if>