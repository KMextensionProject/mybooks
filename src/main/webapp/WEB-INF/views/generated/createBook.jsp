<!DOCTYPE html>
<html>
<head>
  <title>Create Book</title>
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

    input[type="text"],
    input[type="number"] {
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
</head>
<body>
  <form id="myForm" action="http://localhost:8080/haha" method="post" onsubmit="submitForm(event)">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>

    <label for="author">Author:</label>
    <input type="text" id="author" name="author" required>

    <label for="pages">Pages:</label>
    <input type="number" id="pages" name="pages" required>

    <input type="submit" value="Submit">
  </form>

  <script>
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
        if (xhr.readyState === 4 && xhr.status === 200) {
          // Success! Do something with the response.
          console.log(xhr.responseText);
        }
      };
      xhr.send(JSON.stringify(json));
    }
  </script>
</body>
</html>
