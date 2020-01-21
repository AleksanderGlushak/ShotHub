<#macro login path>
    <form action="${path}" method="post">
        <div><label>User name : <input type="text" name="username"/></label></div>
        <div><label>Password : <input type="password" name="password"/></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Sign in"/></div>
    </form>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign out">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</#macro>