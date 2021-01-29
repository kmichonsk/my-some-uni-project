var test=false;

function clear_edit(){
	var x = document.getElementsByClassName("do_edycji");
	for(i=0;x[i];i++){
		x[i].innerHTML="";
		x[i].style.display="none";
		x[i].parentElement.children[1].children[1].style.display="block";
		x[i].parentElement.children[3].style.display="block";
	}
}

function edycja(x,id_stacji){
	clear_edit();
	x.parentElement.parentElement.children[1].children[1].style.display="none";
	x.parentElement.parentElement.children[2].style.display="block";
	x.parentElement.style.display="none";
	var e_95=x.parentElement.parentElement.children[1].children[1].children[0].children[0].children[0].innerHTML;
	var e_98=x.parentElement.parentElement.children[1].children[1].children[0].children[1].children[0].innerHTML;
	var e_diesel=x.parentElement.parentElement.children[1].children[1].children[0].children[2].children[0].innerHTML;
	var e_lpg=x.parentElement.parentElement.children[1].children[1].children[0].children[3].children[0].innerHTML;
	var czas=x.parentElement.parentElement.children[1].children[1].children[1].children[0].innerHTML;
	var e_czas=czas.split(" - ");

	var do_edycji=x.parentElement.parentElement.children[2];
	
	var formularz=document.createElement("form");
	var temp=document.createElement("div");
	var block_95=document.createElement("input");
	var block_98=document.createElement("input");
	var block_d=document.createElement("input");
	var block_lpg=document.createElement("input");
	var czas_od=document.createElement("input");
	var czas_do=document.createElement("input");
	
	block_95.setAttribute("type","number");
	block_95.setAttribute("step","0.01");
	block_95.setAttribute("value",e_95);
	block_95.setAttribute("id","edit_95");
	temp.innerHTML="Benzyna 95: ";
	temp.appendChild(block_95);
	formularz.appendChild(temp);
	temp=document.createElement("div");

	block_98.setAttribute("type","number");
	block_98.setAttribute("step","0.01");
	block_98.setAttribute("value",e_98);
	block_98.setAttribute("id","edit_98");
	temp.innerHTML="Benzyna 98: ";
	temp.appendChild(block_98);
	formularz.appendChild(temp);
	temp=document.createElement("div");

	block_d.setAttribute("type","number");
	block_d.setAttribute("step","0.01");
	block_d.setAttribute("value",e_diesel);
	block_d.setAttribute("id","edit_diesel");
	temp.innerHTML="Diesel: ";
	temp.appendChild(block_d);
	formularz.appendChild(temp);
	temp=document.createElement("div");

	block_lpg.setAttribute("type","number");
	block_lpg.setAttribute("step","0.01");
	block_lpg.setAttribute("value",e_lpg);
	block_lpg.setAttribute("id","edit_lpg");
	temp.innerHTML="Lpg: ";
	temp.appendChild(block_lpg);
	formularz.appendChild(temp);
	temp=document.createElement("div");

	czas_od.setAttribute("type","time");
	czas_od.setAttribute("value",e_czas[0]);
	czas_od.setAttribute("id","edit_czas_od");
	temp.innerHTML="Od: ";
	temp.appendChild(czas_od);
	formularz.appendChild(temp);
	temp=document.createElement("div");

	czas_do.setAttribute("type","time");
	czas_do.setAttribute("value",e_czas[1]);
	czas_do.setAttribute("id","edit_czas_do");
	temp.innerHTML="Od: ";
	temp.appendChild(czas_do);
	formularz.appendChild(temp);

	temp=document.createElement("div");

	var buttons=document.createElement("button");

	buttons=document.createElement("button");
	buttons.setAttribute("onclick","anuluj()");
	buttons.innerHTML="anuluj";
	temp.appendChild(buttons);

	buttons=document.createElement("button");
	buttons.setAttribute("onclick","zapisz('"+id_stacji+"')");
	buttons.innerHTML="zapisz";
	temp.appendChild(buttons);

	do_edycji.appendChild(formularz);
	do_edycji.appendChild(temp);

}

