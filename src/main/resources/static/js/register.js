// 이벤트 리스너 등록
document.getElementById('PwCheck').addEventListener('input', checkPassword);
document.getElementById('register-form').addEventListener('submit', checkForm);

function checkForm(e) {
    console.log("test")
    e.preventDefault();
    let id = document.getElementById("loginId").value;
    let password = document.getElementById('loginPw').value;
    let check = document.getElementById('PwCheck').value
    let phone = document.getElementById('phoneNumber').value;
    let name = document.getElementById('userName').value;
    let reg = /^[0-9]+/g;

    console.log(id)
    console.log(password)
    console.log(check)
    console.log(phone)
    if (id === "" || id.length !== 9) {
        alert("학번을 입력해주세요.");
        id.focus();
        return false;
    }
    if (name === '') {
        alert("이름을 확인해주세요");
        name.focus();
        return false;
    }
    if (password === '' || check === '') {
        alert("비밀번호를 확인해주세요");
        password.focus();
        return;
    }
    if (!reg.test(phone)) {
        alert("전화번호는 숫자만 입력할 수 있습니다.");
        phone.focus();
        return;
    }
    if (phone === "" || phone.length !== 11) {
        alert("전화번호를 확인해주세요");
        phone.focus();
        return false;
    }
    // console.log("test")
    document.getElementById('register-form').submit();
    // document.register-form.submit();
}

function checkPassword() {
    let password = document.getElementById('loginPw').value;
    let check = document.getElementById('PwCheck').value

    if (password !== '' && check !== '') {
        if (password === check) {
            document.getElementById('check').innerHTML = '<p>비밀번호가 일치합니다.</p>';
            document.getElementById('check').style.color = 'blue';
            document.getElementById('btn-register').removeAttribute('disabled');
        } else {
            document.getElementById('check').innerHTML = '<p>비밀번호가 일치하지 않습니다.</p>';
            document.getElementById('check').style.color = 'red';
            document.getElementById('btn-register').setAttribute('disabled', 'disabled');
        }
    }
}