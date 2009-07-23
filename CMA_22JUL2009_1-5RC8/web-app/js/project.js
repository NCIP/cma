
function putFocus(form, element){	
	if (document.forms.length > 0) {
   		document.forms[form].elements[element].focus();
  	}
}
function checkRelapse(selected,relapseValue,div,relapseId){
	selected = selected.value;	
	var showSpan = false;
	if(selected == relapseValue){
		showSpan = true;
	}
	else showSpan = false;
	if (showSpan) {  
			               
            $(div).style.display = "block";        
    } else {		//its default settings        
            $(div).style.display = "none";       
    }
}
function tdiv(hideable)	{							
						var hide = true;	
						
						if($(hideable).style.display=="none") {
							hide = false;
						}
						else hide = true;		
						if(hide){
							if(win)	{
							Effect.BlindUp(hideable);
							}
							else {
							$(hideable).style.display = "none";	
							}
						}
						else{							
							if(win)	{ 
							Effect.BlindDown(hideable); 
							}
							else {
							$(hideable).style.display = "block"; 
							}		
						}
			
					}


function ediv(el, div, changeParam, resetParamId) {     
    var el = el.value; //the element value to check	  			   				
    var hideable = div;  //the element to show or hide
    var showDiv = false;
    if (el == changeParam) {
        showDiv = true;
    } else {
        showDiv = false;
    }
    if (showDiv) {
        if (win) {
            Effect.BlindDown(hideable);
        } else {
            $(hideable).style.display = "block";
        }
    } else {							
							//its default settings							
        if (win) {
            setBackToDefault(resetParamId);
            Effect.BlindUp(hideable);
        } else {
            setBackToDefault(resetParamId);
            $(hideable).style.display = "none";
        }
    }
}

function dropdownDiv(el, div, resetParamId) {     
    var el = el.value; //the element value to check	  			   				
    var hideable = div;  //the element to show or hide
    var showDiv = false;
    if (el == '2') {
        showDiv = true;
    } else {
        showDiv = false;
    }
    if (showDiv) {
        if (win) {
            Effect.BlindDown(hideable);
        } else {
            $(hideable).style.display = "block";
        }
    } else {							
							//its default settings							
        if (win) {
            setBackToDefault(resetParamId);
            Effect.BlindUp(hideable);
        } else {
            setBackToDefault(resetParamId);
            $(hideable).style.display = "none";
        }
    }
}


function thresholdDiv(el, div1, div2, div3, div4) {     
    var el = el.value; //the element value to check	  			   				
	//default to '1'.
    var showDiv1 = div1; //the element to show.
    var showDiv2 = div3; //the element to show.
    
    var hideDiv1 = div2; //the element to hide.
    var hideDiv2 = div4; //the element to hide.
    
    if (el == '2') {
		showDiv1 = div2;
		showDiv2 = div4;
    
		hideDiv1 = div1;
		hideDiv2 = div3;
    } 
	if (win) {
		setBackToDefault(hideDiv1);
		Effect.BlindUp(hideDiv1);
		setBackToDefault(hideDiv2);
		Effect.BlindUp(hideDiv2);
	} else {
		setBackToDefault(hideDiv1);
		$(hideDiv1).style.display = "none";
		setBackToDefault(hideDiv2);
		$(hideDiv2).style.display = "none";
	}
	if (win) {
		Effect.BlindDown(showDiv1);
		Effect.BlindDown(showDiv2);
	} else {
		$(showDiv1).style.display = "block";
		$(showDiv2).style.display = "block";
	}		
}

function toggleADiv(aId){	
	var a = document.getElementById(aId);
	
	if(a.className == 'plus'){
		a.className = 'minus';
		a.innerHTML = '&nbsp;-&nbsp;';
	}
	else {
		a.className = 'plus';
		a.innerHTML = '&nbsp;+&nbsp;';
	}
}
			
function setBackToDefault(resetParamId) {
	$(resetParamId).value="";	
}

