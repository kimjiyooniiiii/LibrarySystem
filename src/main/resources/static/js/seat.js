
var seat_id = 0;
var user_id = 1;
function setValue(value) {
    seat_id = value;
    console.log(seat_id);
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