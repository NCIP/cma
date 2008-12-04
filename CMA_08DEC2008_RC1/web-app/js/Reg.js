var Reg = {

'pReg' : function()	{
//alert("In pReg");
 	var msg = "Please fill in all required fields";
 	var e = "";
	try	{
		if($('lastName').value == "")	{
			$('lastName').style.border="1px solid red";
			e=msg;
		}
		if($('firstName').value == "")	{
			$('firstName').style.border="1px solid red";
			e=msg;
		}
		if($('phone').value == "")	{
			$('phone').style.border="1px solid red";
			e=msg;
		}
		if($('email').value == "")	{
			$('email').style.border="1px solid red";
			e=msg;
		}
		
		if($('institution').value == "")	{
			$('institution').style.border="1px solid red";
			e=msg;
		}
		if($('cap').value == "")	{
			$('cap').style.border="1px solid red";
			e=msg;
		}
		if($('email').value != "" && !Reg.isValidEmail($('email').value))	{
			$('email').style.border="1px solid red";
			e += "Invalid Email";
		}
		
		if(e!="")	{
			throw(e);
		}
		
		//alert("form is okay");
		//hide the submit button, so they dont submit again.
		//  hide() and show() are prototype functions...
		$('regButtons').hide();
		$('regStatus').show();

		RegHelper.pReg($('lastName').value, $('firstName').value, $('email').value,$('institution').value, $('cap').value, $('phone').value, $('dept').value, Reg.pReg_cb);
	}
	catch(err)	{
		$('regErr').innerHTML = err;
		Fat.fade_element('regErr');
	}
},
'pReg_cb' : function(txt)	{
	//eval the txt and look at .status, .un, and .ps, .msg
	var res = eval('(' + txt + ')');
	if(res.status == "pass")	{
		$('regForm').reset();
		$('regStatus').hide();
		
	//// Enable all commented lines from here to "Enable to here for login" line below if login is enabled after registration:
		//$('regButtons').innerHTML = "Please login below";
		//$('regButtons').show();
		
		// Display courtesy login section
		//$('loginElements').show();		

		//$('username').value = res.un;
		//$('password').value = res.ps;

		//$('regErr').innerHTML = "Thanks for registering";
		//$('loginMsg').innerHTML = "Please click 'login' to login using a temporary account.  This username and password was sent to the email account you registered.  Your full account details will be emailed to you shortly.";

		//$('submitButton').focus();
	//// Enable to here for login
	
		//$('regButtons').innerHTML = "Please see message below";
		//$('regButtons').show();
		
		//$('captchaDiv').innerHTML = "Please see message below";
		//$('captchaDiv').show();
		
		$('loginMsg').innerHTML = "A registration confirmation was sent to the email account you registered.  Your full account details will be emailed to you shortly. Please <a href=\"index.gsp\"> click here</a> to return to the CMA home page.";		
		Fat.fade_element('loginMsg');
	}
	else	{
		//alert(res.msg);
		var t = "";
		if(res.msg == "BAD_CAPTCHA")	{
			t= "Please enter the text displayed in the image again.  Refresh the page if you would like another image or email ncicb@pop.nci.nih.gov for assistance";
		}
		if(t=="")	{
			t = "An error occured when processing your registration.  Please try again later or email ncicb@pop.nci.nih.gov for assistance";
		}
		
		$('regErr').innerHTML = t;
		
		setTimeout(function()	{
		$('regButtons').show();
		$('regStatus').hide();
		}, 500);
		
		Fat.fade_element('regErr');

	}
},
'isValidEmail' : function(str)	{
   return (str.indexOf(".") > 2) && (str.indexOf("@") > 0);
}

}