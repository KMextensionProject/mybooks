<html>
<body>

<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />

<!-- <script type="text/javascript" src="jsgrid.min.js"></script> -->

 <div id="jsGrid"></div>

<script>
$("#jsGrid").jsGrid({
    width: "100%",
    height: "auto",
    inserting: false, // use other screen ?
    filtering: true,
    editing: true,
    sorting: true,
    paging: true,

    //for loadData method Need to set auto load true
    autoload: true,
    pageSize: 20,
    pageButtonCount: 3,
    deleteConfirm: "Do you really want to delete this author?",

    noDataContent: "Directory is empty",

        controller: {
            loadData: function(filter) {
            	// delete unused query parameters
            	for (var propName in filter) {
            		if (filter[propName] === "") {
            			delete filter[propName];
            		}
            	}
                var d = $.Deferred();
                $.ajax({
                    url: "http://localhost:8080/mybooks/author/",
                    dataType: "json",
                    data: filter
                }).done(function(response) {
                    d.resolve(response);
                });
 
                return d.promise();
            },
            updateItem: function(item) {
            	var itemJson = JSON.stringify(item);
                return $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/mybooks/author/" + item.author_id,
                    dataType: "json",
                    contentType: "application/json",
                    data: itemJson
                });
            },
            deleteItem: function(item) {
                return $.ajax({
                    type: "DELETE",
                    url: "http://localhost:8080/mybooks/author/" + item.author_id,
                });
            }
        },

    fields: [{
    	name: "author_id",
    	type: "number",
    	width: 15,
    	visible: false,
    	editing: false
    }, {
        name: "author_name",
        title: 'Author name',
        type: "text",
        width: 200,
        editing: false
    }, {
        type: "control",
        _createFilterSwitchButton: function() {
		    return this._createOnOffSwitchButton("filtering", this.searchModeButtonClass, false);
		}
    }]
});
// hide search row after grid creation
$("#jsGrid").jsGrid("option", "filtering", false);
</script>

</body>
</html>