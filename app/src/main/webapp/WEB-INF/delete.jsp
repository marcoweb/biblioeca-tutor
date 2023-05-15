<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8" />
        <title>Remover Livro</title>
    </head>
    <body>
        <h1>Remover Livro</h1>
        <p>Deseja excluir o livro ${livro.titulo} ?</p>
        <form action="/delete" method="post">
            <input type="hidden" name="id" value="${livro.id}" />
            <button type="submit">Excluir</button>
        </form>
    </body>
</html>

