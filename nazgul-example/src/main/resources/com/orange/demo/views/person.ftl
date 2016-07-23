<#-- @ftlvariable name="" type="com.orange.demo.views.PersonView" -->
<html>
<body>
<!-- calls getPerson().getName() and sanitizes it -->
<h1>Hello, ${person.name?html}!</h1>
</body>
</html>