//depends on api.js, prototype.js
var refPoint = 'demon';
var theMap = "";


function init(imgId) {
	refPoint = imgId;

	try {
	    theMap = $(refPoint).readAttribute("usemap").replace("#", "");
	}
	catch(e)	{
		theMap = $(refPoint).useMap.replace("#", "");
	}

	//imgOffTop =  $(refPoint).offsetTop;
	//imgOffLeft = $(refPoint).offsetLeft;
	imgW =  $(refPoint).width;
	imgH =  $(refPoint).height;

	imgOffTop = $(refPoint).cumulativeOffset().top;
	imgOffLeft = $(refPoint).cumulativeOffset().left;
	//alert(imgOffTop + " " + imgOffLeft + " " + imgW + " " + imgH );

	$(refPoint).ondrag = function () { return false; };
	$(refPoint).onselectstart = function () { return false; };
	addEventListeners($(refPoint),"mousedown",mouseDownHandle);
	
	$(refPoint).onmousedown = function (evt) { evt = getEvent(evt); bubbleCancel(evt); };
	$(refPoint).ondragcenter = function () { this.style.border = "1px solid #600"; };
	
}
	
	function mouseDownHandle(evt) {
			
		var drag, startX, startY, posX, posY, endX, endY;
		evt = getEvent(evt);
		drag = document.createElement("div");
		drag.id = "drag";
		drag.style.position = "absolute";
		startX = getPos('X',evt);
		startY = getPos('Y',evt);
		
		drag.style.left = startX + "px";
		drag.style.top = startY + "px";
		
		document.body.appendChild(drag);
		 
		addEventListeners(document,"mousemove",mouseMoveHandle);
		addEventListeners(document,"mouseup",mouseUpHandle,false);
		bubbleCancel(evt);
				 
		function mouseMoveHandle(evt) {
			var innerWidth, innerHeight;
			innerWidth = getInnerDimension('W');
			innerHeight = getInnerDimension('H');
		 	evt = getEvent(evt);
		 	posX = getPos('X',evt);
		 	posY = getPos('Y',evt);
			if ( posX > startX && posY > startY ) {
				drag.style.right = "";
				drag.style.bottom = "";
				drag.style.left = startX + "px";
				drag.style.top = startY + "px";
			 	drag.style.width = (posX - startX) + "px";
			 	drag.style.height = (posY - startY) + "px";
			} else if ( posX < startX && posY > startY ) {
				drag.style.left = "";
				drag.style.bottom = "";
				drag.style.right = (innerWidth - startX) + "px";
				drag.style.top = startY + "px";
			 	drag.style.width = (startX - posX) + "px";
			 	drag.style.height = (posY - startY) + "px";
			} else if ( posX < startX && posY < startY ) {
				drag.style.left = "";
				drag.style.top ="";
				drag.style.right = (innerWidth - startX) + "px";
				drag.style.bottom = (innerHeight - startY) + "px";
			 	drag.style.width = (startX - posX) + "px";
			 	drag.style.height = (startY - posY) + "px";
			} else if ( posX > startX && posY < startY ) {
				drag.style.right = "";
				drag.style.top = "";
				drag.style.left = startX + "px";
				drag.style.bottom = (innerHeight - startY) + "px";
				drag.style.width = (posX - startX) + "px";
				drag.style.height = (startY - posY) + "px";
			}
			bubbleCancel(evt);
		 }
		 
		 function mouseUpHandle(evt) {
		 	evt = getEvent(evt);
		 	endX = getPos('X',evt);
		 	endY = getPos('Y',evt);
			document.body.removeChild(drag);
			
			removeEventListeners(document,"mousemove",mouseMoveHandle);
			removeEventListeners(document,"mouseup",mouseUpHandle);
			bubbleCancel(evt);
			
		 	//alert("Start: " + startX + "," + startY+ "\nEnd: " + endX + "," + endY);
		 	getLassoed(startX, startY, endX, endY);
		 }
	}
	
	function getLassoed(startX, startY, endX, endY)	{
		//lets re-arrange the box
		if(startX>=endX)	{
			var tmp = startX;
			startX = endX;
			endX=tmp;
			tmp = "";
		}
		if(startY>=endY)	{
			var tmp = startY;
			startY = endY;
			endY=tmp;
			tmp = "";
		}
		
		var lassoed = Array();
		//give the box startX, startY, endX, endY - which points did we get
		//in the pic 20,30 should be 20+offsetLeft, 30+offsetTop
		var rects = document.getElementsByName(theMap)[0].getElementsByTagName("area");
		
		for(var i=0; i<rects.length; i++)	{
			var cds = rects[i].coords.split(",");
			//alert(cds);
			var pStartX = (parseInt(cds[0])+imgOffLeft);
			var pStartY = (parseInt(cds[1])+imgOffTop);
			var pEndX = (parseInt(cds[2])+imgOffLeft);
			var pEndY = (parseInt(cds[3])+imgOffTop);
			//alert(pStartX+ "," + pStartY+ "," + pEndX+ "," + pEndY);
			
			//see if the box we passed contains the point
			if(startX<=pStartX && startY<=pStartY && endX >= pEndX && endY>=pEndY)	{
				//hit
				lassoed.push(rects[i].title);
				// JB: Edit the displayed Patient ID
				//TestHelper.translateId(rects[i].id, addToPending);				
				//addToPending(TestHelper.translateId(rects[i].id));
				addToPending(rects[i].id);
			}
		}
		if(lassoed.length>0)	{
			//alert("You selected: " + lassoed);
			//alert("Sending: " + pendingSamples);
			PatientIdHelper.translateIds(pendingSamples, updatePending);
			//writePendings();
		}
		else	{
		
		}
	}
	
