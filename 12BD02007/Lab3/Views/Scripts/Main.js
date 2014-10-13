var counter = 0;
var ActiveId = null;

function edit(id) {
    if (count != 0) {
        if (ActiveId == id) {
            save();
        }

        alert("Previous changes should be submited before making new ones!")
        return;
    }

    var btn = document.getElementById(id);
    var field = btn.parentNode.nextSibling.nextSibling;

    while (field != null) {
        field.innerHTML = '<input type="text" name="Edited" value="'+ field.innerHTML.trim() +'"/>';
        field = field.nextSibling;
    }

    btn.value = "Save";
    ActiveId = id;

    counter = 1;
}

function save() {
    document.forms[0].action = "Student/Edit";
    document.forms[0].submit();
}