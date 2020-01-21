<#import "parts/common.ftl" as c>
<#import "parts/shots.ftl" as s>

<@c.page>
    <#if auth>
    <a href="/Home">Go to my Shots</a>
    </#if>
    <br/>
    <@s.shots/>
</@c.page>