function stacje(){
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
		var dane= JSON.parse(this.response);
		var select_field=document.getElementById("add_siec");
		var i=0;
		while(dane[i]){
			var opcja=document.createElement("OPTION");
			opcja.value = dane[i].id;
			opcja.innerHTML = dane[i].name;
			select_field.appendChild(opcja);
			i++;
		}
    }
  };
  if(test){
	xhttp.open("GET", "http://localhost:8080/api/brands", true);
  }
  else{
	xhttp.open("GET", "./api/brands", true);
  }
  xhttp.send();
}

window.onload = function() {
	stacje();
};

function szukaj_stacji(){
	document.getElementById("results").innerHTML="";

    var miasto=document.getElementsByName("miasto")[1].value;
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var danee= JSON.parse(this.response);
			map.setCenter(new google.maps.LatLng(danee.results[0].geometry.location.lat, danee.results[0].geometry.location.lng));
			map.setZoom(11);
		}
	};
	xhttp.open("GET", "https://maps.googleapis.com/maps/api/geocode/json?address="+miasto+"&key=AIzaSyCZVFHwLmdrV2Uvgo3bOXYH8CwJ5CSB0Vw", true);
	xhttp.send();	
    var on=document.getElementsByName("on")[1].checked;
    var b95=document.getElementsByName("95")[1].checked;
    var b98=document.getElementsByName("98")[1].checked;
    var lpg=document.getElementsByName("lpg")[1].checked;
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
		var dane= JSON.parse(this.response);
		var i=0;
	    while(dane[i]){
		    var stacja= document.createElement("DIV");
		    stacja.className = "stacja";
		    var nazwa= document.createElement("DIV");
		    nazwa.className = "nazwa_stacji";
		    nazwa.innerHTML = dane[i].name;
		    stacja.appendChild(nazwa);
		    var stacja_info=document.createElement("DIV");
		    stacja_info.className="stacja_info";
		    var adres=document.createElement("DIV");
		    adres.className = "adres_stacji";
		    adres.innerHTML=dane[i].address + " " + dane[i].city + " " + dane[i].postalCode;
			stacja_info.appendChild(adres);
			
			
			var temp=document.createElement("DIV");
		    stacja.appendChild(stacja_info);
		    var paliwa=document.createElement("DIV");
		    paliwa.className="spacja_paliwa";

			var paliwo=document.createElement("DIV");
			paliwo.innerHTML="Benzyna 95: <span>"+dane[i].priceFuel95 +"</span> zł";
			paliwa.appendChild(paliwo);

			paliwo=document.createElement("DIV");
			paliwo.innerHTML="Benzyna 98: <span>"+dane[i].priceFuel98 +"</span> zł";
			paliwa.appendChild(paliwo);

			paliwo=document.createElement("DIV");
			paliwo.innerHTML="Diesel: <span>"+dane[i].priceFuelDiesel +"</span> zł";
			paliwa.appendChild(paliwo);

			paliwo=document.createElement("DIV");
			paliwo.innerHTML="Lpg: <span>"+dane[i].priceFuelLpg +"</span> zł";
			paliwa.appendChild(paliwo);

		    temp.appendChild(paliwa);
		    var godziny=document.createElement("DIV");
		    godziny.className = "godziny_stacji";
		    godziny.innerHTML="otwarte: <span>"+dane[i].openingHours+"</span>";
			temp.appendChild(godziny);
			stacja_info.appendChild(temp);
			var edit_block=document.createElement("DIV");
			edit_block.style.display="none";
			stacja.appendChild(edit_block);
			edit_block.className="do_edycji";
			var edit_image=document.createElement("DIV");
			edit_image.className= "edit_image";
			var sett_img=document.createElement("img");
			sett_img.setAttribute("src","ustawienia.png");
			sett_img.setAttribute("onclick", "edycja(this,'"+dane[i].id+"')");
			edit_image.appendChild(sett_img);
			stacja.appendChild(edit_image);
			document.getElementById("results").appendChild(stacja);
			
			if(dane[i].brand.name.toLowerCase()=="inna"){
				console.log(dane[i].lat,dane[i].lat);
				var marker = new google.maps.Marker({
					position: new google.maps.LatLng(dane[i].latitude,dane[i].longitude),
					map: map
				});
			}
			else{
				console.log(dane[i].lat,dane[i].lat);
				var marker = new google.maps.Marker({
					position: new google.maps.LatLng(dane[i].latitude,dane[i].longitude),
					map: map,
					icon: "loga/"+dane[i].brand.name.toLowerCase()+".png"
				});
			}
		    i++;
	    }
    }
	};
	if(test){
		xhttp.open("GET", "http://localhost:8080/api/stations?city="+miasto+"&priceFuel95="+b95+"&priceFuel98="+b98+"&priceFuelDiesel="+on+"&priceFuelLpg="+lpg, true);
	}
	else{
		xhttp.open("GET", "./api/stations?city="+miasto+"&priceFuel95="+b95+"&priceFuel98="+b98+"&priceFuelDiesel="+on+"&priceFuelLpg="+lpg, true);
	}
    
    xhttp.send();
}