function checkNull(text) {
    if (text.value == "") {
        scroll(0, 0);
        text.focus();
        text.style.border = "2px solid red";
        alert("Please Fill in a Unique Query Name");
        return false;
    } else {
        return checkQueryName();
    }
}
function spawnx(url, winw, winh, name) {
    var w = window.open(url, name, "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + ",scrollbars=yes,resizable=yes");
	
	//check for pop-up blocker
    if (w == null || typeof (w) == "undefined") {
        alert("You have pop-ups blocked.  Please click the highlighted link at the top of this page to view the report.  You may disable your pop-up blocker for this site to avoid doing this in the future.");
        /*
		if(document.all) {
			  document.all.popup.visible = "true"; 
			  document.all.popup.className = "pop";
		      document.all.popup.innerText = "You have pop-ups blocked.  Click <a href="javascript:spawnx('"+url+"',"+winw+","+winh+",'"+name+"');">here</a> to view the report."; 
			  
		    } else { 
			  document.getElementById('popup').visible = "true";
			  document.getElementById('popup').className= "pop";
		      document.getElementById('popup').innerHTML = "You have pop-ups blocked.  Click <a href="javascript:spawnx('"+url+"',"+winw+","+winh+",'"+name+"');">here</a> to view the report."; 	  
		} 
		*/
        document.write("<Br><Br><span class=\"pop\">You have pop-ups blocked.  Click <a href=\"javascript:spawnx('" + url + "'," + winw + "," + winh + ",'" + name + "');\">here</a> to view the report.</span>");
		//scroll(0, 8000);
    } else {
        w.focus();
    }
}
function spawn(url, winw, winh) {
    var w = window.open(url, "_blank", "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + ",scrollbars=yes,resizable=yes");
}
function spawnme(url, winw, winh) {
    var w = window.open(url, "_blank", "screenX=0,screenY=0,status=yes,toolbar=yes,menubar=yes,location=yes,width=" + winw + ",height=" + winh + ",scrollbars=yes,resizable=yes");
}
function alertUser() {
    if (confirm("This will eliminate all data currently entered in the form and will not add a query")) {
        location.href = "menu.do";
    }
}
function hideLoadingMessage() {
    if (document.getElementById("spnLoading") != null) {
        document.getElementById("spnLoading").style.display = "none";
    }
}
function checkToggle(box, id) {
    if (box.value == "false") {
        toggleDiv(id, false);
    } else {
        toggleDiv(id, true);
    }
}
function toggleDiv(id, state) {
    if (state) {
        document.getElementById(id).style.display = "none";
    } else {
        document.getElementById(id).style.display = "block";
    }
}
function move(fbox, tbox) {
    var arrFbox = new Array();
    var arrTbox = new Array();
    var arrLookup = new Array();
    var i;
    for (i = 0; i < tbox.options.length; i++) {
        arrLookup[tbox.options[i].text] = tbox.options[i].value;
        arrTbox[i] = tbox.options[i].text;
    }
    var fLength = 0;
    var tLength = arrTbox.length;
    for (i = 0; i < fbox.options.length; i++) {
        arrLookup[fbox.options[i].text] = fbox.options[i].value;
        if (fbox.options[i].selected && fbox.options[i].value != "") {
            arrTbox[tLength] = fbox.options[i].text;
            tLength++;
        } else {
            arrFbox[fLength] = fbox.options[i].text;
            fLength++;
        }
    }
	//arrFbox.sort();
	//arrTbox.sort();
    fbox.length = 0;
    tbox.length = 0;
    var c;
    for (c = 0; c < arrFbox.length; c++) {
        var no = new Option();
        no.value = arrLookup[arrFbox[c]];
        no.text = arrFbox[c];
        fbox[c] = no;
    }
    for (c = 0; c < arrTbox.length; c++) {
        var no = new Option();
        no.value = arrLookup[arrTbox[c]];
        no.text = arrTbox[c];
        tbox[c] = no;
    }
}
function saveMe(tbox, fbox) {

    var strValues = "";
    //Only consider tbox.
    if (tbox == null || fbox == null || !tbox.length) { // || !fbox.length) {
        return;
    }
    var boxLength = 0;
    if (tbox.length) {
        boxLength = tbox.length;
    }
    //var fboxLength = 0;
    //if (fbox.length) {
    //    fboxLength = fbox.length;
   // }
    var count = 0;
    if (boxLength != 0) {
        for (i = 0; i < boxLength; i++) {
            if (count == 0) {
                strValues = tbox.options[i].value;
            } else {
                strValues = strValues + "," + tbox.options[i].value;
            }
            count++;
        }
    }
    if (strValues.length == 0) {
		//alert("You have not made any selections");
    } else {
	//alert("Here are your values you've selected:rn" + strValues);
        for (i = 0; i < boxLength; i++) {
            tbox.options[i].selected = true;
        }
    }
}

   //http://johankanngard.net/2005/11/14/remove-an-element-in-a-javascript-array/
Array.prototype.remove = function (s) {
    for (i = 0; i < this.length; i++) {
        if (s == this[i]) {
            this.splice(i, 1);
        }
    }
};

//http://simon.incutio.com/archive/2004/05/26/addLoadEvent
function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != "function") {
        window.onload = func;
    } else {
        window.onload = function () {
            if (oldonload) {
                oldonload();
            }
            func();
        };
    }
}

