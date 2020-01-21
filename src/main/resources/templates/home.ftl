<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "parts/shots.ftl" as s>
<@c.page>
<@l.logout/>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="Enter discription" required="true"/>
        <input type="number" name="cost" placeholder="Enter cost" required="true"/>
        <input type="file" name="image" placeholder="Choose the shot" required="true"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Add</button>
    </form>
    </div>
    <div>Shots list</div>
    <@s.shots/>
    <br/>
    <a href="/ShotHub">Go to ShotHub</a>
</@c.page>