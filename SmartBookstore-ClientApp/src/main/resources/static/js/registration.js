$(document).ready(function () {
  $("#btn-register").click(function (event) {
      event.preventDefault(); // Mencegah form melakukan submit secara default

      var formData = {
          username: $("input[name='username']").val(),
          password: $("input[name='password']").val(),
          fullname: $("input[name='fullname']").val(),
          email: $("input[name='email']").val(),
          phone: $("input[name='phone']").val()
      };

      $.ajax({
          url: "http://localhost:9090/auth/registration",
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify(formData),
          success: function (response) {
              alert("Registration successful!");
              window.location.href = "/login";
          },
          error: function (xhr, status, error) {
              alert("Registration failed: " + xhr.responseText);
          }
      });
  });
});
