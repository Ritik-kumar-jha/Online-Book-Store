function matchPassword()
{
    if(document.getElementById('rpassword').value!=document.getElementById('password').value)
    {
	  alert("Confirm password does not match with password")
	  return false;
    }
    return true;
}
function setMaxValue()
{
	  document.getElementById("quantity").max=document.getElementById("aq").value;
}
function findCost()
{
   document.getElementById("cost").innerHTML=document.getElementById("quantity").value*document.getElementById("price1").innerHTML; 
   document.getElementById("tbp").value=document.getElementById("cost").innerHTML;
}
 function totalCost(i)
 {
    document.getElementById("costt"+i).innerHTML=document.getElementById(i).value*document.getElementById("price"+i).innerHTML; 
    document.getElementById("tcost").innerHTML=parseInt(document.getElementById("tcost").innerHTML)+parseInt(document.getElementById("price"+i).innerHTML);
    document.getElementById("tobepaid").value=document.getElementById("tcost").innerHTML;
 }
 function checkQuantity(){
	   if(parseInt(document.getElementById("quantity").value.trim())>parseInt(document.getElementById("aq").value.trim())){
		   alert("Quantity can not be more than available quantity");
		   return false
	   }
	   return true;
 }
 c=0;
 function mark(cb)
 {
	 if(cb.checked && c==4){
		 alert("Maximum 4 allowed at a time")
		 cb.checked=false;
		 return;
	 }
		 
	 if(cb.checked)
		 c++;
	 else
		 c--;
	 if(c==1){
		 document.getElementById("oc").disabled=false;
		 document.getElementById("rc").disabled=false;
	 }
	 else if(c==0){
		 document.getElementById("oc").disabled=true;
		 document.getElementById("rc").disabled=true; 
	 }
 }
 function allowDigits(e,t,n)
 {
	 return ((e.charCode>=48 && e.charCode<=57) && t.value.length<n);
 }
 function checkOtp()
 {
	if(document.getElementById("gotp").innerHTML!=document.getElementById("eotp").value)
	{
		alert("Enter correct OTP");
		return false;
	}
	return true;
 }
 function checkDate()
 {
	 if(document.getElementById("fr").value>document.getElementById("to").value)
	 {
		alert("From date can not be greater than to date");
		return false;
	 }
	 return true;
 }
