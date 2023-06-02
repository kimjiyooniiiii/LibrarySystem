
var seat_id = 0; // 현재 좌석 선택안하고 확인시 에러 발생
var user_id = 1;
var is_used = true;

function setValue(value, state) {
    seat_id = value;
    console.log(seat_id);

    is_used = state;
    if(is_used) console.log("true")
}

function submit() {
    var form = document.createElement("form");
    form.action = "/seat";
    form.method = "post";

    var inputSeatId = document.createElement("input");
    inputSeatId.type = "number";
    inputSeatId.name = "seatId";
    inputSeatId.value = seat_id;
    form.appendChild(inputSeatId);
    
    var inputUserId = document.createElement("input");
    inputUserId.type = "number";
    inputUserId.name = "userId";
    inputUserId.value = user_id;
    form.appendChild(inputUserId);

    var inputIsUsed = document.createElement("input");
    inputIsUsed.type = "hidden";
    inputIsUsed.name = "isUsed";
    inputIsUsed.value = is_used;
    form.appendChild(inputIsUsed);

    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);                
}


$(document).ready(function () {
    $(".empty_seat").click(function () {
        // Remove the active class from all buttons
        $(".empty_seat").removeClass("active");
        // Add the active class to the clicked button
        $(this).addClass("active");
    });
});