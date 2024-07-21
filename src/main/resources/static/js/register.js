$(document).ready(
    function () {
        $("#sign_up").click(function (e) {
            e.preventDefault();

            let formData = {
                _csrf: $("#r_csrf").val(),
                user_id: $("#r_user_id").val(),
                user_pw: $("#r_user_pw").val(),
                user_name: $("#r_user_name").val(),
                phone_number: $("#r_phone_number").val(),
                email: $("#r_email").val()
            };
            $.ajax({
                type: "POST",
                url: "/register",
                data: formData,
                success: function (response) {
                    console.log("회원가입 성공:", response);
                    alert("회원가입 성공");
                    window.location.href = "/login";
                },
                error: function (error) {
                    console.log("회원가입 실패:", error);
                    alert("회원가입 실패");
                }
            });
        });
    });