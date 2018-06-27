function saveSheet(title) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	}
	xhttp.open("POST", "/welcome/theme/set?value=" + title, true);
	xhttp.send();
}

function setStylesheet(title){ //Main stylesheet switcher function. Second parameter if defined causes a random alternate stylesheet (including none) to be enabled
var i, cacheobj, altsheets=[""]
for(i=0; (cacheobj=document.getElementsByTagName("link")[i]); i++) {
	if(cacheobj.getAttribute("rel").toLowerCase()=="alternate stylesheet" && cacheobj.getAttribute("title")) { //if this is an alternate stylesheet with title
		cacheobj.disabled = true;
		altsheets.push(cacheobj); //store reference to alt stylesheets inside array
		if(cacheobj.getAttribute("title") == title){ //enable alternate stylesheet with title that matches parameter
			cacheobj.disabled = false; //enable chosen style sheet
		}
	}
}
return ""
}

function chooseStyle(styletitle){
	if (document.getElementById){
		setStylesheet(styletitle);
		saveSheet(styletitle);
	}
}

function getStyle() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		setStylesheet(xhttp.response);
	}
	xhttp.open("GET", "/welcome/theme/get", true);
	xhttp.send();
}

getStyle();