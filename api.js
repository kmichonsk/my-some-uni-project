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
  xhttp.open("GET", "./api/brands", true);
  xhttp.send();
}

window.onload = function() {
	stacje();
};

function szukaj_stacji(){
    var miasto=document.getElementsByName("miasto")[1].value;
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
		    stacja.appendChild(stacja_info);
		    var paliwa=document.createElement("DIV");
		    paliwa.className="spacja_paliwa";
		    var temp=0;
		    if(dane[i].hasFuel95){
			    var paliwo=document.createElement("DIV");
			    paliwo.innerHTML="Benzyna 95: "+dane[i].hasFuel95 +" zł";
			    paliwa.appendChild(paliwo);
			    temp++;
		    }
		    if(dane[i].hasFuel98){
			    var paliwo=document.createElement("DIV");
			    paliwo.innerHTML="Benzyna 98: "+dane[i].hasFuel98 +" zł";
			    paliwa.appendChild(paliwo);
			    temp++;
		    }
		    if(dane[i].hasFuelDisesel){
			    var paliwo=document.createElement("DIV");
			    paliwo.innerHTML="Diesel: "+dane[i].hasFuelDisesel +" zł";
			    paliwa.appendChild(paliwo);
			    temp++;
		    }
		    if(dane[i].hasFuelLpg){
			    var paliwo=document.createElement("DIV");
			    paliwo.innerHTML="Lpg: "+dane[i].hasFuelLpg +" zł";
			    paliwa.appendChild(paliwo);
			    temp++;
		    }
		    if(temp==0){
			    var brak=document.createElement("DIV");
			    brak.innerHTML="brak paliw";
			    paliwa.appendChild(brak);
		    }
		    stacja_info.appendChild(paliwa);
		    var godziny=document.createElement("DIV");
		    godziny.className = "godziny_stacji";
		    godziny.innerHTML="otwarte: "+dane[i].openingHours;
		    stacja_info.appendChild(godziny);
		    document.getElementById("results").appendChild(stacja);
		    i++;
	    }
    }
    };
    xhttp.open("GET", "./api/stations?city="+miasto+"&hasFuel95="+b95+"&hasFuel98="+b98+"&hasFuelDiesel="+on+"&hasFuelLpg="+lpg, true);
    xhttp.send();
}

function dodaj_stacje(){
    var miasto=document.getElementsByName("miasto")[0].value;
    var ulica=document.getElementsByName("ulica")[0].value;
    var kod=document.getElementsByName("kod")[0].value;
    var nazwa=document.getElementsByName("nazwa_stacji")[0].value;
    var siec=document.getElementsByName("siec")[0].value;
    var on=document.getElementsByName("on")[0].checked;
    var b95=document.getElementsByName("95")[0].checked;
    var b98=document.getElementsByName("98")[0].checked;
    var lpg=document.getElementsByName("lpg")[0].checked;
    var godziny=document.getElementsByName("godziny_otwarcia")[0].value +" - "+document.getElementsByName("godziny_zamkniecia")[0].value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
		
    }
    };
    xhttp.open("POST", "./api/stations", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send("name="+nazwa+"&address="+ulica+"&city="+miasto+"&postalCode="+kod+"&hasFuel95="+b95+"&hasFuel98="+b98+"&hasFuelDiesel="+on+"&hasFuelLpg="+lpg+"&brandID="+siec+"&openingHours="+godziny);
}