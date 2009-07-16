function saveGroupFromClinical(k, ln)	{
	var  url = "./saveSamplesFromClinical";
	new Ajax.Request(url, {
  		parameters: { sessionKey: k, listName: ln },
  		onSuccess: function(transport) {
		    var notice = $('saveSamplesStatus');
		    if (transport.responseText == 'pass')	{
		      notice.update('List Saved Successfully.');
		      $('saveSamplesButton').disable();
		    }
		    else
		      notice.update('List Did Not Save.  Please Try Again Later.');
		}
  	});
}