function radioFold(formElement){
       var element = formElement.name;
       
       
   if (element == "cytobandRegionStart" || element == "cytobandRegionEnd"){
        document.forms[0].region[0].checked = true;
        document.forms[0].region[1].checked = false;
        document.forms[0].basePairStart.value = "";
        document.forms[0].basePairEnd.value = "";        
       } 
   
   if (element == "basePairStart" || element == "basePairEnd"){
        document.forms[0].region[1].checked = true;
        document.forms[0].region[0].checked = false;
        document.forms[0].cytobandRegionStart.value = "";
        document.forms[0].cytobandRegionEnd.value = "";     
       }      
   
   if (element == "sampleList"){
         document.forms[0].sampleGroup[0].checked = true;
        }
	
   if (element == "geneList"){ 
	      document.forms[0].geneGroup[0].checked = true;
	      }	
   if (element == "cloneListSpecify"){ 
	      document.forms[0].cloneId[0].checked = true;
	      }
   if (element == "snpListSpecify"){
          document.forms[0].snpId[0].checked = true;
          }
   if (element == "foldChangeValueUp"){ 
	      document.forms[0].regulationStatus[0].checked = true;
	      document.forms[0].regulationStatus[1].checked = false;
	      document.forms[0].regulationStatus[2].checked = false;
	      document.forms[0].regulationStatus[3].checked = false;
	      }
    if (element == "foldChangeValueDown"){
	      document.forms[0].regulationStatus[0].checked = false;
	      document.forms[0].regulationStatus[1].checked = true;
	      document.forms[0].regulationStatus[2].checked = false;
	      document.forms[0].regulationStatus[3].checked = false;
	      }
    if ((element == "foldChangeValueUDUp") || (element == "foldChangeValueUDDown")){
	      document.forms[0].regulationStatus[0].checked = false;
	      document.forms[0].regulationStatus[1].checked = false;
	      document.forms[0].regulationStatus[2].checked = true;
	      document.forms[0].regulationStatus[3].checked = false;
	      }
	if ((element == "foldChangeValueUnchangeFrom") || (element == "foldChangeValueUnchangeTo")){      
	      document.forms[0].regulationStatus[0].checked = false;
	      document.forms[0].regulationStatus[1].checked = false;
	      document.forms[0].regulationStatus[2].checked = false;
	      document.forms[0].regulationStatus[3].checked = true;
	      }
	if (element == "cnAmplified"){ 
	      
	      document.forms[0].copyNumber[0].checked = true;
	      document.forms[0].copyNumber[1].checked = false;
	      document.forms[0].copyNumber[2].checked = false;
	      document.forms[0].copyNumber[3].checked = false;
	      }
	if (element == "cnDeleted"){ 
	      
	      document.forms[0].copyNumber[0].checked = false;
	      document.forms[0].copyNumber[1].checked = true;
	      document.forms[0].copyNumber[2].checked = false;
	      document.forms[0].copyNumber[3].checked = false;
	      }
	 if ((element == "cnADAmplified") || (element == "cnADDeleted")){      
	      
	      document.forms[0].copyNumber[0].checked = false;
	      document.forms[0].copyNumber[1].checked = false;
	      document.forms[0].copyNumber[2].checked = true;
	      document.forms[0].copyNumber[3].checked = false;
	      }  
	 if ((element == "cnUnchangeFrom") || (element == "cnUnchangeTo")){      
	      document.forms[0].cnDeleted.value = " ";
	      document.forms[0].cnAmplified.value = " ";
	      document.forms[0].cnADAmplified.value = " ";
	      document.forms[0].cnADDeleted.value = " ";
	      document.forms[0].copyNumber[0].checked = false;
	      document.forms[0].copyNumber[1].checked = false;
	      document.forms[0].copyNumber[2].checked = false;
	      document.forms[0].copyNumber[3].checked = true;
	      } 
	 if(element == "existingGroups"){	 	
	  	  document.forms[0].variousSamplesRadio.checked = true;	 
	 } 
 }