function dodaj_stacje(){
    var miasto=document.getElementsByName("miasto")[0].value;
    var ulica=document.getElementsByName("ulica")[0].value;
    var kod=document.getElementsByName("kod")[0].value;
    var nazwa=document.getElementsByName("nazwa_stacji")[0].value;
    var siec=document.getElementsByName("siec")[0].value;
    var on=document.getElementsByName("on")[0].value;
    var b95=document.getElementsByName("95")[0].value;
    var b98=document.getElementsByName("98")[0].value;
    var lpg=document.getElementsByName("lpg")[0].value;
	var godziny=document.getElementsByName("godziny_otwarcia")[0].value +" - "+document.getElementsByName("godziny_zamkniecia")[0].value;
	var adres=miasto+" "+ulica;
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var long_lat= JSON.parse(this.response);
			var lat_c = long_lat.results[0].geometry.location.lat;
			var long_c = long_lat.results[0].geometry.location.lng;
			
			var xhttp_b = new XMLHttpRequest();
    		xhttp_b.onreadystatechange = function() {
    			if (this.readyState == 4 && this.status == 200) {
		
    			}
    		};
			if(test){
				xhttp_b.open("POST", "http://localhost:8080/api/stations", true);
			}
			else{
				xhttp_b.open("POST", "./api/stations", true);
			}
	
    		xhttp_b.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp_b.send("name="+nazwa+"&address="+ulica+"&city="+miasto+"&postalCode="+kod+"&priceFuel95="+b95+"&priceFuel98="+b98+"&priceFuelDiesel="+on+"&priceFuelLpg="+lpg+"&brandID="+siec+"&openingHours="+godziny+"&longitude="+long_c+"&latitude="+lat_c);
		}
	};
	xhttp.open("GET", "https://maps.googleapis.com/maps/api/geocode/json?address="+adres+"&key=AIzaSyCZVFHwLmdrV2Uvgo3bOXYH8CwJ5CSB0Vw", true);
	xhttp.send();
}

function zapisz(x){
	var b95=document.getElementById("edit_95").value;
	var b98=document.getElementById("edit_98").value;
	var diesel=document.getElementById("edit_diesel").value;
	var lpg=document.getElementById("edit_lpg").value;
	var czas_od=document.getElementById("edit_czas_od").value;
	var czas_do=document.getElementById("edit_czas_do").value;
	var url;
	var aktualizacja={
		"id":x,
		"priceFuel95":b95,
		"priceFuel98":b98,
		"priceFuelDiesel":diesel,
		"priceFuelLpg":lpg,
		"openingHours":czas_od+" - "+czas_do
	};

	if(test){
		url = "http://localhost:8080/api/stations";
	}
	else{
		url = "./api/stations";
	}

	var xhr = new XMLHttpRequest();
	xhr.open("PATCH", url);

	xhr.setRequestHeader("Accept", "application/json");
	xhr.setRequestHeader("Content-Type", "application/json");

	xhr.onreadystatechange = function () {
		if (xhr.readyState === 4) {
			clear_edit();
			szukaj_stacji();
		}
	};
	xhr.send(aktualizacja);
}
function anuluj(){
	clear_edit();
}
