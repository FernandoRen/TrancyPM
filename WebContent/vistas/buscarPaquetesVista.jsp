<%@ include file = "header-footer/header.jsp" %>
	
	<div class="container">
		<div class="row text-center">
			<div class="col-md-12 my-2">
				<h2>Search Package Section</h2>
			</div>
		</div>
		<div class="row my-2">
			<div class="col-md-4">
				<h3>Search your package</h3>
				<form>
				  <div class="form-group">
				    <label for="guidePackageNumber">Guide Number</label>
				    <input type="text" class="form-control" id="guidePackageNumber" name="guidePackageNumber" placeholder="Type guide number of your package">
				  </div>
				  
				  <div class="text-center">
				  	<button type="submit" class="btn btn-primary"><i class="fa fa-search" aria-hidden="true"></i> Search</button>
				  </div>
				</form>
			</div>
		
			<div class="col-md-8">
				
				<table id="providerTable" class="display" style="width:100%">
			        <thead>
			            <tr>
			                <th>Name</th>
			                <th>Position</th>
			                <th>Office</th>
			                <th>Age</th>
			                <th>Start date</th>
			                <th>Salary</th>
			            </tr>
			        </thead>
			        <tbody>
			            <tr>
			                <td>Suki Burks</td>
			                <td>Developer</td>
			                <td>London</td>
			                <td>53</td>
			                <td>2009-10-22</td>
			                <td>$114,500</td>
			            </tr>
			            <tr>
			                <td>Prescott Bartlett</td>
			                <td>Technical Author</td>
			                <td>London</td>
			                <td>27</td>
			                <td>2011-05-07</td>
			                <td>$145,000</td>
			            </tr>
			            <tr>
			                <td>Gavin Cortez</td>
			                <td>Team Leader</td>
			                <td>San Francisco</td>
			                <td>22</td>
			                <td>2008-10-26</td>
			                <td>$235,500</td>
			            </tr>
			            <tr>
			                <td>Martena Mccray</td>
			                <td>Post-Sales support</td>
			                <td>Edinburgh</td>
			                <td>46</td>
			                <td>2011-03-09</td>
			                <td>$324,050</td>
			            </tr>
			            <tr>
			                <td>Unity Butler</td>
			                <td>Marketing Designer</td>
			                <td>San Francisco</td>
			                <td>47</td>
			                <td>2009-12-09</td>
			                <td>$85,675</td>
			            </tr>
			            <tr>
			                <td>Howard Hatfield</td>
			                <td>Office Manager</td>
			                <td>San Francisco</td>
			                <td>51</td>
			                <td>2008-12-16</td>
			                <td>$164,500</td>
			            </tr>
			            <tr>
			                <td>Hope Fuentes</td>
			                <td>Secretary</td>
			                <td>San Francisco</td>
			                <td>41</td>
			                <td>2010-02-12</td>
			                <td>$109,850</td>
			            </tr>
			            <tr>
			                <td>Vivian Harrell</td>
			                <td>Financial Controller</td>
			                <td>San Francisco</td>
			                <td>62</td>
			                <td>2009-02-14</td>
			                <td>$452,500</td>
			            </tr>
			            <tr>
			                <td>Timothy Mooney</td>
			                <td>Office Manager</td>
			                <td>London</td>
			                <td>37</td>
			                <td>2008-12-11</td>
			                <td>$136,200</td>
			            </tr>
			        </tbody>
			    </table>
				
			</div>
		</div>
	</div>

<%@ include file = "header-footer/footer.jsp" %>
<script src="./../js/providerAjax.js"></script>