function doubleDropdownForNameValuePairs(selection1, selection2, value1ToValue2I, value1ToValue2II) {
    /* initialize selection2 options */
    selection2.options.length = 0;
    var value1 = selection1.options[selection1.selectedIndex].value;
    var value2Arr1 = value1ToValue2I[value1];
    var value2Arr2 = value1ToValue2II[value1];
    if (value2Arr1 != null) {
        for (i = 0; i < value2Arr1.length; i++) {
            selection2.options[i] = new Option(value2Arr1[i], value2Arr2[i]);
        }
    }
}

function popCoin(gene,key)	{
	//alert(gene + " : " + key + " : " + alg);
	var url = "popCoinGraph.do?method=popCoinGraph&geneSymbol="+encodeURIComponent(gene)+"&reporter="+encodeURIComponent(key);
	window.open( url, 'page2', 'status,resizable,scrollbars,width=700px,height=730px,screenX=100,screenY=100');
}

 function toggleGenePlot(a, b)	{
	if(document.getElementById("geneChart"))	{
		var chart = document.getElementById("geneChart");
		//alert(chart.src + " = " + a);
		var imgURL = chart.src.split("filename=");
		if(arguments[1])	{
			if(imgURL[1] == a)	{
				chart.src = (imgURL[0] + "filename=" + b);
				if($('graphLink'))
					$('graphLink').href = (imgURL[0] + "filename=" + b);
				chart.useMap = "#"+b;
			}
			else if(imgURL[1] == b)	{
				chart.src = (imgURL[0] + "filename=" + a);
				if($('graphLink'))
					$('graphLink').href = (imgURL[0] + "filename=" + a);
				chart.useMap = "#"+a;
			}
			else	{
				chart.src = (imgURL[0] + "filename=" + a);
				if($('graphLink'))
					$('graphLink').href = (imgURL[0] + "filename=" + a);
				chart.useMap = "#"+a;
			}
		}
		else	{
			chart.src = (imgURL[0] + "filename=" + a);
			if($('graphLink'))
				$('graphLink').href = (imgURL[0] + "filename=" + a);
			chart.useMap = "#"+a;
		}
		//reset
		if(document.getElementsByName("graphTypeLinks"))	{
			var lnks = document.getElementsByName("graphTypeLinks");
			for(var i=0; i<lnks.length; i++)	{
				lnks[i].style.color = "";
				lnks[i].style.textDecoration = "underline";
			}
		}
		//highlight the default one
		if($(a+"_link"))	{
			$(a+"_link").style.color = "black";
			$(a+"_link").style.textDecoration = "none";
		}
		else	{
			setTimeout(function()	{
				if($(a+"_link"))	{
					$(a+"_link").style.color = "black";
					$(a+"_link").style.textDecoration = "none";
				}
			}, 100);
		}
	} 
}
function togglePCAPlot(plot) {
	if(document.getElementById("pcaChart"))	{
		var chart = document.getElementById("pcaChart");
		var imgURL = chart.src.split("filename=");
		chart.src = (imgURL[0] + "filename=" + plot);
		chart.useMap = "#"+plot;
		
		//reset
		if(document.getElementsByName("graphTypeLinks"))	{
			var lnks = document.getElementsByName("graphTypeLinks");
			for(var i=0; i < lnks.length; i++)	{
				lnks[i].style.backgroundColor = "#FFFAE1";
				lnks[i].style.textDecoration = "underline";
			}
		}
		//highlight the default one
		var temp = plot+"_link";

		var defaultLink = document.getElementById(plot+"_link");
		defaultLink.style.backgroundColor = "#F2f2f2";
		defaultLink.style.textDecoration = "none";
		//call to the graphics part from lassoHelper.js
		setMain("pcaChart");
	}
}
var w;
function spawnAnnot(reporterId){
	try	{
		var url = "annotationViewer.jsp?";
		var winw = 800;
		var winh = 550;
		w = window.open(url, "_blank",
			"screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + 
			",scrollbars=yes,resizable=yes");
			
			DynamicListHelper.processRepoterAnnotation(reporterId, spawnAnnot_cb);
		}
		catch(err)	{
			alert("Annotation link is not currently available.");
		}
	}
function spawnAnnot_cb(txt)	{
	try	{
		var page = escape(txt);
		//alert(page);
		w.location.replace('annotationViewer.jsp?p='+page);
	}
	catch(err){}
}