////////////////////////////
// save when lassoed
// LEGACY
////////////////////////////
	
//hold our pending samples
var pendingSamples = new Array();
		
function addToPending(sample)	{
		//add this to the JS array
		pendingSamples[pendingSamples.length] = sample;
		//add to array list
		pendingSamples = pendingSamples.uniq();
}				
function updatePending(translatedSamples)	{
		//alert("Received back: " + translatedSamples);
		pendingSamples = translatedSamples;
		writePendings();
}		
function clearPending()	{
	//clear the JS array
	pendingSamples = new Array();
	//clear the array list and a_js arrays
	//A_clearTmpSamples();
	writePendings();
}		

function writePendings()	{
	var html = "";	
	var sampleList = "";
	$("pending_samples").innerHTML = "";
	if(pendingSamples.length>0)	{
		for(var j=0; j<pendingSamples.length; j++)	{
			html += "<span style=\"margin-left:5px;\">\n";
			html += "<a href=\"#\" onmouseover=\"mapshow('"+pendingSamples[j]+"');return overlib('Sample:<br>\\n"+pendingSamples[j]+" ');\" onmouseout=\"maphide();return nd();\">"+ pendingSamples[j] + "</a><br/>\n";
			html += "</span>";
			if (sampleList == "")
				sampleList = pendingSamples[j];
			else
				sampleList = sampleList+","+pendingSamples[j];
			
			//currentTmpSamples is declared in a_saveSamples.js
			currentTmpSamples = sampleList;
		}
	}
	//html = pendingSamples;	
	$("pending_samples").innerHTML = html;
	if (pendingSamples.length == 0){
		$("sampleCount").innerHTML = "";
	}
	else {
		$("sampleCount").innerHTML = pendingSamples.length + " samples selected";
	}
}

	
///////////////////////
// MARKER DISPLAY
// LEGACY
///////////////////////
	
var marker, markersrc, markersize;
var coordx   = new Array();
var coordy   = new Array();

if (ie) {
	markersrc = "../images/marker.gif";
} else if(saf) {
	markersrc = "../images/blank.gif";
} else {
	markersrc = "../images/marker.png";
}

markersize = 32;

function initMarkerPoints(myMap)	{
	
	if($("the_marker") == null )	{
		var marker = document.createElement('img');
		 marker.src = markersrc;
		 marker.id = "the_marker";
		 marker.alt = "";
		 marker.style.left = "0px";
		 marker.style.top = "0px";
		 marker.style.display = "none"; //none
		 marker.style.position = "absolute";
		 document.body.appendChild(marker);
	}
	var theMap = document.getElementsByName(myMap);
	theAreas = theMap[0].getElementsByTagName("area");
	//////////////
	//look at each area
	for(var i=0; i<theAreas.length; i++)	{
		//parse the coords
		var _minx, _miny, _maxx, _maxy;
		var s = theAreas[i].coords.replace(" ", "").split(",");
		//so there could be N coords, unless we force Jfreechart to use a square
		
		//convert to ints for comparisons
		for(var eachs = 0; eachs<s.length; eachs++)	{
			s[eachs] = parseInt(s[eachs]);
		}
		
		//get the center point...based on id now not title
		//coordx[theAreas[i].title] = Math.ceil(s[0] + ((s[2]-s[0])/2));
		//coordy[theAreas[i].title] = Math.ceil(s[1] + ((s[3]-s[1])/2));
		coordx[theAreas[i].id] = Math.ceil(s[0] + ((s[2]-s[0])/2));
		coordy[theAreas[i].id] = Math.ceil(s[1] + ((s[3]-s[1])/2));
	}
}

function maphide () {
	var _marker = $("the_marker");
    _marker.style.display = "none";
  }
  
function mapshow (city) {
	//main.reset();
    var offset = 0 - (markersize/2);
    var ofst = $$(".currentPlot")[0].cumulativeOffset();
   
    var x = coordx[city] + offset + ofst.left;
    var y = coordy[city] + offset + ofst.top;
    var _marker = $("the_marker");
    _marker.style.left = x + "px";
    _marker.style.top = y + "px";
    _marker.style.display = "block";
}