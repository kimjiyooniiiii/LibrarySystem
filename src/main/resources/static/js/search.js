

function reserve(book_id){
    var form = document.createElement("form");
    form.action = "/reservation";
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



