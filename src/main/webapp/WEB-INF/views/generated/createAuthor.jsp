<!DOCTYPE html>
<html>
<head>
  <title>Create Author</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }

    form {
      width: 300px;
      margin: 0 auto;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }

    input[type="text"] {
      width: 100%;
      padding: 5px;
      margin-bottom: 10px;
      box-sizing: border-box;
    }

    input[type="submit"] {
      background-color: #4CAF50;
      color: white;
      padding: 10px 15px;
      border: none;
      cursor: pointer;
    }
  </style>
  <script>
    function showPopup(message) {
      alert(message);
    }

    function submitForm(event) {
      event.preventDefault();
      var form = document.getElementById("myForm");
      var formData = new FormData(form);
      var json = {};

      for (var entry of formData.entries()) {
        json[entry[0]] = entry[1];
      }

      var xhr = new XMLHttpRequest();
      xhr.open(form.method, form.action, true);
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            showPopup("Author successfully created");
          } else {
            showPopup("Oops, there was a problem");
          }
        }
      };
      xhr.send(JSON.stringify(json));
    }
  </script>
</head>
<body>
  <form id="myForm" action="http://localhost:8080/haha" method="post" onsubmit="submitForm(event)">
    <label for="authorName">Author Full Name:</label>
    <input type="text" id="authorName" name="authorName" required>

    <input type="submit" value="Submit">
  </form>
</body>
</html>
