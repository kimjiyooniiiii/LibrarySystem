

function reserve(book_id, student_id){
    var form = document.createElement("form");
    form.action = "/reservation";
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

    
 
