<#macro shots>
    <#list messages as message>
        <div>
            <span>${message.text}</span>
            <b>${message.cost}</b>
            <img src=${message.filePath}>
        </div>
    <#else>
        You don't have any shots!
    </#list>
</#macro>
