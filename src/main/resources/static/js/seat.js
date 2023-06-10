
var urlParams = new URLSearchParams(window.location.search);

var seat_id = 0; // 현재 좌석 선택안하고 확인시 에러 발생
var student_id = "0";
var enable = true;

function setValue(value, state, st_id) {
    seat_id = value;
    enable = state;
    student_id = st_id;
    console.log(seat_id);
    console.log(student_id);
    if(enable) console.log("true")
}

function submit() {
    var form = document.createElement("form");
    form.action = "/seat";
    form.method = "post";

    var inputSeatId = document.createElement("input");
    inputSeatId.type = "number";
    inputSeatId.name = "seat_id";
    inputSeatId.value = seat_id;
    form.appendChild(inputSeatId);
    
    var inputStudentId = document.createElement("input");
    inputStudentId.type = "hidden";
    inputStudentId.name = "student_id";
    inputStudentId.value = student_id;
    form.appendChild(inputStudentId);

    var inputEnable = document.createElement("input");
    inputEnable.type = "number";
    inputEnable.name = "enable";
    if(enable)
        inputEnable.value = 1;
    else
        inputEnable.value = 0;
    form.appendChild(inputEnable);
    
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);                
}

$(document).ready(function(){
    $(".btn-group button").click(function(){
        // Remove the active class from all buttons
        $(".btn-group button").removeClass("active");
        // Add the active class to the clicked button
        $(this).addClass("active");
    });
});
