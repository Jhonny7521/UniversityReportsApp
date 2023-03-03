
 function habilitarNombre() {
    const inputText = document.querySelector("#hospitalName");
    inputText.value = "";
    inputText.disabled = false;
    
    const inputSelect = document.querySelector("#hospitalLocation");
    inputSelect.selectedIndex = 0;
    inputSelect.disabled = true;
  }

  function habilitarSede() {
    const inputText = document.querySelector("#hospitalName");
    inputText.value = "";
    inputText.disabled = true;
    
    const inputSelect = document.querySelector("#hospitalLocation");
    inputSelect.selectedIndex = 0;
    inputSelect.disabled = false;
  }