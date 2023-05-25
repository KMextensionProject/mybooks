<html>
<body>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>

<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />

<link type="text/css" rel="stylesheet" href="jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="jsgrid-theme.min.css" />
<!-- <script type="text/javascript" src="jsgrid.min.js"></script> -->

 <div id="jsGrid"></div>

<script>
$("#jsGrid").jsGrid({
    width: "100%",
    height: "auto",
    filtering: true,
    editing: false,
    sorting: true,
    paging: true,

    //for loadData method Need to set auto load true
    autoload: true,
    pageSize: 15,
    pageButtonCount: 5,
    deleteConfirm: "Do you really want to delete the client?",

    noDataContent: "Directory is empty",

        controller: {
            loadData: function() {
                var d = $.Deferred();
 
                $.ajax({
                    // CORS policy enable on local server for now
                    url: "http://localhost:8080/mybooks/books/",
                    dataType: "json"
                }).done(function(response) {
                    d.resolve(response);
                });
 
                return d.promise();
            }
        },

    fields: [{
        name: "isbn",
        title: 'ISBN',
        type: "text",
        width: 30
    }, {
        name: "title",
        title: 'Title',
        type: "text",
        width: 50
    }, {
        name: "authors[0].name",
        title: 'Author',
        type: "text",
        width: 50
    }, {
        name: "pages",
        title: 'Pages',
        type: "text",
        width: 30
    }, {
        name: "bindingType.name",
        title: 'Binding',
        type: "text",
        width: 50
    }, {
        name: "format.dimensions",
        title: 'Format',
        type: "text",
        width: 30
    }, {
        name: "language.name",
        title: "Language",
        type: "text",
        width: 50
    },  {
        name: "orderInSeries",
        title: "Series #",
        type: "text",
        width: 50
    }, {
        type: "control" 
    }]
});
</script>
 
</body>
</html>