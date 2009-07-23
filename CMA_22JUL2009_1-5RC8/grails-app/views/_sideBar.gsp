<div class="sidebar">
	<div style="padding: 10px;">
		<b>Administration:</b>
		<ul>
			<li>
				<a href="${createLink(controller:'inbox')}" title="">View Results</a>
			</li>
			<li>
				<a href="${createLink(controller:'manageLists')}" title="">List Management</a>
			</li>
		</ul>
		<br/>
		<b>Help:</b>
		<ul>
			<li>CMA Version <g:meta name="app.version"/></li>
			<li>
				<a href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank">Application Support</a>
			</li>
			<li>
				<a href="#" onclick="Help.popGuide();return false;" target="_blank">User Guide</a>
			</li>
		</ul>
		<br />
		<b>News:</b>
		<ul id="sidebarNews"></ul>
		<div id="sidebar">

			<!--
			<div style="text-align: left; margin-top: 20px;">
				<b>TEST Lists:</b>
				<div id="sidebar________UL">
					<img src="${createLinkTo(dir:'images',file:'indicator.gif')}" />
				</div>
			</div>

			<br />
			<br />
			<b style="color: #A90101; font-size: 10px;">Items in Red are "custom" lists</b>
			-->
		</div>
	</div>
</div>
<g:javascript>
	Event.observe(window, "load", function()	{
		//load up the news
		new Ajax.Updater('sidebarNews', '${createLinkTo(dir:'',file:grailsApplication.config.cma.dataContext+'news.html')}');
	});
</g:javascript>