<h1 align="center">🧑‍💻 ForoHubSD 🧑‍💻</h1>
<h2>¿Que es?</h2>
<p>Es un proyecto centrado en la creación de una API rest. El proyecto esta integrado con Spring Boot y Spring Security para la Autenticación y Autorización. También cuenta con diferentes funcionalidades, en donde puedes realizar solicitudes Http.<p>
<h2>🔨 Funcionalidades</h2>
  
- `Iniciar sesión` : Podras realizar una solicitud Http con los datos del login (nombre y contraseña). Allí Spring Secirity se encargara de verificar que el usuario exita en la Base Datos y que la contraseña sea correta. Y devuelve una respuesta con un token "JWT". ➡️ Consulta POST: http://localhost:8080/login

- `Agregar un tópico` : Podras agregar un nuevo topico mediante una solicitud Http. El cuerpo de la solicitud debe contener el Id del Usuario, el Titulo del tópico, el mensaje del tópico y el nombre del curso al cual esta asociado. Todos los datos son obligatorios y no se podra agregar un nuevo tópico con el mismo "titulo" y "mensaje" de un tópico ya existente. ➡️ Consulta POST: http://localhost:8080/topicos

- `Lista tópicos` : Aquí podras realizar una consulta Http para obtener una lista de todos los topicos existentes. Esta lista estara Paginada por lo que, solo se mostraran 10 tópicos por pagina y cada tópico estara ordena por fecha de forma ascendente. ➡️ Consulta GET: http://localhost:8080/topicos

- `Listar tópico por id` : En esta parte podras cosultar un tópico en especifico mediante su Id, realizando una consulta Http. ➡️ Consulta GET: http://localhost:8080/topicos/id

- `Actualizar tópico por id` : Aqui podras realizar una consulta Http con el id del tópico y con un cuerpo que contenga los mismos datos de `Agregar un tópico`. Todos los datos son obligatorios y se verificara que el tópico exista, que el Nombre del curso coincida y que el Id de usuario coincida con el tópico a actualizar. ➡️ Consulta PUT: http://localhost:8080/topicos/id

- `Eliminar un tópico por id` : Podras realizar una consulta Http indicando el id del tópico. Se verificara que el tópico exista y se eliminara de la Base de Datos. ➡️ Consulta DELETE: http://localhost:8080/topicos/id

<p>⚠️Importante: es obligatorio Iniciar sesión (hacer el login) para poder realizar las demás solicitudes sin ningún tipo de problema. Tambien deberas agregar al HEADER de la solicitud HTTP el token devuelto en la consulta de Iniciar sesión.</p>
