

function reserve(book_id, student_id){
    var form = document.createElement("form");
    form.action = "/book/reservation";
    form.method = "post";

    var inputBookId = document.createElement("input");
    inputBookId.type = "number";
    inputBookId.name = "book_id";
    inputBookId.value = book_id;
    form.appendChild(inputBookId);

    var inputStudentId = document.createElement("input");
    inputStudentId.type = "hidden";
    inputStudentId.name = "student_id";
    inputStudentId.value = student_id;
    form.appendChild(inputStudentId);


    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}

function rent(book_id, student_id){
    var form = document.createElement("form");
    form.action = "/book/rent";
    form.method = "post";

    var inputBookId = document.createElement("input");
    inputBookId.type = "number";
    inputBookId.name = "book_id";
    inputBookId.value = book_id;
    form.appendChild(inputBookId);

    var inputStudentId = document.createElement("input");
    inputStudentId.type = "hidden";
    inputStudentId.name = "student_id";
    inputStudentId.value = student_id;
    form.appendChild(inputStudentId);


    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}

function getUserId(book_id){
    var form = document.createElement("form");
    form.action = "/admin/rent";
    form.method = "get";

    var inputBookId = document.createElement("input");
    inputBookId.type = "number";
    inputBookId.name = "book_id";
    inputBookId.value = book_id;
    form.appendChild(inputBookId);

    // var inputStudentId = document.createElement("input");
    // inputStudentId.type = "hidden";
    // inputStudentId.name = "student_id";
    // inputStudentId.value = student_id;
    // form.appendChild(inputStudentId);


    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}

function bookReturn(book_id){
    var form = document.createElement("form");
    form.action = "/book/return/bookId";
    form.method = "post";

    var inputBookId = document.createElement("input");
    inputBookId.type = "number";
    inputBookId.name = "book_id";
    inputBookId.value = book_id;
    form.appendChild(inputBookId);

    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}

