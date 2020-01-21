<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <#if RequestParameters.error??>
        There is a user with such a name already!
    </#if>
    <@l.login "/login"/>
    <a href="/registration">Register</a>
</@c.page>