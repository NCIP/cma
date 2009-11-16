var undefined;

function getFirstNodeOfType(nodeType,parent) {
	parent = (parent) ? parent : document;
	for ( var m = parent.firstChild; m != null; m = m.nextSibling ) {
		switch (nodeType) {
			case Node.ELEMENT_NODE:
				if (m.nodeType == 1) return m;
				break;
			case Node.TEXT_NODE:
				if (m.nodeType == 3) return m;
				break;
		}
	}
	return null;
}

function getFirstElementOfClass(tagName,className,root) {
    var nodeOfInterest, kids;
    root = (root) ? root : window.document;
    kids = root.getElementsByTagName(tagName);
    for ( var j = 0; j < kids.length; j++ ) {
        if ( kids[j].className.indexOf(className) != -1 ) nodeOfInterest = kids[j];
    }
    return nodeOfInterest;
}

function getElementsByClassName(tagName,className,root) {
    var nodesOfInterest, kids;
    root = (root) ? root : window.document;
    nodesOfInterest = new Array();
    kids = root.getElementsByTagName(tagName);
    for ( var j = 0; j < kids.length; j++ ) {
        if ( kids[j].className.indexOf(className) != -1 ) nodesOfInterest[nodesOfInterest.length] = kids[j];
    }
    return nodesOfInterest;
}

function getFirstAncestorOfTagName(n,tagName) {
	//alert('entering recurse');
	if (!n.parentNode) {
		return;
	} else if (n.parentNode.nodeName == tagName.toUpperCase()) {
		return n.parentNode;
	} else {
		getFirstAncestorOfTagName(n.parentNode,tagName);
	}
}

function getFirstChildOfTagName(n,tagName) {
	tagName = tagName.toUpperCase();
	if (n.hasChildNodes()) {
		for ( var i = 0; i < n.childNodes.length; i++ ) {
			if ( n.childNodes[i].nodeName == tagName ) {
				return n.childNodes[i];
			}
		}
	}
	return;
}

function addEventListeners(EventSource,EventType,EventHandler,captures) {
	captures = (captures) ? captures : false;
	if ( document.addEventListener ) {
		EventSource.addEventListener(EventType,EventHandler,captures);
	} else if (document.attachEvent) {
		EventType = "on" + EventType;
		EventSource.attachEvent(EventType,EventHandler);
	} else {
		EventType = "on" + EventType;
		EventSource[EventType] = EventHandler;
	}
}

function removeEventListeners(EventSource,EventType,EventHandler,captures) {
	captures = (captures) ? captures : false;
	if ( document.removeEventListener ) {
		EventSource.removeEventListener(EventType,EventHandler,captures);
	} else if (document.detachEvent) {
		EventType = "on" + EventType;
		EventSource.detachEvent(EventType,EventHandler);
	} else {
		EventType = "on" + EventType;
		EventSource[EventType] = null;
	}
}

function getEvent(evt) {
	return (evt) ? evt : (window.event) ? window.event : null;
}

function getTarget(evt) {
	return (evt.target) ? evt.target : (evt.srcElement) ? evt.srcElement : null;
}

function getCurrentTarget(evt,currentTarget) {
	return (evt.currentTarget) ? evt.currentTarget : (currentTarget) ? currentTarget : (this) ? this : null;
}

function getPos(n,evt) {
	if (n == 'X') {
		return (evt.pageX) ? evt.pageX : (document.documentElement.scrollLeft) ? (document.documentElement.scrollLeft + evt.clientX) : (document.body.scrollLeft) ? (document.body.scrollLeft + evt.clientX) : evt.clientX;
	} else {
		return (evt.pageY) ? evt.pageY : (document.documentElement.scrollTop) ? (document.documentElement.scrollTop + evt.clientY) : (document.body.scrollTop) ? (document.body.scrollTop + evt.clientY) : evt.clientY;
	}
}

function getInnerDimension(n) {
	if (n == 'W') {
			return (window.innerWidth) ? window.innerWidth : (document.documentElement.clientWidth) ? document.documentElement.clientWidth : (document.body.clientWidth) ? document.body.clientWidth : 0;
	} else {
			return (window.innerHeight) ? window.innerHeight : (document.documentElement.clientHeight) ? document.documentElement.clientHeight : (document.body.clientHeight) ? document.body.clientHeight : 0;	
	}
}

function bubbleCancel(evt) {
	if (!evt) return;
	if (evt.stopPropagation) {
		evt.stopPropagation();
		evt.preventDefault();
	} else if (typeof evt.cancelBubble != undefined) {
		evt.cancelBubble = true;
		evt.returnValue = false;
	} else {
		return false;
		evt = null;
	}
}

var isOpera = false;
var isSafari = false;
var isIE5Mac = false;
var isIEWin = false;
var isIEWin50 = false;
var isIEWin55plus = false;

function UADetect() {
	var UA = navigator.userAgent;
	//document.write(UA);
	if (UA.indexOf("Opera") != -1)
		isOpera = true;
	else if (UA.indexOf("AppleWebKit") != -1)
		isSafari = true;
	else if (UA.indexOf("Mac") != -1 && UA.indexOf("MSIE ") != -1)
		isIE5Mac = true;
	else if (UA.indexOf("Win") != -1 && UA.indexOf("MSIE") != -1 && UA.indexOf("Opera") == -1) {
		isIEWin = true;
		if (UA.indexOf("MSIE 5.0") != -1)
			isIEWin50 = true;
		else
			isIEWin55plus = true;
	}
}

UADetect();