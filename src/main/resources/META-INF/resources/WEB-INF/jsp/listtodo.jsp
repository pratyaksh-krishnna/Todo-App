<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<h1>Your TODO</h1>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todo}" var="todo">
				<tr>
					<td>${todo.id}</td>
					<td>${todo.description}</td>
					<td>${todo.targetdate}</td>
					<td>${todo.done}</td>
					<td><a href="updatetodo?id=${todo.id}" class="btn btn-success">update</a></td>
					<td><a href="deletetodo?id=${todo.id}" class="btn btn-warning">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="addtodo" class="btn btn-success">Add Todo</a>
</div>
<%@ include file="common/footer.jspf"%>