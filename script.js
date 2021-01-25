document.getElementById("main").style.height = window.innerHeight +"px";

function show(x){
	if(x=="add"){
        if(document.getElementById("add_form").style.display=="block"){
            document.getElementById("add_form").style.display = "none";
            document.getElementById("add_arrow").style.msTransform = "rotate(0deg)"; 
  		    document.getElementById("add_arrow").style.transform = "rotate(0deg)"; 
        }
        else{
		    document.getElementById("add_form").style.display = "block";
		    document.getElementById("search_form").style.display = "none";
		    document.getElementById("add_arrow").style.msTransform = "rotate(180deg)"; 
  		    document.getElementById("add_arrow").style.transform = "rotate(180deg)"; 
		    document.getElementById("search_arrow").style.msTransform = "rotate(0deg)"; 
            document.getElementById("search_arrow").style.transform = "rotate(0deg)"; 
        }
	}
	else if(x=="search"){
        if(document.getElementById("search_form").style.display=="block"){
            document.getElementById("search_form").style.display = "none";
            document.getElementById("search_arrow").style.msTransform = "rotate(0deg)"; 
  		    document.getElementById("search_arrow").style.transform = "rotate(0deg)"; 
        }
        else{
            document.getElementById("add_form").style.display = "none";
            document.getElementById("search_form").style.display = "block";
            document.getElementById("add_arrow").style.msTransform = "rotate(0deg)"; 
            document.getElementById("add_arrow").style.transform = "rotate(0deg)"; 
            document.getElementById("search_arrow").style.msTransform = "rotate(180deg)"; 
            document.getElementById("search_arrow").style.transform = "rotate(180deg)"; 
        }
	}
	else{}
}
