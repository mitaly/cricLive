var counter1 = 1;
var limit1 = 11;
function addInput1(divName){
     if (counter1 == limit1)  {
          alert("You have reached the limit of adding " + counter1 + " inputs");
     }
     else {
          var newInput = document.createElement('input');
          newInput.type = 'text';
          newInput.name = 'myInputs[]';
          newInput.className = 'textPlayers';
          newInput.placeholder="Player "+(counter1+1);
          document.getElementById(divName).appendChild(newInput);
          counter1++;
     }
}

var counter2 = 1;
var limit2 = 11;
function addInput2(divName){
     if (counter2 == limit2)  {
          alert("You have reached the limit of adding " + counter2 + " inputs");
     }
     else {
    	  var newInput = document.createElement('input');
          newInput.type = 'text';
          newInput.name = 'myInputs[]';
          newInput.className = 'textPlayers';
          newInput.placeholder="Player "+(counter2+1);
          document.getElementById(divName).appendChild(newInput);
          counter2++;
     }
}